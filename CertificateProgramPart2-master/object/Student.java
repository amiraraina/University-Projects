package object;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a student who is a user of the system.
 */
public class Student extends User {
    /** The name of the student. */
    private String name;
    /** The unique ID of the student. */
    private int ID;
    /** The set of courses associated with the student. */
    private Set<Course> setCourses; // Anis
    /**
     * Constructs a new Student object with the specified user ID, password, and name.
     * 
     * @param userid   The user ID of the student.
     * @param password The password of the student.
     * @param name     The name of the student.
     */
    public Student(int userid, String password, String name) {
        super(userid, password);
        this.name = name;
    }

    /**
     * Retrieves the ID of the student.
     * 
     * @return The ID of the student.
     */
    public int getID() {
        return super.getUserid();
    }

    /**
     * Sets the ID of the student.
     * 
     * @param ID The ID to set.
     */
    public void setID(int ID){
        this.ID = ID;
    }

    // given id, get the name
    /**
     * Retrieves the name associated with the specified ID.
     * 
     * @param id The ID of the object whose name is to be retrieved.
     * @return The name associated with the specified ID.
     */
    public String getName(int id) {
        return name;
    }

    /**
     * Retrieves the name of the student.
     * 
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the set of courses the student is enrolled in.
     * 
     * @return The set of courses the student is enrolled in.
     */
    public Set<Course> getCourses() {
        return setCourses;
    }

    /**
     * Adds the student to a course.
     * 
     * @param course The course to add the student to.
     */
    public void setInCourse(Course course) { //Anis
        setCourses.add(course);
    }

    /**
     * Removes the student from a course.
     * 
     * @param course The course to remove the student from.
     */
    public void unenrollFromCourse(Course course) { //Anis
        setCourses.remove(course);
    }

    /**
     * Returns a string representation of the student.
     * 
     * @return A string representation of the student.
     */
    @Override
    public String toString() {
        return "studentID=" + ID + ", name=" + name;
    }
}