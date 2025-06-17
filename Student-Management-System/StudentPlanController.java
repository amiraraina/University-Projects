import dataManager.CourseList;
import dataManager.StudentList;
import object.Course;
import object.Lecturer;
import object.Requirement;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

/**
 * The StudentPlanController class controls the functionality of the student's course planning form in the application.
 * It implements the Initializable interface to initialize the controller class.
 */
public class StudentPlanController implements Initializable {
    /**
     * Default constructor for the StudentPlanController class.
     * Constructs a new instance of the StudentPlanController.
     */
    public StudentPlanController() {
        // Constructor implementation
    }
    /** The button for editing courses. */
    @FXML
    private Button editCourseBtn;

    /** The button for planning courses. */
    @FXML
    private Button planCourseBtn;

    /** The profile image view. */
    @FXML
    private ImageView profile;

    /** The table view displaying courses for semester 1. */
    @FXML
    private TableView<Course> sem1;

    /** The table column displaying the credit hours for courses in semester 1. */
    @FXML
    private TableColumn<Course, Integer> sem1CreditCol;

    /** The table column displaying the ID for courses in semester 1. */
    @FXML
    private TableColumn<Course, String> sem1IDCol;

    /** The table column displaying the lecturer for courses in semester 1. */
    @FXML
    private TableColumn<Course, Lecturer> sem1LecturerCol;

    /** The table column displaying notes for courses in semester 1. */
    @FXML
    private TableColumn<Course, String> sem1NoteCol;

    /** The table column displaying requirements for courses in semester 1. */
    @FXML
    private TableColumn<Course, Requirement> sem1ReqCol;

    /** The table view displaying courses in semester 2. */
    @FXML
    private TableView<Course> sem2;

    /** The table column displaying the credit hours for courses in semester 2. */
    @FXML
    private TableColumn<Course, Integer> sem2CreditCol;

    /** The table column displaying the ID for courses in semester 2. */
    @FXML
    private TableColumn<Course, String> sem2IDCol;

    /** The table column displaying the lecturer for courses in semester 2. */
    @FXML
    private TableColumn<Course, Lecturer> sem2LecturerCol;

    /** The table column displaying notes for courses in semester 2. */
    @FXML
    private TableColumn<Course, String> sem2NoteCol;

    /** The table column displaying requirements for courses in semester 2. */
    @FXML
    private TableColumn<Course, Requirement> sem2ReqCol;

    /** The table view displaying courses in semester 3. */
    @FXML
    private TableView<Course> sem3;

    /** The table column displaying the ID for courses in semester 3. */
    @FXML
    private TableColumn<Course, String> sem3IDCol;

    /** The table column displaying the credit hours for courses in semester 3. */
    @FXML
    private TableColumn<Course, Integer> sem3CreditCol;

    /** The table column displaying the lecturer for courses in semester 3. */
    @FXML
    private TableColumn<Course, Lecturer> sem3LecturerCol;

    /** The table column displaying notes for courses in semester 3. */
    @FXML
    private TableColumn<Course, String> sem3NoteCol;

    /** The table column displaying requirements for courses in semester 3. */
    @FXML
    private TableColumn<Course, Requirement> sem3ReqCol;

    /** The button for viewing courses. */
    @FXML
    private Button viewCourseBtn;

    /** The button for removing courses. */
    @FXML
    private Button RemoveBtn;

    /** The combo box for selecting courses. */
    @FXML
    private ComboBox<Course> courseComboBox; // Change from ComboBox<String> to ComboBox<Course>

    /** The combo box for selecting semesters. */
    @FXML
    private ComboBox<String> semComboBox;

    /** The text displaying the student's name. */
    @FXML
    private Text studentName;

    /** The text displaying the total number of courses in semester 1. */
    @FXML
    private Text totalsem1;

    /** The text displaying the total number of courses in semester 2. */
    @FXML
    private Text totalsem2;

    /** The text displaying the total number of courses in semester 3. */
    @FXML
    private Text totalsem3;

    /** The button for signing out. */
    @FXML
    private Button signOutBtn;

