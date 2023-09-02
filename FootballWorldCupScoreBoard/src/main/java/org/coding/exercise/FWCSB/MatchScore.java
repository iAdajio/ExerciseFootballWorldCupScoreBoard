package org.coding.exercise.FWCSB;

public class MatchScore {

    public MatchScore(int scoreHome, int scoreAway){
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
    }
    public MatchScore(){
        this.scoreHome = 0;
        this.scoreAway = 0;
    }

    int scoreHome;
    int scoreAway;

    public int getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
    }
}
