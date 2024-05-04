/**
 * Abstract class representing a command that can be executed by the application.
 */
public abstract class Command {
    /**
     *  Executes the command.
     */
    public abstract void execute();

    /**
     * Returns a description of the command.
     *
     * @return a description of the command
     */
    public abstract String getDescription();

    /**
     * Finds a task by its ID.
     *
     * @param id the ID of the task to find
     * @return the task with the specified ID, or null if not found
     */
    public abstract Task findById(long id);

    /**
     * Gets the ID of the task associated with this command.
     *
     * @return the ID of the task associated with this command
     */
    public abstract long getTaskId();
}