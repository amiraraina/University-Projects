package object;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;

/**
 * Represents a course in MMU.
 * A Course has an ID, note, credit hour, lecturer, and requirement.
 */
public class Course {
    /**
     * The unique identifier of the course.
     */
    private String id;
    /**
     * The number of credit hours associated with the course.
     */
    private int creditHour;
    /**
     * The lecturer or instructor of the course.
     */
    private String lecturer;
    /**
     * The requirements needed for taking this course.
     */
    private Requirement requirement; // Course has a requirement
    /**
     * Additional notes or information about the course.
     */
    private String note;
    /**
     * The selection status of the course. It indicates whether the course is selected or not.
     */
    private BooleanProperty selected;

    /**
     * Constructs a new Course object with default values.
     */
    public Course(){};

    /**
     * Constructs a new Course object with the specified parameters.
     * @param id The unique identifier of the course.
     * @param creditHour The credit hours associated with the course.
     * @param lecturer The name of the lecturer for the course.
     * @param requirement The requirement object associated with the course.
     * @param note Additional notes or comments for the course.
     */
    public Course(String id, int creditHour, String lecturer, Requirement requirement, String note) {
        this.id = id;
        this.lecturer = lecturer;
        this.creditHour = creditHour;
        this.requirement = requirement;
        this.note = note; // can leave as null
    }

    // public Course(String id, int creditHour, String lecturer, Requirement requirement, String note, boolean isSelected) {
    //     this.id = id;
    //     this.creditHour = creditHour;
    //     this.lecturer = lecturer;
    //     this.requirement = requirement;
    //     this.note = note; 
    //     this.selected = new SimpleBooleanProperty(isSelected); // Initialize the BooleanProperty
    // }

    /**
     * Retrieves whether the course is selected.
     * @return true if the course is selected, false otherwise.
     */
    public boolean isSelected() {
        return selected.get();
    }

    /**
     * Sets the selection status of the course.
     * @param selected The new selection status of the course.
     */
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    /**
     * Gets the BooleanProperty representing the selection status of the course.
     * @return The BooleanProperty representing the selection status.
     */
    public BooleanProperty selectedProperty() {
        return selected;
    }

    /**
     * Sets the unique identifier of the course.
     * @param id The new unique identifier of the course.
     */
    public void setId(String id) {
        this.id = id;
    } 

    /**
     * Gets the unique identifier of the course.
     * @return The unique identifier of the course.
     */
    public String getId(){
        return id;
    }

    /**
     * Sets the ID of the course.
     * @param id The ID to set for the course.
     */
    public void setNote(String id) {
        this.id = id;
    } 

    /**
     * Gets the note of the course.
     * @return The note of the course.
     */
    public String getNote(){
        return note;
    }

    /**
     * Sets the credit hour of the course.
     * @param creditHour The credit hour to set for the course.
     */
    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    /**
     * Gets the credit hour of the course.
     * @return The credit hour of the course.
     */
    public int getCreditHour(){
        return creditHour;
    }

    /**
     * Sets the name of the lecturer for the course.
     * @param lecturer The name of the lecturer to set for the course.
     */
    public void setName(String lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * Gets the name of the lecturer for the course.
     * @return The name of the lecturer for the course.
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Sets the requirement for the course.
     * @param requirement The requirement to set for the course.
     */
    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    /**
     * Gets the requirement for the course.
     * @return The requirement for the course.
     */
    public Requirement getRequirement() {
        return requirement;
    }

    /**
     * Returns a string representation of the course.
     * @return The ID of the course.
     */
    @Override
    public String toString() {
        return id;
    }
}