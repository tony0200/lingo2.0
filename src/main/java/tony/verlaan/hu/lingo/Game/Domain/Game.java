package tony.verlaan.hu.lingo.Game.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ResourceUtils;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "game")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "points")
    private int points;

    @Column(name = "number_of_plays")
    private int numberOfTurns;

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Guess> guesses = new ArrayList<>();

/*    @OneToOne
    private Score score;*/

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "game_state")
    private GameState gameState;

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WordToGuess> wordsToGuess = new ArrayList<>();

    public Game(String email, int points, int turns) {
        this.email = email;
        this.points = points;
        this.numberOfTurns = turns;
    }

    public ArrayList<Character> makeGrid() {
        String word = getWordToGuessString();
        ArrayList<Character> characters = new ArrayList<>();
        for (Character character : word.toCharArray()) {
            characters.add('.');
        }
        characters.set(0, word.charAt(0));
        for (Guess guess : guesses) {
            if (guess.getWordToGuess() == getWordToGuess()) {
                assert guess.getFeedbackSet() != null;
                for (Feedback feedback : guess.getFeedbackSet()) {
                    if (feedback.getState().equals(Feedback.State.CORRECT)) {
                        characters.set(feedback.getPlace(), feedback.getLetter());
                    }
                }
                break;
            }
        }
        return characters;
    }

    public void addGuess() {
        this.numberOfTurns--;
        if (this.numberOfTurns == 0) {
            this.gameState = GameState.LOST;
            this.endDate = new Date();
        }
        this.gameState = GameState.RUNNING;
    }

    public String nextWord() {
        String word = getWordToGuessString();
        int newWordLenght;
        if (wordsToGuess.size() == 0) {
            newWordLenght = 5;
        } else {
            newWordLenght = word.length() + 1;
        }
        if (newWordLenght == 8) {
            newWordLenght = 5;
        }
        ArrayList<String> words = getWords(newWordLenght);
        return words.get(new Random().nextInt(words.size()));
    }

    public ArrayList<String> getWords(int letters) {
        ArrayList<String> returnList = new ArrayList<>();
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("data/woorden" + letters + ".txt");
            BufferedReader br = new BufferedReader( new InputStreamReader(is));
            String line = br.readLine();
            String[] words = line.split(", ");
            returnList.addAll(Arrays.asList(words));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public String getWordToGuessString() {
        String word = null;
        for (WordToGuess wordToGuess : wordsToGuess) {
            if (wordToGuess.getEndDate() == null) {
                word = wordToGuess.getWord();
                break;
            }
        }
        return word;
    }

    public WordToGuess getWordToGuess() {
        for (WordToGuess wordToGuess : wordsToGuess) {
            if (wordToGuess.getEndDate() == null) {
                return wordToGuess;
            }
        }
        return null;
    }

    public ArrayList<Guess> getGuessesByWord() {
        ArrayList<Guess> guesses = new ArrayList<>();
        int wordLenght = getWordToGuessString().length();
        int place = 0;
        Guess emptyGuess = new Guess(this, "empty", getWords(wordLenght), getWordToGuess());
        emptyGuess.setFeedback();
        while (place < 5) {
            guesses.add(emptyGuess);
            place++;
        }
        place = 0;
        for (Guess guess : getGuesses()) {
            if (getWordToGuess() == guess.getWordToGuess()) {
                guesses.set(place, guess);
                place++;
            }
        }
        return guesses;
    }

    public boolean addWordToGuess(WordToGuess wordToGuess) {
        return wordsToGuess.add(wordToGuess);
    }

    public void newRound() {
        points++;
        numberOfTurns = 5;
    }
}