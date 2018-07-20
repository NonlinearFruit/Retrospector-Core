
package retrospector.core.interactor;

import retrospector.core.boundry.Request;
import retrospector.core.request.model.RequestableMedia;

public class CrudMediaRequest implements Request{
    public enum Crud {Create, Read, Update, Delete}
    
    private Crud crud;
    private RequestableMedia media;
    private int mediaId;
    
    public CrudMediaRequest(Crud crud, RequestableMedia media) {
        this(crud, media.getId());
        this.media = media;
    }
    
    public CrudMediaRequest(Crud crud, int mediaId) {
        this.crud = crud;
        this.mediaId = mediaId;
    }

    public Crud getCrud() {
        return crud;
    }

    public RequestableMedia getMedia() {
        return media;
    }

    public int getMediaId() {
        return mediaId;
    }
}
