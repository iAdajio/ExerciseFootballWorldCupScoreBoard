package org.coding.exercise.FWCSB;


public interface IFootballMatch {


    public MatchScore getMatchScore();
    public int getId();
    public String getTeamHome();
    public String getTeamAway();
    public boolean isActive();
    public void setActive(boolean active);
    


}
