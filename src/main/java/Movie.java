/**
* Klasse Movie zur Festlegung von Titel und FSK eines Films.
* @author Anja Wolf
* @version 1.0
*/

class Movie {

  /**
  * Unveränderliche Objektvariablen und damit unveränderliche Klasse.
  */

  private final String title;
  private final Fsk fsk;
  private final int age;

  /**
  * Custom-Konstruktor um einen Film anzulegen.
  * @param title Titel des Films
  * @param age Freigabealter als int
  */

  Movie(String title, int age) {
    this.title = title;
    this.fsk = Fsk.getFskForAge(age);
    this.age = age;
  }

  /**
  * Öffentliche Getter für Objektvariablen.
  */

  public String getTitle() {
    return this.title;
  }

  public Fsk getFsk() {
    return this.fsk;
  }

  /**
  * Methode um bereits vorhandene toString Methode zu überschreiben.
  * @return neue Schreibweise "Titel (FSK alter)"
  */

  @Override
  public String toString() {
    String newWriting = this.title + " (" + fsk.toString() + ")";
    return newWriting;
  }

  /**
  * Methode um bereits vorhandene equals Methode zu überschreiben.
  * @return true oder false ob Film einem anderen Film entspricht
  */

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Movie)) {
      return false;
    }
    Movie mov = (Movie) other;
    return (mov.getFsk() == this.fsk && mov.getTitle() == this.title);
  }


  /**
  * Methode um bereits vorhandene hashCode Methode zu überschreiben.
  * @return neuer Hashcode eines Films wenn gespeichert in Set
    (gleich bei gleichem titel + Fsk)
  */

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + title.hashCode();
    result = 31 * result + age;
    return result;
  }

}
