package ebmatches;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryTest {

    GameRepository repository = new GameRepository(List.of(
            new Game("Hungary", "Portugal", 3, 1),
            new Game("Hungary", "Italy", 6, 0),
            new Game("Italy", "Portugal", 2, 1)
    ));

    @Test
    void testGetLargestGoalDiffMatch() {
        Optional<Game> largestGoalDiff = repository.getLargestGoalDiffMatch();
        assertEquals("Hungary", largestGoalDiff.get().getFirstCountry());
    }

}