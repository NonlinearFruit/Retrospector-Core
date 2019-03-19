package retrospector.core.interactor.search;

public class QueryLeaf {

  private LeafComparator comparator;
  private RetrospectorAttribute attribute;
  private String searchValue;

  public QueryLeaf(RetrospectorAttribute attribute, LeafComparator comparator, String searchValue) {
    this.attribute = attribute;
    this.comparator = comparator;
    this.searchValue = searchValue;
  }

  public LeafComparator getComparator() {
    return comparator;
  }

  public RetrospectorAttribute getAttribute() {
    return attribute;
  }

  public String getSearchValue() {
    return searchValue;
  }
}
