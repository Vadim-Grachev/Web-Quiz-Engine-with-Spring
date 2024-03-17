package engine.api;

import engine.dto.Quiz;
import engine.dto.QuizStorageService;
import engine.dto.User;
import engine.dto.UserStorageService;
import engine.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class Controller {

    private final QuizStorageService quizStorageService;
    private final UserStorageService userStorageService;
    @Autowired
    public Controller(QuizStorageService quizStorageService, UserStorageService userStorageService) {
        this.quizStorageService = quizStorageService;
        this.userStorageService = userStorageService;
    }

    @GetMapping("quizzes")
    public List<Quiz> getQuizzes() {
        return quizStorageService.getQuizzes();
    }

    @PostMapping("quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz,
                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        quiz.setUser(userPrincipal.user());
        return quizStorageService.add(quiz); }

    @GetMapping("quizzes/{id}")
    public Quiz getQuiz(@PathVariable long id) {
        return quizStorageService.get(id); }

    @PostMapping("quizzes/{id}/solve")
    public Response solveQuiz(@PathVariable long id, @RequestBody QuizAnswer quizAnswer) {
        return quizStorageService.solve(id, quizAnswer); }

    @DeleteMapping("quizzes/{id}")
    public ResponseEntity<String> delete(@PathVariable long id,
                                         @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (quizStorageService.get(id).getUser().getEmail()
                .equals(userPrincipal.user().getEmail())) {
            quizStorageService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    @PostMapping("register")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody User user) {
        userStorageService.registerNewUser(user);
        return ResponseEntity.ok().build(); }
}
