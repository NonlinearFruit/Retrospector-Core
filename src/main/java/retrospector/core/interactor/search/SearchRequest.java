package retrospector.core.interactor.search;

import retrospector.core.boundry.Request;

public class SearchRequest implements Request {

  private String query;

  public SearchRequest(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
