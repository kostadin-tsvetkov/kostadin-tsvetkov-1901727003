package service;

import entity.SchoolClass;
import entity.Student;
import entity.Subject;
import entity.Teacher;
import enums.UserTypes;
import repository.SchoolClassRepository;
import security.Session;

import java.util.List;

/**
 * Holds the methods with the business logic related to the school classes
 * The methods are receiving the parameters from the controllers
 * The methods are using the repository classes for database transactions
 */
public class SchoolClassService {
    private SchoolClassRepository schoolClassRepository = new SchoolClassRepository();

    /**
     * Method used for creating school classes. Performs a check for user's permissions
     * @param subjects
     * @param students
     * @param teacher
     * @param session
     * @return a copy of the created school class
     */
    public SchoolClass createSchoolClass(List<Subject> subjects, List<Student> students, Teacher teacher, Session session) {

        if (session == null || session.getLoggedUserType() != UserTypes.TEACHER) {
            throw new UnsupportedOperationException();
        }

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setStudents(students);
        schoolClass.setTeacher(teacher);
        schoolClass.setSubjects(subjects);

        return schoolClassRepository.createSchoolClass(schoolClass);
    }
}
