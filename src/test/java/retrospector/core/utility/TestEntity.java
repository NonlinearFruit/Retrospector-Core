
package retrospector.core.utility;

import java.time.LocalDate;
import retrospector.core.entity.Factoid;
import retrospector.core.entity.Media;
import retrospector.core.entity.Review;
import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.request.model.RequestableMedia;
import retrospector.core.request.model.RequestableReview;

public class TestEntity {
    public static Media getMedia() {
        Media media = new Media(
                "Sherlock Holmes",
                "Arthur Conan Doyle",
                "Book",
                Media.Type.MINISERIES
        );
        media.setId(12);
        media.setEpisode("A Study In Scarlet");
        media.setDescription("...");
        
        return media;
    }

    public static Review getReview() {
        Review review = new Review(
                9,
                LocalDate.now(),
                "Diana",
                ""
        );
        review.setId(998);
        review.setMediaId(12);
        
        return review;
    }

    public static Factoid getFactoid() {
        Factoid factoid = new Factoid(
                "Genre",
                "Gothic Thriller"
        );
        factoid.setId(1094);
        factoid.setMediaId(12);
        
        return factoid;
    }
    
    public static RequestableMedia getRequestableMedia() {
        RequestableMedia media = new RequestableMedia(
                "Nancy Drew",
                "Carolsomething something",
                "Book",
                RequestableMedia.RequestableType.MINISERIES
        );
        media.setId(123);
        media.setEpisode("B2 The Hidden Staircase");
        media.setDescription("...");
        
        return media;
    }

    public static RequestableReview getRequestableReview() {
        RequestableReview review = new RequestableReview(
                6,
                LocalDate.now(),
                "Ben",
                "Pretty good but not great"
        );
        review.setId(314);
        review.setMediaId(123);
        
        return review;
    }

    public static RequestableFactoid getRequestableFactoid() {
        RequestableFactoid factoid = new RequestableFactoid(
                "Year",
                "1992"
        );
        factoid.setId(598);
        factoid.setMediaId(123);
        
        return factoid;
    }
}
