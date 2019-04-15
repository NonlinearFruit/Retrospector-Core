
package retrospector.core.interactor.crud;

import retrospector.core.request.model.RequestableFactoid;

public class CrudFactoidRequest extends CrudRequest<RequestableFactoid>{

    public CrudFactoidRequest(Crud crud, RequestableFactoid factoid) {
        super(crud, factoid);
    }
    
    public CrudFactoidRequest(Crud crud, int factoidId) {
        super(crud, factoidId);
    }
    
    public CrudFactoidRequest(Crud crud) {
        super(crud);
    }
}
