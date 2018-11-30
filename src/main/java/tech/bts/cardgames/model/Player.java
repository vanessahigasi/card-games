package tech.bts.cardgames.model;

public class Player {

    private String name;
    private int discardCount;
    private Card pickedCard;

    public Player(String name) {
        this.name = name;
        this.discardCount = 0;
        this.pickedCard = null;
    }

    public int getDicardCount() {
        return discardCount;
    }

    public void setDicardCount(int dicardCount) {
        this.discardCount = dicardCount;
    }

    public Card getPickedCard() {
        return pickedCard;
    }

    public void setPickedCard(Card pickedCard) {
        this.pickedCard = pickedCard;
    }
}
