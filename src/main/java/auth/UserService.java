package auth;

import domain.User;

public interface UserService {
    User findUser(String username, String passwd);
    User findUser(long id);
    void syncUser(User user);
}
