package service;

import entity.Subject;
import enums.UserTypes;
import repository.SubjectRepository;
import security.Session;

/**
 * Holds the methods with the business logic related to the subjects
 * The methods are receiving the parameters from the controllers
 * The methods are using the repository classes for database transactions
 */
public class SubjectService {
    private SubjectRepository subjectRepository = new SubjectRepository();

    /**
     * Method used for creating a subject. Performs check for user's permissions
     * @param name
     * @param session
     * @return a copy of the created subject
     * @throws UnsupportedOperationException
     */
    public Subject createSubject(String name, Session session) {

        if (session == null || session.getLoggedUserType() != UserTypes.TEACHER) {
            throw new UnsupportedOperationException();
        }

        Subject subject = new Subject();
        subject.setName(name);

        return subjectRepository.createSubject(subject);
    }
}
