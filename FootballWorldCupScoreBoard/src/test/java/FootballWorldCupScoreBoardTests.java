import org.coding.exercise.FWCSB.*;
import org.coding.exercise.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * Main Test Class
 * Contains two types of tests:
 * 1. Acceptance Criteria tests - to test the main features given by Task
 * 2. Boundary tests - test the quality and sturdiness of code
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
        assertFalse(ScoreBoardService.instance().finishGameById(fbm.getId()));// false because the game is not active
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
        IFootballMatch fbm2 = ScoreBoardService.instance().getGameById(fbm.getId());
        assertEquals(1,fbm2.getMatchScore().getScoreHome() + fbm2.getMatchScore().getScoreAway());
        ScoreBoardService.instance().finishGameById(fbm.getId());
        assertFalse(ScoreBoardService.instance().updateScoreById(fbm.getId(), 1,3));// should not update if game is not active
        assertEquals(1,fbm2.getMatchScore().getScoreHome() + fbm2.getMatchScore().getScoreAway());// score should still be 1 - 0
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
        FootballMatch fbm6 = new FootballMatch(TeamName.NETHERLANDS, TeamName.NETHERLANDS);

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
    @Test
    void testGetSummaryFullCoverage() {
        ScoreBoardService.reset();

        FootballMatch fbm1 = new FootballMatch(TeamName.MEXICO, TeamName.CANADA);
        FootballMatch fbm2 = new FootballMatch(TeamName.SPAIN, TeamName.BRAZIL);
        FootballMatch fbm3 = new FootballMatch(TeamName.GERMANY, TeamName.FRANCE);
        FootballMatch fbm4 = new FootballMatch(TeamName.URUGUAY, TeamName.ITALY);
        FootballMatch fbm5 = new FootballMatch(TeamName.ARGENTINA, TeamName.AUSTRALIA);
        FootballMatch fbm6 = new FootballMatch(TeamName.NETHERLANDS, TeamName.ENGLAND);

        ScoreBoardService.instance().startGame(fbm1);
        ScoreBoardService.instance().startGame(fbm2);
        ScoreBoardService.instance().startGame(fbm3);
        ScoreBoardService.instance().startGame(fbm4);
        ScoreBoardService.instance().startGame(fbm5);
        ScoreBoardService.instance().startGame(fbm6);

        ScoreBoardService.instance().updateScoreById(fbm1.getId(),2,2);
        ScoreBoardService.instance().updateScoreById(fbm2.getId(),5,5);
        ScoreBoardService.instance().updateScoreById(fbm3.getId(),2,2);
        ScoreBoardService.instance().updateScoreById(fbm4.getId(),5,5);
        ScoreBoardService.instance().updateScoreById(fbm5.getId(),2,2);
        ScoreBoardService.instance().updateScoreById(fbm6.getId(),2,2);
        // to force increase in coverage
        ScoreBoardService.instance().getGameById(fbm1.getId()).setId(100);//makes fbm1 top of list
        ScoreBoardService.instance().getGameById(fbm5.getId()).setId(fbm6.getId());//makes fbm1 top of list

        String expectedSummary = """
                Uruguay 5 - Italy 5
                Spain 5 - Brazil 5
                Mexico 2 - Canada 2
                Argentina 2 - Australia 2
                Netherlands 2 - England 2
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
        assertFalse(ScoreBoardService.instance().startGame(fbm));// should return false if the game is already playing
    }
    @Test
    void testTeamAlreadyPlaying(){
        ScoreBoardService.reset();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        FootballMatch fbm2 = new FootballMatch(TeamName.ARGENTINA,TeamName.BRAZIL);
        FootballMatch fbm3 = new FootballMatch(TeamName.BRAZIL,TeamName.ARGENTINA);
        ScoreBoardService.instance().startGame(fbm);
        assertFalse(ScoreBoardService.instance().startGame(fbm2));// should return false if the one team is already playing
        assertFalse(ScoreBoardService.instance().startGame(fbm3));// should return false if the one team is already playing
        ScoreBoardService.instance().finishGameById(fbm.getId());
        assertTrue(ScoreBoardService.instance().startGame(fbm3));// now it should be true, because Brazil finished their previous game
    }

    /**
     * Check score updating when game is finished.
     */
    @Test
    void testCanUpdateScoreIfGameNotActive(){
        ScoreBoardService.reset();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        ScoreBoardService.instance().startGame(fbm);
        ScoreBoardService.instance().finishGameById(fbm.getId());
        assertFalse(ScoreBoardService.instance().updateScoreById(fbm.getId(),0,1));
    }

    /**
     * Check how the code handles invalid score inputs
     */
    @Test
    void testInvalidScoreInput(){
        ScoreBoardService.reset();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        ScoreBoardService.instance().startGame(fbm);
        assertFalse(ScoreBoardService.instance().updateScoreById(fbm.getId(), 0,-1));
        assertFalse(ScoreBoardService.instance().updateScoreById(100, 0,-1));// invalid team
        ScoreBoardService.instance().finishGameById(fbm.getId());
        assertFalse(ScoreBoardService.instance().updateScoreById(fbm.getId(), 0,2));
    }

    /**
     * Check how the code handles repeated game start
     */
    @Test
    void testInvalidStart(){
        ScoreBoardService.reset();
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);
        assertTrue(ScoreBoardService.instance().startGame(fbm));
        assertFalse(ScoreBoardService.instance().startGame(fbm));
        FootballMatch fbm2 = new FootballMatch(TeamName.BRAZIL,TeamName.BRAZIL);
        assertFalse(ScoreBoardService.instance().startGame(fbm2));
    }

    /**
     * Check if the code outputs the correct summary of only active games
     */
    @Test
    void testSummaryOnlyActiveGames(){
        ScoreBoardService.reset();

        FootballMatch fbm1 = new FootballMatch(TeamName.MEXICO, TeamName.CANADA);
        FootballMatch fbm2 = new FootballMatch(TeamName.SPAIN, TeamName.BRAZIL);
        FootballMatch fbm5 = new FootballMatch(TeamName.ARGENTINA, TeamName.AUSTRALIA);
        FootballMatch testTeam = new FootballMatch(TeamName.ARGENTINA, TeamName.AUSTRALIA);

        ScoreBoardService.instance().startGame(fbm1);
        ScoreBoardService.instance().startGame(fbm2);
        ScoreBoardService.instance().startGame(fbm5);
        ScoreBoardService.instance().startGame(testTeam);
        ScoreBoardService.instance().finishGameById(testTeam.getId());

        ScoreBoardService.instance().updateScoreById(fbm1.getId(),0,5);
        ScoreBoardService.instance().updateScoreById(fbm2.getId(),10,2);
        ScoreBoardService.instance().updateScoreById(fbm5.getId(),3,1);

        String expectedSummary = """
                Spain 10 - Brazil 2
                Mexico 0 - Canada 5
                Argentina 3 - Australia 1
                """;

        assertEquals(expectedSummary,ScoreBoardService.instance().getGameSummary());
    }

    @Test
    void testMainTutorial(){
        ScoreBoardService.reset();
        Main.main(null);
    }

}
