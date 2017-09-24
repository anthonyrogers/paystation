package paystation.domain;

import org.junit.Test;
import paystation.domain.Strategies.LinearRateStrategy;
import paystation.domain.Strategies.ProgressiveRateStrategy;
import paystation.domain.Utility.IllegalCoinException;
import paystation.domain.Utility.PayStation;
import paystation.domain.Utility.PayStationImpl;

import static junit.framework.TestCase.assertEquals;

public class TestIntegration {

    private PayStation ps;

    /**
     * Integration testing for the linear rate configuration
     */
    @Test
    public void shouldIntegrateLinearRateCorrectly()
            throws IllegalCoinException {
        // Configure pay station to be the progressive rate pay station
        ps = new PayStationImpl( new LinearRateStrategy() );
        // add $ 2.0:
        addOneDollar(); addOneDollar();

        assertEquals( "Linear Rate: 2$ should give 80 min ",
                80 , ps.readDisplay() );
    }
    /**
     * Integration testing for the progressive rate configuration
     */
    @Test
    public void shouldIntegrateProgressiveRateCorrectly()
            throws IllegalCoinException {
        // reconfigure ps to be the progressive rate pay station
        ps = new PayStationImpl( new ProgressiveRateStrategy() );
        // add $ 2.0: 1.5 gives 1 hours, next 0.5 gives 15 min
        addOneDollar(); addOneDollar();

        assertEquals( "Progressive Rate: 2$ should give 75 min ",
                75 , ps.readDisplay() );
    }

    private void addOneDollar() throws IllegalCoinException {
        ps.addPayment(25); ps.addPayment(25);
        ps.addPayment(25); ps.addPayment(25);
    }
}
