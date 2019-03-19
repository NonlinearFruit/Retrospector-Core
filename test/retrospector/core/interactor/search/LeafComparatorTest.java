package retrospector.core.interactor.search;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class LeafComparatorTest {
  @Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {
        {"~", LeafComparator.Contains},
        {"=", LeafComparator.Equal},
        {">", LeafComparator.GreaterThan},
        {"<", LeafComparator.LessThan}
      });
    }

  @Parameter(0)
  public String value;
  @Parameter(1)
  public LeafComparator comparator;

  @Test
  public void getValue_Works() {
    assertEquals(value, comparator.getValue());
  }
}
