// This class will house the required info for all products,
// as well as any functions necessary to access said info.

public class Product {
    // initializing private variables for the "Product" class.
    // "private" variables since they should only stay in this class.
    private int id;
    private String name;
    private String category;
    private int pQuantity;
    private double price;
    private int reorder_level;
    private int suppler_ID;

    // Single constructor for all private variables a part of the "Product" class:
    public Product(int id, String name, String category, int pQuantity,
                   double price, int reorder_level, int suppler_ID) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.pQuantity = pQuantity;
        this.price = price;
        this.reorder_level = reorder_level;
        this.suppler_ID = suppler_ID;
    }

    // Setter/Getters below for all private variables:
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public int getpQuantity() {
        return pQuantity;
    }
    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getReorder_level() {
        return reorder_level;
    }
    public void setReorder_level(int reorder_level) {
        this.reorder_level = reorder_level;
    }

    public int getSuppler_ID() {
        return suppler_ID;
    }
    public void setSuppler_ID(int suppler_ID) {
        this.suppler_ID = suppler_ID;
    }

    public boolean needsReorder() {
        return pQuantity < reorder_level;
    }

    @Override
    public String toString() {
        return ("ID: " + id + " | Name: " + name + " | Category: " + category
        + " | Quantity: " + pQuantity + " | Price: $" + String.format("%.2f", price));
    }
}
