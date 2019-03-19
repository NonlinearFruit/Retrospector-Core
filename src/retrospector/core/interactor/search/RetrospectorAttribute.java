package retrospector.core.interactor.search;

public enum RetrospectorAttribute {
  MediaTitle("T"), MediaCreator("C"), MediaSeason("S"), MediaEpisode("E"), MediaCategory("A"), MediaDescription("P"),
  ReviewRating("#"), ReviewUser("U"), ReviewDate("D"), ReviewContent("R"),
  FactoidTitle("I"), FactoidContent("O");

  private String value;

  private RetrospectorAttribute(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
