package tony.verlaan.hu.lingo.Game.Service;

import org.springframework.stereotype.Service;
import tony.verlaan.hu.lingo.Game.Domain.Game;
import tony.verlaan.hu.lingo.Game.Domain.Score;
import tony.verlaan.hu.lingo.Game.Repository.ScoreRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Service
public class ScoreService {
    private final ScoreRepository<Score> scoreRepository;

    public ScoreService(ScoreRepository<Score> scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public void submit(Game game, String name) {
        Score finalScore = new Score();
        finalScore.setName(name);
        finalScore.setGame(game);
        scoreRepository.save(finalScore);
    }

    public ArrayList<Score> getTopten() {
        ArrayList<Score> scores = (ArrayList<Score>) scoreRepository.findAll();
        ArrayList<Score> topTen = new ArrayList<>();
        Collections.sort(scores, new Comparator<Score>() {
            public int compare(Score o1, Score o2) {
                return Integer.compare(o2.getGame().getPoints(), o1.getGame().getPoints());
            }
        });
        return new ArrayList<>(scores.subList(0, 10));
    }
}