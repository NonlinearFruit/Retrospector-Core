package retrospector.core.interactor.search.fakes;

import retrospector.core.interactor.search.QueryBuilder;
import retrospector.core.interactor.search.QueryTree;

public class QueryBuilder_ReturnsTree extends QueryBuilder {

  private final QueryTree tree;
  private int countOfBuildQueryCalls;
  private String query;

  public QueryBuilder_ReturnsTree(QueryTree tree) {
    super(null, null);
    this.tree = tree;
  }

  @Override
  public QueryTree buildQuery(String query) {
    countOfBuildQueryCalls++;
    this.query = query;
    return tree;
  }

  public int getCountOfBuildQueryCalls() {
    return countOfBuildQueryCalls;
  }

  public String getBuildQueryLastCalledWith() {
    return query;
  }
}
