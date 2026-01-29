package rickshaw;

/**
 * Represents exceptions specific to the Rickshaw application.
 */
public class RickshawException extends Exception {
    /**
     * Constructs a new RickshawException with the specified message.
     *
     * @param message The message to be displayed when exception is thrown.
     */
    public RickshawException(String message) {
        super(message);
    }
}
