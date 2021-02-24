package service;

import entity.Teacher;
import enums.UserTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.TeacherRepository;
import security.Session;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Contains methods for testing the functionality in TeacherService
 */
public class TeacherServiceTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    private final TeacherService teacherService = new TeacherService();

    private Session session;

    /**
     * Method, executed once, used for configuring the environment and setting up object instances before test executions
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        session = new Session();
        session.setLoggedUserType(UserTypes.TEACHER);
    }

    /**
     * Method executed after each test execution. Used for clearing stored data
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @After
    public void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field teachersField = TeacherRepository.class.getDeclaredField("teachers");
        teachersField.setAccessible(true);
        ((List<Teacher>) teachersField.get(this)).clear();
    }

    @Test
    public void testCreateTeacher() {
        Teacher teacher = teacherService.createTeacher(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, session);
        Assert.assertNotNull(teacher);
        Assert.assertEquals(FIRST_NAME, teacher.getFirstName());
        Assert.assertEquals(LAST_NAME, teacher.getLastName());
        Assert.assertEquals(USERNAME, teacher.getUsername());
        Assert.assertEquals(PASSWORD, teacher.getPassword());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateTeacherNullSession() {
        teacherService.createTeacher(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateTeacherUnauthorizedUser() {
        session.setLoggedUserType(UserTypes.STUDENT);
        teacherService.createTeacher(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, session);
    }

    @Test
    public void testCreateTeacherNullValues() {
        Teacher teacher = teacherService.createTeacher(null, null, null, null, session);
        Assert.assertNotNull(teacher);
        Assert.assertNull(teacher.getFirstName());
        Assert.assertNull(teacher.getLastName());
        Assert.assertNull(teacher.getUsername());
        Assert.assertNull(teacher.getPassword());
    }
}