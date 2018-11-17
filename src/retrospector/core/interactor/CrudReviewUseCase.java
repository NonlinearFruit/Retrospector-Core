
package retrospector.core.interactor;

import retrospector.core.boundry.CrudPresenter;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Review;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableReview;

public class CrudReviewUseCase extends CrudUseCase<CrudReviewRequest> {

  private final DataGateway dataGateway;
  private final CrudPresenter<RequestableReview> presenter;

    public CrudReviewUseCase(DataGateway dataGateway, CrudPresenter<RequestableReview> presenter) {
      this.dataGateway = dataGateway;
      this.presenter = presenter;
    }

    @Override
    public void create() {
        Review review = dataGateway.addReview(convert(request.getRequestable()));
        presenter.added(convert(review));
    }
    
    @Override
    public void read() {
        Review review = dataGateway.getReview(request.getRequestableId());
        presenter.retrieved(convert(review));
    }

    @Override
    public void update() {
        Review review = dataGateway.updateReview(convert(request.getRequestable()));
        presenter.updated(convert(review));
    }

    @Override
    public void delete() {
        dataGateway.deleteReview(request.getRequestableId());
        presenter.deleted(request.getRequestableId());
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
