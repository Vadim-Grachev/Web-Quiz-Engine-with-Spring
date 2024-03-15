package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
@Getter
@Setter
@Entity
public class Quiz {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    @Getter
    @NotNull(message = "Quiz must have a title")
    @NotEmpty(message = "Quiz title should not be empty")
    private String title;

    @Getter
    @NotNull(message = "Quiz must have a question")
    @NotEmpty(message = "Quiz title should not be empty")
    private String text;
    @Getter
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
    /*
    public Quiz(String title, String text, List<String> options, Set<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }
    public Quiz() {}

    @JsonIgnore
    public Set<Integer> getAnswer() {
        return answer;
    }
    public boolean isCorrect(List<Integer> answerInput) {
        return answer.equals(answerInput);
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setOptions(List<String> options) {
        this.options = options;
    }
    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }
    public void setId(long id) {
        this.id = id;
    }
    */
}
