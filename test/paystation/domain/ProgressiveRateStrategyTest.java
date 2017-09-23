package paystation.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProgressiveRateStrategyTest {

    private RateStrategy rs;

    @Before
    public void setUp() {
        rs = new ProgressiveRateStrategy();
    }

    @Test
    public void shouldDisplay60MinFor150Cent() throws IllegalCoinException {
        // First hour : $1.5
        rs.calculateTime(150);
    }

    @Test
    public void shouldDisplay120MinuteFor350cent() throws IllegalCoinException {
        // Two hours : $1.50 + 2.00
        assertEquals(2 * 60, rs.calculateTime(350));
    }

    @Test
    public void shouldDisplay180MinFor650() throws IllegalCoinException {
        // Three hours : $1.50 + $2.00 + $3.00
        assertEquals(60 * 3, rs.calculateTime(650));
    }

    @Test
    public void shouldDisplay240MinFor950() throws IllegalCoinException {
        assertEquals(60 * 4, rs.calculateTime(950));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldDisplay75MinFor200() throws IllegalCoinException {
        assertEquals(75, rs.calculateTime(200));
    }
}