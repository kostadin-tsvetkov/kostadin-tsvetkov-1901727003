package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User {
    private Parent parent;
}
