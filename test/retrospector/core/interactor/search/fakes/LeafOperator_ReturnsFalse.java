package retrospector.core.interactor.search.fakes;

import retrospector.core.interactor.search.LeafOperator;

public class LeafOperator_ReturnsFalse extends LeafOperator {

  public LeafOperator_ReturnsFalse() {
    super("");
  }

  @Override
  public boolean parse(String query) {
    return false;
  }

  @Override
  public String getCommand() {
    return "";
  }

  @Override
  public String getComparator() {
    return "";
  }

  @Override
  public String getValue() {
    return "";
  }
}
