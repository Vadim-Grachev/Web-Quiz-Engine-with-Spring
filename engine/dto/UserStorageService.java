package engine.dto;

import engine.dto.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import engine.exception.UserAlreadyExistsException;
import engine.exception.UserNotFoundException;
import engine.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class UserStorageService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        if (userRepository.existsById(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(UserRole.ROLE_USER);
        return userRepository.save(newUser);
    }
    public User get(String email) {
        return userRepository
                .findById(email)
                .orElseThrow(UserNotFoundException::new);
    }
    @Autowired
    public void UserStorageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
