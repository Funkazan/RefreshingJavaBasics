package enums;

/**
 * Represents the possible statuses of a task or process.
 * <ul>
 *   <li>{@link #TODO} - The task has not been started yet.</li>
 *   <li>{@link #IN_PROGRESS} - The task is currently being worked on.</li>
 *   <li>{@link #DONE} - The task has been completed.</li>
 * </ul>
 */
public enum Status {
    TODO,
    IN_PROGRESS,
    DONE;
}
