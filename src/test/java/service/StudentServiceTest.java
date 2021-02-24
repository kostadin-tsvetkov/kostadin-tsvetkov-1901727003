package service;

import entity.Parent;
import entity.Student;
import enums.UserTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.ParentRepository;
import security.Session;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Contains methods used for testing the functionality in StudentService
 */
public class StudentServiceTest {
    public static final String PARENT_FIRST_NAME = "parentFirstName";
    public static final String PARENT_LAST_NAME = "parentLastName";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private final StudentService studentService = new StudentService();
    private final ParentRepository parentRepository = new ParentRepository();

    private Session session;

    /**
     * Method, executed once, used for configuring the environment and setting up object instances before test executions
     */
    @Before
    public void setUp() {
        session = new Session();
        session.setLoggedUserType(UserTypes.TEACHER);
    }

    /**
     * Method executed after each test execution. Used for clearing up stored data
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @After
    public void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field parentsField = ParentRepository.class.getDeclaredField("parents");
        parentsField.setAccessible(true);
        ((List<Parent>) parentsField.get(this)).clear();
    }

    @Test
    public void testCreateStudentWithoutExistingParent() {
        studentService.createStudent(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, PARENT_FIRST_NAME, PARENT_LAST_NAME, session);
    }

    @Test
    public void testCreateStudent() {
        Parent parent = new Parent();
        parent.setFirstName(PARENT_FIRST_NAME);
        parent.setLastName(PARENT_LAST_NAME);

        parentRepository.createParent(parent);

        Student student = studentService.createStudent(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, PARENT_FIRST_NAME, PARENT_LAST_NAME, session);
        Assert.assertNotNull(student);
        Assert.assertEquals(FIRST_NAME, student.getFirstName());
        Assert.assertEquals(LAST_NAME, student.getLastName());
        Assert.assertEquals(USERNAME, student.getUsername());
        Assert.assertEquals(PASSWORD, student.getPassword());
        Assert.assertEquals(PARENT_FIRST_NAME, student.getParent().getFirstName());
        Assert.assertEquals(PARENT_LAST_NAME, student.getParent().getLastName());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateStudentNullSession() {
        studentService.createStudent(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, PARENT_FIRST_NAME, PARENT_LAST_NAME, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateStudentUnauthorizedUser() {
        session.setLoggedUserType(UserTypes.STUDENT);
        studentService.createStudent(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, PARENT_FIRST_NAME, PARENT_LAST_NAME, session);
    }
}