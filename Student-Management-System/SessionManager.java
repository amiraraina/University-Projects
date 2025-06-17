/**
 * The SessionManager class manages the session state of the user.
 * It provides functionality to set and retrieve the current user ID.
 */
public class SessionManager {
    /**
     * Constructs a new SessionManager object.
     * This constructor initializes the session manager.
     */
    public SessionManager() {
        //
    }
    /** The current user ID stored in the session. */
    private static String currentUserId;

    /**
     * Sets the current user ID in the session.
     * @param userId The ID of the user to set as the current user.
     */
    public static void setCurrentUserId(String userId) {
        currentUserId = userId;
    }

    /**
     * Retrieves the current user ID from the session.
     * @return The current user ID.
     */
    public static String getCurrentUserId() {
        return currentUserId;
    }
}