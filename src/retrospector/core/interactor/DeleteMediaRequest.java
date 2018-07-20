
package retrospector.core.interactor;

import retrospector.core.boundry.Request;

public class DeleteMediaRequest implements Request{
    private int mediaId;
    
    public DeleteMediaRequest(int id) {
        this.mediaId = id;
    }
    
    public int getMediaId(){
        return mediaId;
    }
}
