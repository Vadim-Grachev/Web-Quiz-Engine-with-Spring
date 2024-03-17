package engine.dto;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    @ElementCollection
    @CollectionTable(
            name = "QUIZ_ANSWERS",
            joinColumns = @JoinColumn(name = "QUIZ_ID") )
    @Column(name = "QUIZ_ANSWER")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> answer;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    @JsonIgnore
    private User user;
}
