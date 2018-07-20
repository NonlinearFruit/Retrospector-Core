
package retrospector.core.interactor;

import retrospector.core.boundry.Request;
import retrospector.core.request.model.RequestableFactoid;

public class CrudFactoidRequest implements Request{
    public enum Crud {Create, Read, Update, Delete}
    
    private Crud crud;
    private RequestableFactoid factoid;
    private int factoidId;
    
    public CrudFactoidRequest(Crud crud, RequestableFactoid factoid) {
        this(crud, factoid.getId());
        this.factoid = factoid;
    }
    
    public CrudFactoidRequest(Crud crud, int factoidId) {
        this.crud = crud;
        this.factoidId = factoidId;
    }

    public Crud getCrud() {
        return crud;
    }

    public RequestableFactoid getFactoid() {
        return factoid;
    }

    public int getFactoidId() {
        return factoidId;
    }
}
