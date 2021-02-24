package service;

import entity.Parent;
import entity.Student;
import entity.Teacher;
import enums.UserTypes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.ParentRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import security.Session;

/**
 * Contains methods for testing the functionality in AuthService
 */
public class AuthServiceTest {
    public static final String PARENT_USERNAME = "parentUsername";
    public static final String PARENT_PASSWORD = "parentPassword";
    public static final String STUDENT_USERNAME = "studentUsername";
    public static final String STUDENT_PASSWORD = "studentPassword";
    public static final String TEACHER_USERNAME = "teacherUsername";
    public static final String TEACHER_PASSWORD = "teacherPassword";
    public static final String INCORRECT_USERNAME = "test";
    public static final String INCORRECT_PASSWORD = "test";

    private final AuthService authService = new AuthService();

    private final ParentRepository parentRepository = new ParentRepository();
    private final StudentRepository studentRepository = new StudentRepository();
    private final TeacherRepository teacherRepository = new TeacherRepository();

    /**
     * Method, executed once, used for configuring the environment and setting up object instances before test executions
     */
    @Before
    public void setUp() {
        Parent parent = new Parent();
        parent.setUsername(PARENT_USERNAME);
        parent.setPassword(PARENT_PASSWORD);
        parentRepository.createParent(parent);

        Student student = new Student();
        student.setUsername(STUDENT_USERNAME);
        student.setPassword(STUDENT_PASSWORD);
        studentRepository.createStudent(student);

        Teacher teacher = new Teacher();
        teacher.setUsername(TEACHER_USERNAME);
        teacher.setPassword(TEACHER_PASSWORD);
        teacherRepository.createTeacher(teacher);
    }


    @Test
    public void testLoginWithCorrectParentData() {
        Session session = authService.login(PARENT_USERNAME, PARENT_PASSWORD);

        Assert.assertEquals(PARENT_USERNAME, session.getUsername());
        Assert.assertEquals(UserTypes.PARENT, session.getLoggedUserType());
    }

    @Test
    public void testLoginWithCorrectStudentData() {
        Session session = authService.login(STUDENT_USERNAME, STUDENT_PASSWORD);

        Assert.assertEquals(STUDENT_USERNAME, session.getUsername());
        Assert.assertEquals(UserTypes.STUDENT, session.getLoggedUserType());
    }

    @Test
    public void testLoginWithCorrectTeacherData() {
        Session session = authService.login(TEACHER_USERNAME, TEACHER_PASSWORD);

        Assert.assertEquals(TEACHER_USERNAME, session.getUsername());
        Assert.assertEquals(UserTypes.TEACHER, session.getLoggedUserType());
    }

    @Test
    public void testLoginWithWrongCredentials() {
        Session session = authService.login(INCORRECT_USERNAME, INCORRECT_PASSWORD);

        Assert.assertNull(session);
    }

    @Test
    public void testLoginWithNull() {
        Session session = authService.login(null, null);

        Assert.assertNull(session);
    }
}