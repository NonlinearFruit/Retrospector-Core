package retrospector.core.entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FactoidTest {
  
  @Test
  public void DefaultConstructor_SetsTitle() {
    Factoid fact = new Factoid();

    assertEquals("", fact.getTitle());
  }

  @Test
  public void DefaultConstructor_SetsContent() {
    Factoid fact = new Factoid();

    assertEquals("", fact.getContent());
  }

  @Test
  public void MonoConstructor_SetTitle() {
    String title = "Title";
    
    Factoid fact = new Factoid(title);

    assertEquals(title, fact.getTitle());
  }

  @Test
  public void MonoConstructor_SetContent() {
    String title = "Title";
    
    Factoid fact = new Factoid(title);

    assertEquals("", fact.getContent());
  }

  @Test
  public void BiConstructor_SetTitle() {
    String title = "Title";
    String content = "Content";
    
    Factoid fact = new Factoid(title, content);

    assertEquals(title, fact.getTitle());
  }

  @Test
  public void BiConstructor_SetContent() {
    String title = "Title";
    String content = "Content";
    
    Factoid fact = new Factoid(title, content);

    assertEquals(content, fact.getContent());
  }

  @Test
  public void TitleAccessors_Work() {
    String title = "test";
    Factoid fact = new Factoid();

    fact.setTitle(title);
    String result = fact.getTitle();

    assertEquals(title, result);
  }

  @Test
  public void ContentAccessors_Work() {
    String content = "test";
    Factoid fact = new Factoid();

    fact.setContent(content);
    String result = fact.getContent();

    assertEquals(content, result);
  }

  @Test
  public void IdAccessors_Work() {
    int id = 3;
    Factoid fact = new Factoid();

    fact.setId(id);
    int result = fact.getId();

    assertEquals(id, result);
  }

  @Test
  public void MediaIdAccessors_Work() {
    int id = 3;
    Factoid fact = new Factoid();

    fact.setMediaId(id);
    int result = fact.getMediaId();

    assertEquals(id, result);
  }

  @Test
  public void Equals_False_WhenDifferentTypes() {
    Factoid fact = new Factoid();
    String notFact = "";

    assertFalse(fact.equals(notFact));
  }

  @Test
  public void Equals_Works() {
    Factoid factA = new Factoid("title", "content");
    factA.setId(5);
    factA.setMediaId(6);
    Factoid factB = new Factoid(factA.getTitle(), factA.getContent());
    factB.setId(factA.getId());
    factB.setMediaId(factA.getMediaId());

    assertTrue(factA.equals(factB));
    assertEquals(factA.hashCode(), factB.hashCode());
  }

  @Test
  public void Equals_False_WhenTitlesMismatch() {
    Factoid factA = new Factoid("title", "content");
    factA.setId(5);
    factA.setMediaId(6);
    Factoid factB = new Factoid("not "+factA.getTitle(), factA.getContent());
    factB.setId(factA.getId());
    factB.setMediaId(factA.getMediaId());

    assertFalse(factA.equals(factB));
    assertNotEquals(factA.hashCode(), factB.hashCode());
  }

  @Test
  public void Equals_False_WhenContentsMismatch() {
    Factoid factA = new Factoid("title", "content");
    factA.setId(5);
    factA.setMediaId(6);
    Factoid factB = new Factoid(factA.getTitle(), "not "+factA.getContent());
    factB.setId(factA.getId());
    factB.setMediaId(factA.getMediaId());

    assertFalse(factA.equals(factB));
    assertNotEquals(factA.hashCode(), factB.hashCode());
  }

  @Test
  public void Equals_False_WhenIdsMismatch() {
    Factoid factA = new Factoid("title", "content");
    factA.setId(5);
    factA.setMediaId(6);
    Factoid factB = new Factoid(factA.getTitle(),factA.getContent());
    factB.setId(factA.getId()+1);
    factB.setMediaId(factA.getMediaId());

    assertFalse("Completely different", factA.equals(factB));
    assertNotEquals(factA.hashCode(), factB.hashCode());
  }
  
  @Test
  public void Equals_False_WhenMediaIdsMismatch() {
    Factoid factA = new Factoid("title", "content");
    factA.setId(5);
    factA.setMediaId(6);
    Factoid factB = new Factoid(factA.getTitle(),factA.getContent());
    factB.setId(factA.getId());
    factB.setMediaId(factA.getMediaId()+1);

    assertFalse(factA.equals(factB));
    assertNotEquals(factA.hashCode(), factB.hashCode());
  }
}
