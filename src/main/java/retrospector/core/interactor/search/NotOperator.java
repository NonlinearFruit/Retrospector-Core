package retrospector.core.interactor.search;

import java.util.ArrayList;
import java.util.List;

public class NotOperator implements Operator {
  private String whitespace = "\\s*";
  private OperatorType type = OperatorType.Not;
  private String opCharacter;

  NotOperator(String syntaxOp) {
    opCharacter = syntaxOp;
  }

  @Override
  public List<String> parse(String query) {
    List<String> pieces = new ArrayList<>();
    query = query.trim();
    if (query.startsWith(opCharacter))
      pieces.add(query.substring(opCharacter.length()).trim());
    return pieces;
  }

  @Override
  public OperatorType getType() {
    return type;
  }
}
