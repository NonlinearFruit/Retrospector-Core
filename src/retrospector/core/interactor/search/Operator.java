package retrospector.core.interactor.search;

import java.util.List;

public interface Operator {
  List<String> parse(String query);
}
