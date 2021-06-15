package ebmatches;

import java.util.List;

public class GameRepository {

    private List<Game> games;

    public GameRepository(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }
}
