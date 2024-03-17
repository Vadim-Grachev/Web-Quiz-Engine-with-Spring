package engine.dto;


import engine.dto.entity.QuizCompletion;
import engine.dto.entity.QuizCompletionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletionRepository
        extends PagingAndSortingRepository<QuizCompletion, QuizCompletionId> {

    @Query(value = "SELECT c FROM QuizCompletion c WHERE c.userId = :id")
    Page<QuizCompletion> findAll(@Param("id") String userId, Pageable pageable);
}
