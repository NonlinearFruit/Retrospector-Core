package retrospector.core.interactor.search;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class QueryLeafTest {

  @Test
  public void getComparator_Works() {
    LeafComparator comparator = LeafComparator.Equal;

    QueryLeaf leaf = new QueryLeaf(null, comparator, null);

    assertEquals(comparator, leaf.getComparator());
  }

  @Test
  public void getRetrospectorAttribute_Works() {
    RetrospectorAttribute attribute = RetrospectorAttribute.MediaTitle;

    QueryLeaf leaf = new QueryLeaf(attribute, null, null);

    assertEquals(attribute, leaf.getAttribute());
  }

  @Test
  public void getSearchValue_Works() {
    String searchValue = "search";

    QueryLeaf leaf = new QueryLeaf(null, null, searchValue);

    assertEquals(searchValue, leaf.getSearchValue());
  }
}
