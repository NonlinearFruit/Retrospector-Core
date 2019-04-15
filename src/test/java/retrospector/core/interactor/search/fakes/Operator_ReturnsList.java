package retrospector.core.interactor.search.fakes;

import java.util.List;
import retrospector.core.interactor.search.Operator;
import retrospector.core.interactor.search.OperatorType;

public class Operator_ReturnsList implements Operator {

  private String parseLastCalledWith;
  private int countOfParseCalls;
  private List<String> results;

  public Operator_ReturnsList(List<String> results) {
    this.results = results;
  }

  @Override
  public List<String> parse(String query) {
    countOfParseCalls++;
    parseLastCalledWith = query;
    return results;
  }

  public String getParseLastCalledWith() {
    return parseLastCalledWith;
  }

  public int getCountOfParseCalls() {
    return countOfParseCalls;
  }

  @Override
  public OperatorType getType() {
    return OperatorType.And;
  }
}
