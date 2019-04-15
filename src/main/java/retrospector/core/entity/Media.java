
package retrospector.core.entity;

import java.util.ArrayList;
import java.util.List;

public class Media implements Entity{

    public static enum Type{MINISERIES,SERIES,SINGLE,WISHLIST}
    
    private List<Review> reviews;
    private List<Factoid> factoids;
    private String title;
    private String description;
    private String creator;
    private String category;
    private Type type;
    private String seasonId;
    private String episodeId;
    private Integer id;
    
    public Media() {
        this("", "", "");
    }
    
    public Media(String title, String creator, String category){
        this(title, creator, category, Type.SINGLE);
    }
    
    public Media(String title, String creator, String category, Type type){
        this(title, creator, category, type, new ArrayList<>());
    }
    
    public Media(String title, String creator, String category, Type type, List<Review> reviews){
        this(title, creator, category, type, reviews, new ArrayList<>());
    }
    
    public Media(String title, String creator, String category, Type type, List<Review> reviews, List<Factoid> factoids){
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

    public List<Factoid> getFactoids() {
        return factoids;
    }

    private void setFactoids(List<Factoid> factoids) {
        this.factoids = factoids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    private void setReviews(List<Review> reviews) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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
    
    public void clone(Media media){
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
        if (! (object instanceof Media) )
                return false;
        Media media = (Media) object;
        
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
