package engine.dto;

import engine.dto.entity.QuizCompletion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuizCompletionService {

    private final QuizCompletionRepository quizCompletionRepository;

    @Autowired
    public QuizCompletionService(QuizCompletionRepository quizCompletionRepository) {
        this.quizCompletionRepository = quizCompletionRepository;
    }

    public QuizCompletion add(QuizCompletion quizCompletion) {
        return quizCompletionRepository.save(quizCompletion);
    }

    public Page<QuizCompletion> getCompletions(String userId,
                                               int pageNumber,
                                               int pageSize,
                                               String sortBy) {
        Pageable paging = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(sortBy).descending()
        );
        return quizCompletionRepository.findAll(userId, paging);
    }
}
