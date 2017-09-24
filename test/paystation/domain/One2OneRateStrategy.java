package paystation.domain;

import paystation.domain.Strategies.RateStrategy;

public class One2OneRateStrategy implements RateStrategy {
    public int calculateTime(int amount) {
        return amount;
    }
}
