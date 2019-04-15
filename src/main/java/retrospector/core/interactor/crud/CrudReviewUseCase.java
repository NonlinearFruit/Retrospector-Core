
package retrospector.core.interactor.crud;

import retrospector.core.boundry.CrudPresenter;
import retrospector.core.entity.Review;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableReview;
import retrospector.core.datagateway.CrudDataGateway;

public class CrudReviewUseCase extends CrudUseCase<CrudReviewRequest> {

  private final CrudDataGateway<Review> dataGateway;
  private final CrudPresenter<RequestableReview> presenter;

    public CrudReviewUseCase(CrudDataGateway<Review> dataGateway, CrudPresenter<RequestableReview> presenter) {
      this.dataGateway = dataGateway;
      this.presenter = presenter;
    }

    @Override
    public void create() {
        Review review = dataGateway.add(convert(request.getRequestable()));
        presenter.added(convert(review));
    }
    
    @Override
    public void read() {
        Review review = dataGateway.get(request.getRequestableId());
        presenter.retrieved(convert(review));
    }

    @Override
    public void update() {
        Review review = dataGateway.update(convert(request.getRequestable()));
        presenter.updated(convert(review));
    }

    @Override
    public void delete() {
        dataGateway.delete(request.getRequestableId());
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
