import object.*;
import dataManager.StudentList;
import dataManager.CourseList;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The StudentCourseFormController class controls the functionality of the course form in the student interface.
 * It implements the Initializable interface to initialize the controller class.
 */
public class StudentCourseFormController implements Initializable {
    /**
     * Default constructor.
     * Constructs a new StudentCourseFormController.
     */
    public StudentCourseFormController() {
        // Default constructor
    }
    /** The TableView displaying the list of courses available to the student. */
    @FXML
    private TableView<Course> courseTableView;
    
     /** The TableColumn displaying the credit hours of each course in the TableView. */
    @FXML
    private TableColumn<Course, Integer> creditCol;

    /** The Button for editing course details. */
    @FXML
    private Button editCourseBtn;

    /** The TableColumn displaying the ID of each course in the TableView. */
    @FXML
    private TableColumn<Course, String> idCol;

    /** The TableColumn displaying the lecturer of each course in the TableView. */
    @FXML
    private TableColumn<Course, Lecturer> lecturerCol;

    /** The Button for planning courses. */
    @FXML
    private Button planCourseBtn;

    /** The ImageView displaying the student's profile picture. */
    @FXML
    private ImageView profile;

    /** The TableColumn displaying the prerequisites of each course in the TableView. */
    @FXML
    private TableColumn<Course, Course> reqCol;

    /** The TableColumn displaying additional notes for each course in the TableView. */
    @FXML
    private TableColumn<Course, Course> noteCol;

    /** The Button for viewing course details. */
    @FXML
    private Button viewCourseBtn;

    /** The Text field displaying the name of the currently logged-in student. */
    @FXML
    private Text studentName;

    /** The Button for signing out of the application. */
    @FXML
    private Button signOutBtn;

    /** The Stage of the JavaFX application. */
    private Stage stage;

    /** The Scene of the JavaFX application. */
    private Scene scene;

    /** The Parent root of the JavaFX application. */
    private Parent root;

    /**
     * Initializes the controller class.
     * @param location The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize columns
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        creditCol.setCellValueFactory(new PropertyValueFactory<>("creditHour"));
        lecturerCol.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        reqCol.setCellValueFactory(new PropertyValueFactory<>("requirement"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));

        //Get student name in welcome greeting
        int studentId =Integer.parseInt(SessionManager.getCurrentUserId());
        String name = StudentList.getNameFromStudentList(StudentList.students, studentId);
        studentName.setText(name);

        // Load data into the table
        courseTableView.setItems(FXCollections.observableArrayList(CourseList.course));
    }
      
    /**
     * Switches the scene to the student page.
     * @param event The ActionEvent that triggered the method call.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void switchToStudentPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/StudentCourseForm.fxml");
    }

    /**
     * Switches the scene to the plan page.
     * @param event The ActionEvent that triggered the method call.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void switchToPlanPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/StudentPlanForm.fxml");
    }

    /**
     * Signs the student out of the application and switches the scene to the login page.
     * @param event The ActionEvent that triggered the method call.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void signOut(ActionEvent event) throws IOException {
        PageSwitcher.signOut(event);
        PageSwitcher.switchToPage(event, "fxml/login.fxml");
    }     
}