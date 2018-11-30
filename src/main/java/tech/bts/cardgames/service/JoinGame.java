package tech.bts.cardgames.service;

public class JoinGame {

    private long gameId;
    private String username;

    public JoinGame() {
        //needed to Post in SpringBoot
    }

    public long getGameId() {
        return gameId;
    }

    public String getUsername() {
        return username;
    }
}
