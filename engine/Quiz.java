package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Quiz {
    static int idCounter = 1;
    private int id;
    @NotNull(message = "Quiz must have a title")
    @NotEmpty(message = "Quiz title should not be empty")
    private String title;

    @NotNull(message = "Quiz must have a question")
    @NotEmpty(message = "Quiz title should not be empty")
    private String text;
    @NotNull(message = "Quiz must have options")
    @Size(min = 2, message = "Quiz must contain at least two options")
    private ArrayList<String> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> answer = new ArrayList<>();

    public Quiz(String title, String text, ArrayList<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }
    public Quiz() {
        this.id = idCounter++;
    }
    public int getId() { return id; }
    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }
    public ArrayList<String> getOptions() {
        return options;
    }
    @JsonIgnore
    public List<Integer> getAnswer() {
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
    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
    public void setId(int id) {
        this.id = id;
    }
}
