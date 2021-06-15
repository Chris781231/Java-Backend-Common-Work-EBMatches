package ebmatches;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class GameRepository {

    private List<Game> games;

    public GameRepository(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    public Optional<Game> getLargestGoalDiffMatch() {
        return games.stream()
                .max(Comparator.comparingInt(g -> Math.abs(g.getFirstCountryScore() - g.getSecondCountryScore())));
    }
}
