package ebmatches;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryTest {

    GameRepository repository;

    @BeforeEach
    void setUp() {
            repository = new GameRepository(List.of(
            new Game("Hungary", "Portugal", 3, 1),
            new Game("Hungary", "Italy", 6, 0),
            new Game("Italy", "Portugal", 2, 1)
    ));
    }

    @Test
    void testGetLargestGoalDiffMatch() {
        Optional<Game> largestGoalDiff = repository.getLargestGoalDiffMatch();
        assertEquals("Hungary", largestGoalDiff.get().getFirstCountry());
    }


    @Test
    void addGameTest() {
        repository.addGame(new Game("Hungary", "Germany", 4, 1));
        assertEquals(4, repository.getGames().size());
        assertEquals("Germany", repository.getGames().get(3).getSecondCountry());
    }

    @Test
    void addGamesFromFile() throws URISyntaxException {
        Path file = Path.of("results.csv");
        repository.addGamesFromFile(file);

        assertEquals(18, repository.getGames().size());
    }

    @Test
    void getAllKickedGoalByCountryTest1() {
        assertEquals(9, repository.getAllKickedGoalByCountry1("Hungary"));
        assertEquals(2, repository.getAllKickedGoalByCountry1("Italy"));
        assertEquals(2, repository.getAllKickedGoalByCountry1("Portugal"));
    }

    @Test
    void getMostGoalKickCountryTest() {
        repository.addGamesFromFile(Path.of("results.csv"));
        assertEquals("Hungary", repository.getMostGoalKickCountry());
    }

}