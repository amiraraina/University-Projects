import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import object.Course;
import object.Lecturer;
import object.Requirement;
import object.Student;
import dataManager.CourseList;
import dataManager.LecturerList;

/**
 * Controller class for handling the administrative functionality related to courses.
 * Implements Initializable interface to initialize the controller's state.
 */
public class AdminCourseFormController implements Initializable {
    /** The PageSwitcher object for navigating between different pages of the application. */
    private PageSwitcher pageSwitcher;
    /** The filename constant specifying the file path for storing course data in CSV format. */
    public final static String filename = "data/course.csv";
    /**
     * Constructs a new AdminCourseFormController.
     */
    public AdminCourseFormController() {
        // Constructor implementation
    }
    /**
     * Button for adding a new course.
     */
    @FXML
    private Button addCourseBtn;

    /**
     * Button for adding a new lecturer.
     */
    @FXML
    private Button addLecturerBtn;

    /**
     * Button for adding a new student.
     */
    @FXML
    private Button addStudentBtn;

    /**
     * Anchor pane containing the form for adding a new student.
     */
    @FXML
    private AnchorPane addStudentForm;

    /**
     * The table column for displaying the ID of the course.
     */
    @FXML
    private TableColumn<Course, String> idCol;

     /**
     * The table column for displaying the credit hours of the course.
     */
    @FXML
    private TableColumn<Course, Integer> creditCol;

    /**
     * Text field for entering credit hours.
     */
    @FXML
    private TextField creditHourText;

    /**
     * Button for deleting a selected course.
     */
    @FXML
    private Button deleteBtn;

    /**
     * Label for greeting the user.
     */
    @FXML
    private Label greetUser;

    /**
     * Button for navigating to the home page.
     */
    @FXML
    private Button homeBtn;

    /**
     * Text field for entering the ID of a course.
     */
    @FXML
    private TextField idTextField;

    /**
     * Label for displaying warning messages.
     */
    @FXML
    private Label labelWarning;

    /**
     * Table column for displaying lecturers of courses.
     */
    @FXML
    private TableColumn<Course, String> lecturerCol;

    /**
     * Choice box for selecting a lecturer.
     */
    @FXML
    private ChoiceBox<String> lecturerList;

    /**
     * Choice box for selecting a course requirement.
     */
    @FXML
    private ChoiceBox<String> reqList;

    /**
     * Table view for displaying courses.
     */
    @FXML
    private TableView<Course> courseTableView;

    /**
     * Image view for displaying the user's profile picture.
     */
    @FXML
    private ImageView profile;

    /**
     * Table column for displaying course requirements.
     */
    @FXML
    private TableColumn<Course, Requirement> reqCol;

    /**
     * Table column for displaying notes of courses.
     */
    @FXML
    private TableColumn<Course, String> noteCol;

    /**
     * Text field for entering notes for a course.
     */
    @FXML
    private TextField noteTextField;

    /**
     * Button for submitting course information.
     */
    @FXML
    private Button submit;

