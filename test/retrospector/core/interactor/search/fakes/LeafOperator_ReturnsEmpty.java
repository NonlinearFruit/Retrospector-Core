package retrospector.core.interactor.search.fakes;

import retrospector.core.interactor.search.LeafComparator;
import retrospector.core.interactor.search.LeafOperator;

public class LeafOperator_ReturnsEmpty extends LeafOperator {

  public LeafOperator_ReturnsEmpty() {
    super("");
  }

  @Override
  public boolean parse(String query) {
    return true;
  }

  @Override
  public String getCommand() {
    return "";
  }

  @Override
  public String getComparator() {
    return LeafComparator.Contains.getValue();
  }

  @Override
  public String getValue() {
    return "";
  }
}