    /** The list of selected courses for semester 1. */
    private ObservableList<Course> selectedCoursesForSem1;
    /** The list of selected courses for semester 2. */
    private ObservableList<Course> selectedCoursesForSem2;
    /** The list of selected courses for semester 3. */
    private ObservableList<Course> selectedCoursesForSem3;
    /**
     * The studentId field represents the unique identifier of the currently logged-in student.
     * It is obtained by parsing the user ID retrieved from the SessionManager.
     */
    private int studentId =Integer.parseInt(SessionManager.getCurrentUserId()); // get the student id when logged in

    /** Flag indicating whether an error has been shown. */
    private boolean errorShown = false;

    /**
     * Initializes the controller class.
     * This method is called automatically after the FXML file has been loaded.
     * It sets up the table columns, loads enrolled courses, and initializes UI elements.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize columns
        // sem 1 table
        sem1IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sem1CreditCol.setCellValueFactory(new PropertyValueFactory<>("creditHour"));
        sem1LecturerCol.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        sem1ReqCol.setCellValueFactory(new PropertyValueFactory<>("requirement"));
        sem1NoteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
    
        // Initialize columns for sem2
        sem2IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sem2CreditCol.setCellValueFactory(new PropertyValueFactory<>("creditHour"));
        sem2LecturerCol.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        sem2ReqCol.setCellValueFactory(new PropertyValueFactory<>("requirement"));
        sem2NoteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
    
        // Initialize columns for sem3
        sem3IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        sem3CreditCol.setCellValueFactory(new PropertyValueFactory<>("creditHour"));
        sem3LecturerCol.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        sem3ReqCol.setCellValueFactory(new PropertyValueFactory<>("requirement"));
        sem3NoteCol.setCellValueFactory(new PropertyValueFactory<>("note"));

        String name = StudentList.getNameFromStudentList(StudentList.students, studentId);
        studentName.setText(name);

        // Call method to calculate and display total credit hours
        calculateAndDisplayTotalCreditHours();

        // Load course into the courseComboBox
        courseComboBox.setItems(FXCollections.observableArrayList(CourseList.course));

        // Each table view has its own list of items
        selectedCoursesForSem1 = FXCollections.observableArrayList();
        selectedCoursesForSem2 = FXCollections.observableArrayList();
        selectedCoursesForSem3 = FXCollections.observableArrayList();

        //Load semester into semComboBox
        ObservableList<String> semOptions = FXCollections.observableArrayList("semester 1", "semester 2", "semester 3");
        semComboBox.setItems(semOptions);


        // Using Set to get enrolledCourse of student logged in
        Set<String> enrolledCourses = readEnrollmentFromFile(studentId);
        for (String courseInfo : enrolledCourses) {
            String[] parts = courseInfo.split(",");
            String courseId = parts[0];
            String semester = parts[1];
            
            Course course = CourseList.getCourseById(courseId);
            if (course != null) {
                switch (semester) {
                    case "semester 1":
                        selectedCoursesForSem1.add(course);
                        sem1.setItems(selectedCoursesForSem1);
                        break;
                    case "semester 2":
                        selectedCoursesForSem2.add(course);
                        sem2.setItems(selectedCoursesForSem2);
                        break;
                    case "semester 3":
                        selectedCoursesForSem3.add(course);
                        sem3.setItems(selectedCoursesForSem3);
                        break;
                }
            }
        }
        // Add listener to the stage's close request
        Platform.runLater(() -> {
            Stage stage = (Stage) sem1.getScene().getWindow(); // Assuming sem1 is one of your elements
            stage.setOnCloseRequest(event -> {
                if (errorShown) {
                    event.consume(); // Consume the event to prevent the window from closing
                    showCreditHourWarning();
                }
            });
        });
    }
    /**
     * Displays a warning alert indicating that the total credit hours for the semester
     * must be between 3 and 12.
     * This method creates an alert dialog with the specified warning message and displays it to the user.
     * Additionally, it retrieves the student's name and sets it in the welcome greeting text.
     */
    private void showCreditHourWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Total credit hours for the semester must be between 3 and 12.");
        alert.showAndWait();

