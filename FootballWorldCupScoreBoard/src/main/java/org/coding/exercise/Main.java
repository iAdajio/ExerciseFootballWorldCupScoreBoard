package org.coding.exercise;

import org.coding.exercise.FWCSB.FootballMatch;
import org.coding.exercise.FWCSB.ScoreBoardService;
import org.coding.exercise.FWCSB.TeamName;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ScoreBoardService sbs = new ScoreBoardService();

        FootballMatch fbm1 = new FootballMatch(TeamName.MEXICO, TeamName.CANADA);
        FootballMatch fbm2 = new FootballMatch(TeamName.SPAIN, TeamName.BRAZIL);
        FootballMatch fbm4 = new FootballMatch(TeamName.GERMANY, TeamName.FRANCE);
        FootballMatch fbm3 = new FootballMatch(TeamName.URUGUAY, TeamName.ITALY);
        FootballMatch fbm5 = new FootballMatch(TeamName.ARGENTINA, TeamName.AUSTRALIA);

        sbs.startGame(fbm1);
        sbs.startGame(fbm2);
        sbs.startGame(fbm3);
        sbs.startGame(fbm4);
        sbs.startGame(fbm5);

        sbs.updateScoreById(fbm1.getId(),0,5);
        sbs.updateScoreById(fbm2.getId(),10,2);
        sbs.updateScoreById(fbm3.getId(),2,2);
        sbs.updateScoreById(fbm4.getId(),6,6);
        sbs.updateScoreById(fbm5.getId(),3,1);

        String expectedSummary = """
                Uruguay 6 - Italy 6
                Spain 10 - Brazil 2
                Mexico 0 - Canada 5
                Argentina 3 - Australia 1
                Germany 2 - France 2
                """;

        System.out.println(sbs.getGameSummary());

    }
}