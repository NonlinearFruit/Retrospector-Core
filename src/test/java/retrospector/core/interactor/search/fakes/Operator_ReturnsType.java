package retrospector.core.interactor.search.fakes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrospector.core.interactor.search.Operator;
import retrospector.core.interactor.search.OperatorType;

public class Operator_ReturnsType implements Operator {

  private int countOfGetTypeCalls;
  private OperatorType type;

  public Operator_ReturnsType(OperatorType type) {
    this.type = type;
  }

  @Override
  public List<String> parse(String query) {
    if (query.isEmpty())
      return new ArrayList<>();
    return Arrays.asList(query.substring(1));
  }

  @Override
  public OperatorType getType() {
    countOfGetTypeCalls++;
    return type;
  }

  public int getCountOfGetTypeCalls() {
    return countOfGetTypeCalls;
  }
}
