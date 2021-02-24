package entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Class which provides abstraction for users
 */
@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
