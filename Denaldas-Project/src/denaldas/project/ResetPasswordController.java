/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package denaldas.project;

import MySQL.MySQL;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Denalda
 */
public class ResetPasswordController implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button resetPASS_btn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    public void handleResetPassword_btn(ActionEvent event) throws SQLException {

        stage = (Stage) resetPASS_btn.getScene().getWindow();

        String Email = email.getText();
        String Password = password.getText();

        System.out.println(Email);
        System.out.println(Password);

        if (Email.isEmpty() || Password.isEmpty()) {
             String title = "Form Error";
            String message = "Please fill the required fields.";
            
            WarningMessage(title, message);
            return;
        } else {
            if (Password.length() < 8) {
                String title = "Form Error";
                String message = "Your password must be at least 8 characters.";
                
                ErrorMessage(title, message);
                
            } else {

                MySQL mySQL = new MySQL();
                mySQL.updateData(Email, Password);
               
               String title = "Reset Password";
                    String message = "Updated successfully!";
                 
                   SucessMessage(title, message);
            }
        }
    }

    @FXML
    public void backToSignin(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
     @FXML
    private void minimize_clicked(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void maximized_clicked(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }
    
      private void ErrorMessage(String title, String message){
                  
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.millis(3000));
    }
    
     private void SucessMessage(String title, String message){
                  
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }
     
      private void WarningMessage(String title, String message){
                  
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndDismiss(Duration.millis(3000));
    }
      
       private void InformationMessage(String title, String message){
                  
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
    }
}
