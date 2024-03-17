package engine.dto;

import engine.api.QuizAnswer;
import engine.api.Response;
import engine.exception.QuizNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizStorageService {
    private final QuizRepository quizRepository;
    @Autowired
    public QuizStorageService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }
    public Quiz get(long id) {
        return quizRepository
                .findById(id)
                .orElseThrow(QuizNotFoundException::new);
    }
    public List<Quiz> getQuizzes() {
        List<Quiz> allQuizzes = new ArrayList<>();
        quizRepository.findAll().forEach(allQuizzes::add);
        return allQuizzes;
    }
    public Response solve(long id, QuizAnswer quizAnswer) {
        return this
                .get(id)
                .getAnswer()
                .equals(quizAnswer.getAnswer())
                ? new Response(true, Response.CORRECT_ANSWER)
                : new Response(false, Response.WRONG_ANSWER);
    }
    public void delete(long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return;
        }
        throw new QuizNotFoundException();
    }
}
