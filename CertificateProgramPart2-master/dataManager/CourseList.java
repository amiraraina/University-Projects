package dataManager;
import object.Course;
import object.Requirement;
import object.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of courses and provides utility methods for managing courses.
 */
public class CourseList {
  /**
   * The filename of the CSV file storing course data.
   */
  public final static String filename = "data/course.csv";
  /**
   * A list containing Course objects read from the CSV file.
   */
  public static List<Course> course = CourseList.readCourseFromFile();
  /**
   * Reads course data from the CSV file and returns a list of Course objects.
   *
   * @return A list of Course objects read from the CSV file.
   */
  public static List<Course> readCourseFromFile() {
    List<Course> courses = new ArrayList<>();
    try {
      List<String> lines = Files.readAllLines(Path.of(filename));
      for (int i = 0; i < lines.size(); i++) {
        String[] items = lines.get(i).split(",");
        // items[0] is course id, items[1] is creditHour, lecturer, requirement, items[4] is extra note
        int credit = Integer.parseInt(items[1]); // convert String to int
        Requirement requirement = new Requirement(items[3], 0, null); // get only the id
        courses.add(new Course(items[0], credit, items[2], requirement, items[4]));
      }
    } catch (IOException ex) {
      System.out.println("Error loading " + ex.getMessage());
    }
    return courses;
  }
  
  /**
   * Writes course data to the CSV file.
   *
   * @param courses The list of Course objects to write to the CSV file.
   */
  public static void writeCourseToFile(List<Course> courses) {
    try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filename))) {
        for (Course course : courses) {
            writer.write(course.getId() + "," + course.getCreditHour() + "," + course.getLecturer() + "," +
                         course.getRequirement() + "," + course.getNote());
            writer.newLine();
        }
    } catch (IOException ex) {
        System.out.println("Error writing to file: " + ex.getMessage());
    }
}

// similarly
// public static String getNameFromStudentList(List<Student> students, int id) {
//   for (Student student : students) {
//       if (student.getID() == id) { // get name if student matches
//           String[] parts = student.getName().split(" ");
//           if (parts.length > 1)
//               // return student.getName(); 
//               return parts[parts.length - 1]; // get last name
//       }
//   } // handle if there is no match
//   return null; 
// }

/**
 * Retrieves a course by its ID.
 *
 * @param courseId The ID of the course to retrieve.
 * @return The Course object with the specified ID, or null if not found.
 */
public static Course getCourseById(String courseId) {
    for (Course course : course)
        if (course.getId().equals(courseId))
            return course;
    return null; // Course not found
}

/**
 * Retrieves a course by its lecturer's name.
 *
 * @param lecturerName The name of the lecturer.
 * @return The Course object taught by the specified lecturer, or null if not found.
 */
public static Course getCourseByLecturer(String lecturerName) {
  for (Course course : course)
      if (course.getLecturer().equals(lecturerName))
          return course;
  return null; // Course not found
}
}