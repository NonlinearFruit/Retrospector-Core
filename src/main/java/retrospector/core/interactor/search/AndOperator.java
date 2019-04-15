package retrospector.core.interactor.search;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AndOperator implements Operator {
  private String whitespace = "\\s*";
  private OperatorType type = OperatorType.And;
  private String opCharacter;

  AndOperator(String syntaxOp) {
    opCharacter = syntaxOp;
  }

  public List<String> parse(String query) {
    List<String> pieces = new ArrayList<>();
    String[] fragments = query.trim().split(whitespace + Pattern.quote(opCharacter) + whitespace);
    if (fragments.length == 1)
      return pieces;
    for (String piece : fragments)
      pieces.add(piece);
    return pieces;
  }

  @Override
  public OperatorType getType() {
    return type;
  }
}
