import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    // creating the methods/functions that will be used here:
    public static void viewAllProducts() {
        List<Product> productList = ProductDataAccessObject.getAllProducts();
        // In case the database is empty:
        if (productList.isEmpty()) {
            System.out.println("No products found/An error has occurred.");
        }
        else {
            // Otherwise, print out each product of the resulting list one by one in proper formatting:
            for (Product product : productList) {
                System.out.println(product.toString());
            }
        }
    }

    public static Product getByProductID(int id) {
        return ProductDataAccessObject.getProductById(id);
    }

    public static void addNewProduct() {
        // Default output string:
        String output = "Operation has failed.";
        // Since user can only input primitives, we have to take in each primitive that makes up a product.
        // Then, we can combine what they inputted into a singular product to add to the database.
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter product category: ");
            String category = scanner.nextLine();
            System.out.print("Enter product quantity: ");
            int quantity = scanner.nextInt();
            System.out.print("Enter product price (no dollar signs): ");
            double price = scanner.nextDouble();
            System.out.print("Enter product reorder level: ");
            int reorder_level = scanner.nextInt();
            System.out.print("Enter product supplier ID: ");
            int supplier_ID = scanner.nextInt();

            // Passing in "0" for the product ID since the database will auto-generate an ID:
            Product newProduct = new Product(0, name, category, quantity, price, reorder_level, supplier_ID);
            ProductDataAccessObject.addProduct(newProduct);
            String result = newProduct.toString();
            System.out.println(result);
    }

    public static void changeProductStock() {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter the new amount of stock: ");
        int newStock = scanner.nextInt();
        ProductDataAccessObject.updateStock(id, newStock);
        // next two lines for some sort of feedback from the program to the user:
        String result = ProductDataAccessObject.updateStock(id, newStock);
        System.out.println(result);
    }

    public static void viewLowStocks() {
        // In case the database is empty:
        List<Product> lowStockProducts = ProductDataAccessObject.getLowStockProducts();
        if  (lowStockProducts.isEmpty()) {
            System.out.println("No products in low stock found.");
        }
        else {
            // Otherwise, the products will be printed one by one and properly formatted as a string:
            for (Product product : lowStockProducts) {
                System.out.println(product.toString());
            }
        }
    }

    public static void main(String[] args) {
        do {    // list the options available:
            System.out.println("\n=== PRODUCT INVENTORY MANAGEMENT SYSTEM ===");
            System.out.println("1. View all products");
            System.out.println("2. Search for a product by ID");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product's stock");
            System.out.println("5. View products with low stock");
            System.out.println("6. Exit the program");
            System.out.print("Enter your number of choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    scanner.nextLine();
                    Main.viewAllProducts();
                    break;  // need a "break" here so that it doesn't go from top/down through the switch cases.
                case 2:
                    scanner.nextLine();
                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    System.out.println(Main.getByProductID(id));
                    break;
                case 3:
                    scanner.nextLine();     // added nextLine() here since the following function uses it:
                    Main.addNewProduct();
                    break;
                case 4:
                    scanner.nextLine();     // added nextLine() here since the following function uses it:
                    Main.changeProductStock();
                    break;
                case 5:
                    scanner.nextLine();
                    Main.viewLowStocks();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default: System.out.print("\nInvalid choice, please try again: ");
                choice = scanner.nextInt();
            }
        } while (true);
    }
}
