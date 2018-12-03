package tech.bts.cardgames.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void generate() {

        for (int m = 1; m <= 8; m++) {

            for (int s = 1; s <= 9 - m; s++) {
                int i = 10 - (m+s);
                this.add(new Card(m, s, i));
            }
        }
    }
    public void shuffle() {

        System.out.println("Shuffling " + this.cards.size() + " cards");

        Random random = new Random();

        /*
        int index1 = 0;
        int index2 = random.nextInt(this.cards.size()); //random index in cards


        Card card1 = this.cards.get(index1);
        Card card2 = this.cards.get(index2);

        this.cards.set(index2,card1); //change positions
        this.cards.set(index1,card2);
        */

        for (int i = 0; i < this.cards.size(); i++) {

            //pick a random index and swap it with card at index 'i'

            int randomIndex = random.nextInt(this.cards.size()); //random index after index i

             // int randomIndex = random.nextInt(this.cards.size()); //random index

            System.out.println("Swapping cards at indexes " + i + " and " + randomIndex);

            Card card1 = this.cards.get(i);
            Card card2 = this.cards.get(randomIndex);

            this.cards.set(randomIndex,card1); //change positions
            this.cards.set(i,card2);


        }

    }

    public Card pickCard() {

        return cards.remove(cards.size() - 1);
    }

    public Hand deal(int size) {

        List<Card> handCards = new ArrayList<Card>();

        for (int i = 1; i <= size; i++) {
            handCards.add(this.pickCard());
        }

        Hand result = new Hand(handCards);
        return result;
    }
}
