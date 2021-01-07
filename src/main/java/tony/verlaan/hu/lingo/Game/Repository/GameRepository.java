package tony.verlaan.hu.lingo.Game.Repository;

import org.springframework.data.repository.CrudRepository;
import tony.verlaan.hu.lingo.Game.Domain.Game;

import java.util.List;

public interface GameRepository<G> extends CrudRepository<Game, Long> {
    List<Game> findGameByEmail(String email);

    List<Game> findByEmailOrderByEndDate(String email);

}
