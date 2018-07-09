import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MovieApp {

  public static void main(String[] args) {
    Moviestore moviestore = new Moviestore(2);
    Set<Movie> movies = new HashSet<>();
    // movies.add(new Movie("Reservoir Dogs", 18));
    // moviestore.addMovie("Reservoir Dogs", 18);
    //
    // movies.add(new Movie("Pulp Fiction", 16));
    // moviestore.addMovie("Pulp Fiction", 16);

    movies.add(new Movie("Reservoir Dogs", 18));
    List<Integer> serials = moviestore.addMovies("Reservoir Dogs", 18, 10);

    System.out.println(movies);
    System.out.println(moviestore.getAvailableMovies());

    if (movies == moviestore.getAvailableMovies()) {
      System.out.println("Yes");
    }

    Movie m0 = new Movie("Jim Knopf", 0);
    System.out.println(m0.equals(null));
  }


}
