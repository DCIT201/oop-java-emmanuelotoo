public class Motorcycle extends Vehicle {
    public Motorcycle(String vehicleId, String model, double baseRentalRate, String additionalInfo) {
        super(vehicleId, model, baseRentalRate, additionalInfo);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days * 0.8;  // Motorcycle is cheaper
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable(); // Returns true if the vehicle is available
    }
}
