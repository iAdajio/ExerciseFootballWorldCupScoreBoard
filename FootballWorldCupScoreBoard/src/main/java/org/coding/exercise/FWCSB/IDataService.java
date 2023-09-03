package org.coding.exercise.FWCSB;

import java.util.List;
import java.util.Map;

public interface IDataService {

    List<IFootballMatch> getAllActive();
    IFootballMatch getById(int id);
    void add(IFootballMatch footballMatch);
    boolean hasById(int id);

}
