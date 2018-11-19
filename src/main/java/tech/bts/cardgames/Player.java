package tech.bts.cardgames;

public class Player {

    private String name;
    private int battlepoints;
    private Hand hand;

    public Player(String name) {
        this.name = name;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }
}
