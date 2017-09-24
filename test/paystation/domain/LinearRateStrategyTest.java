package paystation.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import paystation.domain.Strategies.LinearRateStrategy;
import paystation.domain.Strategies.RateStrategy;

import static org.junit.Assert.*;

public class LinearRateStrategyTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldDisplay120MinFor300Cent() {
        RateStrategy rateStrategy = new LinearRateStrategy();
        assertEquals(300 / 5 * 2, rateStrategy.calculateTime(300));
    }

    @After
    public void tearDown() throws Exception {
    }
}