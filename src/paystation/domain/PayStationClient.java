package paystation.domain;

public class PayStationClient {

    public static void main(String[] args) throws IllegalCoinException {

        // Turning on the PayStation (Beep Boop Boop).....
        PayStation payStation = new PayStationImpl();

        // Giving the PayStation some coins...well attempting to at least
        try {
            payStation.addPayment(5);
            payStation.addPayment(5);
            payStation.addPayment(10);
            payStation.addPayment(25);
            payStation.addPayment(25);
        } catch (IllegalCoinException e) {
            e.printStackTrace();
        }

        System.out.println(payStation.cancel());

        // Displaying the total amount of coins paid to the PayStation
        int currentCoinValueBeingReturned = payStation.getTotalOfAllCoins();
        System.out.println("Displaying the total amount of coins paid to the PayStation ----->  " + currentCoinValueBeingReturned);

        // Displaying that empty()'s return value equals currentCoinValueBeingReturned()
        int dispensedCoins = payStation.empty();
        System.out.println("Displaying that empty()'s return value equals currentCoinValueBeingReturned() -----> " + dispensedCoins);

        // Showing the value of insertedSoFar is 0 after calling empty()
        System.out.println("Showing the value of insertedSoFar is 0 after calling empty() -----> " + payStation.getIsertedSoFar());

        // Showing the value of totalBought is also 0 after calling empty()
        System.out.println("Showing the value of totalBought is also 0 after calling empty() -----> " + payStation.getTimeBought());
    }
}
