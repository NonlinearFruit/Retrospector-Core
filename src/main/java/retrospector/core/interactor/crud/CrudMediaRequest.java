package retrospector.core.interactor.crud;

import retrospector.core.request.model.RequestableMedia;

public class CrudMediaRequest extends CrudRequest<RequestableMedia>{

    public CrudMediaRequest(Crud crud, RequestableMedia media) {
        super(crud, media);
    }
    
    public CrudMediaRequest(Crud crud, int mediaId) {
        super(crud, mediaId);
    }
    
    public CrudMediaRequest(Crud crud) {
        super(crud);
    }
}
