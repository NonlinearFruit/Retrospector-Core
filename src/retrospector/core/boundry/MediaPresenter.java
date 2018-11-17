
package retrospector.core.boundry;

import java.util.List;
import retrospector.core.request.model.RequestableMedia;

public interface MediaPresenter {
    public void mediaAdded(RequestableMedia media);
    public void mediaRetrieved(RequestableMedia convert);
    public void mediaUpdated(RequestableMedia convert);
    public void mediaDeleted(int mediaId);
    public void mediaRetrievedAll(List<RequestableMedia> media);
}
