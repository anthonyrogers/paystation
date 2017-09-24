package paystation.domain.Strategies;

public class ProgressiveRateStrategy implements RateStrategy {

    public int calculateTime(int amount) {
        int time = 0;
        if (amount >= 350) {
            time = 120 + (amount - 350) / 5;
        } else if (amount >= 150) {
            time = 60 + (amount - 150) * 3 / 10;
        } else {
            time = amount * 2 / 5;
        }
        return time;
    }
}

