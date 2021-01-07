package tony.verlaan.hu.lingo.Game.Repository;

import org.springframework.data.repository.CrudRepository;
import tony.verlaan.hu.lingo.Game.Domain.Feedback;

public interface FeedbackRepository<F> extends CrudRepository<Feedback, Long> {
}
