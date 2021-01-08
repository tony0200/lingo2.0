package tony.verlaan.hu.lingo.Game.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class WordToGuess {

    @Id
    @GeneratedValue
    private int id;

    private String word;

    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @OneToMany(
            mappedBy = "wordToGuess",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Guess> guesses;

    public WordToGuess(String word, Game game) {
        this.word = word;
        this.game = game;
    }
}
