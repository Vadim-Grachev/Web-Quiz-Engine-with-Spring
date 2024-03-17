package engine.dto;

import engine.api.QuizAnswer;
import engine.dto.entity.Quiz;

import engine.exception.QuizNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public boolean solve(long id, QuizAnswer quizAnswer) {
        return  get(id)
                .getAnswer()
                .equals(quizAnswer.getAnswer());
    }
    public void delete(long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return;
        }
        throw new QuizNotFoundException();
    }

    public Page<Quiz> getAllQuizzes(int pageNumber, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return (Page<Quiz>) quizRepository.findAll(paging);
    }
}
