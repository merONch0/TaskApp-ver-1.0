import java.util.ArrayList;
import java.util.Scanner;

public class RemoveTaskCommand extends Command {
    private ArrayList<Task> tasks;

    public RemoveTaskCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    @Override
    public long getTaskId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task ID to remove: ");
        return scanner.nextLong();
    }
    @Override
    public Task findById(long id) {
        for (Task task: tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
    @Override
    public void execute(){
        long id = 0;
        while (id == 0) {
            id = getTaskId();
        }
        Task task = findById(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Task removed successfully.\n");
        } else {
            System.out.println("Task not found.\n");
        }
    }
    @Override
    public String getDescription() {
        return "Removes task";
    }
}
