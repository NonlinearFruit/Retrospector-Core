package retrospector.core.interactor.search;

import java.util.Arrays;
import java.util.Optional;

public class LeafOperator {
  private String opCharacter;

  private  String command;
  private String comparator;
  private String value;

  public LeafOperator(String syntaxOp) {
    opCharacter = syntaxOp;
  }

  public boolean parse(String query) {
    query = query.trim();
    if (!query.startsWith(opCharacter))
      return false;
    query = query.substring(opCharacter.length());

    comparator = findFirstComparator(query);
    if (comparator.isEmpty())
      return false;

    String[] commandValue = query.split(comparator);
    if (commandValue.length < 2)
      return false;

    command = commandValue[0];
    if (command.isEmpty())
      return false;

    value = commandValue[1];
    return true;
  }

  public String getCommand() {
    return command;
  }

  public String getComparator() {
    return comparator;
  }

  public String getValue() {
    return value;
  }

  private String findFirstComparator(String query) {
    Optional<String> result = Arrays.asList(LeafComparator.values())
            .stream()
            .map(c -> c.getValue())
            .filter(v -> query.contains(v))
            .sorted((a,b) -> query.indexOf(a) - query.indexOf(b))
            .findFirst();

    if (!result.isPresent())
      return "";

    return result.get();
  }
}
