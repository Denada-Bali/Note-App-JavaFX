/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clasess;

import denaldas.project.DenaldasProject;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Denalda
 */
public class FxmlLoader {
    private Pane view;
    
    public Pane getPage(String fileName){
       try{  
           
         URL fileUrl = DenaldasProject.class.getResource("/denaldas/project/"+ fileName +".fxml");
        if(fileUrl ==null){
            throw new java.io.FileNotFoundException("FXML file can't be found");
        }
       
            view = new FXMLLoader().load(fileUrl);
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("No page " + fileName + " please check FxmlLoader ");
        }
      return view;
    }
}
