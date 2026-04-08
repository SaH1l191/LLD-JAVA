
import java.util.HashMap;
import java.util.Map;

//entities : User,Account
 
//Card : Cardnumber, cardtype, expirydate,accountNumber,pin
//Account : accountnumber, accounttype, balance , all details of account holder, transaction history
//AtmMachineContext : 
//transaction entity : id, type, amount, date, status for logs , storage 
//Cash enum { type : 100, 200, 500, 2000 }....
//AtmMachineInventory : Cash , fast loookup : hashmap Coin:count
//transaciton type enum  : withdraw, deposit, balance inquiry, fund transfer

//patterns : 
//strategy pattern : for differnet transaction types 
//chain of responsibility : for withdraw transaction : 2000, 500, 200, 100
//factory : CardFactory, AccountFactory(saving,current etc ) - overengineering
//State design :  atmmachineStates : idle,insertCart,selectTransactionState,processTransactionState, maintainence state
//observer pattern : for logs etc 




//optional
// class Transaction {
//     private final String transactionId;
//     private final TransactionType transactionType;
//     private final double amount;
//     private final String date; 
//     private String status; 

//     public Transaction(String transactionId, TransactionType transactionType, double amount, String date) {
//         this.transactionId = transactionId;
//         this.transactionType = transactionType;
//         this.amount = amount;
//         this.date = date;
//         this.status = "PENDING"; 
//     }
//     public void updateStatus(String status) {
//         this.status = status;
//     }
// }

enum CashType {
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    TWO_THOUSAND(2000);
    private final int value;
    CashType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}

enum TransactionType {
    WITHDRAW,
    DEPOSIT, 
    // BALANCE_INQUIRY,
    // FUND_TRANSFER
}

class Card {
    private final String cardNumber;
    private final String accountNumber;
    private String cardType;  
    private String pin;

    public Card(String cardNumber, String cardType, String pin, String accountNumber) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;  
        this.pin = pin;
        this.accountNumber = accountNumber;
    }
    private boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }
}


class Account {
    private final String accountNumber; 
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber; 
        this.balance = balance;
    }
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }
    public void deposit(double amount) {
        balance += amount;
    }
    public double getBalance() {
        return balance;
    }
}

interface WithdrawStrategy {
    Map<CashType,Integer> dispense(double amount, AtmInventory inventory);
}

class HighestFirstStrategy implements WithdrawStrategy {
    @Override
    public Map<CashType, Integer> dispense(double amount, AtmInventory inventory) {
        Map<CashType, Integer> result = new HashMap<>();

        CashType[] order = {
            CashType.TWO_THOUSAND,
            CashType.FIVE_HUNDRED,
            CashType.TWO_HUNDRED,
            CashType.HUNDRED
        };

        for (CashType cashType : order) {
            int noteValue = cashType.getValue();
            int available = inventory.getCount(cashType);

            int needed = (int)(amount / noteValue);
            int toUse = Math.min(needed, available);

            if (toUse > 0) {
                result.put(cashType, toUse);
                amount -= toUse * noteValue;
            }
        }

        if (amount != 0) {
            throw new RuntimeException("Cannot dispense exact amount");
        }

        return result;
    }
}

// class LowestFirstStrategy implements WithdrawStrategy {
//     public Map<CashType,Integer>dispense(double amount, AtmInventory inventory) {
//         Map<CashType,Integer>dispensedCash = new HashMap<>();
//         CashType[] order = {
//             CashType.HUNDRED,
//             CashType.TWO_HUNDRED,
//             CashType.FIVE_HUNDRED,
//             CashType.TWO_THOUSAND
//         };
//         for (CashType cashType : order) {
//             int noteValue = cashType.getValue(); 
//             int available = inventory.getCount(cashType);
//             int needed = (int)(amount / noteValue);
//             int toUse= Math.min(available,needed);
//             if (toUse > 0) {
//                 dispensedCash.put(cashType, toUse);
//                 amount -= toUse * cashType.getValue();
//             }
//         }
//         if (amount != 0) {
//             throw new RuntimeException("Cannot dispense exact amount");
//         }
//         return dispensedCash;
//     }
// }

