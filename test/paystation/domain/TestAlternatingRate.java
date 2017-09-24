package paystation.domain;

import org.junit.Test;
import paystation.domain.Strategies.AlternatingRateStrategy;
import paystation.domain.Strategies.LinearRateStrategy;
import paystation.domain.Strategies.ProgressiveRateStrategy;
import paystation.domain.Strategies.RateStrategy;

import static junit.framework.TestCase.assertEquals;

public class TestAlternatingRate {

    @Test
    public void shouldDisplay120MinFor300centWeekday() {
        RateStrategy rs = new AlternatingRateStrategy(new LinearRateStrategy(),
                                                      new ProgressiveRateStrategy(),
                                                      new FixedDecisionStrategy(false));
        assertEquals(300 / 5 * 2, rs.calculateTime(300));
    }

    @Test
    public void shouldDisplay120MinFor350centWeekend() {
        RateStrategy rs = new AlternatingRateStrategy(new LinearRateStrategy(),
                                                      new ProgressiveRateStrategy(),
                                                      new FixedDecisionStrategy(true));
        assertEquals(300 / 5 * 2, rs.calculateTime(350));
    }
}
