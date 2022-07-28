/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package denaldas.project;

import MySQL.MySQL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.sql.Date;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Denalda
 */
public class AddNoteController implements Initializable {

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXButton saveNoteBTN;

    @FXML
    private TextField title;

//    private Date date;
//    
    private Timestamp time;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void handleSaveNote_btn(ActionEvent event) throws SQLException {

        stage = (Stage) saveNoteBTN.getScene().getWindow();

        String Title = title.getText();
        String Description = description.getText();

        long millis = System.currentTimeMillis();
        java.sql.Date start_date = new java.sql.Date(millis);

        System.out.println(Title);
        System.out.println(Description);
        System.out.println(start_date);

        if (Title.isEmpty() || Description.isEmpty()) {
            String title = "Form Error";
            String message = "Please fill the required fields.";

            WarningMessage(title, message);

            return;
        } else {
            if (Title.length() > 50) {

                String title = "Form Error";
                String message = "The title should not be more than 50 characters.";

                ErrorMessage(title, message);

            } else {

                MySQL mySQL = new MySQL();
                mySQL.insertNewNote(Title, Description, start_date);

                String title = "Saved";
                String message = "Saved successfully!";

                SucessMessage(title, message);

            }
        }
    }

    private void ErrorMessage(String title, String message) {

        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.millis(3000));
    }

    private void SucessMessage(String title, String message) {

        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }

    private void WarningMessage(String title, String message) {

        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndDismiss(Duration.millis(3000));
    }

    private void InformationMessage(String title, String message) {

        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
    }
}
