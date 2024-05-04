import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 *  This class provides methods for parsing and saving tasks to an XML file using the Document Object Model (DOM) API.
 */
public class DOM {
    /**
     * A list of tasks parsed from the XML file.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Parses an XML file and returns a Document object representing its contents.
     *
     * @param filePath the path to the XML file to parse
     * @return a Document object representing the contents of the XML file, or null if an error occurred
     */
    public Document parseXmlFile(String filePath) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns a NodeList containing all the "Task" elements in the specified Document.
     *
     * @param document the Document object to search for "Task" elements
     * @return a NodeList containing all the "Task" elements in the specified Document
     */
    public NodeList getElements(Document document) {
        NodeList tasksElements = document.getDocumentElement().getElementsByTagName("Task");
        return tasksElements;
    }

    /**
     * Adds tasks from a NodeList to the list of tasks.
     *
     * @param taskElements the NodeList containing the "Task" elements to add
     * @return the updated list of tasks
     */
    public ArrayList<Task> addTasksFromNodeList(NodeList taskElements) {
        for (int i = 0; i < taskElements.getLength(); i++) {
            Node taskNode = taskElements.item(i);
            if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
                Element taskElement = (Element) taskNode;
                int id = Integer.parseInt(taskElement.getAttribute("id"));
                String caption = taskElement.getAttribute("caption");
                String description = taskElement.getElementsByTagName("Description").item(0).getTextContent();
                int priority = Integer.parseInt(taskElement.getElementsByTagName("Priority").item(0).getTextContent());
                String deadline = taskElement.getElementsByTagName("Deadline").item(0).getTextContent();
                Status status = Status.valueOf(taskElement.getElementsByTagName("Status").item(0).getTextContent().toUpperCase());
                String completeStatus = null;
                NodeList completeNodes = taskElement.getElementsByTagName("Complete");
                if (completeNodes.getLength() > 0) {
                    completeStatus = completeNodes.item(0).getTextContent();
                }
                tasks.add(new Task(id, caption, description, priority, deadline, status, completeStatus));
            }
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to an XML file using the DOM API.
     *
     * @param tasks the list of tasks to save
     * @param fileName the name of the XML file to save the tasks to
     */
    public void saveTasksToXML(ArrayList<Task> tasks, String fileName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element toDoList = doc.createElement("ToDoList");
            doc.appendChild(toDoList);

            for (Task task : tasks) {
                Element taskElement = doc.createElement("Task");
                taskElement.setAttribute("id", String.valueOf(task.getId()));
                taskElement.setAttribute("caption", task.getCaption());

                Element description = doc.createElement("Description");
                description.appendChild(doc.createTextNode(task.getDescription()));
                taskElement.appendChild(description);

                Element priority = doc.createElement("Priority");
                priority.appendChild(doc.createTextNode(String.valueOf(task.getPriority())));
                taskElement.appendChild(priority);

                Element deadline = doc.createElement("Deadline");
                deadline.appendChild(doc.createTextNode(task.getDeadline()));
                taskElement.appendChild(deadline);

                Element status = doc.createElement("Status");
                status.appendChild(doc.createTextNode(task.getStatus().toString()));
                taskElement.appendChild(status);

                if (task.getCompleteStatus() != null) {
                    Element complete = doc.createElement("Complete");
                    complete.appendChild(doc.createTextNode(task.getCompleteStatus()));
                    taskElement.appendChild(complete);
                }

                toDoList.appendChild(taskElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //TODO: add extension to convert XML to JSON or YAML.
}