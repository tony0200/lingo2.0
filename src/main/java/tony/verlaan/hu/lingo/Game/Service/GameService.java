package tony.verlaan.hu.lingo.Game.Service;

import org.springframework.stereotype.Service;
import tony.verlaan.hu.lingo.Game.Domain.Game;
import tony.verlaan.hu.lingo.Game.Domain.WordToGuess;
import tony.verlaan.hu.lingo.Game.Repository.GameRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameService {

    private final GameRepository<Game> gameRepository;

    public GameService(GameRepository<Game> gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game startGame(String email) {
        Game game = new Game(email, 0, 5);
        gameRepository.save(game);
        return game;
    }

    public ArrayList<Character> makeGrid(Game game) {
        return game.makeGrid();
    }

    public Game gameActive(String email) {
        List<Game> games = gameRepository.findGameByEmail(email);
        Game returnValue = null;
        for (Game game : games) {
            if (game.getEndDate() == null) {
                returnValue = game;
                break;
            }
        }
        return returnValue;
    }

    public void nextMove(Game game) {
        game.addGuess();
        gameRepository.save(game);
    }

    public void nextWord(Game game, WordToGuess wordToGuess) {
        game.getWordToGuess().setEndDate(new Date());
        game.addWordToGuess(wordToGuess);
        game.newRound();
        gameRepository.save(game);
    }

    public Game addWordToGuess(WordToGuess wordToGuess, Game game) {
        game.addWordToGuess(wordToGuess);
        gameRepository.save(game);
        return game;
    }

    public Game getLatestGame(String email) {
        ArrayList<Game> games = (ArrayList<Game>) gameRepository.findByEmailOrderByEndDate(email);
        return games.get(games.size()-1);
    }
}
