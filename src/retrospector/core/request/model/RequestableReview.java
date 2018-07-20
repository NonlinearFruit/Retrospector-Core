
package retrospector.core.request.model;

import java.time.LocalDate;

public class RequestableReview {
    
    private LocalDate date;
    private String user;
    private String review;
    private Integer rating;
    private Integer mediaId;
    private Integer id;
    
    public RequestableReview(Integer rating, LocalDate date, String user, String review){
        setRating(rating);
        setDate(date);
        setUser(user);
        setReview(review);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public void clone(RequestableReview review){
        setId(review.getId());
        setMediaId(review.getMediaId());
        setUser(review.getUser());
        setReview(review.getReview());
        setDate(review.getDate());
        setRating(review.getRating());
    }
    
    @Override
    public boolean equals(Object object) {
        if(! (object instanceof RequestableReview) )
            return false;
        
        RequestableReview review = (RequestableReview) object;
        
        return getId() == review.getId() &&
                getMediaId() == review.getMediaId() &&
                getRating().equals(review.getRating()) &&
                getDate().equals(review.getDate()) &&
                getUser().equals(review.getUser()) &&
                getReview().equals(review.getReview());
    }
}
