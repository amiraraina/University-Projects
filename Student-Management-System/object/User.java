package object;

/**
 * The User class represents a user with a unique user ID and a password.
 */
public class User {
    /**
     * The name of the user.
     */
    private int userid;
    /**
     * The unique identifier of the user.
     */
    private String password;

    /**
     * Constructs a new User object with the specified user ID and password.
     * 
     * @param userid the unique user ID
     * @param password the user's password
     */
    public User(int userid, String password) {
        this.userid = userid;
        this.password = password;
    }
    
    /**
     * Gets the user's ID.
     * 
     * @return the user's ID
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Gets the user's password.
     * 
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }
}