package tech.bts.cardgames.service;

public class GameUser {

    private long gameId;
    private String username;

    public GameUser() {
        //needed to Post in SpringBoot
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }
}
