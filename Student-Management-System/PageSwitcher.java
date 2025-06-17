import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * The PageSwitcher class provides utility methods for switching between different pages/views 
 * within a JavaFX application.
 */
public class PageSwitcher {
    /**
     * Default constructor for the PageSwitcher class.
     * This constructor initializes a new PageSwitcher instance.
     */
    public PageSwitcher() {
        // Default constructor
    }
    /**
     * Switches to the specified FXML page.
     * 
     * @param event The ActionEvent triggering the page switch.
     * @param fxmlFileName The filename of the FXML file representing the target page.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    public static void switchToPage(ActionEvent event, String fxmlFileName) throws IOException {
        try {
            Parent root = FXMLLoader.load(PageSwitcher.class.getResource(fxmlFileName));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    /**
     * Signs out the current user and closes the application window.
     * 
     * @param event The ActionEvent triggering the sign out action.
     * @throws IOException If an I/O error occurs while closing the window.
     */
    public static void signOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); 
    }
}