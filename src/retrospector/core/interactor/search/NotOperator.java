package retrospector.core.interactor.search;

import java.util.ArrayList;
import java.util.List;

public class NotOperator implements Operator {
  private String whitespace = "\\s*";
  private String opCharacter = "!";

  @Override
  public List<String> parse(String query) {
    List<String> pieces = new ArrayList<>();
    query = query.trim();
    if (query.startsWith(opCharacter))
      pieces.add(query.substring(opCharacter.length()).trim());
    return pieces;
  }
}
