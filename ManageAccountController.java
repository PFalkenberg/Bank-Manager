
package bank.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 *
 * @author Paul
 */
public class ManageAccountController {
    
    private String accountType;
    private CheckingAccount manageChecking;
    private SavingsAccount manageSavings;
   
    
    @FXML
    private TextField amount;
    
    @FXML
    private Label message;
    
    @FXML
    private TextField accountNumber;
    
    
    public void deposit(ActionEvent event){ //Deposits money to account
        buildObject();
        double money = Double.parseDouble(amount.getText());
        if(money < 0){ //Prevents negative amounts from being entered
            message.setText("Cannot enter a desposit a negative amount");
        }
        else{
            if(accountType.equals("checking")){
                try {
                    manageChecking.deposit(money);
                    Statement stmt = BankManager.conn.createStatement();
                    String sql = "UPDATE accounts SET balance = '" + manageChecking.getBalance() + "' WHERE account_number = " + Integer.parseInt(accountNumber.getText());
                    stmt.executeUpdate(sql);
                    message.setText("Successfully deposited to Checking Account!");
                } catch (SQLException ex) {
                    Logger.getLogger(ManageAccountController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            else{
                try {
                    manageSavings.deposit(money);
                    Statement stmt = BankManager.conn.createStatement();
                    String sql = "UPDATE accounts SET balance = '" + manageSavings.getBalance() + "' WHERE account_number = " + Integer.parseInt(accountNumber.getText());
                    stmt.executeUpdate(sql);
                    message.setText("Successfully deposited to Savings Account!");
                } catch (SQLException ex) {
                    Logger.getLogger(ManageAccountController.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
         } 
    }
    
    public void withdraw(ActionEvent event){ //Withdraws money from acccount
        buildObject();
        double money = Double.parseDouble(amount.getText());
        if(money < 0){ //Prevents negative amounts from being entered 
            message.setText("Cannot enter a withdraw a negative amount");
        }
        else{
            if(accountType.equals("checking")){ 
                if(money > manageChecking.getBalance()){ //Prevents withdrawing more than the current balance
                    message.setText("Insufficient funds to withdraw that amount");
                }
                else{
                    try {
                        manageChecking.withdraw(money);
                        Statement stmt = BankManager.conn.createStatement();
                        String sql = "UPDATE accounts SET balance = '" + manageChecking.getBalance() + "' WHERE account_number = " + Integer.parseInt(accountNumber.getText());
                        stmt.executeUpdate(sql);
                        message.setText("Successfully withdrew from Checking Account!");
                    } catch (SQLException ex) {
                        Logger.getLogger(ManageAccountController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            else{
                if(money > manageSavings.getBalance()){ //Prevents withdrawing more than the current balance
                    message.setText("Insufficient funds to withdraw that amount");
                }
                else{
                    try {
                        manageSavings.withdraw(money);
                        Statement stmt = BankManager.conn.createStatement();
                        String sql = "UPDATE accounts SET balance = '" + manageSavings.getBalance() + "' WHERE account_number = " + Integer.parseInt(accountNumber.getText());
                        stmt.executeUpdate(sql);
                        message.setText("Successfully withdrew from Savings Account!");
                    } catch (SQLException ex) {
                        Logger.getLogger(ManageAccountController.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            }
         }
    }
    
    public void addInterest(ActionEvent event){ //Adds one month's interest to savings account
        buildObject();
        if(accountType.equals("checking")){ //Prevents interest from being added to Checking accounts
            message.setText("Checking accounts do not accrue interest!");
        }
        else{
            try {
                    manageSavings.addInterest();
                    Statement stmt = BankManager.conn.createStatement();
                    String sql = "UPDATE accounts SET balance = '" + manageSavings.getBalance() + "' WHERE account_number = " + Integer.parseInt(accountNumber.getText());
                    stmt.executeUpdate(sql);
                    message.setText("Successfully added 1 month's interest to Savings Account!");
                } catch (SQLException ex) {
                    Logger.getLogger(ManageAccountController.class.getName()).log(Level.SEVERE, null, ex);

                }
        }
    }
    
    private void buildObject(){ //Helper method: Retrieves account information from Database and uses it to create a Java object
        try {
            int accountNum = Integer.parseInt(accountNumber.getText());
            Statement stmt = BankManager.conn.createStatement();
            String sql = "SELECT * FROM accounts WHERE account_number = " + accountNum;
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){
                accountType = rs.getString("account_type"); //Checks account type to ensure proper object type is created
                if (accountType.equals("checking")){
                    manageChecking = new CheckingAccount(rs.getString(1), rs.getString(2), rs.getDouble(5), rs.getString(4));
                }
                else{
                    manageSavings = new SavingsAccount(rs.getString(1), rs.getString(2), rs.getDouble(5), rs.getString(4));
                }
            }
            else {
                message.setText("Account not found");
            }
        }    
            catch (SQLException ex) {
            Logger.getLogger(ManageAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}