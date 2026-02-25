// This class is responsible for all database operations regarding products.
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDataAccessObject {
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            // Using the DataBaseConnection class, establish a connection to the database to run the functions:
            Connection connection = DataBaseConnection.getConnection();

            // SQL query in java form basically:
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");
            while (resultSet.next()) {
                // Since the query above will return everything from the "Products" table, we need to...
                // create a "product" object to house the information of each "product"...
                // each bit of information being its own variable:
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("pQuantity");
                double price = resultSet.getDouble("price");
                int reorderLevel = resultSet.getInt("reorder_level");
                int supplierId = resultSet.getInt("supplier_ID");

                // Creating the "product" object here after we have obtained the variables for all of its info above:
                Product product = new Product(id, name, category, quantity, price, reorderLevel, supplierId);
                // Adding the "product" to the list:
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // don't have to throw new exception, just print out the StackTrace instead.
        }
        return products;
    }

    public static Product getProductById(int id) {
        Product product = null;
        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            // This will return everything about the "product" that the query returns.
            // Therefore, we can add all of those into a new "product" and just return that.
            // Otherwise, it'll return "null".
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Products WHERE id = " + id);
            while (resultSet.next()) {
                int pid = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
            int quantity = resultSet.getInt("pQuantity");
                double price = resultSet.getDouble("price");
                int reorderLevel = resultSet.getInt("reorder_level");
                int supplierId = resultSet.getInt("supplier_ID");
                product = new Product(id, name, category, quantity, price, reorderLevel, supplierId);
            }
        } catch (SQLException e) {
        e.printStackTrace(); // don't have to throw new exception, just print out the StackTrace instead.
    }
        return product;
    }

    public static String addProduct(Product product) {
        // Initializing return statement:
        String confirmation = "Product not successfully added.";
        // Will be using PreparedStatement function since this is a rather long statement
        // therefore we need to initialize it. ID and dates will not be added since the DB already auto-adds those.
        String sql = "INSERT INTO Products " +
                "(name, category, pQuantity, price, reorder_level, supplier_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DataBaseConnection.getConnection();
            // passing the prepared statement into the "PreparedStatement" function:
            PreparedStatement ps = connection.prepareStatement(sql);
            // For each index of the parameter, we will add the corresponding variable of the product:
                ps.setString(1, product.getName());
                ps.setString(2, product.getCategory());
                ps.setInt(3, product.getpQuantity());
                ps.setDouble(4, product.getPrice());
                ps.setInt(5, product.getReorder_level());
                ps.setInt(6, product.getSuppler_ID());

                // Since we are modifying the database, we need executeUpdate():
                int rows = ps.executeUpdate();
                // if the rows have been successfully added, then we can change the confirmation:
                if (rows > 0) {
                    confirmation = "Product successfully added.";
                }
        } catch (SQLException e) {
            e.printStackTrace(); // don't have to throw new exception, just print out the StackTrace instead.
        }
        return confirmation;
    }

    public static String updateStock(int id, int pQuantity) {
        String confirmation = "Product quantity failed to update.";
        String sql = "UPDATE Products SET pQuantity = ? WHERE id = ?";
        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pQuantity);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            // if the row exists, then we can change the confirmation:
            if (rows > 0) {
                confirmation = "Product quantity successfully updated.";
            }
        } catch (SQLException e) {
            e.printStackTrace(); // don't have to throw new exception, just print out the StackTrace instead.
        }
        return confirmation;
    }

    public static List<Product> getLowStockProducts() {
        List<Product> lowStockList = new ArrayList<>();
        try {
            // Using the DataBaseConnection class, establish a connection to the database to run the functions:
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Products WHERE pQuantity < reorder_level");
            while (resultSet.next()) {
                // Since the query above will return everything from the "Products" table, we need to...
                // create a "product" object to house the information of each "product"...
                // each bit of information being its own variable:
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
            int quantity = resultSet.getInt("pQuantity");
                double price = resultSet.getDouble("price");
                int reorderLevel = resultSet.getInt("reorder_level");
                int supplierId = resultSet.getInt("supplier_ID");

                // Creating the "product" object here after we have obtained the variables for all of its info above:
                Product product = new Product(id, name, category, quantity, price, reorderLevel, supplierId);
                // Adding the "product" to the list:
                lowStockList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // don't have to throw new exception, just print out the StackTrace instead.
        }
        return lowStockList;
    }
}
