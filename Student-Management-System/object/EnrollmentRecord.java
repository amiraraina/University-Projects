package object;

/**
 * The EnrollmentRecord class represents a single enrollment record for a student.
 * It stores information about the student's ID, the course ID, and the semester.
 */
public class EnrollmentRecord {
    /** The ID of the student enrolled in the course. */
    private int studentId;
    /** The ID of the course the student is enrolled in. */
    private String courseId;
    /** The semester in which the student is enrolled in the course. */
    private String semester;

    /**
     * Constructs a new EnrollmentRecord with the specified student ID, course ID, and semester.
     * 
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     * @param semester The semester.
     */
    public EnrollmentRecord(int studentId, String courseId, String semester){
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
    }

    /**
     * Returns the ID of the student.
     * 
     * @return The student ID.
     */
    public int getStudentId(){
        return studentId;
    }

    /**
     * Sets the ID of the student.
     * 
     * @param studentId The new student ID.
     */
    public void setStudentId(int studentId){
        this.studentId = studentId;
    }

    /**
     * Returns the ID of the course.
     * 
     * @return The course ID.
     */
    public String getCourseId(){
        return courseId;
    }

    /**
     * Sets the ID of the course.
     * 
     * @param courseId The new course ID.
     */
    public void setCourseId(String courseId){
        this.courseId = courseId;
    }

    /**
     * Returns the semester.
     * 
     * @return The semester.
     */
    public String getSemester(){
        return semester;
    }

    /**
     * Sets the semester.
     * 
     * @param semester The new semester.
     */
    public void setSemester(String semester){
        this.semester = semester;
    }
}