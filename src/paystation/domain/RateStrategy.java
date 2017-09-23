package paystation.domain;

public interface RateStrategy {

    /*
    Calculate and return the amount of time purchased for parking
    @param amount of payment
    @return number of parking minutes
     */
    public int calculateTime(int amount);
}
