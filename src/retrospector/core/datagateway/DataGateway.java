
package retrospector.core.datagateway;

import java.util.List;
import retrospector.core.entity.Media;
import retrospector.core.entity.Review;
import retrospector.core.entity.Factoid;

public interface DataGateway {
    
    public Media addMedia(Media media);
    public Media getMedia(int mediaId);
    public Media updateMedia(Media media);
    public void deleteMedia(int mediaId);
    public List<Media> getMedia();
    
    public Review addReview(Review review);
    public Review getReview(int reviewId);
    public Review updateReview(Review review);
    public void deleteReview(int reviewId);
    public List<Review> getReviews();
    
    public Factoid addFactoid(Factoid factoid);
    public Factoid getFactoid(int factoidId);
    public Factoid updateFactoid(Factoid factoid);
    public void deleteFactoid(int factoidId);
    public List<Factoid> getFactoids();
}
