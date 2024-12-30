import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalTransactionTest {

    @Test
    void testRentalTransactionCost() {
        Customer customer = new Customer("John Doe", "1234567890");
        Vehicle vehicle = new Car("C001", "Toyota Camry", 50, "Sedan");
        RentalTransaction transaction = new RentalTransaction(vehicle, 3, customer);
        transaction.completeTransaction();
        assertEquals(150, transaction.getTotalCost()); // 50 * 3 = 150
    }

    @Test
    void testReturnVehicle() {
        Vehicle vehicle = new Car("C001", "Toyota Camry", 50, "Sedan");
        RentalTransaction transaction = new RentalTransaction(vehicle, 3, new Customer("John", "123"));
        transaction.completeTransaction();
        assertFalse(vehicle.isAvailable());

        transaction.returnVehicle();
        assertTrue(vehicle.isAvailable());
    }

    @Test
    void testLoyaltyPointsEarned() {
        Customer customer = new Customer("John Doe", "1234567890");
        Vehicle vehicle = new Car("C001", "Toyota Camry", 50, "Sedan");
        RentalTransaction transaction = new RentalTransaction(vehicle, 3, customer);
        transaction.completeTransaction();
        assertEquals(15, customer.getLoyaltyPoints()); // 150 / 10 = 15 points
    }
}
