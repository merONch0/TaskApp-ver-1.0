import java.util.ArrayList;

public class ListTasksCommand extends Command {
    private Status status;
    private ArrayList<Task> tasks;
    public ListTasksCommand (ArrayList<Task> tasks){
       this.tasks = tasks;
       this.status = null;
    }
    public ListTasksCommand(ArrayList<Task> tasks, Status status) {
        this.tasks = tasks;
        this.status = status;
    }
    @Override
    public Task findById(long id) {
        return null;
    }

    @Override
    public long getTaskId() {
        return 0;
    }
    @Override
    public void execute(){
        System.out.println("Tasks: ");
        for (Task task: tasks) {
            if (status == null || task.getStatus().equals(status)) {
                System.out.println(task);
            }
        }
    }
    @Override
    public String getDescription() {
        return "Shows list of all tasks, supplies output with sorting by status";
    }
}
