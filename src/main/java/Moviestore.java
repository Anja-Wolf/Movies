/**
* Klasse MovieStore.
* @author Anja Wolf
* @version 1.0
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



class Moviestore {

  private static int lastMovieSerial = 0;
  private static int lastUserId = 0;

  private Map<Integer, Movie> availableMovies = new HashMap<>(); //Key SerialID
  private Map<Integer, Movie> rentedMovies = new HashMap<>(); //Key SerialID
  private Map<Integer, Customer> customers = new HashMap<>(); //Key UserID

  /**
  * statische Methode um Zähler zu inkrementieren.
  */

  static int getNextMovieSerial() {
    lastMovieSerial++;
    int nextMovieSerial = lastMovieSerial;
    return nextMovieSerial;
  }

  static int getNextUserId() {
    lastUserId++;
    int nextUserId = lastUserId;
    return nextUserId;
  }

  /**
  * Variable max Anzahl an Filmen die ein Kunde ausleihen kann.
  * Setter (ist Variable <= 0 dann bleibt dieser gleich) & Getter
  */

  private int maxRentableMoviesByCustomer;

  public void setMaxRentableMoviesByCustomer(int a) {
    if (a <= 0) {
      return;
    } else {
      this.maxRentableMoviesByCustomer = a;
    }
  }

  public int getMaxRentableMoviesByCustomer() {
    return this.maxRentableMoviesByCustomer;
  }

  /**
  * Custom Konstruktor für Klasse Movie Store mit Variable maxRentableMoviesByCustomer.
  * @param maxRentableMoviesByCustomer
    maximal Anzahl an Filmen die pro Kunde ausgeliehen werden können
    Ist Parameter <= 0, wird Anzahl max ausleihbarer Filme auf 1 gesetzt
  */

  public Moviestore(int maxRentableMoviesByCustomer) {
    if (maxRentableMoviesByCustomer <= 0) {
      this.maxRentableMoviesByCustomer = 1;
    } else {
      this.maxRentableMoviesByCustomer = maxRentableMoviesByCustomer;
    }
  }

  /**
  * Getter um Movie Id mit Film Titel zu erhalten.
  * @param title Titel des Films
  * @return ID des Films; wenn nicht vorhanden: -1
  */

  private int getAvailableMovieId(final String title) {
    for (Integer i: availableMovies.keySet()) {
      Movie mov =  availableMovies.get(i);
      if (mov.getTitle() == title) {
        return i;
      }
    }
    return -1;
  }

  /**
  * Getter um Liste aller verfügbaren Filme zu bekommen.
  * @return neue Liste mit allen neuen Filmen
  */

  public Set<Movie> getAvailableMovies() {
    Set<Movie> movies = new HashSet<>();
    for (Integer i: availableMovies.keySet()) {
      movies.add(availableMovies.get(i));
    }
    return movies;
  }

  /**
  * Getter um Customer mit Id zu bekommen.
  * @param userId ID des Users
  * @return Customer
  */

  public Customer getCustomer(final int userId) {
    for (Integer i: customers.keySet()) {
      if (i == userId) {
        return customers.get(i);
      }
    }
    return null;
  }

  /**
  * Methode um neuen Film hinzuzufügen.
  * @param title Titel des Films
  * @param fskAge Fsk des Films
  * @return serialID des neu hinzugefügten Films in der HashMap
  */

  public int addMovie(final String title, final int fskAge) {
    Movie mov = new Movie(title, fskAge);
    int serId = getNextMovieSerial();
    availableMovies.put(serId, mov);
    return serId;
  }

  /**
  * Methode um mehrere gleiche Filme hinzuzufügen.
  * @param title Titel des Films
  * @param fskAge Fsk des Films
  * @param count Anzahl wie oft dieser Film zur Liste verfügbarer Filme hinzugefügt werden soll
  * @return Liste mit allen SerialId's die neu hinzugefügt wurden
  */

  public List<Integer> addMovies(final String title, final int fskAge,
      final int count) {
    List<Integer> serials = new ArrayList<>();
    Movie mov = new Movie(title, fskAge);
    for (int i = 0; i < count; i++) {
      int serId = getNextMovieSerial();
      availableMovies.put(serId, mov);
      serials.add(serId);
    }
    return serials;
  }

  /**
  * Methode um Customer hinzuzufügen.
  * @param name Name des Kundens
  * @param dayOfBirth Geburtstag
  * @param monthOfBirth Geburtsmonat
  * @param yearOfBirth Geburtsjahr
  * @return UserId des neu hinzugefügten Kundens (Key in Hashmap)
  */

  public int addCustomer(final String name, final int dayOfBirth,
      final int monthOfBirth, final int yearOfBirth) {
    Customer c = new Customer(name, dayOfBirth, monthOfBirth, yearOfBirth);
    int usId = getNextUserId();
    customers.put(usId, c);
    return usId;
  }

  /**
  * Methode um Film auszuleihen.
  * @param userId ID des Users der Film ausleihen soll
  * @param movieTitle Titel des Films der ausgeliehen werden soll
  * @return Result (enum Klasse)
  */

  public Result rentMovie(final int userId, final String movieTitle) {
    if (!customers.containsKey(userId)) {
      return Result.UserDoesNotExist;
    }
    Customer c = customers.get(userId);
    if (c.getRentedMoviesCount() == this.maxRentableMoviesByCustomer) {
      return Result.UserMaximumMoviesReached;
    }
    int movId = getAvailableMovieId(movieTitle);
    if (!availableMovies.containsKey(movId)) {
      return Result.MovieNotAvailable;
    }
    for (Integer i: availableMovies.keySet()) {
      Movie mov = availableMovies.get(i);
      if (!c.rentMovie(i, mov)) {
        return Result.UserTooYoung;
      }
      if (mov.getTitle() == movieTitle) {
        availableMovies.remove(i, mov);
        rentedMovies.put(i, mov);
        return Result.Success;
      }
    }
    return Result.Success;
  }

  /**
  * Methode um Film zurückzugeben.
  * @param movieSerial ID des Films der zurückgegeben werden soll.
  * @return Result (enum Klasse)
  */

  public Result returnMovie(final int movieSerial) {
    if (!rentedMovies.containsKey(movieSerial)) {
      return Result.MovieNotRented;
    }
    Movie mov = rentedMovies.get(movieSerial);
    availableMovies.put(movieSerial, rentedMovies.get(movieSerial));
    rentedMovies.remove(movieSerial, rentedMovies.get(movieSerial));
    for (Integer i: customers.keySet()) {
      if (getCustomer(i).returnMovie(movieSerial) == mov) {
        return Result.Success;
      }
    }
    return Result.MovieRentedButUserNotFound;
  }

  /**
  * Methode um Film zurückzugeben.
  * @param userId ID des Users der Film ausliehen hatte
  * @param movieTitle Titel des Films der zurückgegeben werden soll
  * @return Result (enum Klasse)
  */

  public Result returnMovie(final int userId, final String movieTitle) {
    Customer c = customers.get(userId);
    if (c == null) {
      return Result.UserDoesNotExist;
    }
    if (!c.getRentedMoviesList().contains(movieTitle)) {
      return Result.MovieNotRented;
    }
    int serial;
    serial = getAvailableMovieId(movieTitle);
    availableMovies.put(serial, rentedMovies.get(serial));
    rentedMovies.remove(serial, rentedMovies.get(serial));
    return Result.Success;
  }

}
