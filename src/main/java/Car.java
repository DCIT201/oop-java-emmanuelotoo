public class Car extends Vehicle {
    public Car(String vehicleId, String model, double baseRentalRate, String additionalInfo) {
        super(vehicleId, model, baseRentalRate, additionalInfo);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }
}
