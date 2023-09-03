package org.coding.exercise.FWCSB;

import java.util.HashMap;
import java.util.Map;

public class GameDataSource implements IDataService{
    Map<String,IFootballMatch> gamesPlayed = new HashMap<String, IFootballMatch>();// simulate data source

    @Override
    public Map<String,IFootballMatch> getAll() {
        return gamesPlayed;
    }

    @Override
    public IFootballMatch getById(int id) {
        if(!gamesPlayed.containsKey(String.valueOf(id))){
            return null;
        }
        return gamesPlayed.get(String.valueOf(id));
    }
    @Override
    public void add(IFootballMatch object) {
        gamesPlayed.put(String.valueOf(object.getId()),object);
    }
    @Override
    public boolean hasById(int id) {
        return gamesPlayed.containsKey(String.valueOf(id));
    }

}
