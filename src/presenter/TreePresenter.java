package presenter;
import model.FamilyTree;
import model.Person;
import service.FileOperations;
import view.TreeView;
import java.io.IOException;
public class TreePresenter {
    private FamilyTree<Person> familyTree;
    private TreeView view;
    private FileOperations<Person> fileOperations;
    public TreePresenter(FamilyTree<Person> familyTree, TreeView
            view, FileOperations<Person> fileOperations) {
        this.familyTree = familyTree;
        this.view = view;
        this.fileOperations = fileOperations;
        this.view.setPresenter(this);
    }
    public void addPerson(String name, int birthYear) {
        Person person = new Person(name, birthYear);
        familyTree.addMember(person);
        view.displayMessage("Person added: " + name);
    }
    public void showAllPersons() {
        view.displayPersons(familyTree.getMembers());
    }
    public void sortPersonsByName() {
        familyTree.sortByName();
        view.displayMessage("Persons sorted by name:");
        showAllPersons();
    }
    public void sortPersonsByBirthYear() {
        familyTree.sortByBirthYear();
        view.displayMessage("Persons sorted by birth year:");
        showAllPersons();
    }
    public void saveTree(String fileName) {
        try {
            fileOperations.saveToFile(familyTree, fileName);
            view.displayMessage("Family tree saved to " + fileName);
        } catch (IOException e) {
            view.displayMessage("Error saving family tree: " +
                    e.getMessage());
        }
    }
    public void loadTree(String fileName) {
        try {
            familyTree = fileOperations.loadFromFile(fileName);
            view.displayMessage("Family tree loaded from " +
                    fileName);
        } catch (IOException | ClassNotFoundException e) {
            view.displayMessage("Error loading family tree: " +
                    e.getMessage());
        }
    }
    public void handleUserInput() {
        while (true) {
            view.displayMessage("Enter command (add, list, sortByName, sortByBirthYear, save, load, exit):");
            String command = view.getUserInput();
            switch (command) {
                case "add":
                    view.displayMessage("Enter name:");
                    String name = view.getUserInput();
                    view.displayMessage("Enter birth year:");
                    int birthYear =
                            Integer.parseInt(view.getUserInput());
                    addPerson(name, birthYear);
                    break;
                case "list":
                    showAllPersons();
                    break;
                case "sortByName":
                    sortPersonsByName();
                    break;
                case "sortByBirthYear":
                    sortPersonsByBirthYear();
                    break;
                case "save":
                    view.displayMessage("Enter file name:");
                    saveTree(view.getUserInput());
                    break;
                case "load":
                    view.displayMessage("Enter file name:");
                    loadTree(view.getUserInput());
                    break;
                case "exit":
                    return;
                default:
                    view.displayMessage("Unknown command");
            }
        }
    }
}
