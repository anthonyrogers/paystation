package paystation.domain;
import paystation.domain.Strategies.*;

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
                    delegate.printCancelMapOfCoins(payStation);
                    break;
                case 5:
                    System.out.println("Enter 1 for AlphaTown, 2 for Betatown, or 3 for GamaTown: ");
                    int userChosenRateStrategy = input.nextInt();

                    if (userChosenRateStrategy == 1) {
                        payStation = new PayStationImpl(new LinearRateStrategy());
                    } else if (userChosenRateStrategy == 2) {
                        payStation = new PayStationImpl(new ProgressiveRateStrategy());
                    } else if (userChosenRateStrategy == 3) {
                        payStation = new PayStationImpl(new AlternatingRateStrategy(new LinearRateStrategy(),
                                                                                    new ProgressiveRateStrategy(),
                                                                                    new ClockBasedDecisionStrategy()));
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












