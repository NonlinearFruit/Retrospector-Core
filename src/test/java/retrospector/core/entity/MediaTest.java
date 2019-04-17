package retrospector.core.entity;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import retrospector.core.entity.Media.Type;

public class MediaTest {

  private Media media;
  
  @Before
  public void setup() {
    media = new Media();
  }  

  
  @Test
  public void DefaultConstructor_SetsAllBlank() {
    Media media = new Media();

    assertEquals("", media.getTitle());
    assertEquals("", media.getCreator());
    assertEquals("", media.getCategory());
    assertEquals(Type.SINGLE, media.getType());
    assertEquals("", media.getSeason());
    assertEquals("", media.getEpisode());
    assertEquals("", media.getDescription());
  }

  @Test
  public void TriConstructor_SetsProperValues() {
    String title = "1";
    String creator = "2";
    String category = "3";

    Media media = new Media(title, creator, category);

    assertEquals(title, media.getTitle());
    assertEquals(creator, media.getCreator());
    assertEquals(category, media.getCategory());
    assertEquals(Type.SINGLE, media.getType());
    assertEquals("", media.getSeason());
    assertEquals("", media.getEpisode());
    assertEquals("", media.getDescription());
  }

  @Test
  public void QuadConstructor_SetsProperValues() {
    String title = "1";
    String creator = "2";
    String category = "3";
    Type type = Type.MINISERIES;

    Media media = new Media(title, creator, category, type);

    assertEquals(title, media.getTitle());
    assertEquals(creator, media.getCreator());
    assertEquals(category, media.getCategory());
    assertEquals(type, media.getType());
    assertEquals("", media.getSeason());
    assertEquals("", media.getEpisode());
    assertEquals("", media.getDescription());
  }

  @Test
  public void QuinConstructor_SetsProperValues() {
    String title = "1";
    String creator = "2";
    String category = "3";
    Type type = Type.MINISERIES;
    List<Review> reviews = new ArrayList<>();

    Media media = new Media(title, creator, category, type, reviews);

    assertEquals(title, media.getTitle());
    assertEquals(creator, media.getCreator());
    assertEquals(category, media.getCategory());
    assertEquals(type, media.getType());
    assertEquals("", media.getSeason());
    assertEquals("", media.getEpisode());
    assertEquals("", media.getDescription());
    assertEquals(reviews, media.getReviews());
  }

  @Test
  public void HexConstructor_SetsProperValues() {
    String title = "1";
    String creator = "2";
    String category = "3";
    Type type = Type.MINISERIES;
    List<Review> reviews = new ArrayList<>();
    List<Factoid> factoids = new ArrayList<>();

    Media media = new Media(title, creator, category, type, reviews, factoids);

    assertEquals(title, media.getTitle());
    assertEquals(creator, media.getCreator());
    assertEquals(category, media.getCategory());
    assertEquals(type, media.getType());
    assertEquals("", media.getSeason());
    assertEquals("", media.getEpisode());
    assertEquals("", media.getDescription());
    assertEquals(reviews, media.getReviews());
    assertEquals(factoids, media.getFactoids());
  }

  @Test
  public void IdAccessors_Work() {
    int id = 3;

    media.setId(id);
    int result = media.getId();

    assertEquals(id, result);
  }

  @Test
  public void TitleAccessors_Work() {
    String title = "1";

    media.setTitle(title);
    String result = media.getTitle();

    assertEquals(title, result);
  }

  @Test
  public void CreatorAccessors_Work() {
    String creator = "1";

    media.setCreator(creator);
    String result = media.getCreator();

    assertEquals(creator, result);
  }

  @Test
  public void SeasonAccessors_Work() {
    String season = "1";

    media.setSeason(season);
    String result = media.getSeason();

    assertEquals(season, result);
  }

  @Test
  public void EpisodeAccessors_Work() {
    String episode = "1";

    media.setEpisode(episode);
    String result = media.getEpisode();

    assertEquals(episode, result);
  }

  @Test
  public void CategoryAccessors_Work() {
    String category = "1";

    media.setCategory(category);
    String result = media.getCategory();

    assertEquals(category, result);
  }

  @Test
  public void DescriptionAccessors_Work() {
    String description = "1";

    media.setDescription(description);
    String result = media.getDescription();

    assertEquals(description, result);
  }

  @Test
  public void TypeAccessors_Work() {
    Type type = Type.SERIES;

    media.setType(type);
    Type result = media.getType();

    assertEquals(type, result);
  }

