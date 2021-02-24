package service;

import entity.Subject;
import enums.UserTypes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import security.Session;

/**
 * Contains methods for testing the functionality in SubjectService
 */
public class SubjectServiceTest {

    public static final String SUBJECT_NAME = "subjectName";
    private final SubjectService subjectService = new SubjectService();

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
    public void testCreateSubject() {
        Subject subject = subjectService.createSubject(SUBJECT_NAME, session);
        Assert.assertNotNull(subject);
        Assert.assertEquals(SUBJECT_NAME, subject.getName());
    }

    @Test
    public void testCreateSubjectNullName() {
        Subject subject = subjectService.createSubject(null, session);
        Assert.assertNotNull(subject);
        Assert.assertNull(subject.getName());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateSubjectNullSession() {
        subjectService.createSubject(SUBJECT_NAME, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateSubjectUnauthorizedUser() {
        session.setLoggedUserType(UserTypes.STUDENT);
        subjectService.createSubject(SUBJECT_NAME, session);
    }
}