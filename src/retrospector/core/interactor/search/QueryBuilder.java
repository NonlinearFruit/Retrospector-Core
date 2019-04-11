package retrospector.core.interactor.search;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class QueryBuilder {

  private final LeafOperator leafOperator;
  private final Operator[] operators;
  private final Map<String, RetrospectorAttribute> commandDecoder;

  public QueryBuilder(Map<String, RetrospectorAttribute> commandDecoder, LeafOperator leafOp, Operator... operators) {
    this.leafOperator = leafOp;
    this.operators = operators;
    this.commandDecoder = commandDecoder;
  }

  public QueryTree buildQuery(String query){
    if (query.isEmpty() || operators.length == 0)
      return new QueryTree(OperatorType.GiveMeEverything);

    QueryTree root = null;
    for (Operator operator : operators) {
      List<String> list = operator.parse(query);
      if (!list.isEmpty()) {
        root = new QueryTree(operator.getType());
        for (String subQuery : list)
          addBranches(root, subQuery);
        break;
      }
    }

    if (root == null) {
      root = new QueryTree(OperatorType.GiveMeEverything);
      addLeaf(root, query);
      if (root.hasBranches())
        root = root.getBranches().get(0);
    }

    return root;
  }

  private void addBranches(QueryTree root, String query) {
    for (Operator operator : operators) {
      List<String> list = operator.parse(query);
      if (!list.isEmpty()) {
        QueryTree branch = new QueryTree(operator.getType());
        root.addBranch(branch);
        for (String subQuery : list)
          addBranches(branch, subQuery);
        return;
      }
    }

    addLeaf(root, query);
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

  private void addLeaf(QueryTree root, String query) {
    if (!leafOperator.parse(query)) {
      root.addBranch(getDefaultLeaves(query));
      return;
    }

    RetrospectorAttribute attribute = commandDecoder.get(leafOperator.getCommand());
    LeafComparator comparator = Arrays
            .asList(LeafComparator.values())
            .stream()
            .filter(c -> c.getValue().equals(leafOperator.getComparator()))
            .findFirst()
            .get();
    QueryLeaf leaf = new QueryLeaf(attribute, comparator, leafOperator.getValue());
    root.addLeaf(leaf);
  }
}
