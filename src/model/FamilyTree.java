package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class FamilyTree<T> implements Serializable, Iterable<T> {
    private static final long serialVersionUID = 1L;
    private List<T> members;

    public FamilyTree() {
        this.members = new ArrayList<>();
    }

    public void addMember(T member) {
        this.members.add(member);
    }

    public List<T> getMembers() {
        return members;
    }

    @Override
    public Iterator<T> iterator() {
        return members.iterator();
    }

    public void sortByName() {
        Collections.sort(members, (p1, p2) ->
                p1.toString().compareTo(p2.toString()));
    }

    public void sortByBirthYear() {
        if (members.get(0) instanceof Person) {
            Collections.sort(members, (p1, p2) ->
                    Integer.compare(((Person) p1).getBirthYear(), ((Person)
                            p2).getBirthYear()));
        }
    }
}