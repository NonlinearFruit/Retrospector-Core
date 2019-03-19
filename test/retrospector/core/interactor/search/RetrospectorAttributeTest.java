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
public class RetrospectorAttributeTest {
  @Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {
        {"T", RetrospectorAttribute.MediaTitle},
        {"C", RetrospectorAttribute.MediaCreator},
        {"S", RetrospectorAttribute.MediaSeason},
        {"E", RetrospectorAttribute.MediaEpisode},
        {"A", RetrospectorAttribute.MediaCategory},
        {"P", RetrospectorAttribute.MediaDescription},
        {"#", RetrospectorAttribute.ReviewRating},
        {"U", RetrospectorAttribute.ReviewUser},
        {"D", RetrospectorAttribute.ReviewDate},
        {"R", RetrospectorAttribute.ReviewContent},
        {"I", RetrospectorAttribute.FactoidTitle},
        {"O", RetrospectorAttribute.FactoidContent},
      });
    }

  @Parameter(0)
  public String value;
  @Parameter(1)
  public RetrospectorAttribute attribute;

  @Test
  public void getValue_Works() {
    assertEquals(value, attribute.getValue());
  }
}
