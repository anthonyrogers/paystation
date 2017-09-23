package paystation.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AlternatingRateStrategy implements RateStrategy {
    private RateStrategy weekendStrategy, weekdayStrategy, currentState;

    public AlternatingRateStrategy(RateStrategy weekdayStrategy, RateStrategy weekendStrategy) {
        this.weekdayStrategy = weekdayStrategy;
        this.weekendStrategy = weekendStrategy;
        this.currentState = null;
    }

    public int calculateTime(int amount) {
        if(isWeekend()) {
            currentState = weekendStrategy;
        } else {
            currentState = weekdayStrategy;
        }
        return currentState.calculateTime(amount);
    }

    private boolean isWeekend() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }
}
