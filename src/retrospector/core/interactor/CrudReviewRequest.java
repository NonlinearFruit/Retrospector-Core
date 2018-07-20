
package retrospector.core.interactor;

import retrospector.core.boundry.Request;
import retrospector.core.request.model.RequestableReview;

public class CrudReviewRequest implements Request{

    public enum Crud {Create, Read, Update, Delete}
    
    private Crud crud;
    private RequestableReview review;
    private int reviewId;
    
    public CrudReviewRequest(Crud crud, RequestableReview review) {
        this(crud, review.getId());
        this.review = review;
    }
    
    public CrudReviewRequest(Crud crud, int reviewId) {
        this.crud = crud;
        this.reviewId = reviewId;
    }

    public Crud getCrud() {
        return crud;
    }

    public RequestableReview getReview() {
        return review;
    }

    public int getReviewId() {
        return reviewId;
    }
}
