
package bank.manager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;
/**
 *
 * @author Paul
 */
public class BankManager extends Application {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pass";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/bankmanager?zeroDateTimeBehavior=convertToNull";
    protected static Connection conn = null; //Connection object made protected so it can be accessed from all classes within the package
    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root, 300, 250);

            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        try{
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);  //Establish connection to the Database  
        }
        catch(SQLException e){
            System.err.println(e);
        }
        launch(args);
    }   
}
