
package bank.manager;

import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Paul
 */
public class NewAccountController {
    
    @FXML
    private Label message;
    
    @FXML
    private TextField firstName;
    
    @FXML
    private TextField lastName;
    
    @FXML
    private TextField ssn;
    
    @FXML
    private TextField balance;
    
    
    
    public void createChecking(ActionEvent event) throws SQLException{ //Creates a new checking account
        if(checkSSN() && checkBalance()){
            String fName = firstName.getText();
            String lName = lastName.getText();
            double amount = Double.parseDouble(balance.getText());
            String social = ssn.getText();
           CheckingAccount newChecking = new CheckingAccount(fName, lName, amount, social); 
        
            Statement stmt = BankManager.conn.createStatement();
            String sql = "INSERT INTO accounts (first_name, last_name, SSN, balance, account_number, account_type) VALUES ('" 
                          + fName + "', '" + lName + "', '" + social + "', '" + amount + "', '" + newChecking.getAccountNumber() + "', 'checking')";
            stmt.executeUpdate(sql);
            message.setText("Successfully created Checking Account!");
        }
    }
    
    public void createSavings(ActionEvent event) throws SQLException{ //Creates a new savings account
        if(checkSSN() && checkBalance()){
            String fName = firstName.getText();
            String lName = lastName.getText();
            double amount = Double.parseDouble(balance.getText());
            String social = ssn.getText(); 
            SavingsAccount newSavings = new SavingsAccount(fName,lName,amount,social);
            
            Statement stmt = BankManager.conn.createStatement();
            String sql = "INSERT INTO accounts (first_name, last_name, SSN, balance, account_number, account_type) VALUES ('" 
                          + fName + "', '" + lName + "', '" + social + "', '" + amount + "', '" + newSavings.getAccountNumber() + "', 'savings')";
            stmt.executeUpdate(sql);
            message.setText("Successfully created Savings Account!");
        }
    }
    
    private boolean checkSSN(){ //Helper method: Checks for valid social security
        String social = ssn.getText();
        if(social.length() != 11){
            message.setText("Invalid SSN: Must use ###-##-#### format");
            return false;
        }
        for(int i=0;i<11;i++){
            if (i==3 || i==6){
                if (social.charAt(i) != '-'){
                    message.setText("Invalid SSN: Must use ###-##-#### format");
                    return false;
                }
               
            }
            else if (!Character.isDigit(social.charAt(i))){
                message.setText("Invalid SSN: Must use ###-##-#### format");
                return false;
            }
        }
        return true;
    }
    
    private boolean checkBalance(){ //Helper method: Checks for valid starting balance
        double amount = Double.parseDouble(balance.getText());
        if(amount < 0){
            message.setText("Cannot enter a negative balance.");
            return false;
        }
        return true;
    }
    
}
