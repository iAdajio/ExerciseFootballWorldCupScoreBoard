package org.coding.exercise.FWCSB;

import java.util.Map;

public interface IDataService {

    /**
     * Only to be used for development purposes
     * @return data source List
     */
    Map<String,IFootballMatch> getAll();
    IFootballMatch getById(int id);

    void add(IFootballMatch object);

    boolean hasById(int id);

}
