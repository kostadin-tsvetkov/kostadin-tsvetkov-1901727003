package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Grade {
    private Student student;
    private Subject subject;
    private Integer mark;
}
