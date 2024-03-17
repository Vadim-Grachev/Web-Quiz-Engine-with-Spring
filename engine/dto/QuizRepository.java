package engine.dto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {}
