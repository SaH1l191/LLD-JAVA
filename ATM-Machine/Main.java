
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
    BALANCE_INQUIRY,
    // FUND_TRANSFER
}

interface TransactionStrategy {
    void execute(ATMMachine atm, int amount);
}
class WithdrawTransactionStrategy  implements TransactionStrategy {
    @Override
    public void execute(ATMMachine atm, int amount) {
        atm.performWithdrawal(amount);
    }
}
class DepositTransactionStrategy implements TransactionStrategy {
    @Override
    public void execute(ATMMachine atm, int amount) {
        if(atm.getCurrentAccount() == null) {
            throw new RuntimeException("No account selected");
        }
        if(amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }
        //only 100s allowed for deposit to simplify inventory management
        if (amount % 100 != 0) {
            throw new RuntimeException("Only multiples of 100 allowed");
        }

        atm.getCurrentAccount().deposit(amount);

        int count = amount / CashType.HUNDRED.getValue();
        atm.getInventory().updateInventory(CashType.HUNDRED, count);

        System.out.println("Deposit successful. Balance: " 
            + atm.getCurrentAccount().getBalance());
    }
}
class BalanceInquiryStrategy implements TransactionStrategy {
    @Override
    public void execute(ATMMachine atm, int amount) {
        if (atm.getCurrentAccount() == null) {
            throw new RuntimeException("No account selected");
        }
        System.out.println("Current balance: " 
            + atm.getCurrentAccount().getBalance());
    }
}

class TransactionStrategyFactory {
    public static TransactionStrategy getStrategy(TransactionType type) {
        switch (type) {
            case WITHDRAW:
                return new WithdrawTransactionStrategy();
            case DEPOSIT:
                return new DepositTransactionStrategy();
            case BALANCE_INQUIRY:
                return new BalanceInquiryStrategy();
            default:
                throw new RuntimeException("Invalid transaction type");
        }
    }
}

class Card {

    // private final String cardNumber;
    private final String accountNumber;
    // private String cardType;
    protected String pin;
    protected int pinAttempts;
    protected static final int MAX_ATTEMPTS = 3;
    private boolean isBlocked = false;

    public Card(String cardNumber, String cardType, String pin, String accountNumber) {
        // this.cardNumber = cardNumber;
        // this.cardType = cardType;
        this.pin = pin;
        this.pinAttempts = 0;
        this.accountNumber = accountNumber;
    }

    protected void blockCard() {
        isBlocked = true;
        System.out.println("Card blocked.");
    }

    protected void incrementPinAttempts() {
        pinAttempts++;
    }

    protected void resetPinAttempts() {
        pinAttempts = 0;
    }

    protected boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}

