
package bank.manager;

/**
 *
 * @author Paul
 */
public class CheckingAccount extends BankAccount {
    
    private static final double FEE = 0.2; //Checking fee
    
    public CheckingAccount(String first, String last, double amount, String social){
        super(first, last, amount, social);
    }
    
    @Override
    public void withdraw(double amount){
        amount+= FEE;
        super.withdraw(amount);
    }
    
}
