
package retrospector.core.interactor;

import retrospector.core.request.model.RequestableReview;

public class CrudReviewRequest extends CrudRequest<RequestableReview>{

    public CrudReviewRequest(Crud crud, RequestableReview review) {
        super(crud, review);
    }
    
    public CrudReviewRequest(Crud crud, int reviewId) {
        super(crud, reviewId);
    }
}
