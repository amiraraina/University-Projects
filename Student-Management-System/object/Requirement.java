package object;

/**
 * The Requirement class represents a specific type of course that serves as a requirement
 * within a curriculum. It extends the Course class.
 */
public class Requirement extends Course { // Requirement is a Course
    /**
     * Constructs a new Requirement object with the specified ID, credit hours, and lecturer.
     * 
     * @param id The unique identifier for the requirement.
     * @param creditHour The number of credit hours associated with the requirement.
     * @param lecturer The name of the lecturer or instructor for the requirement.
     */
    public Requirement(String id, int creditHour, String lecturer) {
        super(id, creditHour, lecturer,null, null);
    }

    /**
     * Returns a string representation of the Requirement object.
     * 
     * @return A string representation of the Requirement object, including its ID.
     */
    @Override
    public String toString() {
        return super.toString(); // returns id
    }
}