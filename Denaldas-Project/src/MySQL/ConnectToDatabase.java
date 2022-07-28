/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQL;

import  java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Denalda
 */
public class ConnectToDatabase {
    
    public Connection databaseLink;
        
    public static final String DATABASE_NAME= "dbali";
    public static final String USERNAME= "root";
    public static final String PASSWORD= "";
    public static final String URL= "jdbc:mysql://localhost/"+DATABASE_NAME;
    
    public Connection connect(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return databaseLink;
    }
}