  @Test
  public void Equals_Works(){
    Media mediaA = new Media("1", "2", "3", Type.MINISERIES);
    mediaA.setSeason("4");
    mediaA.setEpisode("5");
    mediaA.setDescription("6");
    mediaA.setId(7);
    Media mediaB = new Media(mediaA.getTitle(), mediaA.getCreator(), mediaA.getCategory(), mediaA.getType());
    mediaB.setSeason(mediaA.getSeason());
    mediaB.setEpisode(mediaA.getEpisode());
    mediaB.setDescription(mediaA.getDescription());
    mediaB.setId(mediaA.getId());

    assertTrue(mediaA.equals(mediaB));
    assertEquals(mediaA.hashCode(), mediaB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenDifferentObjects(){
    assertFalse(media.equals("media"));
  }

  @Test
  public void Equals_ReturnsFalse_WhenTitleMismatch(){
    Media mediaA = new Media("1", "2", "3", Type.MINISERIES);
    mediaA.setSeason("4");
    mediaA.setEpisode("5");
    mediaA.setDescription("6");
    mediaA.setId(7);
    Media mediaB = new Media("not "+mediaA.getTitle(), mediaA.getCreator(), mediaA.getCategory(), mediaA.getType());
    mediaB.setSeason(mediaA.getSeason());
    mediaB.setEpisode(mediaA.getEpisode());
    mediaB.setDescription(mediaA.getDescription());
    mediaB.setId(mediaA.getId());

    assertFalse(mediaA.equals(mediaB));
    assertNotEquals(mediaA.hashCode(), mediaB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenCreatorMismatch(){
    Media mediaA = new Media("1", "2", "3", Type.MINISERIES);
    mediaA.setSeason("4");
    mediaA.setEpisode("5");
    mediaA.setDescription("6");
    mediaA.setId(7);
    Media mediaB = new Media(mediaA.getTitle(), "not "+mediaA.getCreator(), mediaA.getCategory(), mediaA.getType());
    mediaB.setSeason(mediaA.getSeason());
    mediaB.setEpisode(mediaA.getEpisode());
    mediaB.setDescription(mediaA.getDescription());
    mediaB.setId(mediaA.getId());

    assertFalse(mediaA.equals(mediaB));
    assertNotEquals(mediaA.hashCode(), mediaB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenCategoryMismatch(){
    Media mediaA = new Media("1", "2", "3", Type.MINISERIES);
    mediaA.setSeason("4");
    mediaA.setEpisode("5");
    mediaA.setDescription("6");
    mediaA.setId(7);
    Media mediaB = new Media(mediaA.getTitle(), mediaA.getCreator(), "not "+mediaA.getCategory(), mediaA.getType());
    mediaB.setSeason(mediaA.getSeason());
    mediaB.setEpisode(mediaA.getEpisode());
    mediaB.setDescription(mediaA.getDescription());
    mediaB.setId(mediaA.getId());

    assertFalse(mediaA.equals(mediaB));
    assertNotEquals(mediaA.hashCode(), mediaB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenTypeMismatch(){
    Media mediaA = new Media("1", "2", "3", Type.MINISERIES);
    mediaA.setSeason("4");
    mediaA.setEpisode("5");
    mediaA.setDescription("6");
    mediaA.setId(7);
    Media mediaB = new Media(mediaA.getTitle(), mediaA.getCreator(), mediaA.getCategory(), Type.SINGLE);
    mediaB.setSeason(mediaA.getSeason());
    mediaB.setEpisode(mediaA.getEpisode());
    mediaB.setDescription(mediaA.getDescription());
    mediaB.setId(mediaA.getId());

    assertFalse(mediaA.equals(mediaB));
    assertNotEquals(mediaA.hashCode(), mediaB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenSeasonMismatch(){
    Media mediaA = new Media("1", "2", "3", Type.MINISERIES);
    mediaA.setSeason("4");
    mediaA.setEpisode("5");
    mediaA.setDescription("6");
    mediaA.setId(7);
    Media mediaB = new Media(mediaA.getTitle(), mediaA.getCreator(), mediaA.getCategory(), mediaA.getType());
    mediaB.setSeason("not "+mediaA.getSeason());
    mediaB.setEpisode(mediaA.getEpisode());
    mediaB.setDescription(mediaA.getDescription());
    mediaB.setId(mediaA.getId());

    assertFalse(mediaA.equals(mediaB));
    assertNotEquals(mediaA.hashCode(), mediaB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenEpisodeMismatch(){
    Media mediaA = new Media("1", "2", "3", Type.MINISERIES);
    mediaA.setSeason("4");
    mediaA.setEpisode("5");
    mediaA.setDescription("6");
    mediaA.setId(7);
    Media mediaB = new Media(mediaA.getTitle(), mediaA.getCreator(), mediaA.getCategory(), mediaA.getType());
    mediaB.setSeason(mediaA.getSeason());
    mediaB.setEpisode("not "+mediaA.getEpisode());
    mediaB.setDescription(mediaA.getDescription());
    mediaB.setId(mediaA.getId());

    assertFalse(mediaA.equals(mediaB));
    assertNotEquals(mediaA.hashCode(), mediaB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenDescriptionMismatch(){
    Media mediaA = new Media("1", "2", "3", Type.MINISERIES);
    mediaA.setSeason("4");
    mediaA.setEpisode("5");
    mediaA.setDescription("6");
    mediaA.setId(7);
    Media mediaB = new Media(mediaA.getTitle(), mediaA.getCreator(), mediaA.getCategory(), mediaA.getType());
    mediaB.setSeason(mediaA.getSeason());
    mediaB.setEpisode(mediaA.getEpisode());
    mediaB.setDescription("not "+mediaA.getDescription());
    mediaB.setId(mediaA.getId());

    assertFalse(mediaA.equals(mediaB));
    assertNotEquals(mediaA.hashCode(), mediaB.hashCode());
  }

  @Test
  public void Equals_ReturnsFalse_WhenIdMismatch(){
    Media mediaA = new Media("1", "2", "3", Type.MINISERIES);
    mediaA.setSeason("4");
    mediaA.setEpisode("5");
    mediaA.setDescription("6");
    mediaA.setId(7);
    Media mediaB = new Media(mediaA.getTitle(), mediaA.getCreator(), mediaA.getCategory(), mediaA.getType());
    mediaB.setSeason(mediaA.getSeason());
    mediaB.setEpisode(mediaA.getEpisode());
    mediaB.setDescription(mediaA.getDescription());
    mediaB.setId(1+mediaA.getId());

    assertFalse(mediaA.equals(mediaB));
    assertNotEquals(mediaA.hashCode(), mediaB.hashCode());
  }
}
