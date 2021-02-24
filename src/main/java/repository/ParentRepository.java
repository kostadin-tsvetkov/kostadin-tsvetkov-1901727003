package repository;

import entity.Parent;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds all of the methods used for creating or retrieving data from the parents table in the database
 */
public class ParentRepository {
    private static final List<Parent> parents = new ArrayList<>();

    /**
     * Method for reading all of the parents from the database
     * @return list of all parents
     */
    public List<Parent> getAllParents() {
        return parents;
    }

    /**
     * Method used for creating a new record in the parents table
     * @param parent
     * @return copy of the parent record just created in the database
     */
    public Parent createParent(Parent parent) {
        parents.add(parent);
        return parent;
    }

    /**
     * Method used for retrieving a parent by first and last name from the database. Returns null if record not found
     * @param firstName
     * @param lastName
     * @return parent object or null
     */
    public Parent getByName(String firstName, String lastName) {
        for (Parent parent : parents) {
            if (parent.getFirstName().equals(firstName) && parent.getLastName().equals(lastName)) {
                return parent;
            }
        }
        return null;
    }
}
