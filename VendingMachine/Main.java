
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 


enum ItemType {
    SNACK, DRINK, CANDY
}
enum Coin {
    ONE_RUPEE(1) , NICKEL(5) , DIME(10) , QUARTER(25);
    final public   int value ;
    Coin(int value) {
        this.value = value;
    }
}
class Item {
    final private ItemType type;
    final private int  price;
    public Item(ItemType type, int price) {
        this.type = type;
        this.price = price;
    }
    public ItemType getType() {
        return type;
    }
    public int getPrice() {
        return price;
    }
}
class ItemShelf {
    final private int shelfId;
    final private Item item;
    private int quantity; 

    public ItemShelf(int shelfId, Item item, int quantity) {
        this.shelfId = shelfId;
        this.item = item;
        this.quantity = quantity;
    }
    public Item getItem() {
        return item;
    } 
    public void addItems(int count) {
        this.quantity += count;
    }
    public boolean isSoldOut() {
        return quantity == 0;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getShelfId() {
        return shelfId;
    }
    public void removeItem() {
        if(this.quantity > 0) {
            this.quantity--;
        } else {
            throw new RuntimeException("Item is sold out");
        }
    } 
}
class Inventory {
    final private Map<Integer,ItemShelf> shelves; 
    public Inventory() {
        this.shelves = new HashMap<>(); 
    }
    public void addShelf(ItemShelf shelf) {
        shelves.put(shelf.getShelfId(), shelf); 
    }
    public ItemShelf getShelf(int shelfId) {
        ItemShelf shelf = shelves.get(shelfId);
        if(shelf == null) {
            throw new RuntimeException("Shelf not found");
        }
        return shelf;
    }
    public List<ItemShelf> getAllShelves() {
        return new ArrayList<>(shelves.values());
    }
    public List<ItemShelf> getEmptyShelves() {
        List<ItemShelf> emptyShelves = new ArrayList<>();
        for(ItemShelf shelf : shelves.values()) {
            if(shelf.isSoldOut()) {
                emptyShelves.add(shelf);
            }
        }
        return emptyShelves;
    }
    public void restockShelf(int shelfId, int quantity) {
        ItemShelf shelf = getShelf(shelfId); 
        shelf.addItems(quantity);
    }
}

class VendingMachineContext {
    private VendingMachineState currentState;
    final private Inventory inventory;
    private int insertedMoney = 0;
    private int selectedShelfId = -1;
    
    public VendingMachineContext(Inventory inventory) {
        this.inventory = inventory;
        this.currentState = new IdleState(this);
    }
    public void setState(VendingMachineState state) {this.currentState = state;}
    public void insertCoin(Coin coin) {currentState.insertCoin(coin);}
    public void selectItem(int shelfId) { currentState.selectItem(shelfId);}
    public void dispenseItem() {currentState.dispenseItem();}
    public Inventory getInventory() { return inventory;}
    public void addMoney(int amount) {insertedMoney += amount;}
    public int getInsertedMoney() { return insertedMoney;}
    public void resetMoney() { insertedMoney = 0; }
    public void setSelectedShelf(int shelfId) { selectedShelfId = shelfId;}
    public int getSelectedShelf() { return selectedShelfId; }
    public String getCurrentState() {return currentState.getStateName();}
    public void cancelTransaction() {currentState.cancelTransaction();}
}

interface VendingMachineState {
    void insertCoin(Coin coin);
    void selectItem(int shelfId);
    void dispenseItem(); 
    String getStateName();
    void cancelTransaction();
}
class IdleState implements VendingMachineState {

    private final VendingMachineContext context;
    public IdleState(VendingMachineContext context) {
        this.context = context;
        System.out.println("Machine is idle.");
    }

    @Override
    public void insertCoin(Coin coin) {
        context.addMoney(coin.value);
        System.out.println("Inserted: " + coin.value);
        context.setState(new CoinInsertedState(context));
    }
    @Override
    public void selectItem(int shelfId) {System.out.println("Insert coin first.");}
    @Override
    public void dispenseItem() {System.out.println("Insert coin first.");}
    @Override
    public String getStateName() {return "Idle";}
    @Override
    public void cancelTransaction() {System.out.println("No transaction to cancel.");}
}

class CoinInsertedState implements VendingMachineState {
    private final VendingMachineContext context;
    public CoinInsertedState(VendingMachineContext context) {
        this.context = context;
    }

