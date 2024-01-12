package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}

	/**
	 * test nie powiódł się
	 */
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("payment1", 10, 5, new Money(5000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("payment1"));
		testAccount.removeTimedPayment("payment1");
		assertFalse(testAccount.timedPaymentExists("payment1"));
	}

	/**
	 * test nie powiódł się
	 * @throws AccountDoesNotExistException
	 */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("payment1", 2, 2, new Money(500, SEK), SweBank, "Alice");
		testAccount.tick();
		assertEquals(10000000, testAccount.getBalance().getAmount().intValue());
		testAccount.tick();
		assertEquals(9999500, testAccount.getBalance().getAmount().intValue());
	}

	/**
	 * test nie powiódł się
	 */
	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(3000, SEK));
		assertEquals(9997000, testAccount.getBalance().getAmount().intValue());
	}

	/**
	 * test nie powiódł się
	 */
	@Test
	public void testGetBalance() {
		assertEquals(10000000, testAccount.getBalance().getAmount().intValue());
	}
}