        //Get student name in welcome greeting
        String name = StudentList.getNameFromStudentList(StudentList.students, studentId);
        studentName.setText(name);
    }
    /**
     * Handles the event when the user submits selected courses for enrollment.
     * Retrieves the selected course and semester from the respective ComboBoxes.
     * Validates the selected course against prerequisites, previous selections, and semester conflicts.
     * Adds the selected course to the appropriate semester's list and updates the enrollment file.
     * Calculates and displays the total credit hours after adding the course.
     * If no course or semester is selected, displays an alert.
     * @param event The action event triggered when the submission button is clicked.
     */
    @FXML
void submitSelectedCourses(ActionEvent event) {
    Course selectedCourse = courseComboBox.getValue();
    String semester = semComboBox.getValue();

    if (selectedCourse != null && semester != null) {
        // Check prerequisite courses
        if (!checkPrerequisites(selectedCourse)) {
            return;
        }

        // Check if the student has already chosen the selected course for the current or future semesters
        if (hasAlreadyChosenCourse(selectedCourse.getId())) {
            showAlert("You have already chosen the selected course for this or future semesters.");
            return;
        }

        // Check if the selected course conflicts with other courses in the same semester
        if (checkSemesterConflicts(selectedCourse, semester)) {
            return;
        }

        // Calculate total credit hours after adding the selected course
        int totalCreditHoursAfterAdding = calculateTotalCreditHoursAfterAdding(selectedCourse, semester);
        if (totalCreditHoursAfterAdding > 12) {
            showAlert("Adding this course would exceed the maximum credit hours allowed for the semester.");
            return;
        }

        // Add the course to the selected semester
        switch (semester) {
            case "semester 1":
                selectedCoursesForSem1.add(selectedCourse);
                sem1.setItems(selectedCoursesForSem1);
                updateEnrollmentFile(selectedCourse, semester, studentId, true);
                break;
            case "semester 2":
                selectedCoursesForSem2.add(selectedCourse);
                sem2.setItems(selectedCoursesForSem2);
                updateEnrollmentFile(selectedCourse, semester, studentId, true);
                break;
            case "semester 3":
                selectedCoursesForSem3.add(selectedCourse);
                sem3.setItems(selectedCoursesForSem3);
                updateEnrollmentFile(selectedCourse, semester, studentId, true);
                break;
            default:
                // Handle the case where the semester is not recognized
                break;
        }

        // Call method to update total credit hours after adding the course
        calculateAndDisplayTotalCreditHours();

    } else {
        // Handle the case where no course or semester is selected
        showAlert("Please select a course and a semester.");
    }
}

        /**
         * Calculates the total credit hours after adding the selected course to the specified semester.
         * @param selectedCourse The course to be added.
         * @param semester The semester to which the course will be added.
         * @return The total credit hours after adding the selected course.
         */
        private int calculateTotalCreditHoursAfterAdding(Course selectedCourse, String semester) {
            int totalCreditHours = 0;

            // Calculate total credit hours before adding the selected course
            switch (semester) {
                case "semester 1":
                    totalCreditHours = getTotalCreditHours(selectedCoursesForSem1);
                    break;
                case "semester 2":
                    totalCreditHours = getTotalCreditHours(selectedCoursesForSem2);
                    break;
                case "semester 3":
                    totalCreditHours = getTotalCreditHours(selectedCoursesForSem3);
                    break;
                default:
                    break;
            }

            // Add credit hours of the selected course
            totalCreditHours += selectedCourse.getCreditHour();
            return totalCreditHours;
        }

    /**
     * Calculates the total credit hours from the list of selected courses for a semester.
     * @param selectedCourses The list of selected courses for a semester.
     * @return The total credit hours for the semester.
     */
    private int getTotalCreditHours(ObservableList<Course> selectedCourses) {
        int totalCreditHours = 0;
        for (Course course : selectedCourses) {
            totalCreditHours += course.getCreditHour();
        }
        return totalCreditHours;
    }

    /**
     * Checks if the prerequisites for the selected course are met.
     * Prerequisites are specific to certain courses and must be completed before enrollment.
     * @param selectedCourse The course for which prerequisites need to be checked.
     * @return {@code true} if the prerequisites are met or if the selected course has no prerequisites,
     *         {@code false} otherwise.
     */
    private boolean checkPrerequisites(Course selectedCourse) {
        // Check prerequisites for specific courses
        if (selectedCourse.getId().equals("CS214")) {
            if (!hasCompletedCourse("CS113")) {
                showAlert("You must complete CS113 before choosing CS214.");
                return false;
            }
        }
    
        if (selectedCourse.getId().equals("CS224")) {
            if (!hasCompletedCourse("CS123")) {
                showAlert("You must complete CS123 before choosing CS224.");
                return false;
            }
        }
    
        if (selectedCourse.getId().equals("CS316")) {
            if (!hasCompletedCourse("CS133") || !hasCompletedCourse("CS214")) {
                showAlert("You must complete CS133 and CS214 before choosing CS316.");
                return false;
            }
        }
    
        return true;
    }
    /**
     * Checks if adding the selected course to the specified semester causes conflicts with other courses.
     * @param selectedCourse The course to be added.
     * @param semester The semester to check for conflicts.
     * @return {@code true} if adding the course causes conflicts with other courses in the same semester, {@code false} otherwise.
     */
    private boolean checkSemesterConflicts(Course selectedCourse, String semester) {
        // Check if the selected course conflicts with other courses in the same semester
        List<Course> selectedCoursesForCurrentSemester;
        switch (semester) {
            case "semester 1":
                selectedCoursesForCurrentSemester = selectedCoursesForSem1;
                break;
            case "semester 2":
                selectedCoursesForCurrentSemester = selectedCoursesForSem2;
                break;
            case "semester 3":
                selectedCoursesForCurrentSemester = selectedCoursesForSem3;
                break;
            default:
                return false; // Handle the case where the semester is not recognized
        }
    
        for (Course course : selectedCoursesForCurrentSemester) {
            if (checkSemesterConflict(selectedCourse, course)) {
                showAlert("Course conflict: " + selectedCourse.getId() + " and " + course.getId() + " cannot be in the same semester.");
                return true;
            }
        }
    
        return false;
    }
    /**
     * Checks if two courses have a semester conflict.
     * Two courses are considered to have a semester conflict if they are scheduled in the same semester.
     * Semester conflicts are determined based on predefined course pairs known to conflict with each other.
     * Additional conflict checks can be added as needed.
     *
     * @param course1 The first course to check for conflict.
     * @param course2 The second course to check for conflict.
     * @return {@code true} if there is a semester conflict between the two courses, {@code false} otherwise.
     */
    private boolean checkSemesterConflict(Course course1, Course course2) {
        // Check if two courses are in the same semester
        if (course1 == null || course2 == null) {
            return false;
        }
        return course1.getId().equals("CS113") && course2.getId().equals("CS214") ||
                course1.getId().equals("CS214") && course2.getId().equals("CS113") ||
                course1.getId().equals("CS224") && course2.getId().equals("CS123") ||
                course1.getId().equals("CS123") && course2.getId().equals("CS224") ||
                course1.getId().equals("CS316") && course2.getId().equals("CS133") ||
                course1.getId().equals("CS133") && course2.getId().equals("CS316") ||
                course1.getId().equals("CS316") && course2.getId().equals("CS214") ||
                course1.getId().equals("CS214") && course2.getId().equals("CS316") ||
                // Add more conflict checks if needed
                false;
    }
    /**
     * Displays an error alert with the given message.
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(String message) {
        // Create a new error alert
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // Set the title of the alert
        alert.setTitle("Error");
        // Set the header text of the alert (null indicates no header)
        alert.setHeaderText(null);
        // Set the content text of the alert to the provided message
        alert.setContentText(message);
        // Display the alert and wait for user interaction
        alert.showAndWait();
    }
    /**
     * Checks if a student has completed a specific course.
     * This method reads the enrollment data from a file for the given student ID
     * and checks if the course ID with different semester options is present in the enrollment set.
     *
     * @param courseId The ID of the course to check completion for.
     * @return {@code true} if the student has completed the course in any semester, {@code false} otherwise.
     */
    private boolean hasCompletedCourse(String courseId) {
        // Reads the enrollment data for the current student ID
        Set<String> enrolledCourses = readEnrollmentFromFile(studentId); 
        // Checks if the given course ID with different semester options is present in the enrollment set       
        return enrolledCourses.contains(courseId + ",semester 1")
                || enrolledCourses.contains(courseId + ",semester 2")
                || enrolledCourses.contains(courseId + ",semester 3");
    }
    /**
     * Checks if the student has already chosen the specified course for the current or future semesters.
     *
     * @param courseId The ID of the course to check for.
     * @return {@code true} if the student has already chosen the course for any of the current or future semesters,
     *         {@code false} otherwise.
     */
    private boolean hasAlreadyChosenCourse(String courseId) {
        // Check if the student has already chosen the specified course for the current or future semesters
        return selectedCoursesForSem1.stream().anyMatch(course -> course.getId().equals(courseId))
                || selectedCoursesForSem2.stream().anyMatch(course -> course.getId().equals(courseId))
                || selectedCoursesForSem3.stream().anyMatch(course -> course.getId().equals(courseId));
    }

    /**
     * Handles the action event for removing a selected course from the student's enrollment.
     * @param event The action event triggered when the removal button is clicked.
     */
    @FXML
    void removeSelectedCourse(ActionEvent event) {
        Course selectedCourse = courseComboBox.getValue();
    
        if (selectedCourse != null) {

            String semester = semComboBox.getValue();
            // Edit TableView and enrollment.csv based on selected semester
            switch (semester) {
                case "semester 1":
                    // edit the tableview // Remove selected course from semester 1 TableView and enrollment.csv
                    selectedCoursesForSem1.remove(selectedCourse);
                    sem1.setItems(selectedCoursesForSem1);
                    // edit enrollment.csv 
                    break;
                    case "semester 2":
                    // Remove selected course from semester 2 TableView and enrollment.csv
                    selectedCoursesForSem2.remove(selectedCourse);
                    sem2.setItems(selectedCoursesForSem2);
                    break;
                    case "semester 3":
                    // Remove selected course from semester 3 TableView and enrollment.csv
                    selectedCoursesForSem3.remove(selectedCourse);
                    sem3.setItems(selectedCoursesForSem3);
                    break;
                default: // create a warning label
                    // Display a warning label if the semester selection is invalid
                    break;
            }
            // edit csv file
            // removeCourseFromEnrollmentFile(selectedCourse, semester, studentId);
            // Update enrollment file to reflect removed course
            updateEnrollmentFile(selectedCourse, semester, studentId, false);
            // Recalculate and display total credit hours
            calculateAndDisplayTotalCreditHours();   
        } else {
            // Handle the case where no course is selected for removal
            System.out.println("Please select a course to remove.");
        }
    }
