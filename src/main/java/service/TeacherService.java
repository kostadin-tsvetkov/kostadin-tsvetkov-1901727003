package service;

import entity.Teacher;
import enums.UserTypes;
import repository.TeacherRepository;
import security.Session;

/**
 * Holds the methods with the business logic related to the teachers
 * The methods are receiving the parameters from the controllers
 * The methods are using the repository classes for database transactions
 */
public class TeacherService {
    private TeacherRepository teacherRepository = new TeacherRepository();

    /**
     * Mehtod used for creating a teacher. Performs a check for user's permissions
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param session
     * @return a copy of the created teacher
     * @throws UnsupportedOperationException
     */
    public Teacher createTeacher(String firstName, String lastName, String username, String password, Session session) {

        if (session == null || session.getLoggedUserType() != UserTypes.TEACHER) {
            throw new UnsupportedOperationException();
        }

        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setUsername(username);
        teacher.setPassword(password);

        return teacherRepository.createTeacher(teacher);
    }
}
