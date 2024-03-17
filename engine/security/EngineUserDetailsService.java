package engine.security;

import engine.dto.entity.User;
import engine.dto.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class EngineUserDetailsService implements UserDetailsService {

    private final UserStorageService userStorageService;

    @Autowired
    public EngineUserDetailsService(UserStorageService userStorageService) {
        this.userStorageService = userStorageService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userStorageService.get(email);
        return new UserPrincipal(user);
    }
}
