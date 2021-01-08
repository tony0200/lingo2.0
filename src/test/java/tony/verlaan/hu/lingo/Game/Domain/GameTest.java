package tony.verlaan.hu.lingo.Game.Domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Game")
public class GameTest {
/*
    private static final ArrayList<WordToGuess> wordsToGuess = new ArrayList<>();
    private static final ArrayList<Guess> guesses = new ArrayList<>();
    private static final Game game1 = new Game("email1@gmail.com", 0, 5);

    private GameTest() {
        if (game1.getWordsToGuess().isEmpty()) {
            WordToGuess wordToGuess1 = new WordToGuess("woord1", game1);
            WordToGuess wordToGuess2 = new WordToGuess("woord2", game1);
            WordToGuess wordToGuess3 = new WordToGuess("woord3", game1);
            wordToGuess1.setEndDate(new Date());
            wordToGuess2.setEndDate(new Date());

            wordsToGuess.add(wordToGuess1);
            wordsToGuess.add(wordToGuess2);
            wordsToGuess.add(wordToGuess3);
            game1.setWordsToGuess(wordsToGuess);

            Guess guess1 = new Guess(game1, "woord4", game1.getWords(5), wordToGuess1);
            Guess guess2 = new Guess(game1, "tegel", game1.getWords(5), wordToGuess2);
            Guess guess3 = new Guess(game1, "steun", game1.getWords(5), wordToGuess3);
            Guess guess4 = new Guess(game1, "stint", game1.getWords(5), wordToGuess3);

            guesses.add(guess1);
            guesses.add(guess2);
            guesses.add(guess3);
            guesses.add(guess4);
            game1.setGuesses(guesses);
        }
    }

    @Test
    @DisplayName("Gives current wordt to guess")
    void getWordToGuess() {
        WordToGuess word = null;
        for (WordToGuess wordToGuess : wordsToGuess) {
            if (wordToGuess.getWord().equals("woord3")) {
                word = wordToGuess;
            }
        }

        assert word != null;
        assertEquals(word.getWord(), game1.getWordToGuess().getWord());
    }

    @Test
    @DisplayName("Gives all guesses by word")
    void getGuessesByWord() {
        ArrayList<Guess> guessesTest = new ArrayList<>();

        for (Guess guess : guesses) {
            if (guess.getWordToGuess().equals(wordsToGuess.get(2))) {
                guessesTest.add(guess);
            }
        }

        ArrayList<Guess> guessesTestFunction = game1.getGuessesByWord();
        assertTrue(guessesTest.get(0) == guessesTestFunction.get(0) &&
                guessesTest.get(1) == guessesTestFunction.get(1) &&
                "empty".equals(guessesTestFunction.get(2).getWord()) &&
                "empty".equals(guessesTestFunction.get(3).getWord()) &&
                "empty".equals(guessesTestFunction.get(4).getWord()));
    }

    @Test
    @DisplayName("Gives null as current word to guess")
    void getNullWordToGuess() {
        game1.getWordToGuess().setEndDate(new Date());
        assertNull(game1.getWordToGuess());
        game1.addWordToGuess(new WordToGuess("word4", game1));
    }

    @Test
    @DisplayName("Gives next wordToGuess")
    void nextWord() {
        assertEquals(game1.nextWord().length(), game1.getWordToGuess().getWord().length() + 1 );
    }

    @Test
    @DisplayName("Gives next word, but the last word was 7 numbers")
    void nextWordEight() {
        game1.getWordToGuess().setEndDate(new Date());
        game1.addWordToGuess(new WordToGuess("aalfuik", game1));

        assertEquals(game1.nextWord().length(), 5);

        game1.getWordToGuess().setEndDate(new Date());
        game1.addWordToGuess(new WordToGuess("woord3", game1));
    }

    @Test
    @DisplayName("Gives next word, but there was no last word")
    void nextWordNull() {
        Game game2 = new Game("email1@gmail.com", 0, 5);

        assertEquals(game2.nextWord().length(), 5);
    }

    @Test
    @DisplayName("Adds a guess to the variables and checks state game")
    void addGuess() {
        int turns = game1.getNumberOfTurns();
        game1.addGuess();

        assertTrue(game1.getNumberOfTurns() + 1 == turns && game1.getGameState().equals(GameState.RUNNING));
    }

    @Test
    @DisplayName("Adds a guess to the variables and checks state game")
    void addGuessLost() {
        game1.setNumberOfTurns(1);
        int turns = game1.getNumberOfTurns();
        game1.addGuess();

        assertTrue(game1.getNumberOfTurns() + 1 == turns && game1.getEndDate() != null);
    }

    @Test
    @DisplayName("Gives one point and set the nubers of turns to 5")
    void newRound() {
        game1.newRound();

        assertTrue(game1.getNumberOfTurns() == 5 && game1.getPoints() == 1);
    }*/
}
