package org.coding.exercise.FWCSB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles the game data resource.
 */
public class GameDataSource implements IDataService{

    Map<String,IFootballMatch> gamesPlayed = new HashMap<String, IFootballMatch>();// simulate data source

    /**
     * Get only active football games
     * @return list of active games
     */
    @Override
    public List<IFootballMatch> getAllActive() {
        List<IFootballMatch> list = new ArrayList<IFootballMatch>();
        for(Map.Entry<String, IFootballMatch> set: gamesPlayed.entrySet()){
            if(set.getValue().isActive()){
                list.add(set.getValue());
            }
        }
        return list;
    }

    /**
     * Get a single football game instance if it exists.
     * Return null if not game exists by Id
     * WARNING: vulnerable to NULL Pointer Exception
     * @param id
     * @return football game instance
     */
    @Override
    public IFootballMatch getById(int id) {
        if(!gamesPlayed.containsKey(String.valueOf(id))){
            return null;
        }
        return gamesPlayed.get(String.valueOf(id));
    }

    /**
     * Add a football game to the datasource
     * @param footballMatch
     */
    @Override
    public void add(IFootballMatch footballMatch) {
        gamesPlayed.put(String.valueOf(footballMatch.getId()),footballMatch);
    }

    /**
     * Check if data source for football game by Id exists
     * @param id
     * @return true if exists
     */
    @Override
    public boolean hasById(int id) {
        return gamesPlayed.containsKey(String.valueOf(id));
    }

}
