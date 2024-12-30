import java.util.ArrayList;

public class RentalAgency {
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Customer> customers;

    public RentalAgency() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Vehicle findAvailableVehicle(String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(vehicleId) && vehicle.isAvailableForRental()) {
                return vehicle;
            }
        }
        return null;
    }

    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    public boolean hasRentedVehicle(Customer customer) {
        for (RentalTransaction transaction : customer.getRentalHistory()) {
            if (!transaction.getVehicle().isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}
