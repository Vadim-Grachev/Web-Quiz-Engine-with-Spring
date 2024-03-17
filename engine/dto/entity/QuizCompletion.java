package engine.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(QuizCompletionId.class)
public class QuizCompletion {

    @JsonIgnore
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @JsonProperty("id")
    @Id
    @Column(name = "QUIZ_ID")
    private long quizId;

    @JsonProperty("completedAt")
    @Id
    private LocalDateTime completionDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_ID",
            insertable = false,
            updatable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "QUIZ_ID",
            insertable = false,
            updatable = false)
    private Quiz quiz;
}
