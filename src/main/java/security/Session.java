package security;

import enums.UserTypes;
import lombok.Getter;
import lombok.Setter;

/**
 * Used for storing data about the current session. The session is created after login.
 */
@Getter
@Setter
public class Session {
    private UserTypes loggedUserType;
    private String username;
}
