package repository;

import entity.Grade;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds all of the methods used for creating or retrieving data from the grades table in the database
 */
public class GradeRepository {
    private static final List<Grade> grades = new ArrayList<>();

    /**
     * Method for reading all of the grades from the database
     * @return List of all grades
     */
    public List<Grade> getAllGrades() {
        return grades;
    }

    /**
     * Method used for creating a new record in the grades table
     * @param grade
     * @return copy of the grade object just created in the database
     */
    public Grade createGrade(Grade grade) {
        grades.add(grade);
        return grade;
    }

}