    @Override
    public void insertCoin(Coin coin) {
        context.addMoney(coin.value);
        System.out.println("Inserted: " + coin.value);
    }
    @Override
    public void selectItem(int shelfId) {
        ItemShelf shelf;
        try {
            shelf = context.getInventory().getShelf(shelfId);
        } catch (RuntimeException e) {
            System.out.println("Invalid shelf selection.");
            return;
        }
        if (shelf.isSoldOut()) {
            System.out.println("Item out of stock. Returning money: " + context.getInsertedMoney());
            context.resetMoney();
            context.setState(new IdleState(context));
            return;
        }
        int price = shelf.getItem().getPrice();
        if (context.getInsertedMoney() < price) {
            System.out.println("Insufficient funds. Price: " + price);
            context.setSelectedShelf(shelfId);
            context.setState(new InsufficientFundsState(context));
            return;
        }
        context.setSelectedShelf(shelfId);
        context.setState(new DispenseState(context));
        context.dispenseItem();
    }
    @Override
    public void dispenseItem() { System.out.println("Select item first.");}
    @Override
    public String getStateName() {return "CoinInserted";}
    @Override
    public void cancelTransaction() {
        System.out.println("Transaction cancelled. Returning: " + context.getInsertedMoney());
        context.resetMoney();
        context.setState(new IdleState(context));
    }
}


class InsufficientFundsState implements VendingMachineState {
    private final VendingMachineContext context;
    public InsufficientFundsState(VendingMachineContext context) {
        this.context = context;
    }

    @Override
    public void insertCoin(Coin coin) {
        context.addMoney(coin.value);
        System.out.println("Inserted: " + coin.value);
        
        int shelfId = context.getSelectedShelf(); 
        if (shelfId == -1) {
            System.out.println("No item selected.");
            return;
        }
        ItemShelf shelf = context.getInventory().getShelf(context.getSelectedShelf());
        int price = shelf.getItem().getPrice();
        int inserted = context.getInsertedMoney();

        if (inserted  >= price) {
            context.setState(new DispenseState(context));
            context.dispenseItem();
        }else {
            System.out.println("Inserted: " + inserted +" | Remaining: " + (price - inserted));
        }
    }
    @Override
    public void selectItem(int shelfId) {System.out.println("Item already selected.");}
    @Override
    public void dispenseItem() {System.out.println("Insert more coins.");}
    @Override
    public String getStateName() {return "InsufficientFunds";}
    @Override
    public void cancelTransaction() {
        System.out.println("Transaction cancelled. Returning: " + context.getInsertedMoney());
        context.resetMoney();
        context.setSelectedShelf(-1);
        context.setState(new IdleState(context));
    }
}


class DispenseState implements VendingMachineState {
    private final VendingMachineContext context;
    public DispenseState(VendingMachineContext context) {
        this.context = context;
    }

    @Override
    public void insertCoin(Coin coin) {System.out.println("Cannot insert coin while dispensing.");}
    @Override
    public void selectItem(int shelfId) {System.out.println("Already dispensing.");}
    @Override
    public void dispenseItem() {
        ItemShelf shelf = context.getInventory().getShelf(context.getSelectedShelf());
        Item item = shelf.getItem();
        shelf.removeItem();
        System.out.println("Dispensed: " + item.getType());
        int change = context.getInsertedMoney() - item.getPrice();
        if (change > 0) System.out.println("Change returned: " + change);
        context.resetMoney();
        context.setSelectedShelf(-1);
        context.setState(new IdleState(context));
    }
    @Override
    public String getStateName() { return "Dispense";} 
    @Override
    public void cancelTransaction() {
        System.out.println("Cannot cancel while dispensing.");
    }
}
 
// class MaintenanceState extends VendingMachineState {} 
 

public class Main {
    // Helper to create a fresh vending machine with initial stock
    public static VendingMachineContext createFreshVM() {
        Item candy = new Item(ItemType.CANDY, 50);
        Item drink = new Item(ItemType.DRINK, 75);
        Item snack = new Item(ItemType.SNACK, 30);

        ItemShelf shelf1 = new ItemShelf(1, candy, 2);
        ItemShelf shelf2 = new ItemShelf(2, drink, 1);
        ItemShelf shelf3 = new ItemShelf(3, snack, 0);

        Inventory inventory = new Inventory();
        inventory.addShelf(shelf1);
        inventory.addShelf(shelf2);
        inventory.addShelf(shelf3);

        return new VendingMachineContext(inventory);
    }

