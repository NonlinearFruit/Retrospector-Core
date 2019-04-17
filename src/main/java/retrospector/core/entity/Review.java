
package retrospector.core.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Review implements Entity{
    
    private LocalDate date;
    private String user;
    private String review;
    private Integer rating;
    private Integer mediaId;
    private Integer id;
    
    public Review() {
        this(0, LocalDate.now(), "", "");
    }
    
    public Review(Integer rating, LocalDate date, String user, String review){
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
    
    @Override
    public boolean equals(Object object) {
        if(! (object instanceof Review) )
            return false;
        
        Review review = (Review) object;
        
        return getId() == review.getId() &&
                getMediaId() == review.getMediaId() &&
                getRating().equals(review.getRating()) &&
                getDate().equals(review.getDate()) &&
                getUser().equals(review.getUser()) &&
                getReview().equals(review.getReview());
    }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 13 * hash + Objects.hashCode(this.date);
    hash = 13 * hash + Objects.hashCode(this.user);
    hash = 13 * hash + Objects.hashCode(this.review);
    hash = 13 * hash + Objects.hashCode(this.rating);
    hash = 13 * hash + Objects.hashCode(this.mediaId);
    hash = 13 * hash + Objects.hashCode(this.id);
    return hash;
  }
}
