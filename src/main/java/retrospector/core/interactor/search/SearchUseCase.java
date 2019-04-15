package retrospector.core.interactor.search;

import java.util.List;
import java.util.Map;
import retrospector.core.datagateway.SearchDataGateway;

public class SearchUseCase {

  private final SearchDataGateway gateway;
  private final QueryBuilder builder;

  public SearchUseCase(QueryBuilder builder, SearchDataGateway gateway) {
    this.builder = builder;
    this.gateway = gateway;
  }

  public List<Map<RetrospectorAttribute, String>> search(String query) {
    QueryTree tree = builder.buildQuery(query);
    return gateway.search(tree);
  }
}
