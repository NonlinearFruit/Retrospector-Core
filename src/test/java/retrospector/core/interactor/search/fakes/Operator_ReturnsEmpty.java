package retrospector.core.interactor.search.fakes;

import java.util.ArrayList;
import java.util.List;
import retrospector.core.interactor.search.Operator;
import retrospector.core.interactor.search.OperatorType;

public class Operator_ReturnsEmpty implements Operator {

  private String parseLastCalledWith;
  private int countOfParseCalls;

  public Operator_ReturnsEmpty() {
  }

  @Override
  public List<String> parse(String query) {
    countOfParseCalls++;
    parseLastCalledWith = query;
    return new ArrayList<>();
  }

  public String getParseLastCalledWith() {
    return parseLastCalledWith;
  }

  public int getCountOfParseCalls() {
    return countOfParseCalls;
  }

  @Override
  public OperatorType getType() {
    return OperatorType.Not;
  }
}
