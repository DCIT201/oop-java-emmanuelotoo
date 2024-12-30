public class Truck extends Vehicle {
    public Truck(String vehicleId, String model, double baseRentalRate, String additionalInfo) {
        super(vehicleId, model, baseRentalRate, additionalInfo);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days * 1.2;  // Truck is more expensive
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable(); // Returns true if the vehicle is available
    }
}
