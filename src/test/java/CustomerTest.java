import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    @Test
    void testLoyaltyPointsAddition() {
        Customer customer = new Customer("Franklin Wiafe", "1234567890");
        customer.addLoyaltyPoints(100);
        assertEquals(100, customer.getLoyaltyPoints());
    }

    @Test
    void testLoyaltyPointsDiscount() {
        Customer customer = new Customer("Franklin Wiafe", "1234567890");
        customer.addLoyaltyPoints(100); // 100 points = 10% discount
        double totalCost = 200;
        double discountedCost = customer.applyLoyaltyPoints(totalCost);
        assertEquals(190, discountedCost); // 10% discount on 200 = 180
    }

    @Test
    void testRentalHistory() {
        Customer customer = new Customer("Franklin Wiafe", "1234567890");
        Vehicle vehicle = new Car("C001", "Toyota Camry", 50, "Sedan");
        RentalTransaction transaction = new RentalTransaction(vehicle, 3, customer);
        customer.addRentalTransaction(transaction);

        assertEquals(1, customer.getRentalHistory().size());
        assertEquals(vehicle, customer.getRentalHistory().get(0).getVehicle());
    }
}
