package service;

import entity.Grade;
import entity.Student;
import entity.Subject;
import enums.UserTypes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import security.Session;

import java.util.List;

/**
 * Contains methods for testing the functionality in GradeService
 */
public class GradeServiceTest {

    private GradeService gradeService = new GradeService();

    private Student student;
    private Subject subject;
    private Session session;

    /**
     * Method, executed once, used for configuring the environment and setting up object instances before test executions
     */
    @Before
    public void setUp() {
        student = new Student();
        subject = new Subject();

        session = new Session();
        session.setLoggedUserType(UserTypes.TEACHER);
    }

    @Test
    public void testCreateAndGetGradeForStudent() throws IllegalAccessException {
        Grade grade = gradeService.createGrade(student, subject, 2, session);

        Assert.assertEquals(student, grade.getStudent());
        Assert.assertEquals(subject, grade.getSubject());
        Assert.assertEquals(Integer.valueOf(2), grade.getMark());
    }

    @Test(expected = IllegalAccessException.class)
    public void testCreateGradeWithUnauthorizedUser() throws IllegalAccessException {
        session.setLoggedUserType(UserTypes.STUDENT);
        gradeService.createGrade(student, subject, 2, session);
    }

    @Test(expected = IllegalAccessException.class)
    public void testCreateGradeWithNullSession() throws IllegalAccessException {
        gradeService.createGrade(student, subject, 2, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGradeWithBiggerMark() throws IllegalAccessException {
        gradeService.createGrade(student, subject, 11, session);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGradeWithMarkNull() throws IllegalAccessException {
        gradeService.createGrade(student, subject, null, session);
    }

    @Test
    public void testGetGradeForStudent() throws IllegalAccessException {
        gradeService.createGrade(student, subject, 2, session);
        session.setLoggedUserType(UserTypes.PARENT);

        List<Grade> grade = gradeService.getGradesForStudent(student, session);
        Assert.assertEquals(1, grade.size());
        Assert.assertEquals(subject, grade.get(0).getSubject());
        Assert.assertEquals(student, grade.get(0).getStudent());
        Assert.assertEquals(Integer.valueOf(2), grade.get(0).getMark());
    }

    @Test
    public void testGetGradeForStudentWithDifferentStudent() throws IllegalAccessException {
        gradeService.createGrade(student, subject, 2, session);
        session.setLoggedUserType(UserTypes.PARENT);

        List<Grade> grade = gradeService.getGradesForStudent(new Student(), session);
        Assert.assertEquals(0, grade.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetGradeForStudentForUnauthorizedUser() throws IllegalAccessException {
        gradeService.createGrade(student, subject, 2, session);

        gradeService.getGradesForStudent(student, session);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGradesForStudentNullStudent() {
        session.setLoggedUserType(UserTypes.PARENT);
        gradeService.getGradesForStudent(null, session);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetGradeForStudentNullSession() {
        gradeService.getGradesForStudent(student, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetGradeForStudentNullStudent() {
        gradeService.getGradesForStudent(null, session);
    }
}