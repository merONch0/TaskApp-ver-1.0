import java.util.Map;

public class HelpCommand extends Command {
    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println("Available commands:");
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "Shows help for available commands";
    }

    @Override
    public Task findById(long id) {
        return null;
    }

    @Override
    public long getTaskId() {
        return 0;
    }

}
