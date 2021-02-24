package repository;

import entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds all of the methods used for creating or retrieving data from the students table in the database
 */
public class StudentRepository {
    private static final List<Student> students = new ArrayList<>();

    /**
     * Method for reading all of the students from the database
     * @return list of all students
     */
    public List<Student> getAllStudents() {
        return students;
    }

    /**
     * Method used for creating a new record in the students table
     * @param student
     * @return copy of the student record just created in the database
     */
    public Student createStudent(Student student) {
        students.add(student);
        return student;
    }
}
