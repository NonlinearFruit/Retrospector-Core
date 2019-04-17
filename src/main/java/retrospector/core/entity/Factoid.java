
package retrospector.core.entity;

import java.util.Objects;

public class Factoid implements Entity{
    private String title;
    private String content;
    private Integer id;
    private Integer mediaId;
    
    public Factoid() {
        this("", "");
    }
    
    public Factoid(String title) {
        this(title,"");
    }
    
    public Factoid(String title, String content) {
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
        if ( !(object instanceof Factoid))
            return false;
        
        Factoid factoid = (Factoid) object;
        return getId() == factoid.getId() &&
                getMediaId() == factoid.getMediaId() &&
                getTitle().equals(factoid.getTitle()) &&
                getContent().equals(factoid.getContent());
    }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 17 * hash + Objects.hashCode(this.title);
    hash = 17 * hash + Objects.hashCode(this.content);
    hash = 17 * hash + Objects.hashCode(this.id);
    hash = 17 * hash + Objects.hashCode(this.mediaId);
    return hash;
  }
}