// read current student enrollment using List
// public static Map<String, String> readEnrollmentFromFile(int studentId) {
//     Map<String, String> enrolledCourses = new LinkedHashMap<>();
    
//     try {
//         List<String> lines = Files.readAllLines(Path.of("data/enrollment.csv"));
//         for (int i = 0; i < lines.size(); i++) {
//             String[] items = lines.get(i).split(",");
//             if (!items[0].isEmpty()){
//                 int enrolledStudentId = Integer.parseInt(items[0]);
//             // System.out.println(enrolledStudentId);
//             String courseId = items[1];
//             String sem = items[2];
//             //System.out.println(enrolledStudentId + courseId + sem);// printed correctly
//             if (enrolledStudentId == studentId) {
//                 enrolledCourses.put(courseId, sem);
//                 //System.out.println(enrolledCourses); ////correct [CS113=semester 1]
//             }
//         }
//         }
//     } catch (IOException ex) {
//         System.out.println("Error loading " + ex.getMessage());
//     }
//     return enrolledCourses;
// }

// using set to get the enrollment record
/**
 * Reads enrollment data from a file for a specified student ID and returns a set of enrolled courses.
 * Each entry in the set contains the course ID and the semester in the format "courseId,semester".
 * @param studentId The ID of the student for whom enrollment data is being retrieved.
 * @return A Set containing strings representing enrolled courses in the format "courseId,semester".
 */
