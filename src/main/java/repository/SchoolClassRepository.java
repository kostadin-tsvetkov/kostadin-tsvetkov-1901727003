package repository;

import entity.SchoolClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds all of the methods used for creating or retrieving data from the school classes table in the database
 */
public class SchoolClassRepository {
    private static final List<SchoolClass> classes = new ArrayList<>();

    /**
     * Method for reading all of the school classes from the database
     * @return List of school classes
     */
    public List<SchoolClass> getAllSchoolClasses() {
        return classes;
    }

    /**
     * Method used for creating a new record in the school classes table
     * @param schoolClass
     * @return copy of the school class record just created in the database
     */
    public SchoolClass createSchoolClass(SchoolClass schoolClass) {
        classes.add(schoolClass);
        return schoolClass;
    }
}
