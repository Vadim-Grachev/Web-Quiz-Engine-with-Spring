package engine.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import engine.security.UserRole;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {
    @Id
    @NotNull
    @Email(regexp = "\\w+@\\w+\\.\\w+")
    private String email;

    @Size(min = 5, max = 255)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Quiz> quizzes;

    @JsonIgnore
    private UserRole role;
}
