import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MarkDoneTaskCommand extends Command {
    private ArrayList<Task> tasks;

    public MarkDoneTaskCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    @Override
    public Task findById(long id){
        for (Task task: tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public long getTaskId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task ID to mark as done: ");
        return scanner.nextInt();
    }

    @Override
    public void execute() {
        long id = 0;
        while (id == 0) {
            id = getTaskId();
        }
        Task task = findById(id);
        if (task != null) {
        task.setStatus(Status.DONE);
            LocalDateTime ldt = LocalDateTime.now().plusDays(0);
            DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            String formatter = formmat1.format(ldt);
        task.setCompleteStatus(formatter);
        System.out.println("Task " + id + " marked as done\n");
        }
        else {
            System.out.println("Task not found");
        }
    }

    @Override
    public String getDescription() {
        return "Makes task done";
    }
}