package org.coding.exercise;

import org.coding.exercise.FWCSB.FootballMatch;
import org.coding.exercise.FWCSB.ScoreBoardService;
import org.coding.exercise.FWCSB.TeamName;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tutorial 1: Demonstrate how to use the Score board class for one game");
        FootballMatch fbm = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);// create a football match
        ScoreBoardService.instance().startGame(fbm);// start a football match
        ScoreBoardService.instance().updateScoreById(fbm.getId(),0,1);// Update score if Brazil scores
        ScoreBoardService.instance().updateScoreById(fbm.getId(),1,1);// Update score if Spain scores
        System.out.println(ScoreBoardService.instance().getGameSummary());
        ScoreBoardService.instance().finishGameById(fbm.getId());// start a football match

        System.out.println("Tutorial 2: Demonstrate how to use the Score board class for two games");
        FootballMatch fbm2 = new FootballMatch(TeamName.SPAIN,TeamName.BRAZIL);// create a football match
        FootballMatch fbm3 = new FootballMatch(TeamName.ARGENTINA,TeamName.CANADA);// create a football match
        ScoreBoardService.instance().startGame(fbm2);// start a football match
        ScoreBoardService.instance().startGame(fbm3);// start a football match
        ScoreBoardService.instance().updateScoreById(fbm2.getId(),0,1);// Update score if Brazil scores
        ScoreBoardService.instance().updateScoreById(fbm3.getId(),1,2);// Update score if Brazil scores
        System.out.println(ScoreBoardService.instance().getGameSummary());
        ScoreBoardService.instance().finishGameById(fbm2.getId());// start a football match
        ScoreBoardService.instance().finishGameById(fbm3.getId());// start a football match
        System.out.println(ScoreBoardService.instance().getGameSummary());// should be empty since the are not active games
    }
}