package repository;

import entity.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds all of the methods used for creating or retrieving data from the teachers table in the database
 */
public class TeacherRepository {
    private static final List<Teacher> teachers = new ArrayList<>();

    /**
     * Method for reading all of the teachers from the database
     * @return list of all teachers
     */
    public List<Teacher> getAllTeachers() {
        return teachers;
    }

    /**
     * Method used for creating a new record in the teachers table
     * @param teacher
     * @return copy of the teacher record just created in the database
     */
    public Teacher createTeacher(Teacher teacher) {
        teachers.add(teacher);
        return teacher;
    }
}
