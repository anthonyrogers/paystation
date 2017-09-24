package paystation.domain.Strategies;

public class AlternatingRateStrategy implements RateStrategy {

    private RateStrategy weekendStrategy, weekdayStrategy, currentState;
    private WeekendDecisionStrategy decisionStrategy;

    public AlternatingRateStrategy(RateStrategy weekdayStrategy, RateStrategy weekendStrategy,
                                   WeekendDecisionStrategy decisionStrategy) {
        this.weekdayStrategy = weekdayStrategy;
        this.weekendStrategy = weekendStrategy;
        this.decisionStrategy = decisionStrategy;
        this.currentState = null;
    }

    public int calculateTime(int amount) {

        if (decisionStrategy.isWeekend()) {
            currentState = weekendStrategy;
        } else {
            currentState = weekdayStrategy;
        }
        return currentState.calculateTime(amount);
    }
}
