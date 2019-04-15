package retrospector.core.interactor.search;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import retrospector.core.interactor.search.fakes.LeafOperator_ReturnsEmpty;
import retrospector.core.interactor.search.fakes.LeafOperator_ReturnsFalse;
import retrospector.core.interactor.search.fakes.LeafOperator_ReturnsGivenValues;
import retrospector.core.interactor.search.fakes.Operator_ReturnsEmpty;
import retrospector.core.interactor.search.fakes.Operator_ReturnsType;
import retrospector.core.interactor.search.fakes.Operator_SplitsInputExactlyInHalf;
import retrospector.core.interactor.search.fakes.Operator_SutractsOneCharacter;

public class QueryBuilderTest {
  private static final int Once = 1;

  @Test
  public void buildQuery_GetsEverything_WhenEmpty() {
    String query = "";
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsEmpty(), new Operator_ReturnsEmpty());

    QueryTree tree = builder.buildQuery(query);

    assertEquals(OperatorType.GiveMeEverything, tree.getType());
  }

  @Test
  public void buildQuery_GetsEverything_WhenNoOperators() {
    String query = "find this";
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsEmpty());

    QueryTree tree = builder.buildQuery(query);

    assertEquals(OperatorType.GiveMeEverything, tree.getType());
  }

  @Test
  public void buildQuery_CallsOperator() {
    String query = "find this";
    Operator_ReturnsEmpty operator = new Operator_ReturnsEmpty();
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsEmpty(), operator);

    QueryTree tree = builder.buildQuery(query);

    assertEquals(Once, operator.getCountOfParseCalls());
    assertEquals(query, operator.getParseLastCalledWith());
  }

  @Test
  public void buildQuery_CallsMultipleOperators() {
    String query = "find this";
    Operator_ReturnsEmpty[] operators = new Operator_ReturnsEmpty[]{new Operator_ReturnsEmpty(), new Operator_ReturnsEmpty()};
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsEmpty(), operators);

    QueryTree tree = builder.buildQuery(query);

    for (Operator_ReturnsEmpty operator : operators) {
      assertEquals(Once, operator.getCountOfParseCalls());
      assertEquals(query, operator.getParseLastCalledWith());
    }
  }

  @Test
  public void buildQuery_ReturnsDefault_WhenNoOperatorsApply() {
    String query = "find this";
    QueryTree defaultTree = getDefaultLeaves(query);
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsFalse(), new Operator_ReturnsEmpty());

    QueryTree tree = builder.buildQuery(query);

    assertEquals(defaultTree.getType(), tree.getType());
    assertEquals(defaultTree.getBranches().size(), tree.getBranches().size());
    assertEquals(defaultTree.getLeaves().size(), tree.getLeaves().size());
  }

  @Test
  public void buildQuery_BuildsTree() {
    int depth = 4;
    String query = new String(new char[(int) Math.pow(2, depth)]);
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsEmpty(), new Operator_SplitsInputExactlyInHalf());

    QueryTree tree = builder.buildQuery(query);

    verifyTreeHasDepth(tree, depth);
  }

  @Test
  public void buildQuery_UsesOperatorType() {
    OperatorType type = OperatorType.And;
    String query = "find this";
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsEmpty(), new Operator_ReturnsType(type));

    QueryTree tree = builder.buildQuery(query);

    verifyEveryBranchIsType(tree, type);
  }

  @Test
  public void buildQuery_AddsLeaves() {
    String query = "12345678";
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsEmpty(), new Operator_SplitsInputExactlyInHalf());

    QueryTree tree = builder.buildQuery(query);

    int count = query.length();
    assertEquals(count, getLeafCount(tree));
  }

  @Test
  public void buildQuery_BuildsLeaf() {
    String query = "find this";
    String command = "command";
    RetrospectorAttribute attribute = RetrospectorAttribute.FactoidContent;
    LeafComparator comparator = LeafComparator.Equal;
    String value = "value";
    HashMap<String, RetrospectorAttribute> map = new HashMap<>();
    map.put(command, attribute);
    QueryBuilder builder = new QueryBuilder(map, new LeafOperator_ReturnsGivenValues(command, comparator.getValue(), value), new Operator_ReturnsEmpty());

    QueryTree tree = builder.buildQuery(query);

    assertEquals(1, tree.getLeaves().size());
    QueryLeaf leaf = tree.getLeaves().get(0);
    assertEquals(attribute, leaf.getAttribute());
    assertEquals(comparator, leaf.getComparator());
    assertEquals(value, leaf.getSearchValue());
  }

  @Test
  public void buildQuery_UseDefault_WhenLeafOperatorFails() {
    String query = "12345678";
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsFalse(), new Operator_SplitsInputExactlyInHalf());

    QueryTree tree = builder.buildQuery(query);

    int count = query.length();
    assertEquals(count*getLeafCount(getDefaultLeaves("x")), getLeafCount(tree));
  }

  @Test
  public void buildQuery_SubBranchesCorrectlyTyped() {
    String query = "123456";
    QueryBuilder builder = new QueryBuilder(new HashMap<>(), new LeafOperator_ReturnsEmpty(), new Operator_SplitsInputExactlyInHalf(OperatorType.Or), new Operator_SutractsOneCharacter(OperatorType.Not));

    QueryTree tree = builder.buildQuery(query);

    assertEquals(OperatorType.Or, tree.getType());
    assertEquals(2, tree.getBranches().size());
    assertEquals(0, tree.getLeaves().size());

    for (QueryTree branch : tree.getBranches()) {
      assertEquals(OperatorType.Not, branch.getType());
      assertEquals(1, branch.getBranches().size());
      assertEquals(0, branch.getLeaves().size());
    }
  }

  private void verifyTreeHasDepth(QueryTree tree, int depth) {
    if (depth <= 1) {
      assertTrue(tree.getBranches().isEmpty());
      return;
    }
    assertFalse(tree.getBranches().isEmpty());
    verifyTreeHasDepth(tree.getBranches().get(0), depth-1);
  }

  private void verifyEveryBranchIsType(QueryTree tree, OperatorType type) {
    assertEquals(type, tree.getType());
    for (QueryTree branch : tree.getBranches()) {
      verifyEveryBranchIsType(branch, type);
    }
  }

  private int getLeafCount(QueryTree tree) {
    if (!tree.hasBranches())
      return tree.getLeaves().size();
    int sum = 0;
    for (QueryTree queryTree : tree.getBranches()) {
      sum += getLeafCount(queryTree);
    }
    return sum;
  }

  private QueryTree getDefaultLeaves(String query) {
    QueryTree defaultTree = new QueryTree(OperatorType.Or);
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaTitle, LeafComparator.Contains, query));
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaCreator, LeafComparator.Contains, query));
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaSeason, LeafComparator.Contains, query));
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaEpisode, LeafComparator.Contains, query));
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaCategory, LeafComparator.Contains, query));
    return defaultTree;
  }
}