abstract class Account {
    protected final String accountNumber;
    protected int balance;
    public Account(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    public void deposit(int amount) {balance += amount;}
    public int getBalance() {return balance;}
    public String getAccountNumber() {return accountNumber;}

}

class SavingsAccount extends Account {  
    private static final int MIN_BALANCE = 100;
    public SavingsAccount(String accountNumber, int balance) {
        super(accountNumber, balance); 
    }  
    @Override
    public boolean withdraw(int amount) {
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class CurrentAccount extends Account {
    private static final int OVERDRAFT_LIMIT = 500;
    public CurrentAccount(String accNo, int balance) {
        super(accNo, balance);
    }
    @Override
    public boolean withdraw(int amount) {
        if (balance + OVERDRAFT_LIMIT >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}


interface WithdrawStrategy {
    CashType[] getOrder();
}

class HighestFirstStrategy implements WithdrawStrategy {

    public CashType[] getOrder() {
        return new CashType[]{
            CashType.TWO_THOUSAND,
            CashType.FIVE_HUNDRED,
            CashType.TWO_HUNDRED,
            CashType.HUNDRED
        };
    }
}

class LowestFirstStrategy implements WithdrawStrategy {

    public CashType[] getOrder() {
        return new CashType[]{
            CashType.HUNDRED,
            CashType.TWO_HUNDRED,
            CashType.FIVE_HUNDRED,
            CashType.TWO_THOUSAND
        };
    }
}

class AtmInventory {

    private Map<CashType, Integer> cashInventory;

    public AtmInventory(Map<CashType, Integer> cashInventory) {
        this.cashInventory = new HashMap<>();
        initializeInventory(cashInventory);
    }

    public void initializeInventory(Map<CashType, Integer> cashInventory) {
        for (Map.Entry<CashType, Integer> entry : cashInventory.entrySet()) {
            this.cashInventory.put(entry.getKey(), entry.getValue());
        }
        printInventory();
    }

    public void printInventory() {
        System.out.println("Current ATM Inventory:");
        for (Map.Entry<CashType, Integer> entry : cashInventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void updateInventory(CashType cashType, int count) {
        cashInventory.put(cashType, cashInventory.getOrDefault(cashType, 0) + count);
    }

    public int getTotalCash() {
        int total = 0;
        for (Map.Entry<CashType, Integer> entry : cashInventory.entrySet()) {
            total += entry.getKey().getValue() * entry.getValue();
        }
        return total;
    }

    public Map<CashType, Integer> dispenseCash(int amount, WithdrawStrategy strategy) {

        Map<CashType, Integer> tempInventory = new HashMap<>(cashInventory);
        Map<CashType, Integer> result = new HashMap<>();

        int remaining = amount;

        for (CashType type : strategy.getOrder()) {
            int value = type.getValue();
            int available = tempInventory.getOrDefault(type, 0);

            int needed = remaining / value;
            int toUse = Math.min(needed, available);

            if (toUse > 0) {
                result.put(type, toUse);
                remaining -= toUse * value;
                tempInventory.put(type, available - toUse);
            }
        }

        if (remaining > 0) {
            throw new RuntimeException("Cannot dispense exact amount");
        }

        cashInventory.clear();
        cashInventory.putAll(tempInventory);

        return result;
    }

    public int getCount(CashType type) {
        return cashInventory.getOrDefault(type, 0);
    }

    public Map<CashType, Integer> getMutableInventory() {
        return cashInventory;
    }
}

interface AtmState {

    void insertCard(ATMMachine atm, Card card);
    void enterPin(ATMMachine atm, String pin);
    void selectOperation(ATMMachine atm, TransactionType type);
    void performTransaction(ATMMachine atm, int amount);
    void ejectCard(ATMMachine atm); 
}

abstract class BaseState implements AtmState {

    @Override
    public void insertCard(ATMMachine atm, Card card) {
        throw new RuntimeException("Invalid operation in current state");
    }

    @Override
    public void enterPin(ATMMachine atm, String pin) {
        throw new RuntimeException("Invalid operation in current state");
    }

    @Override
    public void selectOperation(ATMMachine atm, TransactionType type) {
        throw new RuntimeException("Invalid operation in current state");
    }

    @Override
    public void performTransaction(ATMMachine atm, int amount) {
        throw new RuntimeException("Invalid operation in current state");
    }

    @Override
    public void ejectCard(ATMMachine atm) {
        throw new RuntimeException("Invalid operation in current state");
    } 
}

class IdleState extends BaseState {

    @Override
    public void insertCard(ATMMachine atm, Card card) {
        if (card.isBlocked()) {
            throw new RuntimeException("Blocked card cannot be inserted");
        }
        System.out.println("Card Inserted. Moving to Card Inserted State.");
        atm.setCurrentCard(card);
        atm.setCurrentState(new CardInsertedState());
    }
}
 
class CardInsertedState extends BaseState {
    @Override
    public void enterPin(ATMMachine atm, String pin) {
        if (atm.getCurrentCard() == null) {
            throw new RuntimeException("No card inserted");
        }
        if (atm.getCurrentCard().isBlocked()) {
            throw new RuntimeException("Blocked card");
        }
        if (atm.getCurrentCard().validatePin(pin)) {
            atm.getCurrentCard().resetPinAttempts();
            System.out.println("Pin entered. Moving to Select Operation State.");
            String accountNumber = atm.getCurrentCard().getAccountNumber();
            Account acc = atm.getAccountByNumber(accountNumber);
            if (acc == null) throw new RuntimeException("Account not found");
            atm.setCurrentAccount(acc);
            atm.setCurrentState(new SelectOperationState());

        } else {
            atm.getCurrentCard().incrementPinAttempts();
            System.out.println("Invalid PIN");
            if (atm.getCurrentCard().pinAttempts >= Card.MAX_ATTEMPTS) {
                atm.getCurrentCard().blockCard();
                System.out.println("Too many incorrect attempts. Card blocked.");
                atm.setCurrentState(new EjectCardState());
            }
        }
    }
}

class SelectOperationState extends BaseState {

    @Override
    public void selectOperation(ATMMachine atm, TransactionType type) {
        atm.setSelectedTransaction(type);
        System.out.println("Operation selected: " + type + ". Moving to Process Transaction State.");
        atm.setCurrentState(new TransactionState());
    }
}

class TransactionState extends BaseState {

    @Override
    public void performTransaction(ATMMachine atm, int amount) {
        TransactionStrategy strategy = atm.getCurrentTransactionStrategy();
        if(strategy == null) {
            throw new RuntimeException("No transaction selected");
        }
        strategy.execute(atm, amount);
        System.out.println("Transaction completed.");
        atm.setCurrentState(new EjectCardState());
    }

}

class EjectCardState extends BaseState {

    @Override
    public void ejectCard(ATMMachine atm) {
        System.out.println("Card ejected");
        atm.setCurrentCard(null);
        atm.setCurrentAccount(null);
        atm.setCurrentState(new IdleState());
        atm.setCurrentTransactionStrategy(null);
    }
}

class ATMMachine {

    private AtmInventory inventory;
    private AtmState currentState;
    private Card currentCard;
    private TransactionStrategy currentTransactionStrategy;
    private WithdrawStrategy withdrawStrategy;
    private Map<String, Account> accounts;
    private Account currentAccount;

    public ATMMachine(AtmInventory inventory, Map<String, Account> accounts) {
        this.inventory = inventory;
        this.accounts = accounts;
        this.withdrawStrategy = new HighestFirstStrategy(); // default strategy
        currentState = new IdleState();
    }

    public void setWithdrawStrategy(WithdrawStrategy strategy) {
        this.withdrawStrategy = strategy;
    }

    public void setCurrentState(AtmState state) {
        this.currentState = state;
    }

    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    public void setSelectedTransaction(TransactionType type) {
        this.currentTransactionStrategy = TransactionStrategyFactory.getStrategy(type);
    }

    public void selectOperation(TransactionType type) {
        currentState.selectOperation(this, type);
    }

    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }

    public void ejectCard() {
        currentState.ejectCard(this);
    }

    protected void performWithdrawal(int amount) {

        if (currentAccount == null) {
            throw new RuntimeException("No account selected");
        }
        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }
        if (inventory.getTotalCash() < amount) {
            throw new RuntimeException("ATM has insufficient cash");
        }
        if (!currentAccount.withdraw(amount)) {
            throw new RuntimeException("Insufficient balance");
        }
        try {
            Map<CashType, Integer> dispensedCash = inventory.dispenseCash(amount, withdrawStrategy);
            printTransaction(dispensedCash);
        } catch (RuntimeException e) {
            currentAccount.deposit(amount); //revert
            throw e;
        }
    }

    public void performTransaction(int amount) {
        currentState.performTransaction(this, amount);
    }

    public void setCurrentTransactionStrategy(TransactionStrategy strategy) {
        this.currentTransactionStrategy = strategy;
    }

    public void printTransaction(Map<CashType, Integer> dispensedCash) {
        System.out.println("Dispensed Cash:");
        for (Map.Entry<CashType, Integer> entry : dispensedCash.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public AtmState getCurrentState() {
        return currentState;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public Account getAccountByNumber(String accNo) {
        return accounts.get(accNo);
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public AtmInventory getInventory() {
        return inventory;
    }

    public TransactionStrategy getCurrentTransactionStrategy() {
        return currentTransactionStrategy;
    }
}

public class Main {

    public static void main(String[] args) {

        Map<CashType, Integer> cashMap = new HashMap<>();
        cashMap.put(CashType.HUNDRED, 10);
        cashMap.put(CashType.TWO_HUNDRED, 10);
        cashMap.put(CashType.FIVE_HUNDRED, 10);
        cashMap.put(CashType.TWO_THOUSAND, 5);
        AtmInventory inventory = new AtmInventory(cashMap);

        Map<String, Account> accounts = new HashMap<>();
        Account savings = new SavingsAccount("SAV123", 5000);
        Account current = new CurrentAccount("CUR123", 2000);

        accounts.put("SAV123", savings);
        accounts.put("CUR123", current);

        Card savingsCard = new Card("1111", "VISA", "1234", "SAV123");
        Card currentCard = new Card("2222", "MASTER", "5678", "CUR123");

        ATMMachine atm = new ATMMachine(inventory, accounts);
        

        //Test flows :- 

        System.out.println("\n=== FLOW 1: SAVINGS -> BALANCE INQUIRY ===");
        atm.insertCard(savingsCard);
        atm.enterPin("1234");
        atm.selectOperation(TransactionType.BALANCE_INQUIRY);
        atm.performTransaction(0); 
        atm.ejectCard();

        System.out.println("\n=== FLOW 2: SAVINGS -> Wrong Pin -> BALANCE INQUIRY ===");
        atm.insertCard(savingsCard);
        atm.enterPin("1234");
        atm.selectOperation(TransactionType.BALANCE_INQUIRY);
        atm.performTransaction(0); 
        atm.ejectCard();

        System.out.println("\n=== FLOW 3: SAVINGS → DEPOSIT ===");
        atm.insertCard(savingsCard);
        atm.enterPin("1234");
        atm.selectOperation(TransactionType.DEPOSIT);
        atm.performTransaction(1000); // multiples of 100
        atm.ejectCard();
        System.out.println("\n Inventory after deposit:");
        inventory.printInventory();

    }
}
