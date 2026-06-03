public class ParkingFeeService {

    private FeeStrategy feeStrategy;

    public ParkingFeeService(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public double calculateParkingFee(int hours) {
        return feeStrategy.calculate(hours);
    }
}


