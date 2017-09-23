package paystation.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestGammaWeekdayRate {
    @Test
    public void shouldDisplay120MinFor300Cent() {
        RateStrategy rateStrategy = new AlternatingRateStrategy(new LinearRateStrategy(),
                                                                new ProgressiveRateStrategy());
        assertEquals(120, rateStrategy.calculateTime(300));
    }
}
