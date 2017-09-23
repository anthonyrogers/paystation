package paystation.domain;

public class LinearRateStrategy implements RateStrategy {
    public int calculateTime(int amount) {
        return amount * 2 / 5;
    }
}
