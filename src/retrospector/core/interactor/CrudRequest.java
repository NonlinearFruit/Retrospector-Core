
package retrospector.core.interactor;

import retrospector.core.boundry.Request;
import retrospector.core.request.model.RequestableEntity;

public abstract class CrudRequest<T extends RequestableEntity> implements Request{
    public enum Crud {Create, Read, Update, Delete}
    
    private Crud crud;
    private T entity;
    private int entityId;
    
    public CrudRequest(Crud crud, T entity) {
        this(crud, entity.getId());
        this.entity = entity;
    }
    
    public CrudRequest(Crud crud, int entityId) {
        this.crud = crud;
        this.entityId = entityId;
    }

    public Crud getCrud() {
        return crud;
    }

    public T getRequestable() {
        return entity;
    }

    public int getRequestableId() {
        return entityId;
    }
}
