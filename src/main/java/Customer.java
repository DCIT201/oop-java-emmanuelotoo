import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
    private String name;
    private String phoneNumber;
    private HashMap<String, RentalTransaction> rentalHistory;
    private int loyaltyPoints;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rentalHistory = new HashMap<>();
        this.loyaltyPoints = 0;
    }

    public void addRentalTransaction(RentalTransaction transaction) {
        rentalHistory.put(transaction.getVehicle().getVehicleId(), transaction);
    }

    public void addLoyaltyPoints(int points) {
        loyaltyPoints += points;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    // Display loyalty points and available discounts based on points
    public void displayLoyaltyInfo() {
        System.out.println("Current Loyalty Points: " + loyaltyPoints);
        if (loyaltyPoints >= 200) {
            System.out.println("You are eligible for a 20% discount on your next rental!");
        } else if (loyaltyPoints >= 150) {
            System.out.println("You are eligible for a 15% discount on your next rental!");
        } else if (loyaltyPoints >= 100) {
            System.out.println("You are eligible for a 10% discount on your next rental!");
        } else if (loyaltyPoints >= 50) {
            System.out.println("You are eligible for a 5% discount on your next rental!");
        } else {
            System.out.println("No discount available yet. Rent more to earn loyalty points.");
        }
    }

    public double applyLoyaltyPoints(double totalCost) {
        // Calculate the discount amount: 1 point = $0.10 discount, 10 points = $1 discount
        int discountAmount = loyaltyPoints / 10;  // 1 point = $0.10, so loyaltyPoints / 10 gives dollar amount

        double discount = discountAmount * 1.00;  // $1 per 10 points

        // If the discount exceeds the total cost, cap it to the total cost
        if (discount > totalCost) {
            discount = totalCost;  // Prevent discount from exceeding total cost
        }

        // Deduct the points used for the discount
        int pointsUsed = (int) (discount / 0.10);  // Calculate how many points were used for the discount
        loyaltyPoints -= pointsUsed;  // Deduct the points used for the discount

        // Return the total cost after applying the discount
        return totalCost - discount;
    }



    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<RentalTransaction> getRentalHistory() {
        return new ArrayList<>(rentalHistory.values());
    }
}
