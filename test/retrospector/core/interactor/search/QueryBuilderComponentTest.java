package retrospector.core.interactor.search;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class QueryBuilderComponentTest {

  private QueryBuilder builder;

  @Before
  public void setup() {
    HashMap<String, RetrospectorAttribute> map = new HashMap<>();
    map.put("U", RetrospectorAttribute.ReviewUser);
    map.put("T", RetrospectorAttribute.MediaTitle);
    map.put("C", RetrospectorAttribute.MediaCreator);
    map.put("S", RetrospectorAttribute.MediaSeason);
    map.put("E", RetrospectorAttribute.MediaEpisode);
    map.put("A", RetrospectorAttribute.MediaCategory);
    map.put("P", RetrospectorAttribute.MediaDescription);
    map.put("#", RetrospectorAttribute.ReviewRating);
    map.put("U", RetrospectorAttribute.ReviewUser);
    map.put("D", RetrospectorAttribute.ReviewDate);
    map.put("R", RetrospectorAttribute.ReviewContent);
    map.put("I", RetrospectorAttribute.FactoidTitle);
    map.put("O", RetrospectorAttribute.FactoidContent);
    builder = new QueryBuilder(map, new LeafOperator("`"), new AndOperator(":"), new OrOperator("|"), new NotOperator("!"));
  }

  @Test
  public void leaves_Work() {
    String query = "`U~ben";

    QueryTree tree = builder.buildQuery(query);

    assertEquals(1, tree.getLeaves().size());
    QueryLeaf leaf = tree.getLeaves().get(0);
    assertEquals(RetrospectorAttribute.ReviewUser, leaf.getAttribute());
    assertEquals(LeafComparator.Contains, leaf.getComparator());
    assertEquals("ben", leaf.getSearchValue());
  }

  @Test
  public void default_Works() {
    String query = "find this";
    QueryTree defaultTree = new QueryTree(OperatorType.Or);
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaTitle, LeafComparator.Contains, query));
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaCreator, LeafComparator.Contains, query));
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaSeason, LeafComparator.Contains, query));
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaEpisode, LeafComparator.Contains, query));
    defaultTree.addLeaf(new QueryLeaf(RetrospectorAttribute.MediaCategory, LeafComparator.Contains, query));

    QueryTree tree = builder.buildQuery(query);

    assertEquals(defaultTree.getType(), tree.getType());
    assertEquals(defaultTree.getBranches().size(), tree.getBranches().size());
    assertEquals(defaultTree.getLeaves().size(), tree.getLeaves().size());
  }

  @Test
  public void and_Works() {
    String query = "a:b:c";

    QueryTree tree = builder.buildQuery(query);

    assertEquals(OperatorType.And, tree.getType());
    assertEquals(3, tree.getBranches().size());
  }

  @Test
  public void or_Works() {
    String query = "a|b|c";

    QueryTree tree = builder.buildQuery(query);

    assertEquals(OperatorType.Or, tree.getType());
    assertEquals(3, tree.getBranches().size());
  }

  @Test
  public void not_Works() {
    String query = "!a";

    QueryTree tree = builder.buildQuery(query);

    assertEquals(OperatorType.Not, tree.getType());
    assertEquals(1, tree.getBranches().size());
  }

  @Test
  public void complicatedQuery_Works() {
    String query = "`U=Ben:`T~Sherlock|`S~Sherlock:!`C=BBC";

    QueryTree tree = builder.buildQuery(query);

    assertEquals(OperatorType.And, tree.getType());
    assertEquals(2, tree.getBranches().size());
    assertEquals(1, tree.getLeaves().size());

    QueryLeaf leaf = tree.getLeaves().get(0);
    assertEquals(RetrospectorAttribute.ReviewUser, leaf.getAttribute());
    assertEquals(LeafComparator.Equal, leaf.getComparator());
    assertEquals("Ben", leaf.getSearchValue());

    QueryTree orBranch = tree.getBranches().get(0);
    assertEquals(OperatorType.Or, orBranch.getType());
    assertEquals(0, orBranch.getBranches().size());
    assertEquals(2, orBranch.getLeaves().size());

    QueryLeaf titleLeaf = orBranch.getLeaves().get(0);
    assertEquals(RetrospectorAttribute.MediaTitle, titleLeaf.getAttribute());
    assertEquals(LeafComparator.Contains, titleLeaf.getComparator());
    assertEquals("Sherlock", titleLeaf.getSearchValue());

    QueryLeaf seasonLeaf = orBranch.getLeaves().get(1);
    assertEquals(RetrospectorAttribute.MediaSeason, seasonLeaf.getAttribute());
    assertEquals(LeafComparator.Contains, seasonLeaf.getComparator());
    assertEquals("Sherlock", seasonLeaf.getSearchValue());

    QueryTree notBranch = tree.getBranches().get(1);
    assertEquals(OperatorType.Not, notBranch.getType());
    assertEquals(0, notBranch.getBranches().size());
    assertEquals(1, notBranch.getLeaves().size());

    QueryLeaf notLeaf = notBranch.getLeaves().get(0);
    assertEquals(RetrospectorAttribute.MediaCreator, notLeaf.getAttribute());
    assertEquals(LeafComparator.Equal, notLeaf.getComparator());
    assertEquals("BBC", notLeaf.getSearchValue());

  }
}
