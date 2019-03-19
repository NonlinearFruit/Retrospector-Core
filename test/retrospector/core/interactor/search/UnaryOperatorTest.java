package retrospector.core.interactor.search;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class UnaryOperatorTest {
  @Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {
        {"!", new NotOperator()}
      });
    }

  @Parameter(0)
  public String syntaxOp;
  @Parameter(1)
  public Operator operator;

  @Test
  public void parse_Parses() {
    String content = "this";
    String query = syntaxOp + content;

    List<String> pieces = operator.parse(query);

    assertEquals(1, pieces.size());
    assertEquals(content, pieces.get(0));
  }

  @Test
  public void parse_Parses_WhenEmpty() {
    String empty = "";

    List<String> pieces = operator.parse(empty);

    assertTrue(pieces.isEmpty());
  }

  @Test
  public void parse_Parses_WhenNoOp() {
    String noop = "words with spaces and such.";

    List<String> pieces = operator.parse(noop);

    assertTrue(pieces.isEmpty());
  }
;
  @Test
  public void parse_Trims() {
    String word = "  words  ";
    String input = syntaxOp + word;

    List<String> pieces = operator.parse(input);

    for (String piece : pieces)
      assertEquals(word.trim(), piece);
  }
}
