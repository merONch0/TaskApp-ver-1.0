import java.io.File;
import java.io.IOException;
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String[] args) {
        DOM docObjManager = new DOM();
        File xmlFile = new File("task_list_file.xml");
        ArrayList<Task> tasks = new ArrayList<>();
        Document document = null;
        Scanner scanner = new Scanner(System.in);

        if (!xmlFile.exists()) {
            try {
                xmlFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: cannot create new file: " + e.getMessage());
            }
        }

        if (!xmlFile.exists() || xmlFile.length() != 0) {
            document = docObjManager.parseXmlFile("task_list_file.xml");
        }

        if (document == null) {
            System.out.println("File is empty or exists. Creating new file and adding first task");
            MakeTaskCommand createCommand = new MakeTaskCommand(tasks);
            createCommand.execute();
        } else {
            document = docObjManager.parseXmlFile("task_list_file.xml");
            NodeList taskElements = docObjManager.getElements(document);
            tasks = docObjManager.addTasksFromNodeList(taskElements);
        }

        while (true) {
            String enteredCmd = "";
            while (enteredCmd.isEmpty()) {
                System.out.println("Enter wish command, help to more info");
                enteredCmd = scanner.nextLine();
            }
            if (enteredCmd.equalsIgnoreCase("exit")) {
                break;
            }
            CommandParser commandParser = new CommandParser(tasks);
            Command cmd = commandParser.parse(enteredCmd);
            if (cmd != null) {
                cmd.execute();
            }
            docObjManager.saveTasksToXML(tasks, "task_list_file.xml");
        }

        scanner.close();
    }
}
