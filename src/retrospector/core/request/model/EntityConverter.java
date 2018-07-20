
package retrospector.core.request.model;

import java.util.stream.Collectors;
import retrospector.core.entity.Factoid;
import retrospector.core.entity.Media;
import retrospector.core.entity.Media.Type;
import retrospector.core.entity.Review;
import retrospector.core.request.model.RequestableMedia.RequestableType;

public class EntityConverter {
    
    public static Media convert(RequestableMedia requestable) {
        Media media = new Media(
                requestable.getTitle(),
                requestable.getCreator(),
                requestable.getCategory()
        );
        media.setId(requestable.getId());
        media.setSeason(requestable.getSeason());
        media.setEpisode(requestable.getEpisode());
        media.setType(convert(requestable.getType()));
        media.setDescription(requestable.getDescription());
        media.getReviews().addAll(requestable
                        .getReviews()
                        .stream()
                        .map((x)->convert(x))
                        .collect(Collectors.toList())
        );
        media.getFactoids().addAll(requestable
                        .getFactoids()
                        .stream()
                        .map((x)->convert(x))
                        .collect(Collectors.toList())
        );
        return media;
    }
    
    public static Type convert(RequestableType requestable) {
        switch(requestable) {
            default:
            case SINGLE: return Type.SINGLE;
            case MINISERIES: return Type.MINISERIES;
            case SERIES: return Type.SERIES;
            case WISHLIST: return Type.WISHLIST;
        }
    }
    
    public static Review convert(RequestableReview requestable) {
        Review review = new Review(
                requestable.getRating(),
                requestable.getDate(),
                requestable.getUser(),
                requestable.getReview()
        );
        review.setId(requestable.getId());
        review.setMediaId(requestable.getMediaId());
        
        return review;
    }
    
    public static Factoid convert(RequestableFactoid requestable) {
        Factoid factoid = new Factoid(
                requestable.getTitle(),
                requestable.getContent()
        );
        factoid.setId(requestable.getId());
        factoid.setMediaId(requestable.getMediaId());
        
        return factoid;
    }    
    public static RequestableMedia convert(Media media) {
        RequestableMedia requestable = new RequestableMedia(
                media.getTitle(),
                media.getCreator(),
                media.getCategory()
        );
        requestable.setId(media.getId());
        requestable.setSeason(media.getSeason());
        requestable.setEpisode(media.getEpisode());
        requestable.setType(convert(media.getType()));
        requestable.setDescription(media.getDescription());
        requestable.getReviews().addAll(media
                        .getReviews()
                        .stream()
                        .map((x)->convert(x))
                        .collect(Collectors.toList())
        );
        requestable.getFactoids().addAll(media
                        .getFactoids()
                        .stream()
                        .map((x)->convert(x))
                        .collect(Collectors.toList())
        );
        
        return requestable;
    }
    
    public static RequestableType convert(Type type) {
        switch(type) {
            default:
            case SINGLE: return RequestableType.SINGLE;
            case MINISERIES: return RequestableType.MINISERIES;
            case SERIES: return RequestableType.SERIES;
            case WISHLIST: return RequestableType.WISHLIST;
        }
    }
    
    public static RequestableReview convert(Review review) {
        RequestableReview requestable = new RequestableReview(
                review.getRating(),
                review.getDate(),
                review.getUser(),
                review.getReview()
        );
        requestable.setId(review.getId());
        requestable.setMediaId(review.getMediaId());
        
        return requestable;
    }
    
    public static RequestableFactoid convert(Factoid factoid) {
        RequestableFactoid requestable = new RequestableFactoid(
                factoid.getTitle(),
                factoid.getContent()
        );
        requestable.setId(factoid.getId());
        requestable.setMediaId(factoid.getMediaId());
        
        return requestable;
    }
}