    /**
     * Reference to the JavaFX stage.
     */
    private Stage stage;
    /**
     * Reference to the JavaFX scene.
     */
    private Scene scene;
    /**
     * Reference to the root node of the JavaFX scene.
     */
    private Parent root;
    
    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up bindings, event handlers, and initializes UI elements.
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up cell value factories for table columns
        idCol.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        creditCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("creditHour"));
        lecturerCol.setCellValueFactory(new PropertyValueFactory<Course, String>("lecturer"));
        reqCol.setCellValueFactory(new PropertyValueFactory<Course, Requirement>("requirement"));
        noteCol.setCellValueFactory(new PropertyValueFactory<Course, String>("note"));

        // Populate course table view with data
        courseTableView.setItems(FXCollections.observableArrayList(CourseList.course));

        // Fill requirement choice box
        ObservableList<String> allRequirements = FXCollections.observableArrayList();
        for (Course x : CourseList.course) {
            allRequirements.add(x.getId());
        }
        Course nilCourse = new Course("Nil", 0, null, null, null);
        allRequirements.add(nilCourse.getId());
        reqList.setItems(allRequirements);
        
        // Fill the lecturer choicebox
        for (Lecturer x : LecturerList.lecturer) {
            lecturerList.getItems().addAll(x.toString());
        }      
    }

    /**
     * Handles the action event when the submit button is clicked.
     * Validates input fields and either updates an existing course or adds a new one.
     * @param event The action event triggered by clicking the submit button.
     */
    @FXML // Submit button
    void submit(ActionEvent event){ // ID, String name
        try {
            // Validate input fields
            if (idTextField.getText().isEmpty() || creditHourText.getText().isEmpty() 
                || lecturerList.getValue() == null || reqList.getValue() == null) 
                {
                    labelWarning.setText("Please fill in all the blank");
                    clearLabel();

                } else if (courseAlreadyExists(idTextField.getText()) ){
                    // To update current course data (based on the unique id)
                    ObservableList<Course> currentTableData = courseTableView.getItems();
                    for (Course c : currentTableData) {
                        if (c.getId().equals(idTextField.getText())) { //select the unique course id
                            c.setCreditHour(Integer.parseInt(creditHourText.getText()));
                            c.setName(lecturerList.getValue());
                            c.setRequirement(new Requirement(reqList.getValue(), 0, null));

                            courseTableView.setItems(currentTableData);
                            courseTableView.refresh();
                            break;
                            }
                    }
                    labelWarning.setText("Updated successfully");
                    clearLabel();
                    
                } else {
                    // Create a new course if id doesn't exist
                    ObservableList<Course> courses = courseTableView.getItems();
                    Course newCourse = new Course(idTextField.getText(),
                                       Integer.parseInt(creditHourText.getText()),
                                       lecturerList.getValue(),
                                       new Requirement(reqList.getValue(), 0, null),noteTextField.getText());
                    courses.add(newCourse);
                    courseTableView.setItems(courses);
                    // update csv file
                    CourseList.writeCourseToFile(courses);

                    labelWarning.setText("Added successfully");
                    clearLabel();
                }
            } catch (Exception e) {
                // Handle exception
            }
    }

    /**
     * Checks if a course with the given ID already exists.
     * @param courseID The ID of the course to check.
     * @return True if the course already exists, false otherwise.
     */
    private boolean courseAlreadyExists(String courseID) {
        Map<String, Course> courseMap = new HashMap<>();   
        for (Course c : courseTableView.getItems()) {
            courseMap.put(c.getId(), c);
        }
        return courseMap.containsKey(courseID);
    }

    /**
     * Handles the action event when the delete button is clicked.
     * Removes the selected course from the table view.
     * @param event The action event triggered by clicking the delete button.
     */
    @FXML
    void remove(ActionEvent event) {
        
        Course selectedID = courseTableView.getSelectionModel().getSelectedItem();
        if (selectedID != null) {
            courseTableView.getItems().remove(selectedID);

            // Rewrite csv
            List<Course> courses = new ArrayList<>(courseTableView.getItems());
            CourseList.writeCourseToFile(courses);
        }
        
    }

    /**
     * Handles the mouse event when a row in the table view is clicked.
     * Displays details of the selected course.
     * @param event The mouse event triggered by clicking a row in the table view.
     */
    @FXML
    void rowClicked(MouseEvent event) {
        Course courseClicked = courseTableView.getSelectionModel().getSelectedItem();
        idCol.setText(String.valueOf(courseClicked.getId()));
        creditCol.setText(String.valueOf(courseClicked.getId()));
        lecturerCol.setText(String.valueOf(courseClicked.getLecturer()));
        reqCol.setText(String.valueOf(courseClicked.getRequirement()));
        noteTextField.setText(String.valueOf(courseClicked.getNote()));
    }
    
    /**
     * Handles the action event when the switch to student page button is clicked.
     * Navigates to the student page.
     * @param event The action event triggered by clicking the switch to student page button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void switchToStudentPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminStudentForm.fxml");
    }

    /**
     * Switches to the lecturer page when triggered by an action event.
     *
     * @param event The action event that triggers the method.
     * @throws IOException If an I/O error occurs while switching to the lecturer page.
     */
    @FXML
    void switchToLecturerPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminLecturerForm.fxml");
    }

    /**
     * Switches to the course page when triggered by an action event.
     *
     * @param event The action event that triggers the method.
     * @throws IOException If an I/O error occurs while switching to the course page.
     */
    @FXML
    void switchToCoursePage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminCourseForm.fxml");
    }

    /**
     * Switches to the home page when triggered by an action event.
     *
     * @param event The action event that triggers the method.
     * @throws IOException If an I/O error occurs while switching to the home page.
     */
    @FXML
    void switchToHomePage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminHome.fxml");
    }

    /**
     * Signs out the user and switches to the login page when triggered by an action event.
     *
     * @param event The action event that triggers the method.
     * @throws IOException If an I/O error occurs while signing out or switching to the login page.
     */
    @FXML
    void signOut(ActionEvent event) throws IOException {
        PageSwitcher.signOut(event);
        PageSwitcher.switchToPage(event, "fxml/login.fxml");
    }

    /**
     * Clears the warning label after a delay of 4 seconds.
     */
    void clearLabel(){
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(e -> labelWarning.setText(""));
        pause.play();
    }
}