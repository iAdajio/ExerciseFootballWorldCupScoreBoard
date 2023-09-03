import org.coding.exercise.FWCSB.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Main Test Class
 */
public class FootballWorldCupScoreBoardTests {

    /*
    * Below tests for the "Acceptance Criteria" from README.md
    * */

    /**
     * Testing the operation of starting a football match
     */
    @Test
    void testStartGame() {
        ScoreBoardService.reset();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        ScoreBoardService.instance().startGame(fbm);
        assertTrue(ScoreBoardService.instance().isPlayingById(fbm.getId()));
    }

    /**
     * Testing the operation of finishing a football match
     */
    @Test
    void testFinnish() {
        ScoreBoardService.reset();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        ScoreBoardService.instance().startGame(fbm);
        assertTrue(ScoreBoardService.instance().finishGameById(fbm.getId()));
        assertFalse(ScoreBoardService.instance().isPlayingById(fbm.getId()));
    }

    /**
     * Testing the operation of updating the score board
     */
    @Test
    void testUpdateScore() {
        ScoreBoardService.reset();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        ScoreBoardService.instance().startGame(fbm);
        assertTrue(ScoreBoardService.instance().updateScoreById(fbm.getId(),1,0));
        IFootballMatch fbm2 = ScoreBoardService.instance().getActiveGameById(fbm.getId());
        assertEquals(1,fbm2.getMatchScore().getScoreHome() + fbm2.getMatchScore().getScoreAway());
    }

    /**
     * Testing the operation of getting the summary of games played
     */
    @Test
    void testGetSummary() {
        ScoreBoardService.reset();

        FootballMatch fbm1 = new FootballMatch(TeamName.MEXICO, TeamName.CANADA);
        FootballMatch fbm2 = new FootballMatch(TeamName.SPAIN, TeamName.BRAZIL);
        FootballMatch fbm3 = new FootballMatch(TeamName.GERMANY, TeamName.FRANCE);
        FootballMatch fbm4 = new FootballMatch(TeamName.URUGUAY, TeamName.ITALY);
        FootballMatch fbm5 = new FootballMatch(TeamName.ARGENTINA, TeamName.AUSTRALIA);

        ScoreBoardService.instance().startGame(fbm1);
        ScoreBoardService.instance().startGame(fbm2);
        ScoreBoardService.instance().startGame(fbm3);
        ScoreBoardService.instance().startGame(fbm4);
        ScoreBoardService.instance().startGame(fbm5);

        ScoreBoardService.instance().updateScoreById(fbm1.getId(),0,5);
        ScoreBoardService.instance().updateScoreById(fbm2.getId(),10,2);
        ScoreBoardService.instance().updateScoreById(fbm3.getId(),2,2);
        ScoreBoardService.instance().updateScoreById(fbm4.getId(),6,6);
        ScoreBoardService.instance().updateScoreById(fbm5.getId(),3,1);

        String expectedSummary = """
                Uruguay 6 - Italy 6
                Spain 10 - Brazil 2
                Mexico 0 - Canada 5
                Argentina 3 - Australia 1
                Germany 2 - France 2
                """;

        assertEquals(expectedSummary,ScoreBoardService.instance().getGameSummary());
    }

    /*
    * Below tests are used boundary Testing
    * */

    /**
     * The test must Pass if startGame returns false, indicating that the game is already playing
     */
    @Test
    void testIfGamePlayingAlready() {
        ScoreBoardService.reset();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        ScoreBoardService.instance().startGame(fbm);
        FootballMatch duplicate = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        assertFalse(ScoreBoardService.instance().startGame(duplicate));// should return false if it is a duplicate game
        assertFalse(ScoreBoardService.instance().isPlayingById(duplicate.getId()));// should return false, since the duplicate was ignored
    }


}
