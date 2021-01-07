package tony.verlaan.hu.lingo.Game.Service;

import org.springframework.stereotype.Service;
import tony.verlaan.hu.lingo.Game.Domain.Game;
import tony.verlaan.hu.lingo.Game.Domain.Guess;
import tony.verlaan.hu.lingo.Game.Repository.GuessRepository;

import java.util.ArrayList;
import java.util.Date;

@Service
public class GuessService {

    private final GuessRepository<Guess> guessRepository;

    public GuessService(GuessRepository<Guess> guessRepository) {
        this.guessRepository = guessRepository;
    }

    public Guess makeGuess(Game game, String word, ArrayList<String> words) {
        Guess guess = new Guess(game, word, words, game.getWordToGuess());
        guessRepository.save(guess);
        return guess;
    }

    public boolean inTime(Game game) {
        ArrayList<Guess> guesses = (ArrayList<Guess>) guessRepository.findGuessByGameOrderBySubmitted(game);
        Date time = new Date();
        time.setTime(time.getTime() - 12000);
        if (guesses.size() == 0) {
            return true;
        } else {
            return time.before(guesses.get(guesses.size() - 1).getSubmitted());
        }
    }
}
