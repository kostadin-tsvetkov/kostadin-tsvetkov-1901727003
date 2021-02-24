package service;

import entity.Parent;
import enums.UserTypes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import security.Session;

/**
 * Contains methods for testing the functionality in ParentService
 */
public class ParentServiceTest {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private final ParentService parentService = new ParentService();

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

    @Test
    public void testCreateParent() {
        Parent parent = parentService.createParent(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, session);
        Assert.assertEquals(FIRST_NAME, parent.getFirstName());
        Assert.assertEquals(LAST_NAME, parent.getLastName());
        Assert.assertEquals(USERNAME, parent.getUsername());
        Assert.assertEquals(PASSWORD, parent.getPassword());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateParentWithUnauthorizedUser() {
        session.setLoggedUserType(UserTypes.STUDENT);
        parentService.createParent(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, session);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateParentWithNullSession() {
        parentService.createParent(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, null);
    }
}