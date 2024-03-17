package engine.dto.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class QuizCompletionId implements Serializable {

    @Serial
    private static final long serialVersionUID = 8730599536453858883L;

    private String userId;
    private long quizId;
    private LocalDateTime completionDate;
}
