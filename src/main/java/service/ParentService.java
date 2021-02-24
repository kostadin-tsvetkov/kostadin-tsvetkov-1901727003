package service;

import entity.Parent;
import enums.UserTypes;
import repository.ParentRepository;
import security.Session;

/**
 * Holds the methods with the business logic related to the parents
 * The methods are receiving the parameters from the controllers
 * The methods are using the repository classes for database transactions
 */
public class ParentService {
    private ParentRepository parentRepository = new ParentRepository();

    /**
     * Method used for creating parent. Checks the permissions of the user
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param session
     * @return a copy of the created parent
     * @throws UnsupportedOperationException
     */
    public Parent createParent(String firstName, String lastName, String username, String password, Session session) {

        if (session == null || session.getLoggedUserType() != UserTypes.TEACHER) {
            throw new UnsupportedOperationException();
        }

        Parent parent = new Parent();
        parent.setFirstName(firstName);
        parent.setLastName(lastName);
        parent.setUsername(username);
        parent.setPassword(password);

        return parentRepository.createParent(parent);
    }
}