class AtmInventory { 
    private Map<CashType,Integer>cashInventory;
    private WithdrawStrategy withdrawStrategy;
    public AtmInventory(Map<CashType, Integer> cashInventory) {
        this.cashInventory = new HashMap<>(); 
        initializeInventory(cashInventory);
        this.withdrawStrategy = new HighestFirstStrategy();
    }
    public void initializeInventory(Map<CashType, Integer> cashInventory) {
        for (Map.Entry<CashType, Integer> entry : cashInventory.entrySet()) {
            this.cashInventory.put(entry.getKey(), entry.getValue());
        }
    }
    public void updateInventory(CashType cashType, int count) {
        cashInventory.put(cashType, cashInventory.getOrDefault(cashType, 0) + count);
    }
    public void setWithdrawStrategy(WithdrawStrategy strategy) {
        this.withdrawStrategy = strategy;
    }

    public int getCount(CashType type) {
        return cashInventory.getOrDefault(type, 0);
    }
}
 
abstract class CashHandler {
    protected CashHandler nextHandler;
    public void setNextHandler(CashHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    public void processCash(double amount,Map<CashType,Integer> inventory) {
    }
}
class TwoThousandHandler extends CashHandler {
    @Override
    public void processCash(double amount, Map<CashType, Integer> inventory) {
        int noteValue = CashType.TWO_THOUSAND.getValue();
        int needed = (int)(amount / noteValue);
        int available = inventory.getOrDefault(CashType.TWO_THOUSAND, 0);
        int toUse = Math.min(needed, available);
        if (toUse > 0) {
            inventory.put(CashType.TWO_THOUSAND, available - toUse);
            amount -= toUse * noteValue;
        }
        if (amount > 0 && nextHandler != null) {
            nextHandler.processCash(amount, inventory);
        }
    }
}
class FiveHundredHandler extends CashHandler {
    @Override
    public void processCash(double amount, Map<CashType, Integer> inventory) {
        int noteValue = CashType.FIVE_HUNDRED.getValue();
        int needed = (int)(amount / noteValue);
        int available = inventory.getOrDefault(CashType.FIVE_HUNDRED, 0);
        int toUse = Math.min(needed, available);
        if (toUse > 0) {
            inventory.put(CashType.FIVE_HUNDRED, available - toUse);
            amount -= toUse * noteValue;
        }
        if (amount > 0 && nextHandler != null) {
            nextHandler.processCash(amount, inventory);
        }
    }
}
class TwoHundredHandler extends CashHandler {
    @Override
    public void processCash(double amount, Map<CashType, Integer> inventory) {
        int noteValue = CashType.TWO_HUNDRED.getValue();
        int needed = (int)(amount / noteValue);
        int available = inventory.getOrDefault(CashType.TWO_HUNDRED, 0);
        int toUse = Math.min(needed, available);
        if (toUse > 0) {
            inventory.put(CashType.TWO_HUNDRED, available - toUse);
            amount -= toUse * noteValue;
        }
        if (amount > 0 && nextHandler != null) {
            nextHandler.processCash(amount, inventory);
        }
    }
}
class HunderedHandler extends CashHandler {
    @Override
    public void processCash(double amount, Map<CashType, Integer> inventory) {
        int noteValue = CashType.HUNDRED.getValue();
        int needed = (int)(amount / noteValue);
        int available = inventory.getOrDefault(CashType.HUNDRED, 0);
        int toUse = Math.min(needed, available);
        if (toUse > 0) {
            inventory.put(CashType.HUNDRED, available - toUse);
            amount -= toUse * noteValue;
        }
        if (amount > 0) {
            throw new RuntimeException("Cannot dispense exact amount");
        }
    }
}

interface AtmState {
    String getStateName();
    void next(ATMMachine atmMachine);
}

class IdleState implements AtmState {
    @Override
    public String getStateName() {
        return "Idle State";
    }
    @Override
    public void next(ATMMachine atmMachine) {
        System.out.println("Card Inserted. Moving to Card Inserted State.");
        // atmMachine.setCurrentState(new CardInsertedState());
    }
}

class ATMMachine {
    private AtmInventory inventory;
    private AtmState currentState;
    public ATMMachine(AtmInventory inventory) {
        this.inventory = inventory;
    }
    public Map<CashType, Integer> withdraw(double amount) {
        
    }
}

public class Main{
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}