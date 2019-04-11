package retrospector.core.interactor.search;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SearchRequestTest {

  @Test
  public void getQuery_ReturnsQuery() {
    String query = "query";

    SearchRequest request = new SearchRequest(query);

    assertEquals(query, request.getQuery());
  }
}
