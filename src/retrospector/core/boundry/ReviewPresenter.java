
package retrospector.core.boundry;

import retrospector.core.request.model.RequestableReview;

public interface ReviewPresenter {
    public void reviewAdded(RequestableReview requestableReview);
    public void reviewRetrieved(RequestableReview requestableReview);
    public void reviewUpdated(RequestableReview requestableReview);
    public void reviewDeleted(Integer id);
}
