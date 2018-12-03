package tech.bts.cardgames.repository;
import org.springframework.stereotype.Repository;
import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GameRepository {


    private Map<Long, Game> gameMap;
    private long nextId;

    public GameRepository() {
        gameMap = new HashMap<>();
        nextId = 0;
    }

    public void create(Game game) {
        game.setId(nextId);
        gameMap.put(nextId,game);
        nextId++;
    }

    public Game getById(long id) {
        return gameMap.get(id);
    }

    public List<Game> getAll() {
        return new ArrayList<>(gameMap.values());
    }
}
