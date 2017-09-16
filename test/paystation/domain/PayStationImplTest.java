/**
 * Testcases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package paystation.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public class PayStationImplTest {

    private PayStation ps;

    @Before
    public void setup() {
        ps = new PayStationImpl();
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents() throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents",
                2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void shouldDisplay14MinFor10And25Cents() throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Should display 14 min for 10+25 cents",
                14, ps.readDisplay());
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldReturnCorrectReceiptWhenBuy() throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull("Receipt reference cannot be null",
                receipt);
        assertEquals("Receipt value must be 16 min.",
                16, receipt.value());
    }

    /**
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100c() throws IllegalCoinException {

        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy() throws IllegalCoinException {

        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        // verify that the display reads 0
        assertEquals("Display should have been cleared",
                0, ps.readDisplay());
        // verify that a following buy scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Next add payment should display correct time",
                14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals("Next buy should return valid receipt",
                14, r.value());
        assertEquals("Again, display should be cleared",
                0, ps.readDisplay());
    }

    /**
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel() throws IllegalCoinException {

        ps.addPayment(10);
        ps.cancel();
        assertEquals("Cancel should clear display",
                0, ps.readDisplay());
        ps.addPayment(25);
        assertEquals("Insert after cancel should work",
                10, ps.readDisplay());
    }

    /**
     * Verify that empty will return the total amount of money collected by paystation
     * since the last call
     * @return
     */
    @Test
    public void shouldReturnTotalCoinValue() throws IllegalCoinException {

        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.buy();

        int total = ps.empty();
        assertEquals("Total should be 40", 40, total);
    }

    /**
     * Canceled entry does not add to the amount returned by empty
     */
    @Test
    public void cancelDoesNotAddToAmount() throws IllegalCoinException {

        // Adding some change to the PayStation
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.buy();

        // Getting the total value of the PayStation after adding some
        // change and testing that it contains the right info
        int total = ps.getTotalOfAllCoins();
        assertEquals("Checking that the total value = 40", 40, total);

        // Adding 5 cents and will prompt for a cancel.  Then checking if the total is still
        // the same as above.
        ps.addPayment(5);
        ps.cancel();
        assertEquals("Checking that the total is still 40 after canceling the 5 cent payment", 40, total);
    }

    /**
     * Call to empty resets the total to zero.
     */
    @Test
    public void callToEmptyResetsTheTotalToZero() throws IllegalCoinException {

        // Simulating data intake
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.buy();

        // Testing that there is data present in the first variable to compare against
        int firstTestTotal = ps.getTotalOfAllCoins();
        assertEquals("Checking that the total of all coins is 40 cents", 40, firstTestTotal);

        // Calling empty and comparing against the total coins after the call to empty()...which should be 0.
        ps.empty();
        int secondTestTotal = ps.getTotalOfAllCoins();
        assertEquals("Now checking that the total of all coins is 0 after calling empty()", 0, secondTestTotal);
    }

    /**
     * Call to cancel returns a map containing one coin entered.
     */
    @Test
    public void cancelReturnsOneCoin() throws IllegalCoinException {

        // Creating the first test map to compare with
        Map<Integer, Integer> firstTestMap = new HashMap<>();
        firstTestMap.put(5, 1);

        // Creating second test map to compare against
        ps.addPayment(5);
        Map<Integer, Integer> secondTestMap = ps.cancel();

        // Testing if the objects are equal proving that the map contains one coin entered
        assertEquals("Testing if only one coin was entered but then cancel() was invoked", firstTestMap, secondTestMap);
    }

    /**
     * Call to cancel returns a map containing a mixture of coins entered
     * @throws IllegalCoinException
     */
    @Test
    public void shouldReturnAMapOfTheSpecificCoinsEnteredBeforeCancel() throws IllegalCoinException {

        // Creating a map to put keys in to test against the PayStation map
        Map<Integer, Integer> firstMapToTest = new HashMap<>();
        firstMapToTest.put(5, 1);
        firstMapToTest.put(10, 2);
        firstMapToTest.put(25, 1);

        // Adding elements to PayStation
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);

        // Creating a second map using the return type from cancel()
        Map<Integer, Integer> mapForTest = ps.cancel();

        // Comparing the two objects
        assertEquals(firstMapToTest, mapForTest);
    }

    /**
     * Call to cancel returns a map that does not contain a key for a coin not entered.
     */
    @Test
    public void shouldReturnQuarterAndDimeNotNickel() throws IllegalCoinException {

        // Simulate a live transaction
        ps.addPayment(25);
        ps.buy();
        ps.addPayment(10);
        ps.buy();

        // Create a map to test the returned data
        Map<Integer, Integer> mapTest = new HashMap<>(ps.cancel());
        assertFalse(mapTest.containsKey(5));
    }

    /**
     * Call to cancel clears the map.
     */
    @Test
    public void cancelShouldClearMap() throws IllegalCoinException {

        // Pseudo-transaction actors at work...
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);

        // Just wanted to showcase the pseudo transaction to prove items were stored in there
        // before.  Ran cancel here to clear the map, and then created a new Map to test if
        // the map was empty or not.
        ps.cancel();
        Map<Integer, Integer> cancelMapTest = ps.cancel();
        assertTrue(cancelMapTest.isEmpty());
    }

    /**
     * Call to buy clears the map
     */
    @Test
    public void buyShouldClearMap() throws IllegalCoinException {

        // Transaction kids role playing again...
        ps.addPayment(10);
        ps.addPayment(25);
        ps.buy();

        // I know calling cancel() seems bizarre here...but it returns a map
        // and I want to prove if that map is empty...so thats why I did that.
        Map<Integer, Integer> testHashMap = ps.cancel();
        assertTrue(testHashMap.isEmpty());
    }

}
