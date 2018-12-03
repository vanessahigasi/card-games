package tech.bts.cardgames.service;

public class GameUser {

    private long gameId;
    private String username;

    public GameUser() {
        //needed to Post in SpringBoot
    }

    public GameUser(long gameId, String username) {
        this.gameId = gameId;
        this.username = username;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
