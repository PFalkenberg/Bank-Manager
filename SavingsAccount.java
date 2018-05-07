
package bank.manager;

/**
 *
 * @author Paul
 */
public class SavingsAccount extends BankAccount {
    
    private static final double RATE = .03; //interest rate
    
    public SavingsAccount(String first, String last, double amount, String social){
        super(first, last, amount, social);
    }
    
    public void addInterest(){
        double newBalance = getBalance() + (getBalance()*.03 /12 ); //One month's interest
        setBalance(newBalance);
    }
}
