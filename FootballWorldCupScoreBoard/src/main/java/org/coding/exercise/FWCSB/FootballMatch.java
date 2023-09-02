package org.coding.exercise.FWCSB;

public class FootballMatch implements IFootballMatch {
    public static int idInc = 0;// auto increment counter for new game id
    int id;// unique id for each game from idInc
    String teamHome;
    String teamAway;
    MatchScore matchScore;
    public FootballMatch(String teamHome, String teamAway, MatchScore ms){
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        matchScore = ms;
    }
    public FootballMatch(String teamHome, String teamAway){
        matchScore = new MatchScore(0,0);
    }
    public int getId() {
        return id;
    }
    public MatchScore getMatchScore(){
        return matchScore;
    }
}
