package paystation.domain;

import paystation.domain.Strategies.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

/**
 * This is just a client-driver simulation for the PayStation which uses methods
 * derived by the PayStation interface and the PayStationImpl object.
 *
 * Written by: Philip Cappelli
 */
public class PayStationClient {

    public static void main(String[] args) {

        PayStationImpl payStation = new PayStationImpl();
        Scanner input = new Scanner(System.in);
        int selection;

        do {
            displayMenu();
            selection = input.nextInt();

            switch (selection) {
                case 1:
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
                    break;
                case 2:
                    int time = payStation.readDisplay();
                    System.out.println("Current time purchased: " + time);
                    break;
                case 3:
                    Receipt receipt = payStation.buy();
                    System.out.println(receipt.value());
                    break;
                case 4:
                    Map<Integer, Integer> cancelMap = payStation.cancel();
                    System.out.println(cancelMap);
                    break;
                case 5:
                    System.out.println("Enter 1 for AlphaTown, 2 for Betatown, or 3 for GamaTown: ");
                    int userChosenRateStrategy = input.nextInt();

                    if (userChosenRateStrategy == 1) {
                        payStation = new PayStationImpl(new LinearRateStrategy());
                    } else if (userChosenRateStrategy == 2) {
                        System.out.println("Betatown woot woooooot");
                        payStation = new PayStationImpl(new ProgressiveRateStrategy());
                    } else if (userChosenRateStrategy == 3) {
                        payStation = new PayStationImpl(new AlternatingRateStrategy(new LinearRateStrategy(),
                                                                                    new ProgressiveRateStrategy(),
                                                                                    new ClockBasedDecisionStrategy()));
                    }
                    break;
                default:
                    System.out.println("Please select only between options 1 through 4");
            }
        } while (selection != 4);
    }

    private static void displayMenu() {
        System.out.println("1. Deposit Coins");
        System.out.println("2. Display");
        System.out.println("3. Buy Ticket");
        System.out.println("4. Cancel");
        System.out.println("5. Change Rate Strategy");
    }
}












