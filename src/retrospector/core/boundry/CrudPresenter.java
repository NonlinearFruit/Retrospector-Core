package retrospector.core.boundry;

import java.util.List;
import retrospector.core.request.model.RequestableEntity;

public interface CrudPresenter<T extends RequestableEntity> {
    public void added(T entity);
    public void retrieved(T entity);
    public void updated(T entity);
    public void deleted(int id);
    public void retrievedAll(List<T> entity);
}
