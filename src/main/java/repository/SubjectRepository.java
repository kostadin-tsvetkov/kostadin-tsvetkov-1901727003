package repository;

import entity.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds all of the methods used for creating or retrieving data from the subjects table in the database
 */
public class SubjectRepository {
    private static final List<Subject> subjects = new ArrayList<>();

    /**
     * Method for reading all of the subjects from the database
     * @return list of all subjects
     */
    public List<Subject> getAllSubjects() {
        return subjects;
    }

    /**
     * Method used for creating a new record in the subjects table
     * @param subject
     * @return copy of the subject record just created in the database
     */
    public Subject createSubject(Subject subject) {
        subjects.add(subject);
        return subject;
    }

    /**
     * Method used for retrieving a subject by name from the database. Returns null if record not found
     * @param name
     * @return subject object or null
     */
    public Subject getSubjectByName(String name) {
        for (Subject subject : subjects) {
            if (subject.getName().equals(name)) {
                return subject;
            }
        }

        return null;
    }
}
