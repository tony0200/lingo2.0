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
public class Score {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
}