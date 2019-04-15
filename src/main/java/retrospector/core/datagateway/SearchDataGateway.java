package retrospector.core.datagateway;

import java.util.List;
import java.util.Map;
import retrospector.core.interactor.search.QueryTree;
import retrospector.core.interactor.search.RetrospectorAttribute;

public interface SearchDataGateway {
  List<Map<RetrospectorAttribute, String>> search(QueryTree query);
}
