import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EditTaskCommand extends Command {
    private ArrayList<Task> tasks;

    public EditTaskCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
    public long getTaskId() {
        Scanner scanner = new Scanner(System.in);
        long id;
        while (true) {
            try {
                id = scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Enter correct ID: ");
                scanner.nextLine();
            }
        }
        return id;
    }

    @Override
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        Task task = null;
        long id = 0;
        boolean warnKey;

        while (id == 0) {
            System.out.println("Enter task ID to edit: ");
            id = getTaskId();
            task = findById(id);
        }

        String action = "";
        String changeParam = "";
        warnKey = false;

        if (task != null) {
            System.out.println("Do you want edit caption? (yes/no)");
            while (action.isEmpty() || (!action.equals("yes") && !action.equals("no"))) {
                action = scanner.nextLine();
            }
            if (action.equals("yes")) {
                System.out.println("Enter new caption:");
                while (changeParam.isEmpty() || changeParam.length()>50) {
                    if (warnKey) {
                        System.out.println("Enter correct caption (length > 50)");
                    }
                    changeParam = scanner.nextLine();
                    warnKey = true;
                }
                task.setCaption(changeParam);
            }

            action = "";
            changeParam = "";

            System.out.println("Do you want edit description? (yes/no)");
            while (action.isEmpty() || (!action.equals("yes") && !action.equals("no"))) {
                action = scanner.nextLine();
            }
            if (action.equals("yes")) {
                System.out.println("Enter new description:");
                while (changeParam.isEmpty()) {
                    changeParam = scanner.nextLine();
                }
                task.setDescription(changeParam);
            }

            action = "";
            int intChangeParam = 0;

            System.out.println("Do you want edit priority? (yes/no)");
            while (action.isEmpty() || (!action.equals("yes") && !action.equals("no"))) {
                action = scanner.nextLine();
            }
            if (action.equals("yes")) {
                System.out.println("Enter new priority:");
                while (intChangeParam == 0 || intChangeParam < 0 || intChangeParam > 10) {
                    try {
                        intChangeParam = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Enter correct priority: ");
                        scanner.nextLine();
                    }
                }
                task.setPriority(intChangeParam);
            }

            action = "";
            String dateChangeParam = "";
            String datePattern = "\\d{4}-\\d{2}-\\d{2}";
            warnKey = false;
            System.out.println("Do you want edit deadline? (yes/no)");
            while (action.isEmpty() || (!action.equals("yes") && !action.equals("no"))) {
                action = scanner.nextLine();
            }
            if (action.equals("yes")) {
                System.out.println("Enter new deadline (yyyy-mm-dd):");
                while (dateChangeParam.isEmpty() || !dateChangeParam.matches(datePattern)) {
                    if (warnKey) {
                        System.out.println("Enter correct format of date (yyyy-mm-dd): ");
                    }
                    dateChangeParam = scanner.next();
                    warnKey = true;
                }
                task.setDeadline(dateChangeParam);
            }
        } else {
            System.out.println("Task not found. ");
        }
    }
    @Override
    public String getDescription() {
        return "Edits task";
    }
}
