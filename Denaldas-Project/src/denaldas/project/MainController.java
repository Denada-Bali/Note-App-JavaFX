/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package denaldas.project;

import MySQL.MySQL;
import java.sql.SQLException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.CheckBox;

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
public class MainController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button signInBtn;
    @FXML
    private CheckBox rememberMe;

    Preferences preferences;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        preferences = Preferences.userNodeForPackage(MainController.class);
//
//        if (preferences != null) {
//            if (preferences.get("email", null) != null && !preferences.get("email", null).isEmpty()) {
//                email.setText(preferences.get("email", null));
//                password.setText(preferences.get("password", null));
//            }
//        }
    }

    @FXML
    public void handleLogin_Button(ActionEvent event) throws SQLException {

        String Email = email.getText().trim();
        String Password = password.getText().trim();

        //Window window = signInBtn.getScene().getWindow();
        stage = (Stage) signInBtn.getScene().getWindow();

        if (Email.isEmpty() || Password.isEmpty()) {
 
            String title = "Form Error";
            String message = "Please fill the required fields.";
            
            WarningMessage(title, message);
 
            return;
        } else {
            if (Password.length() < 8) {
                //showAlert(Alert.AlertType.INFORMATION, stage, "Form Error!", "Your password must be at least 8 characters.");

                String title = "Form Error";
                String message = "Your password must be at least 8 characters.";
                
                InformationMessage(title, message);

            } else {

                MySQL mySQL = new MySQL();
                boolean signin = mySQL.validate(Email, Password);

                if (!signin) {
                    String title = "Failed";
                    String message = "The Email and Password you entered is incorrect.";
                    
                    ErrorMessage(title, message);

                }else {
                    //infoBox("Login Successful!", null, "Successful");

//                    if (rememberMe.isSelected()) {
//                        preferences.put("email", Email);
//                        preferences.put("password", Password);
//                    } else {
//                        preferences.put("emailTextField", "");
//                        preferences.put("password", "");
//                    }
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("MainBorder.fxml"));
                        stage = (Stage) rememberMe.getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        String title = "Sign In";
                        String message = "Login successfully!";
                        
                        SucessMessage(title, message);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @FXML
    public void handleSignup_btn(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void handleLostPassword_hyperlink(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
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

        Node node = (Node) event.getSource();

        stage = (Stage) node.getScene().getWindow();

        stage.setFullScreenExitHint(" ");
        //  stage.setFullScreen(true);

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
