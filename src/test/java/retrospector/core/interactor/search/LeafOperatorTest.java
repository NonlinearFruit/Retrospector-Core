package retrospector.core.interactor.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class LeafOperatorTest {
  private String opSyntax = "`";
  private LeafOperator operator;

  @Before
  public void Setup() {
    operator = new LeafOperator(opSyntax);
  }

  @Test
  public void parse_NoResult_WhenEmpty() {
    String query = "";

    boolean success = operator.parse(query);

    assertFalse(success);
  }

  @Test
  public void parse_NoResult_WhenWhiteSpace() {
    String query = "    ";

    boolean success = operator.parse(query);

    assertFalse(success);
  }

  @Test
  public void parse_NoResult_WhenBlankCommand() {
    String query = opSyntax + "";

    boolean success = operator.parse(query);

    assertFalse(success);
  }

  @Test
  public void parse_NoResult_WhenNoComparator() {
    String query = opSyntax + "U" + "Ebenezr";

    boolean success = operator.parse(query);

    assertFalse(success);
  }

  @Test
  public void parse_NoResult_WhenNoCommand() {
    String query = opSyntax + LeafComparator.Contains.getValue() + "Ebenezer";

    boolean success = operator.parse(query);

    assertFalse(success);
  }

  @Test
  public void parse_NoResult_WhenNoValue() {
    String query = opSyntax + "U" + LeafComparator.Contains.getValue();

    boolean success = operator.parse(query);

    assertFalse(success);
  }

  @Test
  public void parse_Parses() {
    String command = "U";
    String comparator = LeafComparator.Contains.getValue();
    String value = "Ebenezer";
    String query = opSyntax + command + comparator + value;

    operator.parse(query);

    verify(command, comparator, value);
  }

  @Test
  public void parse_Parses_WhenFactoid() {
    String command = "Year";
    String comparator = LeafComparator.GreaterThan.getValue();
    String value = "2010";
    String query = opSyntax + command + comparator + value;

    operator.parse(query);

    verify(command, comparator, value);
  }

  @Test
  public void parse_Parses_WhenRating() {
    String command = "#";
    String comparator = LeafComparator.GreaterThan.getValue();
    String value = "7";
    String query = opSyntax + command + comparator + value;

    operator.parse(query);

    verify(command, comparator, value);
  }

  @Test
  public void parse_NoResult_WhenBadComparator() {
    String command = "#";
    String comparator = "d";
    String value = "7";
    String query = opSyntax + command + comparator + value;

    boolean success = operator.parse(query);

    assertFalse(success);
  }

  private void verify(String command, String comparator, String value) {
    assertEquals(command, operator.getCommand());
    assertEquals(comparator, operator.getComparator());
    assertEquals(value, operator.getValue());
  }
}
