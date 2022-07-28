/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package denaldas.project;

import Clasess.FxmlLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Denalda
 */
public class MainBorderController implements Initializable {

    @FXML
    private Parent fxml;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Pane view;
    @FXML
    private BorderPane borderPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
     private void loadDashboardButton(ActionEvent event)throws IOException{

       FxmlLoader object = new FxmlLoader();
        view = object.getPage("dashboard");
        borderPane.setCenter(view);
         
     }
     
     @FXML
     private void loadAddNoteButton(ActionEvent event)throws IOException{

       FxmlLoader object = new FxmlLoader();
        view = object.getPage("AddNote");
        borderPane.setCenter(view);      
     }
     
      @FXML
     private void loadScheduleButton(ActionEvent event)throws IOException{

       FxmlLoader object = new FxmlLoader();
        view = object.getPage("Schedule");
        borderPane.setCenter(view);      
     }
     
      @FXML
     private void loadSettingsButton(ActionEvent event)throws IOException{

       FxmlLoader object = new FxmlLoader();
        view = object.getPage("Settings");
        borderPane.setCenter(view);
         
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
}
