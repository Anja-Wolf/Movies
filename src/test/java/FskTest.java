import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FskTest {

  @Test
  public void values() {
    Fsk[] expected = new Fsk[] {Fsk.FSK0, Fsk.FSK6, Fsk.FSK12, Fsk.FSK16, Fsk.FSK18};
    assertArrayEquals(expected, Fsk.values());
  }

  @Test
  public void getFskForAge0() {
    for (int i = -1000; i < 6; i++) {
      assertEquals(Fsk.FSK0, Fsk.getFskForAge(i));
    }
  }

  @Test
  public void getFskForAge6() {
    for (int i = 6; i < 12; i++) {
      assertEquals(Fsk.FSK6, Fsk.getFskForAge(i));
    }
  }

  @Test
  public void getFskForAge12() {
    for (int i = 12; i < 16; i++) {
      assertEquals(Fsk.FSK12, Fsk.getFskForAge(i));
    }
  }

  @Test
  public void getFskForAge16() {
    for (int i = 16; i < 18; i++) {
      assertEquals(Fsk.FSK16, Fsk.getFskForAge(i));
    }
  }

  @Test
  public void getFskForAge18() {
    for (int i = 18; i < 1000; i++) {
      assertEquals(Fsk.FSK18, Fsk.getFskForAge(i));
    }
  }

  @Test
  public void ageOkFsk0() {
    for (int i = 0; i < 100; i++) {
      assertTrue(Fsk.FSK0.ageOk(i));
    }
  }

  @Test
  public void ageOkFsk6() {
    for (int i = 0; i < 6; i++) {
      assertFalse(Fsk.FSK6.ageOk(i));
    }
    for (int i = 6; i < 100; i++) {
      assertTrue(Fsk.FSK6.ageOk(i));
    }
  }

  @Test
  public void ageOkFsk12() {
    for (int i = 0; i < 12; i++) {
      assertFalse(Fsk.FSK12.ageOk(i));
    }
    for (int i = 12; i < 100; i++) {
      assertTrue(Fsk.FSK12.ageOk(i));
    }
  }

  @Test
  public void ageOkFsk16() {
    for (int i = 0; i < 16; i++) {
      assertFalse(Fsk.FSK16.ageOk(i));
    }
    for (int i = 16; i < 100; i++) {
      assertTrue(Fsk.FSK16.ageOk(i));
    }
  }

  @Test
  public void ageOkFsk18() {
    for (int i = 0; i < 18; i++) {
      assertFalse(Fsk.FSK18.ageOk(i));
    }
    for (int i = 18; i < 100; i++) {
      assertTrue(Fsk.FSK18.ageOk(i));
    }
  }

  @Test
  public void toStringTest() {
    assertEquals("FSK 0", Fsk.FSK0.toString());
    assertEquals("FSK 6", Fsk.FSK6.toString());
    assertEquals("FSK 12", Fsk.FSK12.toString());
    assertEquals("FSK 16", Fsk.FSK16.toString());
    assertEquals("FSK 18", Fsk.FSK18.toString());
  }
}
