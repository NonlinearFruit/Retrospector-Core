
package retrospector.core.datagateway;

import java.util.List;
import retrospector.core.entity.Entity;

public interface CrudDataGateway<T extends Entity> {
    public T add(T entity);
    public T get(int id);
    public T update(T entity);
    public void delete(int id);
    public List<T> getAll();
    public List<T> getByMediaId(int mediaId);
}
