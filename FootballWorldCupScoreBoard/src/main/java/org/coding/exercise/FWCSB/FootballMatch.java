package org.coding.exercise.FWCSB;

import java.util.Comparator;

public class FootballMatch implements IFootballMatch {
    public static int idInc = 0;// auto increment counter for new game id
    int id;// unique id for each game from idInc
    String teamHome;
    String teamAway;
    MatchScore matchScore;

    boolean active = false;

    /**
     * We don't want anyone created a football match with no data
     */
    private FootballMatch(){

    }
    public FootballMatch(TeamName teamHome, TeamName teamAway){
        this.teamHome = teamHome.label;
        this.teamAway = teamAway.label;
        matchScore = new MatchScore(0,0);
    }
    public int getId() {
        return id;
    }
    public void setId(){
        this.id = idInc;
        idInc++;
    }
    public void setId(int id){
        this.id = id;
    }
    public MatchScore getMatchScore(){
        return matchScore;
    }

    public String getTeamHome() {
        return teamHome;
    }

    public String getTeamAway() {
        return teamAway;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}

class SortGames implements Comparator<IFootballMatch>
{
    public int compare(IFootballMatch o1, IFootballMatch o2)
    {
        if(o1.getMatchScore().totalScore > o2.getMatchScore().totalScore){
            return -1;
        }
        else if(o1.getMatchScore().totalScore < o2.getMatchScore().totalScore){
            return 1;
        }
        else if(o1.getId() > o2.getId()){
            return -1;
        }
        else if(o1.getId() < o2.getId()){
            return 1;
        }
        return 0;
    }
}