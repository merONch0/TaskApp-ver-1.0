import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class DOMTest{
    private DOM dom;
    private ArrayList<Task> tasks;

    @BeforeEach
    void setUp() throws IOException {
        File file = new File("test.xml");
        if (!file.exists()) {
            file.createNewFile();
        }
        dom = new DOM();
        tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", "Description 1", 1, "2023-03-20", Status.NEW, null));
        tasks.add(new Task(2, "Task 2", "Description 2", 2, "2023-03-21", Status.IN_PROGRESS, null));
        tasks.add(new Task(3, "Task 3", "Description 3", 3, "2023-03-22", Status.DONE, "2023-03-21"));
    }

    @Test
    void testParseXmlFile() {
        Document document = dom.parseXmlFile("test.xml");
        assertNotNull(document);
    }

    @Test
    void testGetElements() {
        Document document = dom.parseXmlFile("test.xml");
        NodeList taskElements = dom.getElements(document);
        assertEquals(3, taskElements.getLength());
    }

    @Test
    void testAddTasksFromNodeList() {
        Document document = dom.parseXmlFile("test.xml");
        NodeList taskElements = dom.getElements(document);
        ArrayList<Task> actualTasks = dom.addTasksFromNodeList(taskElements);
        StringBuilder sB1 = new StringBuilder();
        StringBuilder sB2 = new StringBuilder();
        for (Task task: actualTasks) {
            sB1.append(task.toString());
        }
        for (Task task: tasks) {
            sB2.append(task.toString());
        }
        String inputString = sB1.toString();
        String outputString = sB2.toString();
        inputString.equals(outputString);
    }

    @Test
    void testSaveTasksToXML() {
        dom.saveTasksToXML(tasks, "test_output.xml");
        Document document = dom.parseXmlFile("test_output.xml");
        NodeList taskElements = dom.getElements(document);
        ArrayList<Task> actualTasks = dom.addTasksFromNodeList(taskElements);
        StringBuilder sB1 = new StringBuilder();
        StringBuilder sB2 = new StringBuilder();
        for (Task task: actualTasks) {
            sB1.append(task.toString());
        }
        for (Task task: tasks) {
            sB2.append(task.toString());
        }
        String inputString = sB1.toString();
        String outputString = sB2.toString();
        inputString.equals(outputString);
    }
}
