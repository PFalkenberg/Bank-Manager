
package bank.manager;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Paul
 */
public class MainController {
    
    //Each method opens a different window of the program
    
    public void manageAccount(ActionEvent event) throws IOException{
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ManageAccount.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Manage Account");
            primaryStage.setScene(scene);
            primaryStage.show();
    }
    
    public void newAccount(ActionEvent event) throws IOException{
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("New Account.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Create New Account");
            primaryStage.setScene(scene);
            primaryStage.show();
    }
    
    public void viewAccounts(ActionEvent event) throws IOException{
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ViewAccounts.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("View Accounts");
            primaryStage.setScene(scene);
            primaryStage.show();
        
    }
    
}
