import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

  Customer[] customer = new Customer[19];

  private Movie m0 = new Movie("Jim Knopf", 0);
  private Movie m6 = new Movie("Lost in Translation", 6);
  private Movie m12 = new Movie("Krieg der Sterne", 12);
  private Movie m16 = new Movie("Pulp Fiction", 16);
  private Movie m18 = new Movie("Reservoir Dogs", 18);

  /**
   * Initialize customers.
   */
  @Before
  public void initCustomers() {
    LocalDate now = LocalDate.now();
    for (int i = 0; i < 19; i++) {
      customer[i] =
          new Customer("Customer" + i, now.getDayOfMonth(), now.getMonthValue(), now.getYear() - i);
    }
  }

  @Test
  public void getAges() {
    for (int i = 0; i < 19; i++) {
      assertEquals(i, customer[i].getAge());
    }
  }

  @Test
  public void getRentedMoviesCounts() {
    for (int i = 0; i < 19; i++) {
      assertEquals(0, customer[i].getRentedMoviesCount());
    }
  }

  @Test
  public void getRentedMoviesLists() {
    for (int i = 0; i < 19; i++) {
      assertTrue(customer[i].getRentedMoviesList().isEmpty());
    }
  }

  @Test
  public void rentMovies0() {
    for (int i = 0; i < 6; i++) {
      assertTrue(customer[i].rentMovie(1, m0));
      assertFalse(customer[i].rentMovie(2, m6));
      assertFalse(customer[i].rentMovie(3, m12));
      assertFalse(customer[i].rentMovie(4, m16));
      assertFalse(customer[i].rentMovie(5, m18));
      assertEquals(1, customer[i].getRentedMoviesCount());
      Set<String> titles = new HashSet<>();
      titles.add(m0.getTitle());
      assertEquals(titles, new HashSet(customer[i].getRentedMoviesList()));
    }
  }

  @Test
  public void rentMovies6() {
    for (int i = 6; i < 12; i++) {
      assertTrue(customer[i].rentMovie(1, m0));
      assertTrue(customer[i].rentMovie(2, m6));
      assertFalse(customer[i].rentMovie(3, m12));
      assertFalse(customer[i].rentMovie(4, m16));
      assertFalse(customer[i].rentMovie(5, m18));
      assertEquals(2, customer[i].getRentedMoviesCount());
      Set<String> titles = new HashSet<>();
      titles.add(m0.getTitle());
      titles.add(m6.getTitle());
      assertEquals(titles, new HashSet(customer[i].getRentedMoviesList()));
    }
  }

  @Test
  public void rentMovies12() {
    for (int i = 12; i < 16; i++) {
      assertTrue(customer[i].rentMovie(1, m0));
      assertTrue(customer[i].rentMovie(2, m6));
      assertTrue(customer[i].rentMovie(3, m12));
      assertFalse(customer[i].rentMovie(4, m16));
      assertFalse(customer[i].rentMovie(5, m18));
      assertEquals(3, customer[i].getRentedMoviesCount());
      Set<String> titles = new HashSet<>();
      titles.add(m0.getTitle());
      titles.add(m6.getTitle());
      titles.add(m12.getTitle());
      assertEquals(titles, new HashSet(customer[i].getRentedMoviesList()));
    }
  }

  @Test
  public void rentMovies16() {
    for (int i = 16; i < 18; i++) {
      assertTrue(customer[i].rentMovie(1, m0));
      assertTrue(customer[i].rentMovie(2, m6));
      assertTrue(customer[i].rentMovie(3, m12));
      assertTrue(customer[i].rentMovie(4, m16));
      assertFalse(customer[i].rentMovie(5, m18));
      assertEquals(4, customer[i].getRentedMoviesCount());
      Set<String> titles = new HashSet<>();
      titles.add(m0.getTitle());
      titles.add(m6.getTitle());
      titles.add(m12.getTitle());
      titles.add(m16.getTitle());
      assertEquals(titles, new HashSet(customer[i].getRentedMoviesList()));
    }
  }

  @Test
  public void rentMovies18() {
    for (int i = 18; i < 19; i++) {
      assertTrue(customer[i].rentMovie(1, m0));
      assertTrue(customer[i].rentMovie(2, m6));
      assertTrue(customer[i].rentMovie(3, m12));
      assertTrue(customer[i].rentMovie(4, m16));
      assertTrue(customer[i].rentMovie(5, m18));
      assertEquals(5, customer[i].getRentedMoviesCount());
      Set<String> titles = new HashSet<>();
      titles.add(m0.getTitle());
      titles.add(m6.getTitle());
      titles.add(m12.getTitle());
      titles.add(m16.getTitle());
      titles.add(m18.getTitle());
      assertEquals(titles, new HashSet(customer[i].getRentedMoviesList()));
    }
  }

  @Test
  public void returnMovieSerialNothingRented() {
    assertNull(customer[0].returnMovie(113));
  }

  @Test
  public void returnMovieSerialNotRented() {
    customer[0].rentMovie(1, m0);
    assertNull(customer[0].returnMovie(113));
  }

  @Test
  public void returnMovieSerial0() {
    customer[0].rentMovie(1, m0);
    Movie returnedM0 = customer[0].returnMovie(1);
    assertNotNull(returnedM0);
    assertEquals(0, customer[0].getRentedMoviesCount());
  }

  @Test
  public void returnMovieSerial18() {
    customer[18].rentMovie(1, m0);
    customer[18].rentMovie(2, m6);
    customer[18].rentMovie(3, m12);
    customer[18].rentMovie(4, m16);
    customer[18].rentMovie(5, m18);
    assertEquals(5, customer[18].getRentedMoviesCount());

    Movie returnedM0 = customer[18].returnMovie(1);
    assertNotNull(returnedM0);
    assertEquals(4, customer[18].getRentedMoviesCount());

    Movie returnedM6 = customer[18].returnMovie(2);
    assertNotNull(returnedM6);
    assertEquals(3, customer[18].getRentedMoviesCount());

    Movie returnedM12 = customer[18].returnMovie(3);
    assertNotNull(returnedM12);
    assertEquals(2, customer[18].getRentedMoviesCount());

    Movie returnedM16 = customer[18].returnMovie(4);
    assertNotNull(returnedM16);
    assertEquals(1, customer[18].getRentedMoviesCount());

    Movie returnedM18 = customer[18].returnMovie(5);
    assertNotNull(returnedM18);
    assertEquals(0, customer[18].getRentedMoviesCount());
  }

  @Test
  public void returnMovieTitleNothingRented() {
    assertEquals(-1, customer[0].returnMovie("Halligalli"));
  }

  @Test
  public void returnMovieTitleNotRented() {
    customer[0].rentMovie(1, m0);
    assertEquals(-1, customer[0].returnMovie("Halligalli"));
  }

  @Test
  public void returnMovieTitle0() {
    customer[0].rentMovie(1, m0);
    int returnedM0 = customer[0].returnMovie(m0.getTitle());
    assertEquals(1, returnedM0);
    assertEquals(0, customer[0].getRentedMoviesCount());
  }

  @Test
  public void returnMovieTitle18() {
    customer[18].rentMovie(1, m0);
    customer[18].rentMovie(2, m6);
    customer[18].rentMovie(3, m12);
    customer[18].rentMovie(4, m16);
    customer[18].rentMovie(5, m18);
    assertEquals(5, customer[18].getRentedMoviesCount());

    int returnedM0 = customer[18].returnMovie(m0.getTitle());
    assertEquals(1, returnedM0);
    assertEquals(4, customer[18].getRentedMoviesCount());

    int returnedM6 = customer[18].returnMovie(m6.getTitle());
    assertEquals(2, returnedM6);
    assertEquals(3, customer[18].getRentedMoviesCount());

    int returnedM12 = customer[18].returnMovie(m12.getTitle());
    assertEquals(3, returnedM12);
    assertEquals(2, customer[18].getRentedMoviesCount());

    int returnedM16 = customer[18].returnMovie(m16.getTitle());
    assertEquals(4, returnedM16);
    assertEquals(1, customer[18].getRentedMoviesCount());

    int returnedM18 = customer[18].returnMovie(m18.getTitle());
    assertEquals(5, returnedM18);
    assertEquals(0, customer[18].getRentedMoviesCount());
  }

  @Test
  public void toStrings() {
    for (int i = 0; i < 19; i++) {
      assertEquals("Customer" + i + " (Alter: " + i + ")", customer[i].toString());
    }
  }
}
