package paystation.domain;

import paystation.domain.Strategies.WeekendDecisionStrategy;

public class FixedDecisionStrategy implements WeekendDecisionStrategy {
    private boolean isWeekend;

    public FixedDecisionStrategy(boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public boolean isWeekend() {
        return isWeekend;
    }
}
