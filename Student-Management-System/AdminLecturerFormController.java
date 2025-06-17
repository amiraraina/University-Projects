import object.Lecturer;
import object.Student;
import dataManager.LecturerList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

/**
 * The AdminLecturerFormController class controls the functionality of the lecturer form in the administration interface.
 * It implements the Initializable interface to initialize the controller class.
 */
public class AdminLecturerFormController implements Initializable {
    /**
     * Default constructor.
     * Constructs a new instance of AdminLecturerFormController.
     */
    public AdminLecturerFormController() {
        // Default constructor
    }

    /** The text field for entering the ID of a course. */
    @FXML
    private TextField IDtextfield;

    /** The button for adding a new course. */
    @FXML
    private Button addCourseBtn;

    /** The button for adding a new lecturer. */
    @FXML
    private Button addLecturerBtn;

    /** The button for navigating back to the home screen. */
    @FXML
    private Button homeBtn;

    /** The table column displaying the ID of a lecturer. */
    @FXML
    private TableColumn<Lecturer, Integer> addLecturerIDCol;

    /** The table column displaying the name of a lecturer. */
    @FXML
    private TableColumn<Lecturer, String> addLecturerNameCol;

    /** The button for adding a new student. */
    @FXML
    private Button addStudentBtn;

    /** The anchor pane containing the form for adding a new student. */
    @FXML
    private AnchorPane addStudentForm;

    /** The button for deleting an entry. */
    @FXML
    private Button deleteBtn;

    /** The label for greeting the user. */
    @FXML
    private Label greetUser;

    /** The label for displaying warnings or messages. */
    @FXML
    private Label labelWarning;

    /** The table view displaying the list of lecturers. */
    @FXML
    private TableView<Lecturer> lecturerTableView;

    /** The text field for entering the name of a lecturer. */
    @FXML
    private TextField nameTextField;

    /** The image view displaying the user's profile picture. */
    @FXML
    private ImageView profile;

    /** The button for submitting changes or entries. */
    @FXML
    private Button submit;

    /** The stage of the JavaFX application. */
    private Stage stage;

    /** The scene of the JavaFX application. */
    private Scene scene;

    /** The root node of the JavaFX scene graph. */
    private Parent root;

    /**
     * Initializes the controller class.
     * This method is automatically called after the FXML file has been loaded.
     * It configures the columns of the lecturer table view and populates it with data.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addLecturerIDCol.setCellValueFactory(new PropertyValueFactory<Lecturer, Integer>("ID"));
        addLecturerNameCol.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("name"));

        // populate table with student data from file
        lecturerTableView.setItems(FXCollections.observableArrayList(LecturerList.lecturer));
    }

    /**
     * Handles the action event when the submit button is clicked.
     * It validates the input fields and either updates existing lecturer data or adds new lecturer data.
     * Additionally, it adds or updates lecturer data in the database.
     * @param event The action event representing the click on the submit button.
     */
    @FXML //Submit button
    void submit(ActionEvent event){ // ID, String name
        try {
            if (IDtextfield.getText().isEmpty() || nameTextField.getText().isEmpty()) 
                {
                    labelWarning.setText("Please fill in all the blank");
                    clearLabel();
                }
            else if (lecturerAlreadyExists(Integer.parseInt(IDtextfield.getText())) ) {
                // to update current lecturer data
                ObservableList<Lecturer> currentTableData = lecturerTableView.getItems();
                int currentLecturerID = Integer.parseInt(IDtextfield.getText());

                for (Lecturer s : currentTableData){
                    if (s.getID() == currentLecturerID) {
                        s.setName(nameTextField.getText());

                        lecturerTableView.setItems(currentTableData);
                        lecturerTableView.refresh();
                        break;
                    }
                }
                labelWarning.setText("Updated successfully");
                clearLabel();
            }
            else {
                // to add new lecturer data
                    Lecturer lecturer = new Lecturer(Integer.parseInt(IDtextfield.getText()), null, 
                                    nameTextField.getText());
                    ObservableList<Lecturer> lecturers = lecturerTableView.getItems();
                    lecturers.add(lecturer);
                    lecturerTableView.setItems(lecturers);
                    LecturerList.writeLecturerToFile(lecturers);

                    // adding lecturers to database
                    String insertSql = "INSERT INTO useraccounts (username, password, role) VALUES (?, ?, ?)";
                    try (Connection connection = DatabaseConnection.getConnection();
                            PreparedStatement statement = connection.prepareStatement(insertSql)) {
                        statement.setInt(1, Integer.parseInt(IDtextfield.getText()));
                        statement.setString(2, "123");
                        statement.setString(3, "lecturer");
                        statement.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                        labelWarning.setText("Error adding to database.");
                        clearLabel();
                        return;
                    }

                    labelWarning.setText("Added successfully");
                    clearLabel();
            }
        } catch (NumberFormatException e) {
            labelWarning.setText("Invalid ID number.");
            clearLabel();
        }
    }

