package paystation.domain;

import java.util.Map;

/**
 * This is just a client-driver simulation for the PayStation which uses methods
 * derived by the PayStation interface and the PayStationImpl object.
 *
 * Written by: Philip Cappelli
 */

public class PayStationClient {

    // Will throw an exception is an incorrect coin is given as input
    public static void main(String[] args) throws IllegalCoinException {

        // Turning on the PayStation (Beep Boop Boop).....
        PayStationImpl payStation = new PayStationImpl();

        // Just wanted to prove that the object is never null
        Map<Integer, Integer> payStation1 = payStation.cancel();

        if (payStation1 == null) {
            System.out.println("PayStation doesn't exist! DO NOT INSERT MONEY!");
            System.exit(0);
        }

        // Giving the PayStation some coins...well attempting to at least
        try {
            payStation.addPayment(5);
            payStation.addPayment(5);
            payStation.addPayment(10);
            payStation.addPayment(25);
            payStation.addPayment(25);
            payStation.buy();
        } catch (IllegalCoinException e) {
            e.printStackTrace();
        }

        // Testing that the map returned is empty after calling cancel()
        System.out.println(payStation.getTotalOfAllCoins());

        // Displaying the total amount of coins paid to the PayStation
        int currentCoinValueBeingReturned = payStation.getTotalOfAllCoins();
        System.out.println("Displaying total amount of coins paid to the PayStation ----->  " + currentCoinValueBeingReturned);

        // Displaying that empty()'s return value equals currentCoinValueBeingReturned()
        int dispensedCoins = payStation.empty();
        System.out.println("Displaying empty()'s return value equals currentCoinValueBeingReturned() -----> " + dispensedCoins);

        // Showing the value of insertedSoFar is 0 after calling empty()
        System.out.println("Showing value of insertedSoFar is 0 after calling empty() -----> " + payStation.getInsertedSoFar());

        // Showing the value of totalBought is also 0 after calling empty()
        System.out.println("Showing value of totalBought is also 0 after calling empty() -----> " + payStation.getTimeBought());
    }
}
