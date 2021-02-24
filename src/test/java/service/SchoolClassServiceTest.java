package service;

import entity.SchoolClass;
import entity.Student;
import entity.Subject;
import entity.Teacher;
import enums.UserTypes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import security.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods for testing the functionality in the SchoolClassService
 */
public class SchoolClassServiceTest {
    private final SchoolClassService schoolClassService = new SchoolClassService();

    private Session session;
    private Subject subject;
    private Student student;
    private Teacher teacher;
    private List<Student> students;
    private List<Subject> subjects;

    /**
     * Method, executed once, used for configuring the environment and setting up object instances before test executions
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        session = new Session();
        session.setLoggedUserType(UserTypes.TEACHER);

        subject = new Subject();
        student = new Student();
        teacher = new Teacher();

        students = new ArrayList<>();
        students.add(student);

        subjects = new ArrayList<>();
        subjects.add(subject);
    }

    @Test
    public void testCreateSchoolClass() {
        SchoolClass schoolClass = schoolClassService.createSchoolClass(subjects, students, teacher, session);
        Assert.assertNotNull(schoolClass);
        Assert.assertEquals(subjects, schoolClass.getSubjects());
        Assert.assertEquals(students, schoolClass.getStudents());
        Assert.assertEquals(teacher, schoolClass.getTeacher());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateSchoolClassUnauthorizedUser() {
        session.setLoggedUserType(UserTypes.STUDENT);
        schoolClassService.createSchoolClass(subjects, students, teacher, session);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCreateSchoolClassNullSession() {
        schoolClassService.createSchoolClass(subjects, students, teacher, null);
    }
}