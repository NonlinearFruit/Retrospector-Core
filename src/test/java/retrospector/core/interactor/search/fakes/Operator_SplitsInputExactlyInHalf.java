package retrospector.core.interactor.search.fakes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrospector.core.interactor.search.Operator;
import retrospector.core.interactor.search.OperatorType;

public class Operator_SplitsInputExactlyInHalf implements Operator {

  private OperatorType type;

  public Operator_SplitsInputExactlyInHalf() {
    this.type = OperatorType.And;
  }

  public Operator_SplitsInputExactlyInHalf(OperatorType type) {
    this.type = type;
  }

  @Override
  public List<String> parse(String query) {
    int length = query.length();
    if (length % 2 != 0)
      return new ArrayList<>();
    return Arrays.asList(query.substring(0, length/2), query.substring(length/2));
  }

  @Override
  public OperatorType getType() {
    return type;
  }
}