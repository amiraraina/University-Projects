// connect to database (for user role recognition)

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;

/**
 * The LoginController class manages the login functionality of the application.
 * It handles user authentication and navigation to different views based on user roles.
 */
public class LoginController {
    /**
     * Constructs a new LoginController instance.
     * This constructor initializes the LoginController.
     */
    public LoginController() {
        // Constructor logic
    }
    /** The login button in the user interface. */
    @FXML
    private Button login;

    /** The password field for entering the user's password. */
    @FXML
    private PasswordField passwordPasswordField;

    /** The text field for entering the user's username. */
    @FXML
    private TextField usernameTextField;

    /** The label used to display login messages. */
    @FXML
    private Label loginMessageLabel;

    /** The database connection object. */
    private Connection connect;

    /**
     * Handles the action when the login button is clicked.
     * It validates the entered username and password and initiates the login process.
     * @param e The ActionEvent representing the event of clicking the login button.
     */
    public void loginButtonAction(ActionEvent e) {
        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Fill in your username or password");
        }
    }

    /**
     * Validates the entered login credentials.
     * It checks if the username and password combination exist in the database,
     * retrieves the user's role, and navigates to the appropriate view based on the role.
     */
    public void validateLogin() {
        Connection connect = DatabaseConnection.getConnection();
    
        String verifyLoginRole = "SELECT role FROM userAccounts WHERE username = ? AND password = ?"; // to check role
        String verifyLogin = "SELECT count(1) FROM UserAccounts WHERE username = ? AND password = ?"; // to check if username+password exist/correct (exist count = 1)
    
        try {
            // Check if the username and password exist
            PreparedStatement countStatement = connect.prepareStatement(verifyLogin); // check if there is any matching in the UserAccount table (=1)
            countStatement.setString(1, usernameTextField.getText());
            countStatement.setString(2, passwordPasswordField.getText());
    
            ResultSet countResult = countStatement.executeQuery();
            
            if (countResult.next() && countResult.getInt(1) == 1) {
                // If the count is 1, retrieve the role
                PreparedStatement roleStatement = connect.prepareStatement(verifyLoginRole);
                roleStatement.setString(1, usernameTextField.getText());
                roleStatement.setString(2, passwordPasswordField.getText());
    
                ResultSet roleResult = roleStatement.executeQuery();
    
                if (roleResult.next()) { // successful login
                    String role = roleResult.getString("role");
                    //System.out.println(role);
    
                    loginMessageLabel.setText("Login successful!");
    
                    switch (role) {
                        case "admin":
                            Parent adminRoot = FXMLLoader.load(getClass().getResource("fxml/AdminHome.fxml"));
                            switchScene(adminRoot);
                            break;
                        case "student":
                            SessionManager.setCurrentUserId(usernameTextField.getText());
                            Parent studentRoot = FXMLLoader.load(getClass().getResource("fxml/StudentForm.fxml"));
                            switchScene(studentRoot);
                            break;
                        case "lecturer":
                            SessionManager.setCurrentUserId(usernameTextField.getText());
                            Parent lecturerRoot = FXMLLoader.load(getClass().getResource("fxml/LecturerMainForm.fxml"));
                            switchScene(lecturerRoot);
                            break;
                        default:
                            loginMessageLabel.setText("Invalid role.");
                    }
                }
            } else {
                loginMessageLabel.setText("Invalid username or password.");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Switches the scene to the specified root node.
     * It creates a new stage, sets the scene with the provided root node,
     * and hides the current login window.
     * @param root The Parent node representing the root of the new scene.
     */
    private void switchScene(Parent root) {
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        login.getScene().getWindow().hide();
    } 
}