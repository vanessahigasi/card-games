package tech.bts.cardgames;

import java.util.List;

public class Hand {


    private List<Card> cards;
    private Hand hand1;
    private Hand hand2;

    public Hand(Hand hand1, Hand hand2) {
        this.hand1 = hand1;
        this.hand2 = hand2;
    }

    public Hand(List<Card> cards) {

        this.cards = cards;

    }

    public Card calculate() {

        int magic = 0;
        int strength = 0;
        int intelligence = 0;

        for (Card card : cards) {
            magic += card.getMagic();
            strength += card.getStrength();
            intelligence += card.getIntelligence();
        }
        return new Card(magic, strength, intelligence);
    }

}
