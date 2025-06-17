package dataManager;
import object.Course;
import object.Lecturer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The LecturerList class manages a list of lecturers stored in a CSV file.
 */
public class LecturerList {
    /** The filename of the CSV file storing the lecturer data. */
    public final static String filename = "data/lecturer.csv";
    /** The list of lecturers read from the CSV file. */
    public static List<Lecturer> lecturer = readLecturerFromFile();

  /**
   * Reads lecturer data from the CSV file and returns a list of lecturers.
   * @return A list of Lecturer objects read from the CSV file.
   */
  public static List<Lecturer> readLecturerFromFile() {
    List<Lecturer> lecturers = new ArrayList<>();
    try {
      // read students.csv into a list of lines.
      List<String> lines = Files.readAllLines(Path.of(filename));
      for (int i = 0; i < lines.size(); i++) {
        // split a line by comma
        String[] items = lines.get(i).split(",");
        int id = Integer.parseInt(items[0]); // convert String to int
        lecturers.add(new Lecturer(id, null, items[1]));
      }
    } catch (IOException ex) {
      System.out.println("Error loading " + ex.getMessage());
    }
    return lecturers;
  }

/**
 * Writes a list of lecturers to the CSV file.
 * @param lecturers The list of Lecturer objects to be written to the file.
 */
public static void writeLecturerToFile(List<Lecturer> lecturers) {
    try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filename))) {
        for (Lecturer lecturer : lecturers) {
            writer.write(lecturer.getID() + "," + lecturer.getName());
            writer.newLine();
        }
    } catch (IOException ex) {
        System.out.println("Error writing to file: " + ex.getMessage());
    }
}

/**
 * Retrieves the name of a lecturer given their ID from a list of lecturers.
 * @param lecturer The list of Lecturer objects.
 * @param lecturerId The ID of the lecturer whose name is to be retrieved.
 * @return The name of the lecturer with the specified ID, or "Name not found" if not found.
 */
public static String getNameFromLecturerList(List<Lecturer> lecturer, int lecturerId) {
      for (Lecturer x : lecturer)
        if (x.getID() == lecturerId)
            return x.getName();
    return "Name not found";
}
}