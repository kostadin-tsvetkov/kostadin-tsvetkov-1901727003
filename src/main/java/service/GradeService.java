package service;

import entity.Grade;
import entity.Student;
import entity.Subject;
import enums.UserTypes;
import repository.GradeRepository;
import security.Session;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds the methods with the business logic related to the student's grades
 * The methods are receiving the parameters from the controllers
 * The methods are using the repository classes for database transactions
 */
public class GradeService {
    private final GradeRepository gradeRepository = new GradeRepository();

    /**
     * Method used for creating grades. Contains a validation of the input data and checks the permissions of the user
     * @param student
     * @param subject
     * @param mark
     * @param session
     * @return a copy of the created grade
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public Grade createGrade(Student student, Subject subject, Integer mark, Session session) throws IllegalAccessException {

        if (session == null || session.getLoggedUserType() != UserTypes.TEACHER) {
            throw new IllegalAccessException("Not authorized");
        }

        if (mark == null || mark < 2 || mark > 6) {
            throw new IllegalArgumentException();
        }

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setMark(mark);

        return gradeRepository.createGrade(grade);
    }

    /**
     * Method used for retrieving the grades for specific student. Checks the permissions of the user
     * @param student
     * @param session
     * @return list of grades for specific student
     * @throws UnsupportedOperationException
     * @throws IllegalArgumentException
     */
    public List<Grade> getGradesForStudent(Student student, Session session) {

        if (session == null || session.getLoggedUserType() != UserTypes.PARENT) {
            throw new UnsupportedOperationException();
        }

        if (student == null) {
            throw new IllegalArgumentException();
        }

        List<Grade> allGrades = gradeRepository.getAllGrades();
        return allGrades.stream().filter(a -> a.getStudent() == student).collect(Collectors.toList());
    }
}
