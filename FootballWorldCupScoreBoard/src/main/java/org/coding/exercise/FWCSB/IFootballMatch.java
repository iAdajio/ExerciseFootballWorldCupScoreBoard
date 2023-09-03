package org.coding.exercise.FWCSB;

public interface IFootballMatch {
    public MatchScore getMatchScore();
    public int getId();
    public void setId(int id);
    /**
     * Generates auto increment Id
     */
    public void setId();
    public String getTeamHome();
    public String getTeamAway();
    public boolean isActive();
    public void setActive(boolean active);
}
