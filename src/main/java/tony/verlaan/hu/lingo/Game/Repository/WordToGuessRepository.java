package tony.verlaan.hu.lingo.Game.Repository;

import org.springframework.data.repository.CrudRepository;
import tony.verlaan.hu.lingo.Game.Domain.WordToGuess;

public interface WordToGuessRepository<G> extends CrudRepository<WordToGuess, Long> {
}
