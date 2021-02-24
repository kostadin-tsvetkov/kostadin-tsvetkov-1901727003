package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SchoolClass {
    private List<Student> students;
    private List<Subject> subjects;
    private Teacher teacher;
}
