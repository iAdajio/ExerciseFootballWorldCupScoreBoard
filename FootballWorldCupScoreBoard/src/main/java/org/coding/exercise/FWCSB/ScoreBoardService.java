package org.coding.exercise.FWCSB;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreBoardService {
    HashMap<String,IFootballMatch> gamesPlayed = new HashMap<String, IFootballMatch>();
    HashMap<String,IFootballMatch> gamesPlaying = new HashMap<String, IFootballMatch>();
    public ScoreBoardService(){

    }
    public void startGame(IFootballMatch footballMatch){
        throw new UnsupportedOperationException("Not implemented");
    }
    public MatchScore updateScoreById(int id, int scoreHome, int scoreAway){
        throw new UnsupportedOperationException("Not implemented");
    }
    public void finishGameById(int id){
        throw new UnsupportedOperationException("Not implemented");
    }
    public String getGameSummary(){
        throw new UnsupportedOperationException("Not implemented");
    }
    public boolean isPlayingById(int id){
        throw new UnsupportedOperationException("Not implemented");
    }
    public boolean isFinishedById(int id){
        throw new UnsupportedOperationException("Not implemented");
    }
    public IFootballMatch getGameById(int id){
        throw new UnsupportedOperationException("Not implemented");
    }
}
