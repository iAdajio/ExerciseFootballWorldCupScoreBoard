package org.coding.exercise.FWCSB;


import java.util.*;

public class ScoreBoardService {
    Map<String,IFootballMatch> gamesPlayed = new HashMap<String, IFootballMatch>();
    Map<String,IFootballMatch> gamesPlaying = new HashMap<String, IFootballMatch>();
    public ScoreBoardService(){

    }
    public void startGame(IFootballMatch footballMatch){
        gamesPlaying.put(String.valueOf(footballMatch.getId()),footballMatch);
    }
    public boolean updateScoreById(int id, int scoreHome, int scoreAway){
        if(!gamesPlaying.containsKey(String.valueOf(id))){
            return false;
        }
        gamesPlaying.get(String.valueOf(id)).getMatchScore().setScoreHome(scoreHome).setScoreAway(scoreAway);
        return true;
    }
    public boolean finishGameById(int id){
        if(!gamesPlaying.containsKey(String.valueOf(id))){
            return false;
        }
        gamesPlayed.put(String.valueOf(id),gamesPlaying.get(String.valueOf(id)));
        gamesPlaying.remove(String.valueOf(id));
        return true;
    }
    public String getGameSummary(){
        ArrayList<IFootballMatch> orderList = new ArrayList<>();
        for(Map.Entry<String, IFootballMatch> set :gamesPlaying.entrySet()){
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
        return gamesPlaying.containsKey(String.valueOf(id));
    }

    /**
     * Used to determined if the game has finished
     * @param id
     * @return true if id in gamesPlayed and not in gamesPlaying
     */
    public boolean isFinishedById(int id){
        return gamesPlayed.containsKey(String.valueOf(id)) && (!gamesPlaying.containsKey(String.valueOf(id)));
    }
    public IFootballMatch getActiveGameById(int id){
        if(!gamesPlaying.containsKey(String.valueOf(id))){
            return null;
        }
        return gamesPlaying.get(String.valueOf(id));
    }
}
