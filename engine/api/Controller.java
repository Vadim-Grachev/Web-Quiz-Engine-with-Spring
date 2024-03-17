package engine.api;

import engine.dto.*;
import engine.dto.entity.Quiz;
import engine.dto.entity.QuizCompletion;
import engine.dto.entity.User;
import engine.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;


@RestController
@Validated
@RequestMapping("/api/")
public class Controller {

    private final QuizStorageService quizStorageService;
    private final UserStorageService userStorageService;
    private final QuizCompletionService quizCompletionService;
    @Autowired
    public Controller(UserStorageService userService, QuizStorageService quizService,
               QuizCompletionService quizCompletionService) {
        this.userStorageService = userService;
        this.quizStorageService = quizService;
        this.quizCompletionService = quizCompletionService;
    }

    @GetMapping("quizzes")
    public Page<Quiz> getAllQuizzes(
            @Min(0) @RequestParam(defaultValue = "0") int page,
            @Min(10) @Max(30) @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return quizStorageService.getAllQuizzes(page, pageSize, sortBy);
    }

    @PostMapping(value = "quizzes", consumes = "application/json")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz,
                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        quiz.setUser(userPrincipal.user());
        return quizStorageService.add(quiz); }

    @GetMapping("quizzes/{id}")
    public Quiz getQuiz(@PathVariable long id) {
        return quizStorageService.get(id); }

    @PostMapping(value = "/quizzes/{id}/solve", consumes = "application/json")
    public Response solveQuiz(@PathVariable long id,
                              @RequestBody QuizAnswer quizAnswer,
                              @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (quizStorageService.solve(id, quizAnswer)) {
            QuizCompletion quizCompletion = new QuizCompletion(
                    userPrincipal.user().getEmail(),
                    id,
                    LocalDateTime.now(),
                    userPrincipal.user(),
                    quizStorageService.get(id)
            );
            quizCompletionService.add(quizCompletion);
            return new Response(true, Response.CORRECT_ANSWER);
        }
        return new Response(false, Response.WRONG_ANSWER);
        }

    @DeleteMapping("quizzes/{id}")
    public ResponseEntity<String> delete(@PathVariable long id,
                                         @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (quizStorageService.get(id).getUser().getEmail()
                .equals(userPrincipal.user().getEmail())) {
            quizStorageService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    @PostMapping(value = "register", consumes = "application/json")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody User user) {
        userStorageService.registerNewUser(user);
        return ResponseEntity.ok().build(); }


    @GetMapping("/quizzes/completed")
    public Page<QuizCompletion> getUserCompletions(
            @Min(0) @RequestParam(defaultValue = "0") int page,
            @Min(10) @Max(30) @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "completionDate") String sortBy,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        return quizCompletionService.getCompletions(
                userPrincipal.user().getEmail(),
                page,
                pageSize,
                sortBy
        );
    }

}
