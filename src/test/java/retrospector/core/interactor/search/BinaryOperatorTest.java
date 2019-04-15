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
public class BinaryOperatorTest {
  private static final String syntaxOp = "|";

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
      {new AndOperator(syntaxOp), OperatorType.And},
      {new OrOperator(syntaxOp), OperatorType.Or}
    });
  }

  @Parameter(0)
  public Operator operator;
  @Parameter(1)
  public OperatorType type;

  @Test
  public void parse_Parses() {
    String content = "this";
    String query = content;
    int count = 4;
    for (int i = 1; i < count; i++)
      query += syntaxOp + content;

    List<String> pieces = operator.parse(query);

    assertEquals(count, pieces.size());
    for (String piece : pieces)
      assertEquals(content, piece);
  }

  @Test
  public void parse_NoResults_WhenEmpty() {
    String empty = "";

    List<String> pieces = operator.parse(empty);

    assertTrue(pieces.isEmpty());
  }

  @Test
  public void parse_NoResults_WhenNoOp() {
    String noop = "words with spaces and such.";

    List<String> pieces = operator.parse(noop);

    assertTrue(pieces.isEmpty());
  }

  @Test
  public void parse_Trims() {
    String word = "  words  ";
    String input = word + syntaxOp + word;

    List<String> pieces = operator.parse(input);

    for (String piece : pieces)
      assertEquals(word.trim(), piece);
  }

  @Test
  public void getType_ReturnsType() {
    assertEquals(type, operator.getType());
  }
}