public static Set<String> readEnrollmentFromFile(int studentId) {
    // Create a HashSet to store enrolled courses
    Set<String> enrolledCourses = new HashSet<>();

    try {
        // Read all lines from the enrollment file // pay attention to path
        List<String> lines = Files.readAllLines(Path.of("data/enrollment.csv"));
        // Iterate through each line in the file
        for (int i = 0; i < lines.size(); i++) {
            // Split the line into items using comma as delimiter
            String[] items = lines.get(i).split(",");
            // Check if the first item is not empty
            if (!items[0].isEmpty()){
                // Parse the enrolled student ID, course ID, and semester
                int enrolledStudentId = Integer.parseInt(items[0]);
                String courseId = items[1];
                String sem = items[2];
                
                // If the enrolled student ID matches the specified student ID, add the course to the set
                if (enrolledStudentId == studentId) {
                    enrolledCourses.add(courseId + "," + sem); // prints "courseId,semester"
                }
            }
        }
    } catch (IOException ex) {
        // Handle IO exception
        System.out.println("Error loading " + ex.getMessage());
    }
    return enrolledCourses;
}
// private void writeEnrollmentToFile(Course courseId, String semester) {
//     try (BufferedWriter writer = Files.newBufferedWriter(Path.of("data/enrollment.csv"), StandardOpenOption.APPEND)) { // useful when want to add new data to an existing file w/o losing existing data
//         writer.write(SessionManager.getCurrentUserId() + "," + courseId + "," + semester);
//         writer.newLine();
//     } catch (IOException ex) {
//         System.out.println("Error writing to file: " + ex.getMessage());
//     }
// }
// private void removeCourseFromEnrollmentFile(Course courseId, String semester, int studentID) {
//     Path filePath = Path.of("data/enrollment.csv");
//     try {
//         List<String> lines = Files.readAllLines(filePath);
//         BufferedWriter writer = Files.newBufferedWriter(filePath);
//         for (String line : lines) {
//             String[] items = line.split(",");
//             if (items[1].equals(courseId.getId()) && items[2].equals(semester) && studentID == studentId) { // if matches, proceed
//                 continue; 
//             }
//             writer.write(line);
//             writer.newLine();
//         }
//         writer.close();
//     } catch (IOException ex) {
//         System.out.println("Error writing to file: " + ex.getMessage());
//     }
// }

