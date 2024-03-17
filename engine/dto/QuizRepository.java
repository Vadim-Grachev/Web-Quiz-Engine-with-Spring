package engine.dto;
import engine.dto.entity.Quiz;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {

    Object findAll(Pageable paging);
}
