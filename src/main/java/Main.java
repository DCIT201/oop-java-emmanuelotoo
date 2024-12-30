import java.util.Scanner;

public class Main {
    private static RentalAgency rentalAgency = new RentalAgency();
    private static Customer currentCustomer = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding some real-world vehicles to the rental agency using Car class
        rentalAgency.addVehicle(new Car("C001", "Tesla Model 3", 80, "Electric sedan, 2023 model"));
        rentalAgency.addVehicle(new Car("C002", "Ford Mustang Mach-E", 90, "Electric SUV, 2022 model"));
        rentalAgency.addVehicle(new Car("C003", "Nissan Leaf", 60, "Electric hatchback, 2021 model"));
        rentalAgency.addVehicle(new Car("M001", "Harley-Davidson LiveWire", 70, "Electric motorcycle, 2023 model"));
        rentalAgency.addVehicle(new Car("M002", "Zero SR/F", 65, "Electric motorcycle, sport, 2022 model"));
        rentalAgency.addVehicle(new Car("M003", "Kawasaki Ninja e-4", 85, "Electric sportbike, 2023 model"));
        rentalAgency.addVehicle(new Car("T001", "Rivian R1T", 150, "Electric truck, 2023 model"));
        rentalAgency.addVehicle(new Car("T002", "Ford F-150 Lightning", 120, "Electric truck, 2022 model"));
        rentalAgency.addVehicle(new Car("T003", "GMC Hummer EV", 180, "Electric truck, 2023 model"));

        while (true) {
            clearConsole();
            // Main menu
            System.out.println("\nAdvanced Vehicle Rental Management System");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Return a Vehicle");
            System.out.println("3. View Rental History");
            System.out.println("4. Display Loyalty Points");
            System.out.println("5. Exit");

            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    rentVehicle(scanner);
                    break;
                case 2:
                    returnVehicle(scanner);
                    break;
                case 3:
                    viewRentalHistory();
                    break;
                case 4:
                    displayLoyaltyPoints();
                    break;
                case 5:
                    System.out.println("Thank you for using the Vehicle Rental System. Goodbye!");
                    return;
                default:
                    System.out.println("Wrong. Please try again.");
            }
        }
    }

    public static void rentVehicle(Scanner scanner) {
        if (currentCustomer == null) {
            // Ask user to log in or create a new account
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your phone number: ");
            String phone = scanner.nextLine();
            currentCustomer = new Customer(name, phone);
            rentalAgency.addCustomer(currentCustomer);
        }

        if (rentalAgency.hasRentedVehicle(currentCustomer)) {
            System.out.println("You already have a rented vehicle. Please return it before renting another.");
            return;
        }

        System.out.println("\n--- Available Vehicles ---");
        boolean vehicleAvailable = false;
        for (Vehicle vehicle : rentalAgency.getVehicles()) {
            if (vehicle.isAvailableForRental()) {
                System.out.println("ID: " + vehicle.getVehicleId() + ", Model: " + vehicle.getModel() + ", Price per day: $" + vehicle.getBaseRentalRate());
                vehicleAvailable = true;
            }
        }

        if (!vehicleAvailable) {
            System.out.println("No vehicles are currently available for rent please.");
            return;
        }

        System.out.print("\nEnter vehicle ID to rent: ");
        String vehicleId = scanner.nextLine();

        Vehicle selectedVehicle = rentalAgency.findAvailableVehicle(vehicleId);
        if (selectedVehicle == null) {
            System.out.println("Vehicle not available.");
        } else {
            System.out.print("Enter rental duration (days): ");
            int rentalDays = scanner.nextInt();
            RentalTransaction transaction = new RentalTransaction(selectedVehicle, rentalDays, currentCustomer);
            transaction.completeTransaction();
            System.out.println("Rental complete! Total cost after discount: $" + transaction.getTotalCost());
        }
    }

    public static void returnVehicle(Scanner scanner) {
        if (currentCustomer == null) {
            System.out.println("No user logged in.");
            return;
        }

        if (!rentalAgency.hasRentedVehicle(currentCustomer)) {
            System.out.println("You have no rented vehicle.");
            return;
        }

        System.out.println("Returning your rented vehicle...");
        for (RentalTransaction transaction : currentCustomer.getRentalHistory()) {
            if (!transaction.getVehicle().isAvailable()) {
                transaction.returnVehicle();
                System.out.println("Vehicle " + transaction.getVehicle().getModel() + " returned successfully.");
                return;
            }
        }
    }

    public static void viewRentalHistory() {
        if (currentCustomer == null) {
            System.out.println("No user logged in.");
            return;
        }

        System.out.println("\nRental History for " + currentCustomer.getName() + ":");
        for (RentalTransaction transaction : currentCustomer.getRentalHistory()) {
            System.out.println("Vehicle: " + transaction.getVehicle().getModel() + ", Total Cost: $" + transaction.getTotalCost());
        }
    }

    public static void displayLoyaltyPoints() {
        if (currentCustomer == null) {
            System.out.println("No user logged in.");
            return;
        }

        int loyaltyPoints = currentCustomer.getLoyaltyPoints();
        System.out.println("\nCurrent Loyalty Points: " + loyaltyPoints);

        if (loyaltyPoints >= 50) {
            System.out.println("Congratulations! You qualify for a 20% discount on your next rental.");
        } else if (loyaltyPoints >= 25) {
            System.out.println("You qualify for a 10% discount on your next rental.");
        } else {
            System.out.println("Earn more points to unlock exclusive discounts!");
        }
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Unable to clear console.");
        }
    }
}
