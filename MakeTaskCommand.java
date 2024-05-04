import java.util.ArrayList;
import java.util.Scanner;
public class MakeTaskCommand extends Command{
    private ArrayList<Task> tasks;
    public MakeTaskCommand(ArrayList<Task> tasks){
        this.tasks = tasks;
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
        Scanner scanner = new Scanner(System.in);
        String caption = "";
        boolean warnKey = false;

        while (caption.isEmpty() || caption.length()>50) {
            if (!warnKey) {
                System.out.println("Enter task's caption: ");
            } else {
                System.out.println("Enter correct task's caption (not more than 50 symbols): ");
            }
            caption = scanner.nextLine();
            warnKey = true;
        }

        String description = "";

        while (description.isEmpty()) {
            System.out.println("Enter task's descriprion: ");
            description = scanner.nextLine();
        }

        warnKey = false;
        int priority = 0;

        while (priority < 1 || priority > 10) {
            if (!warnKey) {
                System.out.println("Enter task's priority: ");
            } else {
                System.out.println("Enter correct priority (1-10): ");
            }
            priority = scanner.nextInt();
            warnKey = true;
        }

        String  deadline = "";
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        warnKey = false;

        while (deadline.isEmpty() || !deadline.matches(datePattern)) {
            if (!warnKey) {
                System.out.println("Enter task's deadline (yyyy-mm-dd): ");
            } else {
                System.out.println("Enter correct format of date (yyyy-mm-dd): ");
            }
            deadline = scanner.next();
            warnKey = true;
        }

        int lastId = 0;
        for (Task task: tasks) {
            lastId = task.getId();
        }
        int curId = lastId + 1;
        Status status = Status.NEW;
        Task task = new Task(curId, caption, description, priority, deadline, status, null);
        tasks.add(task);
    }
    @Override
    public String getDescription() {
        return "Makes task";
    }
}
