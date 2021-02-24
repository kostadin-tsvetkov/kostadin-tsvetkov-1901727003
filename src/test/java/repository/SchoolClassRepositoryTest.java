package repository;

import entity.SchoolClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Contains methods for testing the functionality in SchoolClassRepository
 */
public class SchoolClassRepositoryTest {

    private final SchoolClassRepository schoolClassRepository = new SchoolClassRepository();

    /**
     * Method, executed once before test executions and after each test execution,
     * used for configuring the environment and setting up object instances before test executions
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Before
    @AfterEach
    public void clear() throws NoSuchFieldException, IllegalAccessException {
        Field classesField = SchoolClassRepository.class.getDeclaredField("classes");
        classesField.setAccessible(true);
        ((List<SchoolClass>) classesField.get(this)).clear();
    }

    @Test
    public void testGetAllSchoolClasses() {
        Assert.assertEquals(0, schoolClassRepository.getAllSchoolClasses().size());
    }

    @Test
    public void testCreateAndGetAllSchoolClasses() {
        SchoolClass schoolClass = new SchoolClass();

        SchoolClass returnedValue = schoolClassRepository.createSchoolClass(schoolClass);

        Assert.assertEquals(schoolClass, returnedValue);

        List<SchoolClass> allSchoolClasses = schoolClassRepository.getAllSchoolClasses();
        Assert.assertEquals(1, allSchoolClasses.size());
        Assert.assertEquals(schoolClass, allSchoolClasses.get(0));
    }
}