
package bank.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 *
 * @author Paul
 */
public class LoginController {
    
    @FXML
    private Label status;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    
    @FXML
    public void Login(ActionEvent event) throws Exception{
        if (username.getText().equals("admin") && password.getText().equals("bank123")){ //Checks Username and Password
            status.setText("Login Success"); 
            
            Stage primaryStage = new Stage(); //Opens "Main" window
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); 
            Scene scene = new Scene(root);

            primaryStage.setTitle("Main");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
            status.setText("Invalid Login"); // Username or Password incorrect
    }
}