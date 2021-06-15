package ebmatches;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GameRepository {

    private List<Game> games;

    public GameRepository(List<Game> games) {
        this.games = new ArrayList<>(games);
    }

    public List<Game> getGames() {
        return new ArrayList<>(games);
    }

    public void addGame(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null");
        }
        games.add(game);
    }

    public void addGamesFromFile(Path file) {

        try (BufferedReader reader = Files.newBufferedReader(file)){
            String line;
            while ((line = reader.readLine()) != null) {
                games.add(getGame(line));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file");
        }
    }

    private Game getGame(String line) {
        String [] parts = line.split(";");
        return new Game(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
    }


    public Optional<Game> getLargestGoalDiffMatch() {
        return games.stream()
                .max(Comparator.comparingInt(g -> Math.abs(g.getFirstCountryScore() - g.getSecondCountryScore())));
    }

    public int getAllKickedGoalByCountry(String country) {
        return games.stream()
                .filter(c -> c.getFirstCountry().equalsIgnoreCase(country)) // Ebből a szűrőből kellene még egy, de a secondra.
                .mapToInt(Game::getFirstCountryScore)
                .sum();
    }
}
