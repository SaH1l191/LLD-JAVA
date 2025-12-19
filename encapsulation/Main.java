 
// restricts direct access to vars 
// and just access to var methods are given 
//declaring class vars as private : using getters,setters
// but using setters to handle controlled modification 


//key features of encapsulation : data hiding 
// modularity , security , fleibiliy using setters ,getters 

//here balnace is not directly modified and exploited , goes through valid updaets ,
// logi c ,etc 
class BankAccount{
    private String accountNumber;
    private double balance ;

    public BankAccount(String accountNumber,double balance){
        this.accountNumber = accountNumber;
        this.balance = balance ;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance ;
    }

    public void deposit(double amount){
        if(amount > 0){
            balance += amount ;
            System.out.println("deposited " + amount);
        }else{
            System.out.println("amount should be greater than 0");
        } 
    }
    public void withdraw(double amount){
        if(amount >0 && balance >= amount){
            balance -= amount ;
            System.out.println("withdrawn " + amount);
        }else{
            System.out.println("amount should be greater than 0 and balance should be greater than amount");
        }
    }

}    
public class Main{
    public static void main(String args[]) {
        BankAccount account = new BankAccount("1234567890",1000);
        account.deposit(100);
        account.withdraw(100);
        account.getAccountNumber();
        System.out.println("balance : " + account.getBalance());

    }
}
// impropved security of data ,ease of maintainence ,flexibility ,readability   
// disadvantes : slight overhead(additioal functions ),complexity 

//
