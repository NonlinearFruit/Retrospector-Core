package retrospector.core.interactor.search;

import java.util.ArrayList;
import java.util.List;

public class AndOperator implements Operator {
  private String whitespace = "\\s*";
  private String opCharacter = ":";

  public List<String> parse(String query) {
    List<String> pieces = new ArrayList<>();
    String[] fragments = query.trim().split(whitespace + opCharacter + whitespace);
    if (fragments.length == 1)
      return pieces;
    for (String piece : fragments)
      pieces.add(piece);
    return pieces;
  }
}
