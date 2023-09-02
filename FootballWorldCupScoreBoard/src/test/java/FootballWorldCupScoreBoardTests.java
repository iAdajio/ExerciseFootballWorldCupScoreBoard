import org.coding.exercise.FWCSB.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FootballWorldCupScoreBoardTests {

    /*
    * Below tests for the "Acceptance Criteria" from README.md
    * */

    /**
     * Testing the operation of starting a football match
     */
    @Test
    void testStartGame() {
        ScoreBoardService sbs = new ScoreBoardService();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        sbs.startGame(fbm);
        assertTrue(sbs.isPlayingById(fbm.getId()));
    }

    /**
     * Testing the operation of finishing a football match
     */
    @Test
    void testFinnish() {
        ScoreBoardService sbs = new ScoreBoardService();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        sbs.startGame(fbm);
        assertTrue(sbs.finishGameById(fbm.getId()));
        assertTrue(sbs.isFinishedById(fbm.getId()));
    }

    /**
     * Testing the operation of updating the score board
     */
    @Test
    void testUpdateScore() {
        ScoreBoardService sbs = new ScoreBoardService();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        sbs.startGame(fbm);
        assertTrue(sbs.updateScoreById(fbm.getId(),1,0));
        IFootballMatch fbm2 = sbs.getActiveGameById(fbm.getId());
        assertEquals(1,fbm2.getMatchScore().getScoreHome() + fbm2.getMatchScore().getScoreAway());
    }

    /**
     * Testing the operation of getting the summary of games played
     */
    @Test
    void testGetSummary() {
        ScoreBoardService sbs = new ScoreBoardService();

        FootballMatch fbm1 = new FootballMatch(TeamName.MEXICO, TeamName.CANADA);
        FootballMatch fbm2 = new FootballMatch(TeamName.SPAIN, TeamName.BRAZIL);
        FootballMatch fbm3 = new FootballMatch(TeamName.GERMANY, TeamName.FRANCE);
        FootballMatch fbm4 = new FootballMatch(TeamName.URUGUAY, TeamName.ITALY);
        FootballMatch fbm5 = new FootballMatch(TeamName.ARGENTINA, TeamName.AUSTRALIA);

        sbs.startGame(fbm1);
        sbs.startGame(fbm2);
        sbs.startGame(fbm3);
        sbs.startGame(fbm4);
        sbs.startGame(fbm5);

        sbs.updateScoreById(fbm1.getId(),0,5);
        sbs.updateScoreById(fbm2.getId(),10,2);
        sbs.updateScoreById(fbm3.getId(),2,2);
        sbs.updateScoreById(fbm4.getId(),6,6);
        sbs.updateScoreById(fbm5.getId(),3,1);

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
