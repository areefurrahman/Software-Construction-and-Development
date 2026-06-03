public class RegularFeeStrategy implements FeeStrategy {

    private static final double RATE_UP_TO_3_HOURS = 50.0;
    private static final double RATE_ABOVE_3_HOURS = 80.0;

    @Override
    public double calculate(int hours) {

        double rate = (hours <= 3)
                ? RATE_UP_TO_3_HOURS
                : RATE_ABOVE_3_HOURS;

        return hours * rate;
    }
}