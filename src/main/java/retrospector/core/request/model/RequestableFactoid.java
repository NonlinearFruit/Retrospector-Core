
package retrospector.core.request.model;

public class RequestableFactoid implements RequestableEntity{
    private String title;
    private String content;
    private Integer id;
    private Integer mediaId;
    
    public RequestableFactoid(String title) {
        this(title,"");
    }
    
    public RequestableFactoid(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    
    @Override
    public boolean equals(Object object) {
        if (! (object instanceof RequestableFactoid) )
            return false;
        
        RequestableFactoid factoid = (RequestableFactoid) object;
        
        return getId() == factoid.getId() &&
                getMediaId() == factoid.getMediaId() &&
                getTitle().equals(factoid.getTitle()) &&
                getContent().equals(factoid.getContent());
    }
}