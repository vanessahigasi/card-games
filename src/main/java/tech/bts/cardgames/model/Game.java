package tech.bts.cardgames.model;

import tech.bts.cardgames.exceptions.*;

import java.util.*;

public class Game {

    public enum State { OPEN, PLAYING }

    public long id;
    private final Deck deck;
    private State state;
    private Map<String,Player> playerByUsername;


    public Game(Deck deck) {
        this.id = id;
        this.deck = deck;
        this.state = State.OPEN;
        this.playerByUsername = new HashMap<>();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void join(String username) {

        if (state != State.OPEN) {
            throw new JoiningNotAllowedException();
        }

        Player player = new Player(username);

        playerByUsername.put(username,player);

        if (playerByUsername.size() == 2) {
            state = State.PLAYING;
        }
    }

    public Set<String> getPlayerNames() {
        return playerByUsername.keySet();
    }

    public Card pickCard(String username) {

        if (!playerByUsername.containsKey(username)) {
            throw new PlayerNotInTheGameException();
        }

        if (state != State.PLAYING) {
            throw new NotPlayingException();
        }

        Player player = playerByUsername.get(username);

        Card pickedCard = player.getPickedCard();

        if (pickedCard != null) {
            throw new CannotPick2CardsInARowException();
        }

        Card newPickedCard = deck.pickCard();

        player.setPickedCard(newPickedCard);

        return newPickedCard;
    }

    public void discard(String username) {

        Player player = playerByUsername.get(username);

        Card pickedCard = player.getPickedCard();

        if (pickedCard == null) {          //if did not pick a card
            throw new DidNotPickCardException();
        }

        int discards = player.getDiscardCount();

        //users has already discarded 2 cards
        if (discards == 2) {
            throw new  TooManyDiscardsException();
        }

        player.setDiscardCount(discards + 1);

        player.setPickedCard(null);
    }


    /**
     * Returns a negative integer(hand 2 win), zero (tie), or a positive integer (hand 1 win)
     * as the first hand is less than, equal to,
     * or greater than the second hand


    private int compare(Hand hand1, Hand hand2) {

        int points1 = 0;
        int points2 = 0;


        Card total1 = hand1.calculate();
        Card total2 = hand2.calculate();

        if (total1.getMagic() > total2.getMagic()) {
            points1++;

        } else if (total1.getMagic() < total2.getMagic()){
            points2++;
        }


        if (total1.getStrength() > total2.getStrength())  {
            points1++;

        } else if (total1.getStrength() < total2.getStrength()) {
            points2++;
        }

        if (total1.getIntelligence() > total2.getIntelligence()) {
            points1++;
        } else if (total1.getIntelligence() < total2.getIntelligence()) {
            points2++;
        }

        return points1 - points2;
    }
    */


}
