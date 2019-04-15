package retrospector.core.interactor.search.fakes;

import retrospector.core.interactor.search.LeafOperator;

public class LeafOperator_ReturnsGivenValues extends LeafOperator {

  private String command;
  private String comparator;
  private String value;

  public LeafOperator_ReturnsGivenValues(String command, String comparator, String value) {
    super("");
    this.command = command;
    this.comparator = comparator;
    this.value = value;
  }

  @Override
  public boolean parse(String query) {
    return true;
  }

  @Override
  public String getCommand() {
    return command;
  }

  @Override
  public String getComparator() {
    return comparator;
  }

  @Override
  public String getValue() {
    return value;
  }
}
