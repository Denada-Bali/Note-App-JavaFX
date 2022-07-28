/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package denaldas.project;

import MySQL.ConnectToDatabase;
import MySQL.MySQL;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.Connection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Denalda
 */
public class DashboardController implements Initializable {

    @FXML
    private ListView<String> noteListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ConnectToDatabase connectNow = new ConnectToDatabase();
        Connection connect = connectNow.connect();

   String SELECT_QUERY_NOTES = "SELECT `title`, `start_Date` FROM `note`;";
        
   try{
       
       Statement statement = connect.createStatement();
       ResultSet queryOutput = statement.executeQuery(SELECT_QUERY_NOTES);
       
       while (queryOutput.next()){
           String title = queryOutput.getString("title");
           Date date = queryOutput.getDate("start_date");
           String listOut = title + "\"" + date +"\""; 
           
           noteListView.getItems().add(listOut);
       }
   }catch(Exception e){
       e.printStackTrace();
   }
        
    }

}
