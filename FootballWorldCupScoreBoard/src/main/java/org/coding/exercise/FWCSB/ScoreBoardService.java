package org.coding.exercise.FWCSB;


import java.util.*;

/**
 * This class represents the Score Board.
 * It's main features are:
 * > Start football games
 * > Update football game scores
 * > Finish football games
 * > Get summary of active football games
 */
public class ScoreBoardService {

    /**
     * Use instance() instead to get the object instance
     */
    private ScoreBoardService(){}
    private static ScoreBoardService INSTANCE;
    /**
     * Get the class instance
     * @return The singleton instance of this class
     */
    public static ScoreBoardService instance(){
        if(INSTANCE == null) {
            INSTANCE = new ScoreBoardService();
        }
        return INSTANCE;
    }
    /**
     * Reinitialise the Singleton Instance
     */
    public static void reset(){
        INSTANCE = new ScoreBoardService();
    }

    // Can easily be replaced by database solution
    IDataService dataService = new GameDataSource();// Implementing the Proxy java Pattern similar to dependency injection

    /**
     * Start a game. When a game starts, it should capture (being initial score 0 – 0)
     * > adding it to the data collection
     * > and setting to Active
     * Will return false if the game is already active
     * @param footballMatch IFootballMatch instance
     * @return true if successful or false if not successful
     */
    public boolean startGame(IFootballMatch footballMatch){
        if(footballMatch.getTeamAway().equals(footballMatch.getTeamHome())){
            return false;// The same team can't play themselves
        }
        if(dataService.hasById(footballMatch.getId())){
            if(dataService.getById(footballMatch.getId()).isActive()){
                return false;// the game already playing
            }
        }
        // if one team is already playing, it can't play another team.
        for(IFootballMatch fm: dataService.getAllActive()){
            if(footballMatch.getTeamHome().equals(fm.getTeamHome()) || footballMatch.getTeamAway().equals(fm.getTeamAway())){
                return false;
            }
            if(footballMatch.getTeamHome().equals(fm.getTeamAway()) || footballMatch.getTeamAway().equals(fm.getTeamHome())){
                return false;
            }
        }
        footballMatch.setId();
        footballMatch.getMatchScore().setScoreHome(0).setScoreAway(0);
        dataService.add(footballMatch);
        footballMatch.setActive(true);
        return true;
    }

    /**
     * Update score by game Id. Receiving the pair score; home team score and away team score
     * updates a game score.
     * Can only update under these conditions:
     * > Game exists and is Active: return true
     * Returns false if game does not exist and is not Active
     * @param id game id
     * @param scoreHome home team
     * @param scoreAway away team
     * @return true if successful or false if not successful
     */
    public boolean updateScoreById(int id, int scoreHome, int scoreAway){
        if(!dataService.hasById(id)){
            return false;// Game does not exist
        }
        if(!dataService.getById(id).isActive()){
            return false;// game not active
        }
        // Only accept positive scores
        if(scoreHome<0 || scoreAway <0){
            return false;
        }
        dataService.getById(id).getMatchScore().setScoreHome(scoreHome).setScoreAway(scoreAway);
        return true;
    }

    /**
     * Finish game by Id. It will remove a match from the scoreboard.
     * If the game does not exist: return false
     * If the game is already false: return false;
     * If the game is active: set game to true and return true
     * @param id game id
     * @return true if successful or false if not successful
     */
    public boolean finishGameById(int id){
        if(!dataService.hasById(id)){
            return false;// no game exists
        }
        if(!dataService.getById(id).isActive()){
            return false;// the game is already false
        }
        dataService.getById(id).setActive(false);
        return true;
    }

    /**
     * Get a summary of games by total score. Those games with the same total score will
     * be returned ordered by the most recently added to our system.
     * Each game format is "{teamHome} - {teamAway}: {scoreHome} - {scoreAway}"
     * @return The game summary
     */
    public String getGameSummary(){
        List<IFootballMatch> orderList = dataService.getAllActive();
        orderList.sort(new SortGames());
        StringBuilder outputText = new StringBuilder();
        for(IFootballMatch match: orderList){
            outputText
                    .append(match.getTeamHome())
                    .append(" ")
                    .append(match.getMatchScore().getScoreHome())
                    .append(" - ")
                    .append(match.getTeamAway())
                    .append(" ")
                    .append(match.getMatchScore().getScoreAway())
                    .append("\n");
        }
        return outputText.toString();
    }

    /**
     * Used to determine if a game by Id is active and playing
     * @param id game id
     * @return true if game is active
     */
    public boolean isPlayingById(int id){
        if(!dataService.hasById(id)){
            return false;
        }
        return dataService.getById(id).isActive();
    }

    /**
     * Get a football game by Id.
     * If the game id does not exist, the null will be returned.
     * WARNING: vulnerable to NULL Pointer Exception
     * @param id game id
     * @return Football game instance
     */
    public IFootballMatch getGameById(int id){
        return dataService.getById(id);
    }
}
