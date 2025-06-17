package object;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The Lecturer class represents a lecturer in the educational system.
 * It extends the User class to inherit user identification and authentication properties.
 */
public class Lecturer extends User {
    /** The unique identifier of the lecturer. */
    private int ID;
    /** The name of the lecturer. */
    private String name;

    /**
     * Constructs a new Lecturer object with the specified user ID, password, and name.
     * 
     * @param userid The user ID of the lecturer.
     * @param password The password of the lecturer.
     * @param name The name of the lecturer.
     */
    public Lecturer(int userid, String password, String name) {
        super(userid, password);
        this.name = name;
    }

    /**
     * Retrieves the ID of the lecturer.
     * 
     * @return The ID of the lecturer.
     */
    public int getID() {
        return super.getUserid();
    }

    /**
     * Sets the ID of the lecturer.
     * 
     * @param ID The ID to set for the lecturer.
     */
    public void setID(int ID){
        this.ID = ID;
    }

    /**
     * Retrieves the name of the lecturer.
     * 
     * @return The name of the lecturer.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the lecturer.
     * 
     * @param name The name to set for the lecturer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the lecturer.
     * 
     * @return The name of the lecturer.
     */
    @Override
    public String toString() {
        return name;
    }
}