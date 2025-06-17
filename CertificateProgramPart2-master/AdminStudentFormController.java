
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.Action;

import javafx.animation.PauseTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import object.Student;
import dataManager.StudentList;

/**
 * The AdminStudentFormController class controls the functionality of the student form in the administration interface.
 * It implements the Initializable interface to initialize the controller class.
 */
public class AdminStudentFormController implements Initializable {
    /**
     * Default constructor.
     * This constructor is automatically called when an instance of this class is created.
     * It initializes the AdminStudentFormController.
     */
    public AdminStudentFormController() {
        // Default constructor
    }
    /** The TableView displaying student data. */
    @FXML
    private TableView<Student> studentTableView;

    /** The TextField for entering student ID. */
    @FXML
    private TextField IDtextfield;

    /** The Button for adding a new student. */
    @FXML
    private Button addBtn;

    /** The Button for adding a course for a student. */
    @FXML
    private Button addCourseBtn;

    /** The Button for adding a lecturer for a student. */
    @FXML
    private Button addLecturerBtn;

    /** The Button for adding a student. */
    @FXML
    private Button addStudentBtn;

    /** The Button for navigating to the home page. */
    @FXML
    private Button homeBtn;

    /** The AnchorPane containing the student form. */
    @FXML
    private AnchorPane addStudentForm;

    /** The TableColumn for displaying student IDs. */
    @FXML
    private TableColumn<Student, Integer> addStudentIDCol;

    /** The TableColumn for displaying student names. */
    @FXML
    private TableColumn<Student, String> addStudentNameCol;

    /** The Button for deleting a student. */
    @FXML
    private Button deleteBtn;

    /** The Label for greeting the user. */
    @FXML
    private Label greetUser;

    /** The TextField for entering student names. */
    @FXML
    private TextField nameTextField;

    /** The Label for displaying warning messages. */
    @FXML
    private Label labelWarning;

    /** The ImageView for displaying student profiles. */
    @FXML
    private ImageView profile;

    /** The JavaFX Stage object. */
    private Stage stage;

    /** The JavaFX Scene object. */
    private Scene scene;

    /** The JavaFX Parent object representing the root of the scene graph. */
    private Parent root;

    /**
     * Initializes the controller class.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addStudentIDCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        addStudentNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        
        studentTableView.setItems(FXCollections.observableArrayList(StudentList.students));
    }

    /**
     * Handles the action when the submit button is clicked.
     * @param event The event representing the action.
     */
    //Submit button
    @FXML
    void submit(ActionEvent event){ // ID, String name

        try {    
            if (IDtextfield.getText().isEmpty() || nameTextField.getText().isEmpty()) 
                {
                    labelWarning.setText("Please fill in all the blank");
                    clearLabel();
                }
            else if (studentAlreadyExists(Integer.parseInt(IDtextfield.getText())) ){
                // to update current student data (edit based on ID)
                ObservableList<Student> currentTableData = studentTableView.getItems();
                int currentStudentID = Integer.parseInt(IDtextfield.getText());

                for (Student s : currentTableData){
                    if (s.getID() == currentStudentID) {
                        s.setName(nameTextField.getText());

                        studentTableView.setItems(currentTableData);
                        studentTableView.refresh();
                        break;
                    }
                }
                labelWarning.setText("Updated successfully");
                clearLabel();
            } else{
                // to add new student data
                    ObservableList<Student> students = studentTableView.getItems();
                    Student student = new Student(Integer.parseInt(IDtextfield.getText()), "123", // all new user by default will have 123 as password 
                                    nameTextField.getText());
                    students.add(student);
                    studentTableView.setItems(students);
                    StudentList.writeStudentToFile(students);

                    // adding student to database (useraccounts table)
                    String insertSql = "INSERT INTO useraccounts (username, password, role) VALUES (?, ?, ?)";
                    try (Connection connection = DatabaseConnection.getConnection();
                            PreparedStatement statement = connection.prepareStatement(insertSql)) {
                        statement.setInt(1, Integer.parseInt(IDtextfield.getText()));
                        statement.setString(2, "123");
                        statement.setString(3, "student");
                        statement.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                        labelWarning.setText("Error adding student to database.");
                        clearLabel();
                        return; // Exit the method
                    }

                    // success message
                    labelWarning.setText("Added successfully");
                    clearLabel();
            }
        } catch (NumberFormatException e) {
            labelWarning.setText("Invalid ID number.");
            clearLabel();
        }
    }

    /**
     * Handles the action when the remove button is clicked.
     * @param event The event representing the action.
     */
    // Remove button
    @FXML
    void remove(ActionEvent event){
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // remove from table
            studentTableView.getItems().remove(selectedStudent); 
            
            // rewrite csv
            List<Student> students = new ArrayList<>(studentTableView.getItems());
            StudentList.writeStudentToFile(students);
            
            // remove from database
            String deleteSql = "DELETE FROM useraccounts WHERE username = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(deleteSql)) {
                statement.setInt(1, selectedStudent.getID());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                labelWarning.setText("Error removing student from database.");
                clearLabel();
                return;
            }
    
        }        
    }

    /**
     * Handles the action when a row in the TableView is clicked.
     * @param event The event representing the action.
     */
    @FXML
    void rowClicked(MouseEvent event){
        Student studentClicked = studentTableView.getSelectionModel().getSelectedItem();
        addStudentIDCol.setText(String.valueOf(studentClicked.getID()));
        addStudentNameCol.setText(String.valueOf(studentClicked.getName()));
    }
    
    /**
     * Checks if a student already exists in the TableView.
     * @param studentID The ID of the student to check.
     * @return True if the student already exists, false otherwise.
     */
    private boolean studentAlreadyExists(int studentID) {
        // Student existence check logic
        // updated using HashMap (3/1)
        Map<Integer, Student> studentMap = new HashMap<>();   
        for (Student s : studentTableView.getItems()) {
            studentMap.put(s.getID(), s);
        }
        return studentMap.containsKey(studentID);

        // for (Student s : studentTableView.getItems()) {
        //     if (s.getID() == studentID) {
        //         return true;
        //     }
        // }
        // return false;
    }
    
    /**
     * Switches the scene to the student form page upon receiving an ActionEvent.
     * @param event The ActionEvent triggered by the user action.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    @FXML
    void switchToStudentPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminStudentForm.fxml");
    }

    /**
     * Switches the scene to the lecturer form page upon receiving an ActionEvent.
     * @param event The ActionEvent triggered by the user action.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    @FXML
    void switchToLecturerPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminLecturerForm.fxml");
    }

    /**
     * Switches the scene to the course form page upon receiving an ActionEvent.
     * @param event The ActionEvent triggered by the user action.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    @FXML
    void switchToCoursePage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminCourseForm.fxml");
    }

    /**
     * Switches the scene to the home page upon receiving an ActionEvent.
     * @param event The ActionEvent triggered by the user action.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    @FXML
    void switchToHomePage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminHome.fxml");
    }

    /**
     * Signs out the current user and switches the scene to the login page upon receiving an ActionEvent.
     * @param event The ActionEvent triggered by the user action.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    @FXML
    void signOut(ActionEvent event) throws IOException {
        PageSwitcher.signOut(event);
        PageSwitcher.switchToPage(event, "fxml/login.fxml");
    }

    /**
     * Clears the warning label after a certain duration.
     */
    void clearLabel() {
        // Label clearing logic
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(e -> labelWarning.setText(""));
        pause.play();
    }
}