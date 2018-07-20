
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
            case Update:
                updateReview();
                break;
        }
    }

    private void createReview() {
        Review review = dataGateway.addReview(convert(request.getReview()));
    }

    private void updateReview() {
        Review review = dataGateway.updateReview(convert(request.getReview()));
    }

    private Review convert(RequestableReview review) {
        return EntityConverter.convert(review);
    }
}
