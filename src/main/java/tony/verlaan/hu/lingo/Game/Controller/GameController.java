package tony.verlaan.hu.lingo.Game.Controller;

import org.springframework.stereotype.Controller;
import tony.verlaan.hu.lingo.Game.Domain.Game;
import tony.verlaan.hu.lingo.Game.Domain.Guess;
import tony.verlaan.hu.lingo.Game.Domain.Score;
import tony.verlaan.hu.lingo.Game.Domain.WordToGuess;
import tony.verlaan.hu.lingo.Game.Service.*;

import java.util.ArrayList;

@Controller
public class GameController {

    private final GameService gameService;
    private final GuessService guessService;
    private final FeedbackService feedbackService;
    private final WordToGuessService wordToGuessService;
    private final ScoreService scoreService;

    public GameController(GameService gameService, GuessService guessService, FeedbackService feedbackService, WordToGuessService wordToGuessService, ScoreService scoreService) {
        this.gameService = gameService;
        this.guessService = guessService;
        this.feedbackService = feedbackService;
        this.wordToGuessService = wordToGuessService;
        this.scoreService = scoreService;
    }

    public Game avtiveGame(String email) {
        Game game = gameService.gameActive(email);
        if (game == null) {
            game = gameService.startGame(email);
            WordToGuess wordToGuess = wordToGuessService.makeWordToGuess(game);
            return gameService.addWordToGuess(wordToGuess, game);
        } else {
            return game;
        }
    }

    public Game nextMove(String word, String email) {
        Game game = avtiveGame(email);
        Guess guess = null;
        if (guessService.inTime(game)) {
            guess = guessService.makeGuess(game, word, game.getWords(game.getWordToGuessString().length()));
        } else {
            guess = guessService.makeGuess(game, "empty", game.getWords(game.getWordToGuessString().length()));
        }
        if (game.getWordToGuessString().equals(guess.getWord())) {
            WordToGuess wordToGuess = wordToGuessService.makeWordToGuess(game);
            gameService.nextWord(game, wordToGuess);
        } else {
            gameService.nextMove(game);
            assert guess.getFeedbackSet() != null;
            feedbackService.saveFeedback(guess.setFeedback());
        }
        return game;
    }

    public void setScore(String name, String email) {
        scoreService.submit(gameService.getLatestGame(email), name);
    }

    public ArrayList<Score> getTopTen() {
        return scoreService.getTopten();
    }
}
