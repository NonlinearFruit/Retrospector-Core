
package retrospector.core.interactor;

import retrospector.core.boundry.Presenter;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Review;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableReview;

public class CrudReviewUseCase extends CrudUseCase<CrudReviewRequest> {

    public CrudReviewUseCase(DataGateway dataGateway, Presenter presenter) {
        super(dataGateway, presenter);
    }

    @Override
    public void create() {
        Review review = dataGateway.addReview(convert(request.getRequestable()));
        presenter.reviewAdded(convert(review));
    }
    
    @Override
    public void read() {
        Review review = dataGateway.getReview(request.getRequestableId());
        presenter.reviewRetrieved(convert(review));
    }

    @Override
    public void update() {
        Review review = dataGateway.updateReview(convert(request.getRequestable()));
        presenter.reviewUpdated(convert(review));
    }

    @Override
    public void delete() {
        dataGateway.deleteReview(request.getRequestableId());
        presenter.reviewDeleted(request.getRequestableId());
    }
    
    private Review convert(RequestableReview review) {
        return EntityConverter.convert(review);
    }
    
    private RequestableReview convert(Review review) {
        return EntityConverter.convert(review);
    }

    @Override
    protected void readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void readAllById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