    public static void main(String[] args) { 
        // ---------- Test 1 ----------
        System.out.println("\n--- Test 1: Buy candy with exact change ---");
        VendingMachineContext vm1 = createFreshVM();
        vm1.insertCoin(Coin.QUARTER);
        vm1.insertCoin(Coin.QUARTER);
        vm1.selectItem(1);


        // ---------- Test 2 ----------
        System.out.println("\n--- Test 2: Buy drink with exact change ---");
        VendingMachineContext vm2 = createFreshVM();
        vm2.insertCoin(Coin.QUARTER);
        vm2.insertCoin(Coin.QUARTER);
        vm2.insertCoin(Coin.DIME);
        vm2.insertCoin(Coin.DIME);
        vm2.insertCoin(Coin.NICKEL);
        vm2.selectItem(2);


        // ---------- Test 3 ----------
        System.out.println("\n--- Test 3: Attempt to buy snack (sold out) ---");
        VendingMachineContext vm3 = createFreshVM();
        vm3.insertCoin(Coin.QUARTER);
        vm3.selectItem(3);


        // ---------- Test 4 ----------
        System.out.println("\n--- Test 4: Insufficient funds then complete payment ---");
        VendingMachineContext vm4 = createFreshVM();
        vm4.insertCoin(Coin.DIME);     // 10
        vm4.selectItem(1);             // candy price 50
        vm4.dispenseItem();            // still insufficient
        vm4.insertCoin(Coin.QUARTER);  // 35
        vm4.insertCoin(Coin.QUARTER);  // 60 → dispense


        // ---------- Test 5 ----------
        System.out.println("\n--- Test 5: Insert coin but dispense without selecting ---");
        VendingMachineContext vm5 = createFreshVM();
        vm5.insertCoin(Coin.QUARTER);
        vm5.dispenseItem();


        // ---------- Test 6 ----------
        System.out.println("\n--- Test 6: Invalid shelf selection ---");
        VendingMachineContext vm6 = createFreshVM();
        vm6.insertCoin(Coin.QUARTER);
        vm6.selectItem(99);


        // ---------- Test 7 ----------
        System.out.println("\n--- Test 7: Cancel transaction after inserting coins ---");
        VendingMachineContext vm7 = createFreshVM();
        vm7.insertCoin(Coin.QUARTER);
        vm7.insertCoin(Coin.DIME);
        vm7.cancelTransaction();


        // ---------- Test 8 ----------
        System.out.println("\n--- Test 8: Cancel transaction during insufficient funds ---");
        VendingMachineContext vm8 = createFreshVM();
        vm8.insertCoin(Coin.DIME);
        vm8.selectItem(1);
        vm8.cancelTransaction();


        // ---------- Test 9 ----------
        System.out.println("\n--- Test 9: Buy multiple candies until stock empty ---");
        VendingMachineContext vm9 = createFreshVM();

        vm9.insertCoin(Coin.QUARTER);
        vm9.insertCoin(Coin.QUARTER);
        vm9.selectItem(1);

        vm9.insertCoin(Coin.QUARTER);
        vm9.insertCoin(Coin.QUARTER);
        vm9.selectItem(1);

        vm9.insertCoin(Coin.QUARTER);
        vm9.insertCoin(Coin.QUARTER);
        vm9.selectItem(1);   // should now be sold out


        // ---------- Test 10 ----------
        System.out.println("\n--- Test 10: Check empty shelves ---");
        VendingMachineContext vm10 = createFreshVM();
        List<ItemShelf> emptyShelves = vm10.getInventory().getEmptyShelves();

        System.out.println("Empty shelves:");
        for (ItemShelf shelf : emptyShelves) {
            System.out.println(
                "Shelf ID: " + shelf.getShelfId() +
                ", Item: " + shelf.getItem().getType()
            );
        }
    }
     
}