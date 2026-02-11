package rickshaw.commands;

/**
 * Represents the type of command.
 */
public enum CommandType {
    /** Lists all tasks. */
    LIST,
    /** Marks a task as done. */
    MARK,
    /** Marks a task as not done. */
    UNMARK,
    /** Deletes a task. */
    DELETE,
    /** Adds a todo task. */
    TODO,
    /** Adds a deadline task. */
    DEADLINE,
    /** Adds an event task. */
    EVENT,
    /** Exits the application. */
    BYE,
    /** Finds tasks by keyword. */
    FIND,
    /** Adds a tag to a task. */
    TAG,
    /** Removes a tag from a task. */
    UNTAG,
}
