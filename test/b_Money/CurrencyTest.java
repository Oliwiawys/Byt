package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
    Currency SEK, DKK, NOK, EUR;

    @Before
    public void setUp() throws Exception {
        /* Setup currencies with exchange rates */
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
    }

    /**
     * Sprawdza, czy getName zwraca poprawną nazwę waluty dla różnych walut.
     */
    @Test
    public void testGetName() {
        assertEquals("SEK", SEK.getName());
        assertEquals("DKK", DKK.getName());
        assertEquals("EUR", EUR.getName());
    }

    /**
     * Sprawdza, czy metoda getRate zwraca poprawny kurs wymiany dla różnych walut.
     */
    @Test
    public void testGetRate() {
        assertEquals(0.15, SEK.getRate(), 0);
        assertEquals(0.20, DKK.getRate(), 0);
        assertEquals(1.5, EUR.getRate(), 0);
    }

    /**
     * Sprawdza, czy metoda setRate ustawia poprawny kurs wymiany dla różnych walut.
     */
    @Test
    public void testSetRate() {
        SEK.setRate(0.2);
        assertEquals(0.2, SEK.getRate(), 0);

        DKK.setRate(0.25);
        assertEquals(0.25, DKK.getRate(), 0);

        EUR.setRate(2.00);
        assertEquals(2.00, EUR.getRate(), 0);
    }

    /**
     * Sprawdza, czy metoda universalValue poprawnie przelicza wartość globalną dla różnych walut.
     */
    @Test
    public void testGlobalValue() {
        assertEquals(15, (int) SEK.universalValue(100));
        assertEquals(40, (int) DKK.universalValue(200));
        assertEquals(450, (int) EUR.universalValue(300));
    }

    /**
     * Sprawdza, czy metoda valueInThisCurrency poprawnie przelicza wartość w danej walucie dla różnych walut.
     */
    @Test
    public void testValueInThisCurrency() {
        assertEquals(75, (int) DKK.valueInThisCurrency(100, SEK));
        assertEquals(750, (int) DKK.valueInThisCurrency(100, EUR));

        assertEquals(133, (int) SEK.valueInThisCurrency(100, DKK));
        assertEquals(1000, (int) SEK.valueInThisCurrency(100, EUR));

        assertEquals(10, (int) EUR.valueInThisCurrency(100, SEK));
        assertEquals(13, (int) EUR.valueInThisCurrency(100, DKK));
    }
}
