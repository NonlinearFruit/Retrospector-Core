
package retrospector.core.boundry;

import java.util.List;
import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.request.model.RequestableMedia;
import retrospector.core.request.model.RequestableReview;

public interface Presenter {
    public void mediaAdded(RequestableMedia media);
    public void mediaRetrieved(RequestableMedia convert);
    public void mediaUpdated(RequestableMedia convert);
    public void mediaDeleted(int mediaId);
    public void mediaRetrievedAll(List<RequestableMedia> media);
    
    public void factoidAdded(RequestableFactoid factoid);
    public void factoidRetrieved(RequestableFactoid factoid);
    public void factoidUpdated(RequestableFactoid factoid);
    public void factoidDeleted(int factoidId);

    public void reviewAdded(RequestableReview requestableReview);
    public void reviewRetrieved(RequestableReview requestableReview);
    public void reviewUpdated(RequestableReview requestableReview);
    public void reviewDeleted(Integer id);
}
