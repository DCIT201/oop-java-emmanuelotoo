import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testCarRentalCost() {
        Vehicle car = new Car("C001", "Toyota Camry", 50, "Sedan");
        assertEquals(150, car.calculateRentalCost(3)); // 50 * 3 = 150
    }

    @Test
    void testMotorcycleRentalCost() {
        Vehicle motorcycle = new Motorcycle("M001", "Harley Davidson", 40, "Cruiser");
        assertEquals(96, motorcycle.calculateRentalCost(3)); // 40 * 3 * 0.8 = 96
    }

    @Test
    void testTruckRentalCost() {
        Vehicle truck = new Truck("T001", "Ford F-150", 100, "Pickup");
        assertEquals(360, truck.calculateRentalCost(3)); // 100 * 3 * 1.2 = 360
    }

    @Test
    void testVehicleAvailability() {
        Vehicle car = new Car("C001", "Toyota Camry", 50, "Sedan");
        car.setAvailable(false);
        assertFalse(car.isAvailableForRental());
    }
}
