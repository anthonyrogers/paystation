package paystation.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith ( Suite.class )
@Suite.SuiteClasses(
              { PayStationImplTest.class,
                LinearRateStrategyTest.class,
                ProgressiveRateStrategyTest.class,
                TestAlternatingRate.class,
                TestIntegration.class } )

public class TestAll {}
