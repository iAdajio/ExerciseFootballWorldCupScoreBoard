package org.coding.exercise.FWCSB;


import java.util.ArrayList;
import java.util.List;

public class ScoreBoardService {

    List<IFootballMatch> gamesPlayed = new ArrayList<IFootballMatch>();



    IFootballMatch currentGame = null;



    MatchScore currentScore = null;

    public ScoreBoardService(){

    }

    public void startGame(IFootballMatch game){
        throw new UnsupportedOperationException("Not implemented");
    }
    public void updateScore(MatchScore ms){
        throw new UnsupportedOperationException("Not implemented");
    }
    public void finishGame(){
        throw new UnsupportedOperationException("Not implemented");
    }
    public String getGameSummary(){
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Getter
     * @return get list of games played
     */
    public List<IFootballMatch> getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Getter
     * @return get the current game
     */
    public IFootballMatch getCurrentGame() {
        return currentGame;
    }

    /**
     * Getter
     * @return get the current game score
     */
    public MatchScore getCurrentScore() {
        return currentScore;
    }

}
