/**
 * Represents a task with a unique ID, caption, description, priority, deadline, status and completion date.
 *
 * @author Evgeniy Matagirov
 * @version 1.0
 */
public class Task {
    /**
     * The unique identifier of the task in int format.
     */
    private int id;
    /**
     * The caption of the task, no more than 50 symbols.
     */
    private String caption;
    /**
     *  The description of the task.
     */
    private String description;

    /**
     *  The priority of the task, takes values 1-10.
     */
    private int priority;
    /**
     *  The deadline of the task in the format "yyyy-MM-dd".
     */
    private String deadline;
    /**
     *  The status of the task.
     *  3 variants: NEW, IN_PROGRESS, DONE.
     */
    private Status status;
    /**
     *  The completion date of the task in the format "yyyy-MM-dd" or null if the task isn't complete.
     */
    private String  completeStatus;

    /**
     *  Creates an empty task object.
     */
    public Task() {
    }

    /**
     *  Creates a task object with the specified parameters.
     *  @param id the unique identifier of the task in int format.
     *  @param caption the caption of the task, no more than 50 symbols.
     *  @param description the description of the task.
     *  @param priority the priority of the task, takes values 1-10.
     *  @param deadline the deadline of the task in the format "yyyy-MM-dd".
     *  @param status the status of the task.
     *  3 variants: NEW, IN_PROGRESS, DONE.
     *  @param completeStatus the completion date of the task in the format "yyyy-MM-dd" or null if the task isn't complete.
     */
    public Task(int id, String caption, String description, int priority, String deadline, Status status, String completeStatus) {
        this.id = id;
        this.caption = caption;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
        this.completeStatus = completeStatus;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status of the task.
     */
    public Status getStatus(){
       return status;
    }

    /**
     * Returns the ID of the task.
     *
     * @return the ID of the task.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the caption of the task.
     *
     * @return the caption of the task.
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the priority  of the task.
     *
     * @return the priority of the task.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the deadline  of the task.
     *
     * @return the deadline of the task.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns the completion date of the task.
     *
     * @return the completion of the task.
     */
    public String  getCompleteStatus() {
        return completeStatus;
    }

    /**
     * Sets the caption of the task.
     * @param caption the new caption of the task.
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Sets the description of the task.
     * @param description the new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the priority of the task.
     * @param priority the new priority of the task.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Sets the deadline of the task.
     * @param deadline the new deadline of the task in "yyyy-MM-dd" format.
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Sets the status of the task.
     * @param status the new status of the task.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Sets the completion date of the task.
     * @param date the new completion date of the task in "yyyy-MM-dd" format.
     */
    public void setCompleteStatus (String date) {
        this.completeStatus = date;
    }

    /**
     * Returns a string representation of the task object.
     * @return a string representation of the task object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Caption: ").append(caption).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Priority: ").append(priority).append("\n");
        sb.append("Deadline: ").append(deadline).append("\n");
        sb.append("Status: ").append(status).append("\n");
        if (completeStatus != null) {
            sb.append("Complete: ").append(completeStatus).append("\n");
        }
        return sb.toString();
    }
}