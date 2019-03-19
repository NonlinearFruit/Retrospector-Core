package retrospector.core.interactor.search;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class QueryTreeTest {
  @Test
  public void getBranches_GetsBranches() {
    QueryTree root = newTree();
    QueryTree branch = newTree();
    root.addBranch(branch);

    List<QueryTree> branches = root.getBranches();

    assertTrue(branches.contains(branch));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getBranches_IsUnmodifiable() {
    QueryTree root = newTree();
    root.addBranch(newTree());
    List<QueryTree> branches = root.getBranches();

    branches.clear();
  }

  @Test
  public void hasBranches_ReturnsTrue_WhenBranchesPresent() {
    QueryTree root = newTree();
    root.addBranch(newTree());

    assertTrue(root.hasBranches());
  }

  @Test
  public void hasBranches_ReturnsFalse_WhenNoBranches() {
    QueryTree tree = newTree();

    assertFalse(tree.hasBranches());
  }

  @Test
  public void Constructor_SetsType() {
    OperatorType type = OperatorType.And;

    QueryTree tree = new QueryTree(type);

    assertEquals(type, tree.getType());
  }

  @Test
  public void getLeaves_GetsLeaves() {
    QueryTree root = newTree();
    QueryLeaf branch = newLeaf();
    root.addLeaf(branch);

    List<QueryLeaf> branches = root.getLeaves();

    assertTrue(branches.contains(branch));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getLeaves_IsUnmodifiable() {
    QueryTree root = newTree();
    root.addLeaf(newLeaf());
    List<QueryLeaf> branches = root.getLeaves();

    branches.clear();
  }

  @Test
  public void hasLeaves_ReturnsTrue_WhenLeavesPresent() {
    QueryTree root = newTree();
    root.addLeaf(newLeaf());

    assertTrue(root.hasLeaves());
  }

  @Test
  public void hasLeaves_ReturnsFalse_WhenNoLeaves() {
    QueryTree tree = newTree();

    assertFalse(tree.hasLeaves());
  }

  private QueryTree newTree() {
    return new QueryTree(null);
  }

  private QueryLeaf newLeaf() {
    return new QueryLeaf(null, null, null);
  }
}
