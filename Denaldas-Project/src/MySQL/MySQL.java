/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Denalda
 */
public class MySQL {
    
    @FXML
    private ListView<String> noteListView;

    public static final String DATABASE_NAME = "dbali";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String URL = "jdbc:mysql://localhost/" + DATABASE_NAME;

    private static final String SELECT_QUERY = "SELECT * FROM signup WHERE email = ? and password = ?";
    private static final String INSERT_QUERY = "INSERT INTO signup (username, email, password) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE signup SET password = ? WHERE email = ?";

    private static final String INSERT_QUERY_NOTES = "INSERT INTO note (title, description, start_date) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY_NOTES = "SELECT title, description, start_date FROM note";

    public boolean validate(String email, String password) throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {

            String title = "No Connection!!";
            String message = "Check your database connection!";

            TRAY_Notification(title, message);

            e.printStackTrace();

        }
        return false;
    }

    public void insertData(String username, String email, String password) throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();  // This line execute the query or update query
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(String email, String password) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();  // This line execute the query or update query
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewNote(String title, String description, Date start_date) throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_NOTES)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setDate(3, start_date);

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();  // This line execute the query or update query
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getData(String title, Date start_date) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_NOTES)) {
           
            preparedStatement.setString(1, title);
            preparedStatement.setDate(3, start_date);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void TRAY_Notification(String title, String message) {

        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.millis(3000));
    }
}
