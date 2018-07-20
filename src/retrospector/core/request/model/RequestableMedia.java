
package retrospector.core.request.model;

import java.util.ArrayList;
import java.util.List;
import retrospector.core.entity.Media;

public class RequestableMedia {

    public static enum RequestableType{MINISERIES,SERIES,SINGLE,WISHLIST}
    
    private List<RequestableReview> reviews;
    private List<RequestableFactoid> factoids;
    private String title;
    private String description;
    private String creator;
    private String category;
    private RequestableType type;
    private String seasonId;
    private String episodeId;
    private Integer id;
    
    public RequestableMedia(String title, String creator, String category){
        this(title, creator, category, RequestableType.SINGLE);
    }
    
    public RequestableMedia(String title, String creator, String category, RequestableType type){
        this(title, creator, category, type, new ArrayList<>());
    }
    
    public RequestableMedia(String title, String creator, String category, RequestableType type, List<RequestableReview> reviews){
        this(title, creator, category, type, reviews, new ArrayList<>());
    }
    
    public RequestableMedia(String title, String creator, String category, RequestableType type, List<RequestableReview> reviews, List<RequestableFactoid> factoids){
        setTitle(title);
        setCreator(creator);
        setCategory(category);
        setType(type);
        setReviews(reviews);
        setFactoids(factoids);
        setSeason("");
        setEpisode("");
        setDescription("");
    }

    public List<RequestableFactoid> getFactoids() {
        return factoids;
    }

    private void setFactoids(List<RequestableFactoid> factoids) {
        this.factoids = factoids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<RequestableReview> getReviews() {
        return reviews;
    }

    private void setReviews(List<RequestableReview> reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public RequestableType getType() {
        return type;
    }

    public void setType(RequestableType type) {
        this.type = type;
    }

    public String getSeason() {
        return seasonId;
    }

    public void setSeason(String seasonId) {
        this.seasonId = seasonId;
    }

    public String getEpisode() {
        return episodeId;
    }

    public void setEpisode(String episodeId) {
        this.episodeId = episodeId;
    }
    
    public void clone(RequestableMedia media){
        setId(media.getId());
        setTitle(media.getTitle());
        setCreator(media.getCreator());
        setSeason(media.getSeason());
        setEpisode(media.getEpisode());
        setDescription(media.getDescription());
        setCategory(media.getCategory());
        setType(media.getType());
        getReviews().clear();
        getReviews().addAll(media.getReviews());
        getFactoids().clear();
        getFactoids().addAll(media.getFactoids());
    }
    
    @Override
    public boolean equals(Object object) {
        if (! (object instanceof RequestableMedia) )
                return false;
        RequestableMedia media = (RequestableMedia) object;
        
        return getId() == media.getId() &&
                getTitle().equals(media.getTitle()) &&
                getCreator().equals(media.getCreator()) &&
                getType().equals(media.getType()) &&
                getCategory().equals(media.getCategory()) &&
                getSeason().equals(media.getSeason()) &&
                getEpisode().equals(media.getEpisode()) &&
                getDescription().equals(media.getDescription());
    }
}
