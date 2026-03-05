
import java.util.*;


enum ProductCategory {
    ELECTRONICS,
    CLOTHING,
    FOOD,
    HOME,
    TOYS,GROCERY
}
 
interface ReplenishmentStrategy { 
    void replenish(Product product);
} 
class JustInTimeStrategy implements ReplenishmentStrategy {
    @Override
    public void replenish(Product product) {
     
        System.out.println("Applying Just-In-Time replenishment for " + product.getName());
       
    }
}
// class BulkOrderStrategy implements ReplenishmentStrategy {
//     @Override
//     public void replenish(Product product) {
//         // Implement Bulk Order replenishment logic
//         System.out.println("Applying Bulk Order replenishment for " + product.getName());
    
//     }
// }
abstract class Product {
    final private String sku;
    final private String name;
    final private double price; 
    private final int threshold ;
    final private ProductCategory category; 
    
    public Product(String sku, String name, double price,int threshold, ProductCategory category) {
        this.sku = sku;
        this.name = name;
        this.price = price; 
        this.threshold = threshold;
        this.category = category;
    } 
    public String getSku() {return sku;}
    public String getName() {return name;}
    public double getPrice() {return price;} 
    public ProductCategory getCategory() {return category;}
    public int getThreshold() {return threshold;}
    public void getProductDetails() {
        System.out.println(String.format(
                "SKU: %s, Name: %s, Price: %.2f, Threshold: %d, Category: %s",
                sku, name, price, threshold, category));
    }
}
class Electronics extends Product {
    private final String brand;
    private final String model;
    public Electronics(String sku, String name, double price, int threshold,
                       String brand, String model) {

        super(sku, name, price, threshold, ProductCategory.ELECTRONICS);
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
}
class Grocery extends Product {
    private final String expiryDate;
    public Grocery(String sku, String name, double price, int threshold,
                   String expiryDate) {
        super(sku, name, price, threshold, ProductCategory.GROCERY);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
class ProductFactory { 
    public Product createElectronics(String sku, String name, double price, int threshold,
                                     String brand, String model) {
        return new Electronics(sku, name, price, threshold, brand, model);
    }

    public Product createGrocery(String sku, String name, double price, int threshold,
                                 String expiryDate) {
        return new Grocery(sku, name, price, threshold, expiryDate);
    }
}
class InventoryItem {

    final private Product product;
    private int quantity;
    public InventoryItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() {return product;}
    public int getQuantity() {return quantity;}
    public void addQuantity(int qty) {quantity += qty;}
    public void removeQuantity(int qty) {quantity -= qty;}
}
class Warehouse {
    final private int id; 
    final private String name;
    final private String location;
    final private Map<String,InventoryItem> inventory; // sku:Product

    public Warehouse(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.inventory = new HashMap<>();
    }
    public void addProduct(Product product, int quantity) { 
        InventoryItem item = inventory.get(product.getSku());
        if(inventory.containsKey(product.getSku())){ 
            item.addQuantity(quantity);
        } else {
            item = new InventoryItem(product, quantity);
            inventory.put(product.getSku(), item); 
        }  
        System.out.println(String.format("Added %d of %s to warehouse %s", quantity, product.getName(), name)); 
        System.out.println(String.format("Current quantity of %s: %d", product.getName(), item.getQuantity())); 
    }
    public boolean removeProduct(String sku, int quantity) { 
        InventoryItem item = inventory.get(sku); 
        if (item == null) {
            System.out.println(String.format(
                    "Product with SKU %s not found in warehouse %s", sku, name));
            return false;
        } 
        int currentQty = item.getQuantity();
        Product product = item.getProduct();

        if (currentQty < quantity) {
            System.out.println(String.format(
                    "Not enough quantity of %s to remove. Current quantity: %d",
                    product.getName(), currentQty));
            return false;
        }

        item.removeQuantity(quantity); 
        System.out.println(String.format(
                "Removed %d of %s from warehouse %s",
                quantity, product.getName(), name));

        System.out.println(String.format(
                "Current quantity of %s: %d",
                product.getName(), item.getQuantity()));

        if (item.getQuantity() == 0) {
            inventory.remove(sku); 
            System.out.println(String.format(
                    "%s is now out of stock in warehouse %s and has been removed from inventory",
                    product.getName(), name));
        } 
        return true;
    }
     
    public int getAvailableQuantity(String sku) { 
        InventoryItem item = inventory.get(sku); 
        return item != null ? item.getQuantity() : 0;
    }
    public void getWarehouseDetails() {
        System.out.println("Warehouse ID: " + id);
        System.out.println("Warehouse Name: " + name);
        System.out.println("Warehouse Location: " + location);
        System.out.println("Products:");
        for (InventoryItem item : inventory.values()) {
            System.out.println("- " + item.getProduct().getName() + " (SKU: " + item.getProduct().getSku() + ", Quantity: " + item.getQuantity() + ")");
        }
    }
     public Product getProductBySku(String sku) {
        return inventory.containsKey(sku) ? inventory.get(sku).getProduct() : null;
    }
    public Collection<InventoryItem> getAllProducts() {
        return inventory.values();
    }
    public InventoryItem getInventoryItem(String sku) {
        return inventory.get(sku);
    }
}

class InventoryManager {
    private  static InventoryManager instance;
    private final List<Warehouse> warehouses; 
    private ReplenishmentStrategy replenishmentStrategy;

    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }
    private InventoryManager() {
        this.warehouses = new ArrayList<>();
        this.replenishmentStrategy = new JustInTimeStrategy(); 
    }
    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }
    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }
    public int getTotalAvailableQuantity(String sku) {
        int total = 0;
        for (Warehouse warehouse : warehouses) {
            total += warehouse.getAvailableQuantity(sku);
        }
        return total;
    }
    public void setReplenishmentStrategy(ReplenishmentStrategy strategy) {
        this.replenishmentStrategy = strategy;
    }
    public Product getProductBySku(String sku) {
        for (Warehouse warehouse : warehouses) {
        Product product = warehouse.getProductBySku(sku);
        if (product != null) {
            return product;
        }
        }
        return null;
    }
    public void checkAndReplenish(String sku) { 
        for (Warehouse warehouse : warehouses) { 
            InventoryItem item = warehouse.getInventoryItem(sku); 
            if (item != null) { 
                Product product = item.getProduct();
                int quantity = item.getQuantity(); 
                if (quantity <= product.getThreshold()) {
                    replenishmentStrategy.replenish(product);
                } else {
                    System.out.println(String.format(
                            "Product %s is above threshold. Current quantity: %d",
                            product.getName(), quantity));
                } 
                return;
            }
        }
    }
    public void performInventoryCheck() { 
        for (Warehouse warehouse : warehouses) { 
            for (InventoryItem item : warehouse.getAllProducts()) { 
                Product product = item.getProduct();
                int quantity = item.getQuantity(); 
                if (quantity < product.getThreshold()) { 
                    if (replenishmentStrategy != null) {
                        replenishmentStrategy.replenish(product);
                    }
                }
            }
        }
    } 
}
 


public class Main {
    public static void main(String[] args) {
        InventoryManager inventoryManager = InventoryManager.getInstance();

        Warehouse warehouse1 = new Warehouse(1, "Central Warehouse", "New York");
        Warehouse warehouse2 = new Warehouse(2, "West Warehouse", "Los Angeles");
        Warehouse warehouse3 = new Warehouse(3, "East Warehouse", "Boston");

        inventoryManager.addWarehouse(warehouse1);
        inventoryManager.addWarehouse(warehouse2);
        inventoryManager.removeWarehouse(warehouse3);

        ProductFactory productFactory = new ProductFactory();
        Product laptop = productFactory.createElectronics("SKU123", "Laptop", 999.99, 5, "Dell", "G15");
        Product apple = productFactory.createGrocery("SKU456", "Apple", 0.99, 20, "2026-03-10");

        warehouse1.addProduct(laptop, 10);
        warehouse2.addProduct(apple, 50);


        warehouse1.getWarehouseDetails();
        warehouse2.getWarehouseDetails();

        inventoryManager.setReplenishmentStrategy(new JustInTimeStrategy());
        inventoryManager.checkAndReplenish("SKU123");
        
    }
}
