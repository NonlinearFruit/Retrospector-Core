package retrospector.core.interactor.search.fakes;

import java.util.List;
import java.util.Map;
import retrospector.core.datagateway.SearchDataGateway;
import retrospector.core.interactor.search.QueryTree;
import retrospector.core.interactor.search.RetrospectorAttribute;

public class SearchDataGateway_ReturnsList implements SearchDataGateway {

  private int countOfSearchCalls;
  private final List<Map<RetrospectorAttribute, String>> results;
  private QueryTree query;

  public SearchDataGateway_ReturnsList(List<Map<RetrospectorAttribute, String>> results) {
    this.results = results;
  }

  @Override
  public List<Map<RetrospectorAttribute, String>> search(QueryTree query) {
    countOfSearchCalls++;
    this.query = query;
    return results;
  }

  public int getCountOfSearchCalls() {
    return countOfSearchCalls;
  }

  public QueryTree getSearchLastCalledWith() {
    return query;
  }
}
