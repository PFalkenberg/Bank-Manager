
package bank.manager;

/**
 *
 * @author Paul
 */
import java.util.Random;

public abstract class BankAccount {
    
    private double balance;
    private String firstName;
    private String lastName;
    private int accountNumber;
    private String ssn;
    private Random numberGen = new Random(); //Used to generate a random account number
    
    public BankAccount(){
        balance = 0;
        accountNumber = numberGen.nextInt(8999) + 1000; 
    }
    
    public BankAccount(String first, String last, double amount, String social){
        firstName = first;
        lastName = last;
        balance = amount;
        ssn = social;
        accountNumber = numberGen.nextInt(8999) + 1000;
    }
    
    public void deposit(double amount){
        balance += amount;
    }
    
    public void withdraw(double amount){
        try{
            if(balance<amount)
                throw new InsufficientFundsException("Insufficient funds to withdraw that amount.");
            else
                balance -= amount;
        }
        catch(InsufficientFundsException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void setBalance(double newBalance){
        balance = newBalance;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public double getBalance(){
        return balance;
    }
    
    public int getAccountNumber(){
        return accountNumber;
    }
    
    public String getSSN(){
        return ssn;
    }
}
