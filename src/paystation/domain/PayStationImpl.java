package paystation.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */

/**
 * ~~~~~~~~~~~~~~~
 * $$$$$$$$$$$$$$$
 * %%%%%%%%%%%%%%%
 * CIS 3238 Lab 2
 * Philip Cappelli
 * %%%%%%%%%%%%%%%
 * $$$$$$$$$$$$$$$
 * ~~~~~~~~~~~~~~~
 *
 * This assignement added the empty() and modified the cancel() method.  The cancel method
 * will reset the PayStation so it's ready for a new transaction. The method will also return a
 * Map object.  I also added a few other methods to make certains tasks easier depending on what
 * value was needed for the logic.
 */
public class PayStationImpl implements PayStation {

    private int insertedSoFar;
    private int totalOfAllCoins;
    private int timeBought;
    private int fiveCounter, tenCounter, twentyfiveCounter;

    // Global HashMap<Integer, Integer> to keep track of inserted coins
    private HashMap<Integer,Integer> coinMapper = new HashMap<>();

    @Override
    public void addPayment(int coinValue) throws IllegalCoinException {

        /* Using the switch statement to add the values into
           a global HashMap so if the user cancels the transaction, the Map
           already knows what the user entered exactly.
         */
        switch (coinValue) {
            case 5:
                coinMapper.put(5, ++fiveCounter);
                break;
            case 10:
                coinMapper.put(10, ++tenCounter);
                break;
            case 25:
                coinMapper.put(25, ++twentyfiveCounter);
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }

        // Keep track of what the coin value that was inserted so far
        insertedSoFar += coinValue;

        // Calculate the time bought to display on the screen
        timeBought = insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {

        // Create receipt to be returned for the amount purchased
        Receipt r = new ReceiptImpl(timeBought);

        // The total should only be increased after a call to buy()
        totalOfAllCoins += insertedSoFar;
        reset();

        return r;
    }

    /**
     * The cancel method will reset the PayStation for a new transaction.
     * Returns Map<Integer, Integer> which defines the coins returned to
     * the user.  The key is the coin type and the associated value is the number
     * of coins that are returned.  After cancel() is called, the global HashMap will
     * be cleared for the next transaction.
     *
     * @return tempMap
     */
    @Override
    public Map<Integer, Integer> cancel() {

        // Create a tempMap to copy the elements from the global HashMap<Integer, Integer>
        Map<Integer, Integer> tempMap = new HashMap<>(coinMapper);

        // Call reset to zero everything out
        reset();

        // Return the tempMap which contains the values before calling reset()
        return tempMap;
    }

    /**
     * Returns the total amount of money collected by the PayStation since the
     * last call and empties it, setting the total to zero.
     *
     * @return gardaArmorTruckGuyWhoTransportsMoney
     */
    @Override
    public int empty() {

        // Get the total amount of coins and save into an int variables
        int gardaArmorTruckGuyWhoTransportsMoney = getTotalOfAllCoins();

        // Clear everything from the machine out. EVERYTHING MUST GO!
        reset();
        resetTotalCoinAmount();

        // Return the previous total from before reset
        return gardaArmorTruckGuyWhoTransportsMoney;
    }

    /**
     * Returns the value the user as inserted so far in the
     * present transaction.
     *
     * @return insertedSoFar
     */
    public int getInsertedSoFar() {
        return insertedSoFar;
    }

    /**
     * The sume of all the coins inserted at the present time
     *
     * @return totalOfAllCoins
     */
    public int getTotalOfAllCoins() {
        return totalOfAllCoins;
    }

    /**
     * Returns the value of time per the amount of coins inserted
     * into the machine
     *
     * @return timeBought
     */
    public int getTimeBought() {
        return timeBought;
    }

    /**
     * Will reset the PayMachine by settings the amount of time and however
     * many coins were inserted so far. Tis method ill also clear the map
     * for future use.
     */
    private void reset() {
        timeBought = insertedSoFar = 0;
        coinMapper.clear();
    }

    /**
     * Resets the total coin amount
     */
    private void resetTotalCoinAmount() {
        totalOfAllCoins = 0;
    }
}
