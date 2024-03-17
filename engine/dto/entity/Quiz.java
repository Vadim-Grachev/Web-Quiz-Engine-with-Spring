package engine.dto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
@Getter
@Setter
@Entity
public class Quiz {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @NotNull(message = "Quiz must have a title")
    @NotEmpty(message = "Quiz title should not be empty")
    private String title;

    @NotBlank
    @NotNull(message = "Quiz must have a question")
    @NotEmpty(message = "Quiz title should not be empty")
    private String text;

    @NotNull(message = "Quiz must have options")
    @Size(min = 2, message = "Quiz must contain at least two options")
    @ElementCollection
    @CollectionTable(
            name = "QUIZ_OPTIONS",
            joinColumns = @JoinColumn(name = "QUIZ_ID") )
    @Column(name = "QUIZ_OPTION")
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    @CollectionTable(
            name = "QUIZ_ANSWERS",
            joinColumns = @JoinColumn(name = "QUIZ_ID") )
    @Column(name = "QUIZ_ANSWER")
    private Set<Integer> answer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=true)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private Set<QuizCompletion> completions;
}
