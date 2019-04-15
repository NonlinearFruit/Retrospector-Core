package retrospector.core.interactor.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import retrospector.core.interactor.search.fakes.QueryBuilder_ReturnsTree;
import retrospector.core.interactor.search.fakes.SearchDataGateway_ReturnsList;

public class SearchUseCaseTest {
  private static final int Once = 1;
  private SearchDataGateway_ReturnsList gateway;
  private QueryBuilder_ReturnsTree builder;
  private SearchUseCase searcher;
  private QueryTree tree;
  private List<Map<RetrospectorAttribute, String>> results;

  @Before
  public void Setup() {
    tree = new QueryTree(null);
    results = Arrays.asList(new HashMap<>());
    gateway = new SearchDataGateway_ReturnsList(results);
    builder = new QueryBuilder_ReturnsTree(tree);
    searcher = new SearchUseCase(builder, gateway);
  }

  @Test
  public void Search_CallsGateway() {
    searcher.search("");

    assertEquals(Once, gateway.getCountOfSearchCalls());
    assertEquals(tree, gateway.getSearchLastCalledWith());
  }

  @Test
  public void Search_ReturnsResults() {
    List<Map<RetrospectorAttribute, String>> results = searcher.search("");

    assertEquals(this.results, results);
  }

  @Test
  public void Search_CallsQueryBuilder() {
    String query = "";

    searcher.search(query);

    assertEquals(Once, builder.getCountOfBuildQueryCalls());
    assertEquals(query, builder.getBuildQueryLastCalledWith());
  }
}
