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
    private static ScoreBoardService INSTANCE;
    public static ScoreBoardService instance(){
        if(INSTANCE == null) {
            INSTANCE = new ScoreBoardService();
        }
        return INSTANCE;
    }
    public static void reset(){
        INSTANCE = new ScoreBoardService();
    }

    // Implementing the Proxy java Pattern similar to dependency injection
    IDataService dataService = new GameDataSource();

    /**
     * Use instance() to get the object instance
     */
    private ScoreBoardService(){

    }
    public boolean startGame(IFootballMatch footballMatch){
        if(dataService.hasById(footballMatch.getId())){
            return false;
        }
        // Check if the is already a game with the same team names. This is to avoid duplicate entries
        for(Map.Entry<String, IFootballMatch> set: dataService.getAll().entrySet()){
            if(set.getValue().equals(footballMatch)){
                return false;
            }
        }
        dataService.add(footballMatch);
        return true;
    }
    public boolean updateScoreById(int id, int scoreHome, int scoreAway){
        if(!dataService.hasById(id)){
            return false;
        }
        dataService.getById(id).getMatchScore().setScoreHome(scoreHome).setScoreAway(scoreAway);
        return true;
    }
    public boolean finishGameById(int id){
        if(!dataService.hasById(id)){
            return false;
        }
        dataService.getById(id).setActive(false);
        return true;
    }
    public String getGameSummary(){
        ArrayList<IFootballMatch> orderList = new ArrayList<>();
        for(Map.Entry<String, IFootballMatch> set: dataService.getAll().entrySet()){
            orderList.add(set.getValue());
        }
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
    public boolean isPlayingById(int id){
        if(!dataService.hasById(id)){
            return false;
        }
        return dataService.getById(id).isActive();
    }

    /**
     * Used to determined if the game has finished
     * @param id
     * @return true if id in gamesPlayed and not in gamesPlaying
     */

    public IFootballMatch getActiveGameById(int id){
        return dataService.getById(id);
    }
}
