package retrospector.core.interactor.search;

public enum LeafComparator {
  Equal("="), Contains("~"), GreaterThan(">"), LessThan("<");

  private String value;

  private LeafComparator(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
