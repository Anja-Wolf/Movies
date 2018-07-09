/**
* Enum Klasse zur Festlegung verschiedener Altersfreigaben von Filmen.
* @author Anja Wolf
* @version 1.0
*/

public enum Fsk {
  FSK0(0), FSK6(6), FSK12(12), FSK16(16), FSK18(18);

  private final int age;

  Fsk(int age) {
    this.age = age;
  }

  public int getAge() {
    return this.age;
  }

  /**
  * Methode um Alter zu überprüfen.
  * @param age Alter wird eingegeben
  * @return boolean true oder false ob Alter für Altersfreigabe zulässig ist
  */

  public boolean ageOk(final int age) {
    if (age >= this.getAge()) {
      return true;
    } else {
      return false;
    }
  }

  /**
  * Methode, um höchste, für das übergebene Alter zulässigen Altersfreigabe zu berechnen.
  * @param age Alter
  * @return höchstmögliche FSK
  */

  public static Fsk getFskForAge(final int age) {
    if (age < 6) {
      return FSK0;
    }
    if (age < 12) {
      return FSK6;
    }
    if (age < 16) {
      return FSK12;
    }
    if (age < 18) {
      return FSK16;
    } else {
      return FSK18;
    }
  }

  /**
  * Methode um bereits vorhandene toString Methode zu überschreiben.
  * @return neue Schreibweise FSK mit Leerzeichen zw. FSK und Alter
  */

  @Override
  public String toString() {
    switch (this) {
      case FSK0:
        return "FSK 0";
      case FSK6:
        return "FSK 6";
      case FSK12:
        return "FSK 12";
      case FSK16:
        return "FSK 16";
      case FSK18:
        return "FSK 18";
      default:
        return "";
    }
  }
}
