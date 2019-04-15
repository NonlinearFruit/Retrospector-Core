package retrospector.core.interactor.search.fakes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrospector.core.interactor.search.Operator;
import retrospector.core.interactor.search.OperatorType;

public class Operator_SutractsOneCharacter implements Operator {

  private OperatorType type;

  public Operator_SutractsOneCharacter() {
    this.type = OperatorType.And;
  }

  public Operator_SutractsOneCharacter(OperatorType type) {
    this.type = type;
  }

  @Override
  public List<String> parse(String query) {
    if (query.length() <= 1)
      return new ArrayList<>();
    return Arrays.asList(query.substring(1));
  }

  @Override
  public OperatorType getType() {
    return type;
  }
}