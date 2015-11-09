package auth;


import java.util.Set;

public interface AuthService {
    AuthAccessElement login(AuthLoginElement loginElement);

    boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed);

}
