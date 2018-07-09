import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class MoviestoreTest {

  LocalDate now = LocalDate.now();
  private Moviestore moviestore;

  @Before
  public void init() {
    moviestore = new Moviestore(2);
  }

  @Test
  public void getMaxRentableMoviesByCustomer() {
    assertEquals(2, moviestore.getMaxRentableMoviesByCustomer());
    assertEquals(1, new Moviestore(-10).getMaxRentableMoviesByCustomer());
  }

  @Test
  public void setMaxRentableMoviesByCustomer() {
    moviestore.setMaxRentableMoviesByCustomer(10);
    assertEquals(10, moviestore.getMaxRentableMoviesByCustomer());
    moviestore.setMaxRentableMoviesByCustomer(-10);
    assertEquals(10, moviestore.getMaxRentableMoviesByCustomer());
  }

  @Test
  public void getAvailableMovies() {
    assertEquals(new HashSet<Movie>(), moviestore.getAvailableMovies());
  }

  @Test
  public void getCustomer() {
    assertNull(moviestore.getCustomer(10));
  }

  @Test
  public void addMovie() {
    Set<Movie> movies = new HashSet<>();
    movies.add(new Movie("Reservoir Dogs", 18));
    moviestore.addMovie("Reservoir Dogs", 18);
    assertEquals(movies, moviestore.getAvailableMovies());
    movies.add(new Movie("Pulp Fiction", 16));
    moviestore.addMovie("Pulp Fiction", 16);
    assertEquals(movies, moviestore.getAvailableMovies());
    movies.add(new Movie("Krieg der Sterne", 12));
    moviestore.addMovie("Krieg der Sterne", 12);
    assertEquals(movies, moviestore.getAvailableMovies());
  }

  @Test
  public void addMovies() {
    Set<Movie> movies = new HashSet<>();
    movies.add(new Movie("Reservoir Dogs", 18));
    List<Integer> serials = moviestore.addMovies("Reservoir Dogs", 18, 10);
    assertEquals(10, serials.size());
    assertEquals(movies, moviestore.getAvailableMovies());
    movies.add(new Movie("Pulp Fiction", 16));
    moviestore.addMovie("Pulp Fiction", 16);
    assertEquals(movies, moviestore.getAvailableMovies());
    movies.add(new Movie("Krieg der Sterne", 12));
    moviestore.addMovies("Krieg der Sterne", 12, 100);
    assertEquals(movies, moviestore.getAvailableMovies());
  }

  @Test
  public void rentMovieUserDoesNotExist() {
    assertEquals(Result.UserDoesNotExist, moviestore.rentMovie(1, ""));
  }

  @Test
  public void rentMovieUserTooYoung() {
    int userId =
        moviestore.addCustomer("Baby", now.getDayOfMonth(), now.getMonthValue(), now.getYear());
    moviestore.addMovie("Reservoir Dogs", 18);
    assertEquals(Result.UserTooYoung, moviestore.rentMovie(userId, "Reservoir Dogs"));
  }

  @Test
  public void rentUserMaximumMoviesReached() {
    moviestore.addMovie("Reservoir Dogs", 18);
    moviestore.addMovie("Pulp Fiction", 16);
    moviestore.addMovie("Krieg der Sterne", 12);
    int userId =
        moviestore.addCustomer(
            "Frank Castle", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    assertEquals(Result.Success, moviestore.rentMovie(userId, "Reservoir Dogs"));
    assertEquals(Result.Success, moviestore.rentMovie(userId, "Pulp Fiction"));
    assertEquals(Result.UserMaximumMoviesReached, moviestore.rentMovie(userId, "Krieg der Sterne"));
  }

  @Test
  public void rentMovieNotAvailable1() {
    int userId =
        moviestore.addCustomer("Baby", now.getDayOfMonth(), now.getMonthValue(), now.getYear());
    assertEquals(Result.MovieNotAvailable, moviestore.rentMovie(userId, "Reservoir Dogs"));
  }

  @Test
  public void rentMovieNotAvailable2() {
    int userId1 =
        moviestore.addCustomer(
            "Frank Castle", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    int userId2 =
        moviestore.addCustomer(
            "Billy Russo", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    moviestore.addMovie("Reservoir Dogs", 18);
    assertEquals(Result.Success, moviestore.rentMovie(userId1, "Reservoir Dogs"));
    assertEquals(Result.MovieNotAvailable, moviestore.rentMovie(userId2, "Reservoir Dogs"));
  }

  @Test
  public void rentMovieAvailable() {
    int userId1 =
        moviestore.addCustomer(
            "Frank Castle", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    int userId2 =
        moviestore.addCustomer(
            "Billy Russo", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    moviestore.addMovies("Reservoir Dogs", 18, 2);
    assertEquals(Result.Success, moviestore.rentMovie(userId1, "Reservoir Dogs"));
    assertEquals(Result.Success, moviestore.rentMovie(userId2, "Reservoir Dogs"));
  }

  @Test
  public void returnMovieNotRented1() {
    int userId =
        moviestore.addCustomer(
            "Frank Castle", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    assertEquals(Result.MovieNotRented, moviestore.returnMovie(4));
  }

  @Test
  public void returnMovieNotRented2() {
    int userId =
        moviestore.addCustomer(
            "Frank Castle", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    int movieserial = moviestore.addMovie("Reservoir Dogs", 18);
    assertEquals(Result.MovieNotRented, moviestore.returnMovie(movieserial));
  }

  @Test
  public void returnMovie() {
    int userId =
        moviestore.addCustomer(
            "Frank Castle", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    int movieserial = moviestore.addMovie("Reservoir Dogs", 18);
    moviestore.rentMovie(userId, "Reservoir Dogs");
    assertEquals(Result.Success, moviestore.returnMovie(movieserial));
  }

  @Test
  public void returnUserDoesNotExistTitle() {
    assertEquals(Result.UserDoesNotExist, moviestore.returnMovie(2, "Kill Bill vol. 1"));
  }

  @Test
  public void returnMovieNotRentedTitle1() {
    int userId =
        moviestore.addCustomer(
            "Frank Castle", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    assertEquals(Result.MovieNotRented, moviestore.returnMovie(userId, "Kill Bill vol. 1"));
  }


  @Test
  public void returnMovieTitle() {
    int userId =
        moviestore.addCustomer(
            "Frank Castle", now.getDayOfMonth(), now.getMonthValue(), now.getYear() - 35);
    int movieserial = moviestore.addMovie("Reservoir Dogs", 18);
    moviestore.rentMovie(userId, "Reservoir Dogs");
    assertEquals(Result.Success, moviestore.returnMovie(userId, "Reservoir Dogs"));
  }

}
