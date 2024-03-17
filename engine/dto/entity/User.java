package engine.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import engine.security.UserRole;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {

    @Id
    @NotNull
    @Email(regexp = "(?i)[\\w!#$%&'*+/=?`{|}~^-]+" +
            "(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@" +
            "(?:[a-z0-9-]+\\.)+[a-z]{2,6}")
    private String email;

    @NotBlank
    @Size(min = 5, max = 255)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Quiz> quizzes;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<QuizCompletion> completions;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
