package engine.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    public static final String CORRECT_ANSWER = "Congratulations, you're right!";
    public static final String WRONG_ANSWER ="Wrong answer! Please, try again.";

    private boolean success;
    private String feedback;
}
