/**
* Klasse Movie zur Festlegung von Kundendetails.
* @author Anja Wolf
* @version 1.0
*/

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



class Customer {

  private String name;
  private LocalDate birthday;
  private final Map<Integer, Movie> rentedMovies = new HashMap<>();

  /**
  * Custom-Konstruktor für Class Customer.
  * @param name Name des Kunden
  * @param dayOfBirth Tag
  * @param monthOfBirth Monat
  * @param yearOfBirth Jahreszahl
  */

  public Customer(final String name, final int dayOfBirth, final int monthOfBirth,
      final int yearOfBirth) {
    this.name = name;
    this.birthday = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
  }

  /**
  * Öffentliche Getter für Objektvariablen.
  * @return Name
  * @return Alter
  */

  public String getName() {
    return this.name;
  }

  public int getAge() {
    Period period = Period.between(this.birthday, LocalDate.now());
    return period.getYears();
  }

  /**
  * Methode um Anzahl der vom Kunden ausgeliehenen Filme auszugeben.
  * @return Anzahl ausgeliehene Filme
  */

  public int getRentedMoviesCount() {
    int counter = 0;
    for (Integer i: rentedMovies.keySet()) {
      counter++;
    }
    return counter;
  }

  /**
  * Methode um Liste mit ausgeliehenen Filmen zu bekommen.
  * @return ArrayListe aller ausgeliehenen Filme
  */

  public List<String> getRentedMoviesList() {
    List<String> movieList = new ArrayList<>();
    for (Integer i: rentedMovies.keySet()) {
      Movie mov =  rentedMovies.get(i);
      movieList.add(mov.getTitle());
    }
    return movieList;
  }

  /**
  * Methode um Film auszuleihen.
  * @param serial Integer für HashMap
  * @param movie Film der ausgeliehen werden soll
  * @return true oder false ob Film ausgeliehen wurde
  */

  public boolean rentMovie(final int serial, final Movie movie) {
    if (this.getAge() >= (movie.getFsk()).getAge()) {
      rentedMovies.put(serial, movie);
      return true;
    } else {
      return false;
    }
  }

  /**
  * Methode um Film zurückzugeben.
  * @param serial Integer aus HashMap
  * @return Film der zurückgegeben wurde; null falls nicht enthalten
  */

  public Movie returnMovie(final int serial) {
    for (Integer i: rentedMovies.keySet()) {
      if (i == serial) {
        Movie mov = rentedMovies.get(i);
        rentedMovies.remove(i, mov);
        return mov;
      }
    }
    return null;
  }

  /**
  * Methode um Film zurückzugeben.
  * @param title Titel des Films
  * @return Seriennummer des Films der zurückgegeben wurde, -1 falls nicht enthalten
  */

  public int returnMovie(final String title) {
    for (Integer i: rentedMovies.keySet()) {
      Movie mov =  rentedMovies.get(i);
      if (mov.getTitle() == title) {
        rentedMovies.remove(i, rentedMovies.get(i));
        return i;
      }
    }
    return -1;
  }

  @Override
  public String toString() {
    String newWriting = this.name + " (Alter: " + this.getAge() + ")";
    return newWriting;
  }

}
