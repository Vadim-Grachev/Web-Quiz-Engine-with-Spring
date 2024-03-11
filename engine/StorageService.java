package engine;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StorageService {
    private final Map<Integer, Quiz> quizzes = new ConcurrentHashMap<>();
    public void add(Quiz quiz) {
        quizzes.put(quiz.getId(), quiz);
    }
    public Optional<Quiz> get(int id) {
        return Optional.ofNullable(quizzes.get(id));
    }
    public Collection<Quiz> getQuizzes() {
        return quizzes.values();
    }
}
