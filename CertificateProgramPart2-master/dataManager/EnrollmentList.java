package dataManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import object.EnrollmentRecord;
import object.Lecturer;

/**
 * The EnrollmentList class represents a list of enrollment records for courses.
 * It provides methods to read enrollment data from a file and retrieve enrollments
 * based on a specified semester.
 */
public class EnrollmentList {
    /**
     * A set containing enrollment records for courses. Populated by reading data
     * from a file upon class initialization.
     */
    public static Set<EnrollmentRecord> enrolledCourses = readEnrollmentFromFile();
    /**
     * Reads enrollment data from a file and populates a set of EnrollmentRecord objects.
     * The file is expected to be in CSV format with each line representing an enrollment.
     * The format for each line should be: studentId,courseId,semester
     *
     * @return A set of EnrollmentRecord objects representing the enrolled courses.
     */
    public static Set<EnrollmentRecord> readEnrollmentFromFile() {
        Set<EnrollmentRecord> enrolledCourses = new HashSet<>();

        try {
            List<String> lines = Files.readAllLines(Path.of("data/enrollment.csv"));
            for (int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(",");
                if (!items[0].isEmpty()){
                    int enrolledStudentId = Integer.parseInt(items[0]);
                    String courseId = items[1];
                    String sem = items[2];
                    EnrollmentRecord record = new EnrollmentRecord(enrolledStudentId, courseId, sem);
                    enrolledCourses.add(record);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error loading " + ex.getMessage());
        }
        return enrolledCourses;
    }

    /**
     * Retrieves a list of enrollment records for a specified semester.
     *
     * @param selectedSemester The semester for which enrollments are to be retrieved.
     * @return A list of enrollment records formatted as strings (studentId,courseId,semester).
     */
    public static List<String> getEnrollmentBySemester(String selectedSemester) {
        List<String> enrollmentsBySemester = new ArrayList<>();
        for (EnrollmentRecord record : enrolledCourses) {
            if (record.getSemester().equalsIgnoreCase(selectedSemester)) {
                String enrollmentString = record.getStudentId() + "," + record.getCourseId() + "," + record.getSemester();
                enrollmentsBySemester.add(enrollmentString);
            }
        }
        return enrollmentsBySemester;
    }
}