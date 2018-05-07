
package bank.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Paul
 */
public class ViewAccountsController {
    
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private ObservableList<AccountList> data;
    
    @FXML
    private TableView<AccountList> table;
    
    @FXML
    private TableColumn<AccountList, String> colFirstName;
    
    @FXML
    private TableColumn<AccountList, String> colLastName;
    
    @FXML 
    private TableColumn<AccountList, String> colSSN;
    
    @FXML 
    private TableColumn<AccountList, Integer> colAccountNumber;
    
    @FXML
    private TableColumn<AccountList, Double> colBalance;
    
    @FXML 
    private TableColumn<AccountList, String> colAccountType;
    
    
    private void setCellTable(){
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colSSN.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        colAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colAccountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));
    }
    
    public void retrieveData(ActionEvent event) throws SQLException{ //Populates table with all accounts in the Database
        setCellTable();
        data = FXCollections.observableArrayList();
        pst = BankManager.conn.prepareStatement("SELECT * FROM accounts");
        rs = pst.executeQuery();
        while(rs.next()){
            data.add(new AccountList(rs.getString(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getDouble(5), rs.getString(6)));
        }
        table.setItems(data);
    }
    
}
