import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import object.Course;
import object.Lecturer;
import object.Student;
import object.User;

/**
 * The Main class represents the entry point of the application. It extends
 * the Application class provided by JavaFX.
 */
public class Main extends Application {
    /**
     * The primary stage of the application.
     */
    private Stage primaryStage;
    /**
     * A list of users in the application.
     */
    private List<User> users;
    /**
     * Observable list of students in the application.
     */
    private ObservableList<Student> students = FXCollections.observableArrayList();
    /**
     * Observable list of lecturers in the application.
     */
    private ObservableList<Lecturer> lecturers = FXCollections.observableArrayList();
    /**
     * Observable list of courses in the application.
     */
    private ObservableList<Course> courses = FXCollections.observableArrayList();

    /**
     * Constructs a new Main instance.
     */
    public Main() {
        //
    }

    /**
     * The main method, which serves as the entry point of the application.
     * It launches the JavaFX application.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method is called when the application is launched. It loads
     * the login page by loading the FXML file "login.fxml" using FXMLLoader.
     * The login.fxml file is associated with the loginController.java file.
     * 
     * @param primaryStage The primary stage of the application
     * @throws Exception If an error occurs during loading of the FXML file
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // start by loading login page
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml")); // the login.fxml connects with loginController.java
        primaryStage.setTitle("Certificate Program");
        primaryStage.setScene(new Scene(root, 1000,600));
        primaryStage.show();
    }
}