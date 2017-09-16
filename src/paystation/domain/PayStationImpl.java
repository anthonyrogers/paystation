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
 * CIS 3238 Lab 2
 * Philip Cappelli
 *
 * This assignement added the empty() and modified the cancel() method.  The cancel method
 * will reset the PayStation so it's ready for a new transaction. The method will also return a
 * Map object
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int totalOfAllCoins;
    private int timeBought;
    private int fiveCounter, tenCounter, twentyfiveCounter;
    private HashMap<Integer,Integer> coinMapper = new HashMap<>();

    @Override
    public void addPayment(int coinValue) throws IllegalCoinException {
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
        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        totalOfAllCoins += insertedSoFar;
        reset();

        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() {
        Map<Integer, Integer> tempMap = new HashMap<>(coinMapper);
        reset();

        return tempMap;
    }

    @Override
    public int empty() {
        int gardaArmorTruckGuyWhoTransportsMoney = getTotalOfAllCoins();
        reset();
        resetTotalCoinAmount();

        return gardaArmorTruckGuyWhoTransportsMoney;
    }

    //@Override
    public int getInsertedSoFar() {
        return insertedSoFar;
    }

    //@Override
    public int getTotalOfAllCoins() {
        return totalOfAllCoins;
    }

    //@Override
    public int getTimeBought() {
        return timeBought;
    }

    private void reset() {
        timeBought = insertedSoFar = 0;
        coinMapper.clear();
    }

    private void resetTotalCoinAmount() {
        totalOfAllCoins = 0;
    }
}
