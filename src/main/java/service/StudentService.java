package service;

import entity.Student;
import enums.UserTypes;
import repository.ParentRepository;
import repository.StudentRepository;
import security.Session;

/**
 * Holds the methods with the business logic related to the students
 * The methods are receiving the parameters from the controllers
 * The methods are using the repository classes for database transactions
 */
public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();
    private final ParentRepository parentRepository = new ParentRepository();

    /**
     * Method used for creating a student. Performs a check for user's permissions
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param parentFirstName
     * @param parentLastName
     * @param session
     * @return a copy of the created student
     * @throws UnsupportedOperationException
     */
    public Student createStudent(String firstName,
                                 String lastName,
                                 String username,
                                 String password,
                                 String parentFirstName,
                                 String parentLastName,
                                 Session session) {

        if (session == null || session.getLoggedUserType() != UserTypes.TEACHER) {
            throw new UnsupportedOperationException();
        }

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUsername(username);
        student.setPassword(password);
        student.setParent(parentRepository.getByName(parentFirstName, parentLastName));

        return studentRepository.createStudent(student);
    }
}
