import dataManager.StudentList;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import object.Student;

/**
 * The StudentFormController class controls the functionality of the student form in the application.
 * It implements the Initializable interface to initialize the controller class.
 */
public class StudentFormController implements Initializable {
    /**
     * Default constructor.
     * Constructs a new StudentFormController instance.
     */
    public StudentFormController() {
        // Default constructor
    }
    /** The plan course button. */
    @FXML
    private Button planCourseBtn;

    /** The profile image view. */
    @FXML
    private ImageView profile;

    /** The text displaying the student's name. */
    @FXML
    private Text studentName;

    /** The view course button. */
    @FXML
    private Button viewCourseBtn;

    /** The sign-out button. */
    @FXML
    private Button signOutBtn;

    /**
     * Initializes the controller class.
     * Retrieves the student's name and sets it in the welcome greeting.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Get student name in welcome greeting
        int studentId =Integer.parseInt(SessionManager.getCurrentUserId());
        // System.out.println(studentId);
        String name = StudentList.getNameFromStudentList(StudentList.students, studentId);
        studentName.setText(name);
    }
    
    /**
     * Switches to the student page.
     * @param event The action event triggered when the button is clicked.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void switchToStudentPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/StudentCourseForm.fxml");
    }

    /**
     * Switches to the plan page.
     * @param event The action event triggered when the button is clicked.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void switchToPlanPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/StudentPlanForm.fxml");
    }
   
    /**
     * Signs out the current user and switches to the login page.
     * @param event The action event triggered when the button is clicked.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void signOut(ActionEvent event) throws IOException {
        PageSwitcher.signOut(event);
        PageSwitcher.switchToPage(event, "fxml/login.fxml");
    }
}