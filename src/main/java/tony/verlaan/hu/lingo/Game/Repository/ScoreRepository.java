package tony.verlaan.hu.lingo.Game.Repository;

import org.springframework.data.repository.CrudRepository;
import tony.verlaan.hu.lingo.Game.Domain.Score;

import java.util.List;

public interface ScoreRepository<S> extends CrudRepository<Score, Long> {
}