/**
 * Calculates and displays the total credit hours for enrolled courses.
 * Resets the errorShown flag before recalculating.
 * Reads the enrollment information from a file for the current student and organizes it by semester.
 * @see #readEnrollmentFromFile(int)
 */
private void calculateAndDisplayTotalCreditHours() {
    // Reset the errorShown flag before recalculating
    errorShown = false;

    // Read the enrolled courses for each semester
    Map<String, Set<String>> enrolledCoursesBySemester = new HashMap<>();
    enrolledCoursesBySemester.put("semester 1", new HashSet<>());
    enrolledCoursesBySemester.put("semester 2", new HashSet<>());
    enrolledCoursesBySemester.put("semester 3", new HashSet<>());

    Set<String> enrolledCourses = readEnrollmentFromFile(studentId);
    for (String courseInfo : enrolledCourses) {
        String[] parts = courseInfo.split(",");
        String courseId = parts[0];
        String semester = parts[1];

        enrolledCoursesBySemester.get(semester).add(courseId);
    }

    // Calculate total credit hours for each semester and update corresponding Text elements
    updateTotalCreditHoursForSemester(enrolledCoursesBySemester.get("semester 1"), totalsem1);
    updateTotalCreditHoursForSemester(enrolledCoursesBySemester.get("semester 2"), totalsem2);
    updateTotalCreditHoursForSemester(enrolledCoursesBySemester.get("semester 3"), totalsem3);
}
/**
 * Updates the total credit hours for the semester based on the enrolled courses and displays it in a text field.
 * Additionally, it checks if the total credit hours fall below 3 or exceed 12, showing an error message if necessary.
 * 
 * @param enrolledCourses A Set of Strings representing the IDs of the courses in which the student is enrolled.
 * @param totalSemesterText The Text object where the total semester credit hours will be displayed.
 */
