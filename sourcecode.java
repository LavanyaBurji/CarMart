import java.sql.*;
import java.util.Scanner;

public class CarMart {
    private static final String URL = "jdbc:postgresql://localhost:5432/carmart";
    private static final String USER = "postgres"; // Change if needed
    private static final String PASSWORD = "password"; // Change if needed

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nCarMart System");
            System.out.println("1. Add");
            System.out.println("2. Search");
            System.out.println("3. Update (only price)");
            System.out.println("4. Sold");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCar(scanner);
                    break;
                case 2:
                    searchCars(scanner);
                    break;
                case 3:
                    updatePrice(scanner);
                    break;
                case 4:
                    markAsSold(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addCar(Scanner scanner) {
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO cars (carid, company, model, seater, fueltype, type, price, sold) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            System.out.print("Enter Car ID: ");
            int carId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Company: ");
            String company = scanner.nextLine();
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();
            System.out.print("Enter Seater: ");
            int seater = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Fuel Type: ");
            String fuelType = scanner.nextLine();
            System.out.print("Enter Type (Hatchback, Sedan, SUV): ");
            String type = scanner.nextLine();
            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();
            System.out.print("Is Sold? (true/false): ");
            boolean sold = scanner.nextBoolean();

            stmt.setInt(1, carId);
            stmt.setString(2, company);
            stmt.setString(3, model);
            stmt.setInt(4, seater);
            stmt.setString(5, fuelType);
            stmt.setString(6, type);
            stmt.setDouble(7, price);
            stmt.setBoolean(8, sold);

            stmt.executeUpdate();
            System.out.println("Car added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding car: " + e.getMessage());
        }
    }

    private static void searchCars(Scanner scanner) {
        System.out.println("\nSearch Options:");
        System.out.println("1. ALL (All unsold cars)");
        System.out.println("2. Company (All cars of specific company)");
        System.out.println("3. Type (Hatchback, Sedan, SUV)");
        System.out.println("4. Price range");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            String query = "";

            switch (searchChoice) {
                case 1:
                    query = "SELECT * FROM cars WHERE sold = false";
                    break;
                case 2:
                    System.out.print("Enter company name: ");
                    String company = scanner.nextLine();
                    query = "SELECT * FROM cars WHERE company = '" + company + "'";
                    break;
                case 3:
                    System.out.print("Enter type (Hatchback, Sedan, SUV): ");
                    String type = scanner.nextLine();
                    query = "SELECT * FROM cars WHERE type = '" + type + "'";
                    break;
                case 4:
                    System.out.print("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    query = "SELECT * FROM cars WHERE price BETWEEN " + minPrice + " AND " + maxPrice;
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt("carid") + " | " +
                        rs.getString("company") + " | " +
                        rs.getString("model") + " | " +
                        rs.getInt("seater") + " | " +
                        rs.getString("fueltype") + " | " +
                        rs.getString("type") + " | " +
                        rs.getDouble("price") + " | " +
                        rs.getBoolean("sold"));
            }

        } catch (SQLException e) {
            System.out.println("Error searching cars: " + e.getMessage());
        }
    }

    private static void updatePrice(Scanner scanner) {
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement("UPDATE cars SET price = ? WHERE carid = ?")) {

            System.out.print("Enter Car ID to update price: ");
            int carId = scanner.nextInt();
            System.out.print("Enter new price: ");
            double newPrice = scanner.nextDouble();

            stmt.setDouble(1, newPrice);
            stmt.setInt(2, carId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Price updated successfully!");
            } else {
                System.out.println("Car ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating price: " + e.getMessage());
        }
    }

    private static void markAsSold(Scanner scanner) {
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement("UPDATE cars SET sold = true WHERE carid = ?")) {

            System.out.print("Enter Car ID to mark as sold: ");
            int carId = scanner.nextInt();

            stmt.setInt(1, carId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Car marked as sold!");
            } else {
                System.out.println("Car ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error marking car as sold: " + e.getMessage());
        }
    }
}
