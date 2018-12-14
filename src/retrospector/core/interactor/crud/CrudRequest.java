
package retrospector.core.interactor.crud;

import retrospector.core.boundry.Request;
import retrospector.core.request.model.RequestableEntity;

public abstract class CrudRequest<T extends RequestableEntity> implements Request{
    public enum Crud {Create, Read, Update, Delete, ReadAll, ReadAllByMediaId}
    
    private Crud crud;
    private T entity;
    private Integer entityId;
    
    public CrudRequest(Crud crud, T entity) {
        this(crud, entity.getId());
        this.entity = entity;
    }
    
    public CrudRequest(Crud crud, Integer entityId) {
        this(crud);
        this.entityId = entityId;
    }
    
    public CrudRequest(Crud crud) {
        this.crud = crud;
    }
    
    public Crud getCrud() {
        return crud;
    }

    public T getRequestable() {
        return entity;
    }

    public Integer getRequestableId() {
        return entityId;
    }
}
