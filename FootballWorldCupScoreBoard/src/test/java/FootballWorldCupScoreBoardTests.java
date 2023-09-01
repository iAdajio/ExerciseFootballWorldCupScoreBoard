import org.coding.exercise.FWCSB.FootballMatch;
import org.coding.exercise.FWCSB.MatchScore;
import org.coding.exercise.FWCSB.ScoreBoardService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FootballWorldCupScoreBoardTests {

    /*
    * Below tests are used to Test the main board operations
    * */

    /**
     * Testing the operation of starting a football match
     */
    @Test
    void testStartGame() {
        ScoreBoardService sbs = new ScoreBoardService();
        FootballMatch fbm = new FootballMatch();
        sbs.startGame(fbm);
        assertEquals(fbm,sbs.getCurrentGame());
    }

    /**
     * Testing the operation of finishing a football match
     */
    @Test
    void testFinnish() {
        ScoreBoardService sbs = new ScoreBoardService();
        FootballMatch fbm = new FootballMatch();
        sbs.finishGame();
        assertNull(sbs.getCurrentGame());// if game ends, then there should be no active game
    }

    /**
     * Testing the operation of updating the score board
     */
    @Test
    void testUpdateScore() {
        ScoreBoardService sbs = new ScoreBoardService();
        FootballMatch fbm = new FootballMatch();
        sbs.startGame(fbm);
        MatchScore ms = new MatchScore();
        ms.setScoreAway(1);
        ms.setScoreAway(1);
        sbs.updateScore(ms);
        assertEquals(ms,sbs.getCurrentScore());
    }

    /**
     * Testing the operation of getting the summary of games played
     */
    @Test
    void testGetSummary() {
        ScoreBoardService sbs = new ScoreBoardService();
        FootballMatch fbm = new FootballMatch();
        String expectedSummary = """
                Uruguay 6 - Italy 6
                Spain 10 - Brazil 2
                Mexico 0 - Canada 5
                Argentina 3 - Australia 1
                Germany 2 - France 2
                """;
        assertEquals(expectedSummary,sbs.getGameSummary());
    }

    /*
    * Below tests are used to Test additional code
    * */
}
