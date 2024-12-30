public class RentalTransaction {
    private Vehicle vehicle;
    private int rentalDays;
    private Customer customer;
    private double totalCost;

    public RentalTransaction(Vehicle vehicle, int rentalDays, Customer customer) {
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.customer = customer;
    }

    // Completes the rental transaction, calculates cost, and applies loyalty points
    public void completeTransaction() {
        // Calculate the base rental cost
        double baseCost = vehicle.calculateRentalCost(rentalDays);

        // Apply the loyalty points for discount and get the final cost
        this.totalCost = customer.applyLoyaltyPoints(baseCost);

        // Calculate loyalty points earned (e.g., 1 point for every $10 spent after discounts)
        int loyaltyPointsEarned = (int) (totalCost / 10);

        // Add loyalty points earned to customer's account
        customer.addLoyaltyPoints(loyaltyPointsEarned);

        // Add this rental transaction to the customer's history
        customer.addRentalTransaction(this);

        // Mark the vehicle as rented
        vehicle.setAvailable(false);

        // Display details to the user
        System.out.println("Transaction complete!");
        System.out.println("Total cost after discount: $" + totalCost);
        System.out.println("You earned " + loyaltyPointsEarned + " loyalty points.");
        System.out.println("Your total loyalty points: " + customer.getLoyaltyPoints());
    }

    // Returns the rented vehicle and makes it available again
    public void returnVehicle() {
        vehicle.setAvailable(true); // Mark the vehicle as available again
        System.out.println("Vehicle " + vehicle.getModel() + " has been successfully returned.");
    }

    // Get the total cost of the rental
    public double getTotalCost() {
        return totalCost;
    }

    // Get the rented vehicle details
    public Vehicle getVehicle() {
        return vehicle;
    }
}
