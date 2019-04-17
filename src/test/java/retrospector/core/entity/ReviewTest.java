package retrospector.core.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ReviewTest {

  private Review review;

  @Before
  public void setup() {
    review = new Review();
  }
  
  @Test
  public void DefaultConstructor_SetsAllDefault() {
    Review review = new Review();
    
    assertEquals(null, review.getId());
    assertEquals(null, review.getMediaId());
    assertEquals((Integer) 0, review.getRating());
    assertEquals(LocalDate.now(), review.getDate());
    assertEquals("", review.getReview());
    assertEquals("", review.getUser());
  }

  @Test
  public void DefaultConstructor_SetsValues() {
    String user = "1";
    String content = "2";
    LocalDate date = LocalDate.now().minus(3, ChronoUnit.DAYS);
    Integer rating = 4;
    Review review = new Review(rating, date, user, content);
    
    assertEquals(null, review.getId());
    assertEquals(null, review.getMediaId());
    assertEquals(rating, review.getRating());
    assertEquals(date, review.getDate());
    assertEquals(content, review.getReview());
    assertEquals(user, review.getUser());
  }

  @Test
  public void IdAccessors_Work() {
    Integer id = 5;

    review.setId(id);
    Integer result = review.getId();

    assertEquals(id, result);
  }

  @Test
  public void MediaIdAccessors_Work() {
    Integer id = 5;

    review.setMediaId(id);
    Integer result = review.getMediaId();

    assertEquals(id, result);
  }

  @Test
  public void UserAccessors_Work() {
    String user = "1";

    review.setUser(user);
    String result = review.getUser();

    assertEquals(user, result);
  }

  @Test
  public void ContentAccessors_Work() {
    String content = "1";

    review.setReview(content);
    String result = review.getReview();

    assertEquals(content, result);
  }

  @Test
  public void RatingAccessors_Work() {
    Integer rating = 5;

    review.setRating(rating);
    Integer result = review.getRating();

    assertEquals(rating, result);
  }

  @Test
  public void DateAccessors_Work() {
    LocalDate date = LocalDate.now().minus(1, ChronoUnit.DAYS);

    review.setDate(date);
    LocalDate result = review.getDate();

    assertEquals(date, result);
  }

  @Test
  public void Equals_Works() {
    Review reviewA = new Review(5, LocalDate.now(), "user", "review");
    Review reviewB = new Review(reviewA.getRating(), reviewA.getDate(), reviewA.getUser(), reviewA.getReview());

    assertEquals(reviewA, reviewB);
    assertEquals(reviewA.hashCode(), reviewB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenDifferentTypes() {
    Review reviewA = new Review(5, LocalDate.now(), "user", "review");

    assertFalse(reviewA.equals("string"));
  }

  @Test
  public void Equals_ReturnsFalse_WhenIdMismatch() {
    Review reviewA = new Review(5, LocalDate.now(), "user", "review");
    reviewA.setId(4);
    reviewA.setMediaId(3);
    Review reviewB = new Review(reviewA.getRating(), reviewA.getDate(), reviewA.getUser(), reviewA.getReview());
    reviewB.setId(1+reviewA.getId());
    reviewB.setMediaId(reviewA.getMediaId());

    assertFalse(reviewA.equals(reviewB));
    assertNotEquals(reviewA.hashCode(), reviewB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenMediaIdMismatch() {
    Review reviewA = new Review(5, LocalDate.now(), "user", "review");
    reviewA.setId(4);
    reviewA.setMediaId(3);
    Review reviewB = new Review(reviewA.getRating(), reviewA.getDate(), reviewA.getUser(), reviewA.getReview());
    reviewB.setId(reviewA.getId());
    reviewB.setMediaId(1+reviewA.getMediaId());

    assertFalse(reviewA.equals(reviewB));
    assertNotEquals(reviewA.hashCode(), reviewB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenRatingMismatch() {
    Review reviewA = new Review(5, LocalDate.now(), "user", "review");
    reviewA.setId(4);
    reviewA.setMediaId(3);
    Review reviewB = new Review(1+reviewA.getRating(), reviewA.getDate(), reviewA.getUser(), reviewA.getReview());
    reviewB.setId(reviewA.getId());
    reviewB.setMediaId(reviewA.getMediaId());

    assertFalse(reviewA.equals(reviewB));
    assertNotEquals(reviewA.hashCode(), reviewB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenDateMismatch() {
    Review reviewA = new Review(5, LocalDate.now(), "user", "review");
    reviewA.setId(4);
    reviewA.setMediaId(3);
    Review reviewB = new Review(reviewA.getRating(), reviewA.getDate().minus(1, ChronoUnit.DAYS), reviewA.getUser(), reviewA.getReview());
    reviewB.setId(reviewA.getId());
    reviewB.setMediaId(reviewA.getMediaId());

    assertFalse(reviewA.equals(reviewB));
    assertNotEquals(reviewA.hashCode(), reviewB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenUserMismatch() {
    Review reviewA = new Review(5, LocalDate.now(), "user", "review");
    reviewA.setId(4);
    reviewA.setMediaId(3);
    Review reviewB = new Review(reviewA.getRating(), reviewA.getDate(), "not "+reviewA.getUser(), reviewA.getReview());
    reviewB.setId(reviewA.getId());
    reviewB.setMediaId(reviewA.getMediaId());

    assertFalse(reviewA.equals(reviewB));
    assertNotEquals(reviewA.hashCode(), reviewB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenReviewMismatch() {
    Review reviewA = new Review(5, LocalDate.now(), "user", "review");
    reviewA.setId(4);
    reviewA.setMediaId(3);
    Review reviewB = new Review(reviewA.getRating(), reviewA.getDate(), reviewA.getUser(), "not "+reviewA.getReview());
    reviewB.setId(reviewA.getId());
    reviewB.setMediaId(reviewA.getMediaId());

    assertFalse(reviewA.equals(reviewB));
    assertNotEquals(reviewA.hashCode(), reviewB.hashCode());
  }
}
