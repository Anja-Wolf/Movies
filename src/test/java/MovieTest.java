import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MovieTest {

  private Movie m0 = new Movie("Jim Knopf", 0);
  private Movie m6 = new Movie("Lost in Translation", 6);
  private Movie m12 = new Movie("Krieg der Sterne", 12);
  private Movie m16 = new Movie("Pulp Fiction", 16);
  private Movie m18 = new Movie("Reservoir Dogs", 18);

  @Test
  public void movie0() {
    assertEquals("Jim Knopf", m0.getTitle());
    assertEquals(Fsk.FSK0, m0.getFsk());
    assertEquals("Jim Knopf (FSK 0)", m0.toString());
  }

  @Test
  public void movie6() {
    assertEquals("Lost in Translation", m6.getTitle());
    assertEquals(Fsk.FSK6, m6.getFsk());
    assertEquals("Lost in Translation (FSK 6)", m6.toString());
  }

  @Test
  public void movie12() {
    assertEquals("Krieg der Sterne", m12.getTitle());
    assertEquals(Fsk.FSK12, m12.getFsk());
    assertEquals("Krieg der Sterne (FSK 12)", m12.toString());
  }

  @Test
  public void movie16() {
    assertEquals("Pulp Fiction", m16.getTitle());
    assertEquals(Fsk.FSK16, m16.getFsk());
    assertEquals("Pulp Fiction (FSK 16)", m16.toString());
  }

  @Test
  public void movie18() {
    assertEquals("Reservoir Dogs", m18.getTitle());
    assertEquals(Fsk.FSK18, m18.getFsk());
    assertEquals("Reservoir Dogs (FSK 18)", m18.toString());
  }

  @Test
  public void equalsNull() {
    assertFalse(m0.equals(null));
  }

  @Test
  public void equalsOtherClass() {
    assertFalse(m0.equals("Toller Film"));
  }

  @Test
  public void equalsOtherTitle() {
    Movie m1 = new Movie("Anderer Titel", 0);
    assertFalse(m0.equals(m1));
    assertFalse(m1.equals(m0));
  }

  @Test
  public void equalsOtherFsk() {
    Movie m1 = new Movie(m0.getTitle(), 6);
    assertFalse(m0.equals(m1));
    assertFalse(m1.equals(m0));
  }

  @Test
  public void equalsSame() {
    assertTrue(m0.equals(new Movie(m0.getTitle(), 0)));
    assertTrue(new Movie(m0.getTitle(), 0).equals(m0));

    assertTrue(m6.equals(new Movie(m6.getTitle(), 6)));
    assertTrue(new Movie(m6.getTitle(), 6).equals(m6));

    assertTrue(m12.equals(new Movie(m12.getTitle(), 12)));
    assertTrue(new Movie(m12.getTitle(), 12).equals(m12));

    assertTrue(m16.equals(new Movie(m16.getTitle(), 16)));
    assertTrue(new Movie(m16.getTitle(), 16).equals(m16));

    assertTrue(m18.equals(new Movie(m18.getTitle(), 18)));
    assertTrue(new Movie(m18.getTitle(), 18).equals(m18));
  }

  @Test
  public void equalsOther() {
    assertFalse(m0.equals(m6));
    assertFalse(m6.equals(m0));

    assertFalse(m6.equals(m12));
    assertFalse(m12.equals(m6));

    assertFalse(m12.equals(m16));
    assertFalse(m16.equals(m12));

    assertFalse(m16.equals(m18));
    assertFalse(m18.equals(m16));

    assertFalse(m18.equals(m0));
    assertFalse(m0.equals(m18));
  }

}
