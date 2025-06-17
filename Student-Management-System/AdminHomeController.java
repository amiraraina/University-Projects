// homepage shows which student is enrolled in which subject
// and which lecturer (label below) teaches which subject 

import java.io.IOException;

import dataManager.CourseList;
import dataManager.EnrollmentList;
import dataManager.StudentList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import object.Course;
import object.EnrollmentRecord;
import object.Lecturer;
import object.Student;

/**
 * The AdminHomeController class controls the functionality of the admin home interface.
 * A homepage that shows which student is enrolled in which subject and which lecturer teaches which subject
 * It handles various actions such as adding courses, lecturers, and students, switching between pages,
 * and displaying relevant information based on user interactions.
 */
public class AdminHomeController {
    /**
     * Default constructor for the AdminHomeController class.
     * This constructor initializes the AdminHomeController.
     */
    public AdminHomeController() {
        // Constructor implementation
    }
    /** The PageSwitcher object for navigating between different pages of the application. */
    private PageSwitcher pageSwitcher;

    /** The Button to add a new course. */
    @FXML
    private Button addCourseBtn;

    /** The Button to add a new lecturer. */
    @FXML
    private Button addLecturerBtn;

    /** The Button to add a new student. */
    @FXML
    private Button addStudentBtn;

    /** The TableColumn for displaying student emails. */
    @FXML
    private TableColumn<Student, String> addStudentEmailCol;

    /** The AnchorPane for adding a new student. */
    @FXML
    private AnchorPane addStudentForm;

    /** The TableColumn for displaying student IDs. */
    @FXML
    private TableColumn<Student, Integer> addStudentIDCol;

    /** The TableColumn for displaying student names. */
    @FXML
    private TableColumn<Student, String> addStudentNameCol;

    /** The ComboBox for selecting a course. */
    @FXML
    private ComboBox<String> courseMenu;

    /** The Label to greet the user. */
    @FXML
    private Label greetUser;

    /** The Button to navigate to the home page. */
    @FXML
    private Button homeBtn;

    /** The Label to display the lecturer's name. */
    @FXML
    private Label lecturerName;

    /** The ImageView for the user's profile picture. */
    @FXML
    private ImageView profile;

    /** The ComboBox for selecting a semester. */
    @FXML
    private ComboBox<String> semBox;

    /** The TableView for displaying student records. */
    @FXML
    private TableView<Student> studentTableView;

    /** The JavaFX Stage object. */
    private Stage stage;
    /** The root Parent node of the scene graph. */
    private Scene scene;
    /** The ObservableList containing all students. */
    private Parent root;

    /**
     * Represents an observable list containing instances of the Student class.
     * This list holds all the student objects within the context it is used.
     * It allows for dynamic updates and bindings with UI components in JavaFX applications.
     */
    private ObservableList<Student> allStudents;

    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     * It sets up the TableView columns, populates ComboBoxes, and initializes the list of students.
     */
    @FXML
    public void initialize() {
        // Initialize TableView columns
        addStudentIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getID()).asObject());
        addStudentNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        // Populate semBox with semester options
        ObservableList<String> semOptions = FXCollections.observableArrayList("semester 1", "semester 2", "semester 3");
        semBox.setItems(semOptions);
        
        // Fill courseMenu with course IDs
        for (Course course : CourseList.course) {
            courseMenu.getItems().add(course.getId().toString());
        }
        
        // Set event handlers for ComboBoxes
        semBox.setOnAction(event -> onCourseSelected());
        courseMenu.setOnAction(event -> onCourseSelected());

        // Initialize the list of students
        allStudents = FXCollections.observableArrayList();
        studentTableView.setItems(allStudents);
    }
    
    /**
     * Handles the event when a course is selected. Updates the lecturer's name label and populates the student table.
     */
    private void onCourseSelected() {
        String selectedCourseId = courseMenu.getValue();
        String selectedSemester = semBox.getValue();

        // Find the corresponding Course object
        Course selectedCourse = CourseList.course.stream()
                .filter(course -> course.getId().equals(selectedCourseId))
                .findFirst()
                .orElse(null);

        // Update lecturerName label
        if (selectedCourse != null && selectedCourse.getLecturer() != null) {
            lecturerName.setText(selectedCourse.getLecturer());
        } else {
            lecturerName.setText("Lecturer: N/A");
        }

        // Populate student table
        allStudents.clear();

        for (EnrollmentRecord enrollment : EnrollmentList.enrolledCourses) {
            String courseId = enrollment.getCourseId();
            int studentId = enrollment.getStudentId();
            String semester = enrollment.getSemester();
            if (courseId.equals(selectedCourseId) && semester.equals(selectedSemester)) {
                Student student = StudentList.getStudentById(studentId);
                if (student != null) {
                    allStudents.add(student);
                }
            }
        }

    }

    /**
     * Switches to the student page upon button click.
     * @param event The ActionEvent object representing the event.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void switchToStudentPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminStudentForm.fxml");
    }
    /**
     * Switches to the lecturer page upon button click.
     * @param event The ActionEvent object representing the event.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void switchToLecturerPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminLecturerForm.fxml");
    }
    /**
     * Switches to the course page upon button click.
     * @param event The ActionEvent object representing the event.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void switchToCoursePage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminCourseForm.fxml");
    }
    /**
     * Switches to the home page upon button click.
     * @param event The ActionEvent object representing the event.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void switchToHomePage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminHome.fxml");
    }
    /**
     * Signs out the user and switches to the login page upon button click.
     * @param event The ActionEvent object representing the event.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void signOut(ActionEvent event) throws IOException {
        PageSwitcher.signOut(event);
        PageSwitcher.switchToPage(event, "fxml/login.fxml");
    }
}