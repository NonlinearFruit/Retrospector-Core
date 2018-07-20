
package retrospector.core.interactor;

import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.Request;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Review;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableReview;

public class CrudReviewUseCase implements Interactor{

    private DataGateway dataGateway;
    private Presenter presenter;
    private CrudReviewRequest request;
    
    public CrudReviewUseCase(DataGateway dataGateway, Presenter presenter) {
        this.dataGateway = dataGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        this.request = (CrudReviewRequest) request;
        switch(this.request.getCrud()) {
            case Create:
                createReview();
                break;
            case Read:
                readReview();
                break;
            case Update:
                updateReview();
                break;
            case Delete:
                deleteReview();
                break;
        }
    }

    private void createReview() {
        Review review = dataGateway.addReview(convert(request.getReview()));
        presenter.reviewAdded(convert(review));
    }
    
    private void readReview() {
        Review review = dataGateway.getReview(request.getReviewId());
        presenter.reviewRetrieved(convert(review));
    }

    private void updateReview() {
        Review review = dataGateway.updateReview(convert(request.getReview()));
        presenter.reviewUpdated(convert(review));
    }

    private void deleteReview() {
        dataGateway.deleteReview(request.getReviewId());
        presenter.reviewDeleted(request.getReviewId());
    }
    
    private Review convert(RequestableReview review) {
        return EntityConverter.convert(review);
    }
    
    private RequestableReview convert(Review review) {
        return EntityConverter.convert(review);
    }
}
