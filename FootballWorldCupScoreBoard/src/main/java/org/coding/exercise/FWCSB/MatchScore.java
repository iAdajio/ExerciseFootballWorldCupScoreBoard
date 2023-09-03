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
    int totalScore;

    public int getScoreHome() {
        return scoreHome;
    }

    public MatchScore setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
        this.totalScore = this.scoreHome + this.scoreAway;
        return this;
    }

    public int getScoreAway() {
        return scoreAway;
    }

    public MatchScore setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
        this.totalScore = this.scoreHome + this.scoreAway;
        return this;
    }
}


