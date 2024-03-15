package engine;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class QuizAnswer {
    private Set<Integer> answer;
}
