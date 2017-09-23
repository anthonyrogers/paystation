package paystation.domain;

public class One2OneRateStrategy implements RateStrategy {
    public int calculateTime(int amount) {
        return amount;
    }
}
