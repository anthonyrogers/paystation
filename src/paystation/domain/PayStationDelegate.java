package paystation.domain;

import paystation.domain.Utility.IllegalCoinException;
import paystation.domain.Utility.PayStation;
import paystation.domain.Utility.Receipt;

import java.util.Map;
import java.util.Scanner;

public class PayStationDelegate {
    public  void displayMenu() {
        System.out.println("\n|  Thank you for choosing PayStation!   |");
        System.out.println("| Select an option below to get started | \n");
        System.out.println("1. Deposit Coins");
        System.out.println("2. Display");
        System.out.println("3. Buy Ticket");
        System.out.println("4. Cancel");
        System.out.println("5. Change Rate Strategy");
        System.out.println("6. Shutdown PayStation");
    }

    public void readDisplay(PayStation payStation) {
        int time = payStation.readDisplay();
        System.out.println("Current time purchased: " + time);
    }

    public  void addCoinsBeingInserted(PayStation payStation) {
        Scanner input = new Scanner(System.in);
        int coinsBeingAdded;
        boolean stillPaying = true;

        while (stillPaying) {
            System.out.println("PayStation only accepts 5, 10, 25 cent values");
            System.out.println("Enter 0 when complete ");
            coinsBeingAdded = input.nextInt();

            if (coinsBeingAdded == 0) {
                stillPaying = false;
            } else {
                try {
                    payStation.addPayment(coinsBeingAdded);
                } catch (IllegalCoinException e) {
                    System.out.println("Invalid coin, ill let it slide this one time...");
                }
            }
        }
    }

    public void printReceipt(PayStation payStation) {
        Receipt receipt = payStation.buy();
        System.out.println("\n$$$ PayStation Parking Receipt $$$");
        System.out.println("---------- Time: " + receipt.value() + " mins ----------");
        System.out.println("     :) Have a Great Day! :)\n");
    }

    public void printCancelMapOfCoins(PayStation payStation) {
        Map<Integer, Integer> cancelMap = payStation.cancel();
        System.out.println(cancelMap);
    }
}
