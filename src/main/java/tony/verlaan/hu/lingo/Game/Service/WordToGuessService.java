package tony.verlaan.hu.lingo.Game.Service;

import org.springframework.stereotype.Service;
import tony.verlaan.hu.lingo.Game.Domain.Game;
import tony.verlaan.hu.lingo.Game.Domain.WordToGuess;
import tony.verlaan.hu.lingo.Game.Repository.WordToGuessRepository;

@Service
public class WordToGuessService {

    private final WordToGuessRepository<WordToGuess> wordToGuessRepository;

    public WordToGuessService(WordToGuessRepository<WordToGuess> wordToGuessRepository) {
        this.wordToGuessRepository = wordToGuessRepository;
    }

    public WordToGuess makeWordToGuess(Game game) {
        WordToGuess wordToGuess = new WordToGuess(game.nextWord(), game);
        wordToGuessRepository.save(wordToGuess);
        return wordToGuess;
    }
}
