package service;

import entity.Parent;
import entity.Student;
import entity.Teacher;
import entity.User;
import enums.UserTypes;
import repository.ParentRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import security.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Service used for authenticating and authorizing users
 */
public class AuthService {
    private StudentRepository studentRepository = new StudentRepository();
    private ParentRepository parentRepository = new ParentRepository();
    private TeacherRepository teacherRepository = new TeacherRepository();

    /**
     * Method used for authentication. Receives username and password and validates the input parametes.
     * If the validation passes a session is created, where the username and the user type are stored.
     *
     * Returns null if the validation of the input parameters fails
     * @param username
     * @param password
     * @return session object containing the username and user type of the logged user. Null if the credentials are incorrect
     */
    public Session login(String username, String password) {
        List<Student> allStudents = studentRepository.getAllStudents();
        List<Parent> allParents = parentRepository.getAllParents();
        List<Teacher> allTeachers = teacherRepository.getAllTeachers();

        List<User> users = new ArrayList<>();

        users.addAll(allStudents);
        users.addAll(allParents);
        users.addAll(allTeachers);

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                Session session = new Session();

                session.setUsername(username);
                if (user.getClass() == Student.class) {
                    session.setLoggedUserType(UserTypes.STUDENT);
                } else if (user.getClass() == Parent.class) {
                    session.setLoggedUserType(UserTypes.PARENT);
                } else {
                    session.setLoggedUserType(UserTypes.TEACHER);
                }
                return session;
            }
        }
        return null;
    }
}
