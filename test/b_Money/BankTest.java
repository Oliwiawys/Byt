package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	/**
	 * test nie powiódł się
	 * @throws AccountExistsException
	 * @throws AccountDoesNotExistException
	 */
	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Alice");
		assertThrows(AccountExistsException.class, () -> SweBank.openAccount("Alice"));
	}

	/**
	 * test nie powiódł się
	 * @throws AccountDoesNotExistException
	 */
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		assertEquals(1000, (int) SweBank.getBalance("Ulrika"));
	}

	/**
	 * test nie powiódł się
	 * @throws AccountDoesNotExistException
	 */
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		SweBank.withdraw("Ulrika", new Money(500, SEK));
		assertEquals(500, (int) SweBank.getBalance("Ulrika"));
	}

	/**
	 * test nie powiódł się
	 * @throws AccountDoesNotExistException
	 */
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(0, (int) SweBank.getBalance("Ulrika"));
	}

	/**
	 * test nie powiódł się
	 * @throws AccountDoesNotExistException
	 */
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		SweBank.transfer("Ulrika", Nordea, "Bob", new Money(500, SEK));
		assertEquals(500, (int) SweBank.getBalance("Ulrika"));
		assertEquals(500, (int) Nordea.getBalance("Bob"));
	}

	/**
	 * test nie powiódł się
	 * @throws AccountDoesNotExistException
	 */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		SweBank.addTimedPayment("Ulrika", "payment1", 2, 2, new Money(500, SEK), Nordea, "Bob");
		SweBank.tick();
		SweBank.tick();

		assertEquals(500, (int) SweBank.getBalance("Ulrika"));
		assertEquals(500, (int) Nordea.getBalance("Bob"));
	}
}
