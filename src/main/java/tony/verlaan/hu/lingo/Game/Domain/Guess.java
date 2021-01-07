package tony.verlaan.hu.lingo.Game.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Guess {

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    private String word;

    @OneToMany(mappedBy = "guess",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @Nullable
    private Set<Feedback> feedbackSet;

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private WordToGuess wordToGuess;

    private Date submitted;

    public Guess(Game game, String word, ArrayList<String> words, WordToGuess wordToGuess) {
        this.game = game;
        if (word.equals("empty")) {
            this.word = word;
        } else {
            if (validate(word, words)) {
                this.word = word;
            } else {
                this.word = "invalid";
            }
        }
        this.wordToGuess = wordToGuess;
        this.submitted = new Date();
    }

    public Set<Feedback> setFeedback() {
        Set<Feedback> set = new HashSet<>();
        int place = 0;
        if (word.equals("invalid")) {
            for (Character character : wordToGuess.getWord().toCharArray()) {
                set.add(new Feedback('-', Feedback.State.INVALID, this, place));
                place++;
            }
        } else if(word.equals("empty")) {
            for (Character character : wordToGuess.getWord().toCharArray()) {
                if (place == 0) {
                    set.add(new Feedback(wordToGuess.getWord().charAt(0), Feedback.State.CORRECT, this, place));
                } else {
                    set.add(new Feedback('.', Feedback.State.EMPTY, this, place));
                }
                place++;
            }
        } else {
            int letterInt = 0;
                for (Character guessLetter : word.toCharArray()) {
                if (guessLetter == wordToGuess.getWord().charAt(place)) {
                    set.add(new Feedback(guessLetter, Feedback.State.CORRECT, this, place));
                } else {
                    boolean present = false;
                    if (wordToGuess.getWord().contains(guessLetter + "")) {
                        present = checkForDoubles(word, wordToGuess.getWord(), letterInt);
                    }
                    if (present) {
                        set.add(new Feedback(guessLetter, Feedback.State.PRESENT, this, place));
                    } else {
                        set.add(new Feedback(guessLetter, Feedback.State.ABSENT, this, place));
                    }
                }
                place++;
                letterInt++;
            }
        }
        feedbackSet = set;
        return feedbackSet;
    }

    private boolean checkForDoubles(String guess, String word, int number) {
        char letter = guess.charAt(number);
        String sub = guess.substring(0,number);

        int numberOfLettersSub = 0;
        for (Character character : sub.toCharArray()) {
            if (letter == character) {
                int place = 0;
                for (Character wordCharacter : word.toCharArray()) {
                    if (wordCharacter == letter) {
                        if (!(guess.charAt(place) == wordCharacter)) {
                            numberOfLettersSub++;
                        }
                    }
                    place++;
                }
            }
        }

        int numberOfLettersWord = 0;
        for (Character character : word.toCharArray()) {
            if (letter == character) {
                numberOfLettersWord++;
            }
        }

        int numberOfLettersGuess = 0;
        for (Character character : guess.toCharArray()) {
            if (letter == character) {
                numberOfLettersGuess++;
            }
        }

        if (numberOfLettersGuess <= numberOfLettersWord) {
            return true;
        } else if(numberOfLettersWord == 0) {
            return false;
        } else return numberOfLettersSub >= numberOfLettersWord;
    }

    public ArrayList<Feedback> getFeedbackInRightOrder() {
        HashMap<Integer, Feedback> hashMap = new HashMap<>();
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        if (game.getWordToGuess() == wordToGuess) {
            assert feedbackSet != null;
            for (Feedback feedback : feedbackSet) {
                hashMap.put(feedback.getPlace(), feedback);
            }
            ArrayList<Integer> places = new ArrayList<>(hashMap.keySet());
            Collections.sort(places);
            for (int place : places) {
                feedbacks.add(hashMap.get(place));
            }
        }
        return feedbacks;
    }

    private boolean validate(String guessWord, ArrayList<String> words) {
        return words.contains(guessWord);
    }
}
