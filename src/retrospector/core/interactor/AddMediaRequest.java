
package retrospector.core.interactor;

import retrospector.core.boundry.Request;
import retrospector.core.request.model.RequestableMedia;

public class AddMediaRequest implements Request{
    private RequestableMedia media;
    
    public AddMediaRequest(RequestableMedia media) {
        this.media = media;
    }
    
    public RequestableMedia getMedia() {
        return media;
    }
}
