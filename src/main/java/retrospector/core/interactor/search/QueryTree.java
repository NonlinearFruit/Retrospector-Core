package retrospector.core.interactor.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryTree {
  private List<QueryTree> branches;
  private List<QueryLeaf> leaves;
  private OperatorType type;

  private QueryTree() {
    branches = new ArrayList<>();
    leaves = new ArrayList<>();
  }

  public QueryTree(OperatorType type) {
    this();
    this.type = type;
  }

  void addBranch(QueryTree branch) {
    branches.add(branch);
  }

  public OperatorType getType() {
    return type;
  }

  boolean hasBranches() {
    return !branches.isEmpty();
  }

  List<QueryTree> getBranches() {
    return Collections.unmodifiableList(branches);
  }

  void addLeaf(QueryLeaf branch) {
    leaves.add(branch);
  }

  List<QueryLeaf> getLeaves() {
    return Collections.unmodifiableList(leaves);
  }

  boolean hasLeaves() {
    return !leaves.isEmpty();
  }
}
