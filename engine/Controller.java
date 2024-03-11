package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("/api/")
public class Controller {

    private final StorageService storage;
    @Autowired
    public Controller(StorageService storage) {
        this.storage = storage;
    }

    @GetMapping("quizzes")
    public Collection<Quiz> getQuizzes() {
        return storage.getQuizzes();
    }
    @PostMapping("quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        storage.add(quiz);
    return quiz;
    }
    @GetMapping("quizzes/{id}")
    public Object getQuiz(@PathVariable int id) {
        return storage.get(id).orElseThrow(QuizNotFoundException::new); }

    @PostMapping("quizzes/{id}/solve")
    public Object solveQuiz(@PathVariable int id, @Valid @RequestBody QuizAnswer quizAnswer) {
        return storage
                .get(id)
                .orElseThrow(QuizNotFoundException::new)
                .getAnswer()
                .equals(quizAnswer.getAnswer())
                ? Response.correctAnswer
                : Response.wrongAnswer;
    }
}
