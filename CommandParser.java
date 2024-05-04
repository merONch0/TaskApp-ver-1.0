import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * CommandParser class is responsible for parsing user input and returning the corresponding Command object.
 *  It maintains a map of available commands and their corresponding Command objects.
 *  It also stores a list of tasks that can be manipulated by the commands.
 */
public class CommandParser {
    /**
     * A map of available commands and their corresponding Command objects.
     */
    private final Map<String, Command> commands;

    /**
     * Constructs a new CommandParser object with the given list of tasks.
     * It initializes the map of available commands and their corresponding Command objects.
     *
     * @param tasks the list of tasks that can be manipulated by the commands.
     */
    public CommandParser(ArrayList<Task> tasks) {
        /*
         * A list of tasks that can be manipulated by the commands.
         */
        commands = new HashMap<>();
        commands.put("help", new HelpCommand(commands));
        commands.put("mktask", new MakeTaskCommand(tasks));
        commands.put("edtask", new EditTaskCommand(tasks));
        commands.put("rmtask", new RemoveTaskCommand(tasks));
        commands.put("mdtask", new MarkDoneTaskCommand(tasks));
        commands.put("list", new ListTasksCommand(tasks));
        commands.put("list -s new", new ListTasksCommand(tasks, Status.NEW));
        commands.put("list -s in_progress", new ListTasksCommand(tasks, Status.IN_PROGRESS));
        commands.put("list -s done", new ListTasksCommand(tasks, Status.DONE));
    }

    /**
     * Parses the given user input and returns the corresponding Command object.
     * If the input is empty or null, it prints an error message and returns null.
     * If the input does not match any available command, it prints an error message and returns null.
     *
     * @param input the user input to be parsed.
     * @return the corresponding Command object, or null if the input is invalid.
     */
    public Command parse(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Input is empty! Please enter command f");
            return null;
        }

        Command command = commands.get(input);
        if (command != null) {
            return command;
        } else {
            System.out.println("Unknown command! Please enter correct command.");
            return null;
        }
    }
}