private void updateTotalCreditHoursForSemester(Set<String> enrolledCourses, Text totalSemesterText) {
    // Initialize the total credit hours counter
    int totalCreditHours = 0;

    // Iterate through the enrolled courses and accumulate their credit hours
    // Read course information from "course.csv" and accumulate credit hours
    for (String courseId : enrolledCourses) {
        // Retrieve the Course object using the course ID
        Course course = CourseList.getCourseById(courseId);
        // If the course is found, add its credit hours to the total
        if (course != null) {
            totalCreditHours += course.getCreditHour();
        }
    } 

    // Update total semester text field with the calculated total credit hours
    totalSemesterText.setText(Integer.toString(totalCreditHours));

    // Check if the total credit hours fall below 3 or exceed 12
    if (totalCreditHours < 3 || totalCreditHours > 12) {
        // Only show the error message once
        // Show an error message if it's not already shown
        if (!errorShown) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Total credit hours for the semester must be between 3 and 12.");
            alert.showAndWait();
            errorShown = true; // Set the errorShown flag to true to indicate that the error has been shown

        }
    }
}

/**
 * Updates the enrollment file based on the specified parameters.
 *
 * @param courseId   The course identifier.
 * @param semester   The semester in which the enrollment occurs.
 * @param studentId  The student identifier.
 * @param add        A boolean indicating whether to add or remove enrollment.
 */
private void updateEnrollmentFile(Course courseId, String semester, int studentId, boolean add) { // pay attention to path
    Path filePath = Path.of("data/enrollment.csv");
    try {
        List<String> lines = Files.readAllLines(filePath); // reads all line from csv file
        BufferedWriter writer = Files.newBufferedWriter(filePath); // open a file to write and iterate each line
        for (String line : lines) {
            String[] items = line.split(","); // split studentid, courseid, semester n
            if (add && items[1].equals(courseId.getId()) && items[2].equals(semester)) {
                // if add = true, Skip writing this line and jump to next iteration since the course is already enrolled (this avoids duplication)
                continue;
            }
            if (!add && items[1].equals(courseId.getId()) && items[2].equals(semester) && Integer.parseInt(items[0]) == studentId) {
                // if add = false and found to be matching with line, skip writing this line and iterate to next
                continue;
            }
            writer.write(line);
            writer.newLine();
        }
        if (add) {
            writer.write(studentId + "," + courseId.getId() + "," + semester); // append to csv file when add is true and no match is found
            writer.newLine();
        }
        writer.close();
    } catch (IOException ex) {
        System.out.println("Error writing to file: " + ex.getMessage());
    }
} // it works by skipping lines tto write that need no change and adding/removing lines as necessary

    /**
     * Switches to the course page when the corresponding button is clicked.
     * @param event The action event triggered when the button is clicked.
     * @throws IOException If an I/O error occurs while switching pages.
     */
    @FXML
    void switchToCoursePage(ActionEvent event) throws IOException {
        PageSwitcher.switchToPage(event, "fxml/StudentCourseForm.fxml");
    }

    /**
     * No changes needed for this method.
     * This method is intended to handle an action event but does not switch pages.
     * @param event The action event triggered when the button is clicked.
     */
    @FXML
    void switchToPlanPage(ActionEvent event) {
        // No changes needed for this method
    }

    /**
     * Signs out the current user and switches to the login page when the sign-out button is clicked.
     * @param event The action event triggered when the button is clicked.
     * @throws IOException If an I/O error occurs while switching pages or signing out.
     */
    @FXML
    void signOut(ActionEvent event) throws IOException {
        PageSwitcher.signOut(event);
        PageSwitcher.switchToPage(event, "fxml/login.fxml");
    }  
}