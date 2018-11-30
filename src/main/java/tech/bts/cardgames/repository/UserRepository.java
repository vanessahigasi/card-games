package tech.bts.cardgames.repository;
import org.springframework.stereotype.Repository;
import tech.bts.cardgames.model.User;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private Map<String, User> userMap;

    public UserRepository() {
        userMap = new HashMap<>();
    }

    public void create(User user) {
        userMap.put(user.getName(),user);
    }
}
