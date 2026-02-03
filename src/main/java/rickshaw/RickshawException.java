package rickshaw;

/**
 * Represents exceptions specific to the Rickshaw application.
 */
public class RickshawException extends Exception {
    /**
     * Constructs a new RickshawException with formatted message
     *
     * @param message message to be displayed when exception is thrown.
     * @param args arguments to be formatted into the message.
     */
    public RickshawException(String message, Object... args) {
        super(String.format(message, args));
    }
}
