package paystation.domain;

public class ProgressiveRateStrategy implements RateStrategy {

    public int calculateTime(int amount) {

        int time = 0;
        // This algorithm needs to be fixed. Breaks the code.  Verified I am able to switch
        // rate strategy at run-time at least
        if (amount >= 150 + 200) {
            amount -= 350;
            time = 120 + amount / 5;
        } else if (amount >= 150) {
            amount -= 150;
            time = 60 + amount * 3 / 10;
        } else {
            time = amount * 2 / 5;
        }
        return time;
    }
}

