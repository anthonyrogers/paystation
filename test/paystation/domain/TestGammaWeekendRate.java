package paystation.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestGammaWeekendRate {
    @Test
    public void shouldDisplay120MinFor350cent() {
        RateStrategy rateStrategy = new AlternatingRateStrategy(new LinearRateStrategy(),
                                                                new ProgressiveRateStrategy());
        assertEquals(120, rateStrategy.calculateTime(350));
    }
}
