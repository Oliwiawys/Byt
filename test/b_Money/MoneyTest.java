package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    Currency SEK, DKK, NOK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000, SEK);
        EUR10 = new Money(1000, EUR);
        SEK200 = new Money(20000, SEK);
        EUR20 = new Money(2000, EUR);
        SEK0 = new Money(0, SEK);
        EUR0 = new Money(0, EUR);
        SEKn100 = new Money(-10000, SEK);
    }

    /**
     * Sprawdza, czy metoda getAmount zwraca poprawną ilość pieniędzy w różnych przypadkach.
     */
    @Test
    public void testGetAmount() {
        assertEquals(10000, (int) SEK100.getAmount());
        assertEquals(1000, (int) EUR10.getAmount());
        assertEquals(20000, (int) SEK200.getAmount());
        assertEquals(2000, (int) EUR20.getAmount());
        assertEquals(0, (int) SEK0.getAmount());
        assertEquals(0, (int) EUR0.getAmount());
        assertEquals(-10000, (int) SEKn100.getAmount());
    }

    /**
     * Sprawdza, czy metoda getCurrency zwraca poprawną walutę dla różnych przypadków.
     */
    @Test
    public void testGetCurrency() {
        assertEquals(SEK, SEK100.getCurrency());
        assertEquals(EUR, EUR10.getCurrency());
        assertEquals(SEK, SEK200.getCurrency());
        assertEquals(EUR, EUR20.getCurrency());
        assertEquals(SEK, SEK0.getCurrency());
        assertEquals(EUR, EUR0.getCurrency());
        assertEquals(SEK, SEKn100.getCurrency());
    }

    /**
     * Sprawdza, czy metoda toString zwraca poprawny ciąg znaków dla różnych przypadków.
     */
    @Test
    public void testToString() {
        assertEquals("100.00 SEK", SEK100.toString());
        assertEquals("10.00 EUR", EUR10.toString());
        assertEquals("200.00 SEK", SEK200.toString());
        assertEquals("20.00 EUR", EUR20.toString());
        assertEquals("0.00 SEK", SEK0.toString());
        assertEquals("0.00 EUR", EUR0.toString());
        assertEquals("-100.00 SEK", SEKn100.toString());
    }

    /**
     * Sprawdza, czy metoda universalValue zwraca poprawną wartość w bazowej walucie dla różnych przypadków.
     */
    @Test
    public void testGlobalValue() {
        assertEquals(1500, (int) SEK100.universalValue());
        assertEquals(1500, (int) EUR10.universalValue());
        assertEquals(3000, (int) SEK200.universalValue());
        assertEquals(3000, (int) EUR20.universalValue());
        assertEquals(0, (int) SEK0.universalValue());
        assertEquals(0, (int) EUR0.universalValue());
        assertEquals(-1500, (int) SEKn100.universalValue());
    }

    /**
     * Sprawdza, czy metoda equals poprawnie porównuje obiekty Money dla różnych przypadków.
     */
    @Test
    public void testEqualsMoney() {
        assertTrue(SEK100.equals(EUR10));
        assertFalse(SEK100.equals(SEK200));
        assertFalse(SEK100.equals(EUR20));
        assertFalse(SEK100.equals(SEK0));
        assertFalse(SEK100.equals(EUR0));
        assertFalse(SEK100.equals(SEKn100));
    }

    /**
     * Sprawdza, czy metoda add poprawnie dodaje wartości pieniędzy w różnych walutach.
     */
    @Test
    public void testAdd() {
        assertEquals(new Money(20000, SEK).getAmount(), SEK100.add(EUR10).getAmount());
        assertEquals(new Money(30000, SEK).getAmount(), SEK100.add(SEK200).getAmount());
        assertEquals(new Money(30000, SEK).getAmount(), SEK100.add(EUR20).getAmount());
        assertEquals(new Money(10000, SEK).getAmount(), SEK100.add(SEK0).getAmount());
        assertEquals(new Money(10000, SEK).getAmount(), SEK100.add(EUR0).getAmount());
        assertEquals(new Money(0, SEK).getAmount(), SEK100.add(SEKn100).getAmount());
    }

    /**
     * Sprawdza, czy metoda sub poprawnie odejmuje wartości pieniędzy w różnych walutach.
     */
    @Test
    public void testSub() {
        assertEquals(new Money(0, SEK).getAmount(), SEK100.sub(EUR10).getAmount());
        assertEquals(new Money(-10000, SEK).getAmount(), SEK100.sub(SEK200).getAmount());
        assertEquals(new Money(-10000, SEK).getAmount(), SEK100.sub(EUR20).getAmount());
        assertEquals(new Money(10000, SEK).getAmount(), SEK100.sub(SEK0).getAmount());
        assertEquals(new Money(10000, SEK).getAmount(), SEK100.sub(EUR0).getAmount());
        assertEquals(new Money(20000, SEK).getAmount(), SEK100.sub(SEKn100).getAmount());
    }

    /**
     * Sprawdza, czy metoda isZero poprawnie identyfikuje wartości pieniędzy jako zerowe lub niezerowe.
     */
    @Test
    public void testIsZero() {
        assertFalse(SEK100.isZero());
        assertFalse(EUR10.isZero());
        assertFalse(EUR20.isZero());
        assertTrue(SEK0.isZero());
        assertTrue(EUR0.isZero());
        assertFalse(SEKn100.isZero());
    }

    /**
     * Sprawdza, czy metoda negate poprawnie zmienia znak wartości pieniędzy.
     */
    @Test
    public void testNegate() {
        assertEquals(new Money(-10000, SEK).getAmount(), SEK100.negate().getAmount());
        assertEquals(new Money(-1000, EUR).getAmount(), EUR10.negate().getAmount());
        assertEquals(new Money(-2000, EUR).getAmount(), EUR20.negate().getAmount());
        assertEquals(new Money(0, SEK).getAmount(), SEK0.negate().getAmount());
        assertEquals(new Money(0, EUR).getAmount(), EUR0.negate().getAmount());
        assertEquals(new Money(10000, SEK).getAmount(), SEKn100.negate().getAmount());
    }

    /**
     * Sprawdza, czy metoda compareTo porównuje poprawnie wartości pieniędzy.
     */
    @Test
    public void testCompareTo() {
        assertTrue(SEK100.compareTo(SEK100) == 0);
        assertTrue(SEK100.compareTo(SEK200) < 0);
        assertTrue(SEK200.compareTo(SEK100) > 0);

        assertTrue(EUR10.compareTo(EUR10) == 0);
        assertTrue(EUR10.compareTo(EUR20) < 0);
        assertTrue(EUR20.compareTo(EUR10) > 0);

        assertTrue(EUR20.compareTo(SEK0) > 0);
        assertTrue(EUR10.compareTo(SEK100) == 0);
    }
}
