package paystation.domain;
import paystation.domain.Strategies.*;
import paystation.domain.Utility.PayStationImpl;

import java.util.Map;
import java.util.Scanner;

/**
 * This is a client-driver simulation for the PayStation which uses methods
 * derived by the PayStation interface and the PayStationImpl object.
 *
 * Written by: Philip Cappelli
 */
public class PayStationClient {
    public static void main(String[] args) {
        PayStationImpl payStation = new PayStationImpl();
        PayStationDelegate delegate = new PayStationDelegate();
        Scanner input = new Scanner(System.in);
        int selection;

        do {
            delegate.displayMenu();
            selection = input.nextInt();

            switch (selection) {
                case 1:
                    delegate.addCoinsBeingInserted(payStation);
                    break;
                case 2:
                    delegate.readDisplay(payStation);
                    break;
                case 3:
                    delegate.printReceipt(payStation);
                    break;
                case 4:
                    Map<Integer, Integer> cancelMap = payStation.cancel();
                    int nickels, dimes, quarters, total;

                    if (!cancelMap.isEmpty()) {
                        nickels = cancelMap.get(5);
                        dimes = cancelMap.get(10);
                        quarters = cancelMap.get(25);
                        total = (5 * nickels) + (10 * dimes) + (25 * quarters);

                        System.out.println("\nNumber of nickels: " + nickels +
                                "\nNumber of dimes:   " + dimes + " \nNumber of quarters: " + quarters);
                        System.out.println("Total change returned: " + total);
                    }
                    break;
                case 5:
                    System.out.println("Enter 1 for AlphaTown, 2 for Betatown, or 3 for GamaTown: ");
                    int userChosenRateStrategy = input.nextInt();

                    // Nested switch statement to take care of rate strategy changes
                    switch (userChosenRateStrategy) {
                        case 1:
                            payStation = new PayStationImpl(new LinearRateStrategy());
                            break;
                        case 2:
                            payStation = new PayStationImpl(new ProgressiveRateStrategy());
                            break;
                        case 3:
                            payStation = new PayStationImpl(new AlternatingRateStrategy(new LinearRateStrategy(),
                                                                                        new ProgressiveRateStrategy(),
                                                                                        new ClockBasedDecisionStrategy()));
                            break;
                        default:
                            System.out.println("Not a valid selection... Returning to menu");
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please select only between options 1 through 6");
            }
        } while (selection != 6);
    }
}