    /**
     * Handles the action event when the remove button is clicked.
     * It removes the selected lecturer from the table view and updates the data file.
     * Additionally, it removes the lecturer from the database.
     * @param event The action event representing the click on the remove button.
     */
    // Remove button
    @FXML
    void remove(ActionEvent event){
        Lecturer selectedLecturer = lecturerTableView.getSelectionModel().getSelectedItem();
        if (selectedLecturer != null) {
            lecturerTableView.getItems().remove(selectedLecturer);

            List<Lecturer> lecturers = new ArrayList<>(lecturerTableView.getItems());
            LecturerList.writeLecturerToFile(lecturers);

            // remove from database
            String deleteSql = "DELETE FROM useraccounts WHERE username = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(deleteSql)) {
                statement.setInt(1, selectedLecturer.getID());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                labelWarning.setText("Error removing from database.");
                clearLabel();
                return;
            }
        }

        labelWarning.setText("Removed successfully");
        clearLabel();
    }

    /**
     * Handles the mouse click event when a row in the lecturer table view is clicked.
     * It displays the details of the selected lecturer in the corresponding text fields.
     * @param event The mouse event representing the click on a row in the lecturer table view.
     */
    @FXML
    void rowClicked(MouseEvent event){
        Lecturer lecturerClicked = lecturerTableView.getSelectionModel().getSelectedItem();
        addLecturerIDCol.setText(String.valueOf(lecturerClicked.getID()));
        addLecturerNameCol.setText(String.valueOf(lecturerClicked.getName()));
    }
    
    private boolean lecturerAlreadyExists(int lecturerID) {
        for (Lecturer s : lecturerTableView.getItems()) {
            if (s.getID() == lecturerID) {
                return true;
            }
        }
        return false;
    }

    /**
     * Switches to the student page upon receiving an action event.
     * @param event The action event triggering the method call.
     * @throws IOException If an I/O error occurs while switching pages.
     */
    @FXML
    void switchToStudentPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminStudentForm.fxml");
    }

    /**
     * Switches to the lecturer page upon receiving an action event.
     * @param event The action event triggering the method call.
     * @throws IOException If an I/O error occurs while switching pages.
     */
    @FXML
    void switchToLecturerPage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminLecturerForm.fxml");
    }

    /**
     * Switches to the course page upon receiving an action event.
     * @param event The action event triggering the method call.
     * @throws IOException If an I/O error occurs while switching pages.
     */
    @FXML
    void switchToCoursePage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminCourseForm.fxml");
    }

    /**
     * Switches to the home page upon receiving an action event.
     * @param event The action event triggering the method call.
     * @throws IOException If an I/O error occurs while switching pages.
     */
    @FXML
    void switchToHomePage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/AdminHome.fxml");
    }

    /**
     * Signs out the current user and switches to the login page upon receiving an action event.
     * @param event The action event triggering the method call.
     * @throws IOException If an I/O error occurs while signing out or switching pages.
     */
    @FXML
    void signOut(ActionEvent event) throws IOException {
        PageSwitcher.signOut(event);
        PageSwitcher.switchToPage(event, "fxml/login.fxml");
    }

    /**
     * Clears the warning label after a specified duration.
     */
    void clearLabel(){
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(e -> labelWarning.setText(""));
        pause.play();
    }
}