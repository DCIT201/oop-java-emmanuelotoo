public abstract class Vehicle {
    private String vehicleId;
    private String model;
    private double baseRentalRate;
    private boolean isAvailable;
    private String additionalInfo;

    public Vehicle(String vehicleId, String model, double baseRentalRate, String additionalInfo) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true;
        this.additionalInfo = additionalInfo;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public abstract double calculateRentalCost(int days);

    public abstract boolean isAvailableForRental();
}
