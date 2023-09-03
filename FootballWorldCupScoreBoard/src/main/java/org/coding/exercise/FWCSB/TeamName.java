package org.coding.exercise.FWCSB;

public enum TeamName {
    AUSTRALIA("Australia"),
    URUGUAY("Uruguay"),
    ITALY("Italy"),
    SPAIN("Spain"),
    BRAZIL("Brazil"),
    MEXICO("Mexico"),
    CANADA("Canada"),
    ARGENTINA("Argentina"),
    GERMANY("Germany"),
    FRANCE("France"),
    ENGLAND("England"),
    NETHERLANDS("Netherlands");

    public final String label;

    private TeamName(String label) {
        this.label = label;
    }
}
