package repository;

import entity.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Contains methods for testing the functionality in SubjectRepository
 */
public class SubjectRepositoryTest {

    public static final String NAME = "name";
    public static final String NEW_NAME = "new name";
    private final SubjectRepository subjectRepository = new SubjectRepository();

    private Subject subject;

    /**
     * Method, executed once, used for configuring the environment and setting up object instances before test executions
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        subject = new Subject();
        subject.setName(NAME);

        clear();
    }

    /**
     * Method used for clearing stored data after each executed test
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @AfterEach
    public void clear() throws NoSuchFieldException, IllegalAccessException {
        Field subjectsField = SubjectRepository.class.getDeclaredField("subjects");
        subjectsField.setAccessible(true);
        ((List<Subject>) subjectsField.get(this)).clear();
    }

    @Test
    public void testCreateAndGetSubject() {
        Assert.assertEquals(0, subjectRepository.getAllSubjects().size());

        Subject returnedvalue = subjectRepository.createSubject(subject);
        Assert.assertNotNull(returnedvalue);
        Assert.assertEquals(subject, returnedvalue);

        List<Subject> allSubjects = subjectRepository.getAllSubjects();
        Assert.assertEquals(1, allSubjects.size());
        Assert.assertEquals(subject, allSubjects.get(0));

        Subject result = subjectRepository.getSubjectByName(NAME);
        Assert.assertEquals(subject, result);
    }

    @Test
    public void testGetSubjectByNameNameNotExisting() {
        Assert.assertNull(subjectRepository.getSubjectByName(NEW_NAME));

    }
}