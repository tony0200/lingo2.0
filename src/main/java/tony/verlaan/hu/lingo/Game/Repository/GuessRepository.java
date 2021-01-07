package tony.verlaan.hu.lingo.Game.Repository;

import org.springframework.data.repository.CrudRepository;
import tony.verlaan.hu.lingo.Game.Domain.Game;
import tony.verlaan.hu.lingo.Game.Domain.Guess;

import java.util.List;

public interface GuessRepository<G> extends CrudRepository<Guess, Long> {

    List<Guess> findGuessByGameOrderBySubmitted(Game game);
}
