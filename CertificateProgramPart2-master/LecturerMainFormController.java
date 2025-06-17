import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dataManager.CourseList;
import dataManager.EnrollmentList;
import dataManager.LecturerList;
import dataManager.StudentList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import object.*;

/**
 * The LecturerMainFormController class controls the functionality of the main form for lecturers in the system.
 * It implements the Initializable interface to initialize the controller class.
 */
public class LecturerMainFormController implements Initializable {
    /**
     * Constructs a new instance of the LecturerMainFormController.
     * This constructor is typically used by the FXMLLoader when loading the FXML file.
     */
    public LecturerMainFormController() {
        // Constructor logic
    }
    /** The TableView displaying information about courses. */
    @FXML
    private TableView<Course> courseTableView;

    /** The TableColumn displaying the credit information for courses. */
    @FXML
    private TableColumn<Course, Integer> creditCol;

    /** The TableColumn displaying the ID information for courses. */
    @FXML
    private TableColumn<Course, String> idCol;

    /** The Text element displaying the name of the lecturer. */
    @FXML
    private Text lecturerName;

    /** The TableView displaying information about students. */
    @FXML
    private TableView<Student> studentTableView;

    /** The TableColumn displaying the note information for courses. */
    @FXML
    private TableColumn<Course, String> noteCol;

    /** The ImageView displaying the profile picture. */
    @FXML
    private ImageView profile;

    /** The TableColumn displaying the requirements for courses. */
    @FXML
    private TableColumn<Course, Requirement> reqCol;

    /** The Button used for signing out. */
    @FXML
    private Button signOutBtn;

    /** The ComboBox for selecting a semester. */
    @FXML
    private ComboBox<String> semBox;

    /** The TableColumn displaying the ID information for students. */
    @FXML
    private TableColumn<Student, Integer> studentIdCol;

    /** The TableColumn displaying the name information for students. */
    @FXML
    private TableColumn<Student, String> studentNameCol;

    /**
     * The lecturer's ID retrieved from the current session.
     */
    private int lecturerId =Integer.parseInt(SessionManager.getCurrentUserId()); // get the lecturer id when logged in
    /**
     * Observable list to hold course details.
     */
    private ObservableList<Course> courseDetail;
    /**
     * Observable list to hold student names.
     */
    private ObservableList<Student> studentNameList;

    /**
     * Initializes the controller class.
     * @param location The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // System.out.println(SessionManager.getCurrentUserId());
        // initialise course table
        idCol.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        creditCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("creditHour"));
        reqCol.setCellValueFactory(new PropertyValueFactory<Course, Requirement>("requirement"));
        noteCol.setCellValueFactory(new PropertyValueFactory<Course, String>("note"));

        // initialise student table
        studentIdCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        studentNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        
        // welcome greeting to lecturer name
        String name = LecturerList.getNameFromLecturerList(LecturerList.lecturer, lecturerId);
        lecturerName.setText(name);

        // populate semBox
        ObservableList<String> semOptions = FXCollections.observableArrayList("semester 1", "semester 2", "semester 3");
        semBox.setItems(semOptions);

        // Course Detail table
        Course course = CourseList.getCourseByLecturer(name);
        // System.out.println(course);
        courseDetail = FXCollections.observableArrayList(); // create a list for the tableview
        courseDetail.add(course);
        courseTableView.setItems(courseDetail);

        // add student to table (read from enrollment)
        studentNameList = FXCollections.observableArrayList(); // create a list for the tableview

    }

    /**
     * Handles the event when a semester is selected.
     * @param event The ActionEvent triggered when a semester is selected.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void selectSemester(ActionEvent event) throws IOException {
        // Semester selection logic
        String selectedSemester = semBox.getValue();
        studentNameList.clear(); // Clear existing items from the list
    
        List<String> enrollments = EnrollmentList.getEnrollmentBySemester(selectedSemester);
        for (String enrollment : enrollments) {
            String[] parts = enrollment.split(",");
            int studentId = Integer.parseInt(parts[0]);
            String courseId = parts[1];
            if (isLecturerTeachingCourse(courseId)) {
                Student student = StudentList.getStudentById(studentId);
                if (student != null) {
                    studentNameList.add(student);
                }
            }
        }
        studentTableView.setItems(studentNameList);
    }
    
    /**
     * Checks if the lecturer is teaching a course with the given course ID.
     * @param courseId The ID of the course to check.
     * @return True if the lecturer is teaching the course, false otherwise.
     */
    private boolean isLecturerTeachingCourse(String courseId) {
        // Logic to check if the lecturer is teaching the course
        for (Course c : courseDetail)
            if (c.getId().equals(courseId)) 
                return true;
        return false;
    }
    
    /**
     * Handles the sign-out action.
     * @param event The ActionEvent triggered when the sign-out button is clicked.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void signOut(ActionEvent event) throws IOException {
        // Sign-out logic
        courseDetail.clear();
        PageSwitcher.signOut(event);
        PageSwitcher.switchToPage(event, "fxml/login.fxml");
    }
}