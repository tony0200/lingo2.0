package tony.verlaan.hu.lingo.Game.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Feedback {

    @Id
    @GeneratedValue
    private int id;

    private Character letter;

    private int place;

    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guess guess;

    enum State{
        CORRECT,
        ABSENT,
        PRESENT,
        INVALID,
        EMPTY
    }

    public Feedback(Character letter, State state, Guess guess ,int place) {
        this.letter = letter;
        this.state = state;
        this.guess = guess;
        this.place = place;
    }
}
