package dataManager;
import object.Course;
import object.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The StudentList class manages a list of Student objects.
 */
public class StudentList {
  /** The filename of the CSV file containing student data. */
  public final static String filename = "data/student.csv"; //Amira
  /** The list of Student objects read from the CSV file. */
  public static List<Student> students = readStudentFromFile();
  /**
   * Reads student data from a CSV file and creates Student objects.
   * @return A list of Student objects read from the file.
   */
  public static List<Student> readStudentFromFile() { //Amira
    List<Student> students = new ArrayList<>();
    try {
      // read students.csv into a list of lines.
      List<String> lines = Files.readAllLines(Path.of(filename));
      for (int i = 0; i < lines.size(); i++) {
        // split a line by comma
        String[] items = lines.get(i).split(",");
        // items[0] is id, items[1] is name
        int id = Integer.parseInt(items[0]); // convert String to int
        students.add(new Student(id, null, items[1]));
      }
    } catch (IOException ex) {
      System.out.println("Error loading " + ex.getMessage());
    }
    return students;
  }

/**
 * Writes student data to a CSV file.
 * @param students The list of Student objects to write to the file.
 */
public static void writeStudentToFile(List<Student> students) {
    try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filename))) {
        for (Student student : students) {
            writer.write(student.getID() + "," + student.getName());
            writer.newLine();
        }
    } catch (IOException ex) {
        System.out.println("Error writing to file: " + ex.getMessage());
    }
}

/**
     * Gets the last name of a student from the list based on their ID.
     * @param students The list of Student objects to search.
     * @param id The ID of the student to find.
     * @return The last name of the student with the given ID, or null if not found.
     */
public static String getNameFromStudentList(List<Student> students, int id) {
  for (Student student : students) {
      if (student.getID() == id) { // get name if student matches
          String[] parts = student.getName().split(" ");
          if (parts.length > 1)
              // return student.getName(); 
              return parts[parts.length - 1]; // get last name
      }
  } // handle if there is no match
  return null; 
}

/**
 * Retrieves a student object from the list by ID.
 * @param studentId The ID of the student to retrieve.
 * @return The Student object with the given ID, or null if not found.
 */
public static Student getStudentById(int studentId) {
    for (Student student : students)
        if (student.getID() == studentId)
            return student;
    return null;
}
}