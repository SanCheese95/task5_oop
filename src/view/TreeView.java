package view;
import model.Person;
import presenter.TreePresenter;

import java.util.List;
public interface TreeView {
    void displayMessage(String message);
    void displayPersons(List<Person> persons);
    String getUserInput();
    void setPresenter(TreePresenter presenter);
}
