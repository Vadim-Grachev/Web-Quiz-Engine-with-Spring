package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class Controller {

    private final StorageService storage;
    @Autowired
    public Controller(StorageService storage) {
        this.storage = storage;
    }

    @GetMapping("quizzes")
    public List<Quiz> getQuizzes() {
        return storage.getQuizzes();
    }
    @PostMapping("quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        storage.add(quiz);
    return quiz;
    }
    @GetMapping("quizzes/{id}")
    public Quiz getQuiz(@PathVariable long id) {
        return storage.get(id); }

    @PostMapping("quizzes/{id}/solve")
    public Response solveQuiz(@PathVariable long id, @RequestBody QuizAnswer quizAnswer) {
        return storage.solve(id, quizAnswer);
    }
}
