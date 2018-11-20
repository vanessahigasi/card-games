package tech.bts.cardgames;

import tech.bts.cardgames.Exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public enum State { OPEN, PLAYING }
    private final Deck deck;
    private State state;
    private List<String> usernames;
    private int countDiscard;
    private List<Card> pickedCard;

    private Map<String,Card> pickedCardByUsername;


    public Game(Deck deck) {
        this.deck = deck;
        this.state = State.OPEN;
        this.usernames = new ArrayList<>();
        this.pickedCardByUsername = new HashMap<>();
        this.countDiscard = 0;
        this.pickedCard = new ArrayList<>();

    }

    public State getState() {
        return state;
    }

    public void join(String username) {

        if (state != State.OPEN) {
            throw new JoiningNotAllowedException();
        }

        usernames.add(username);

        if (usernames.size() == 2) {
            state = State.PLAYING;
        }
    }

    public List<String> getPlayerName() {
        return usernames;
    }

    public Card pickCard(String username) {

        if (!usernames.contains(username)) {
            throw new PlayerNotInTheGameException();
        }

        Card pickedCard = pickedCardByUsername.get(username);
        if (pickedCard != null) {
            throw new CannotPick2CardsInARowException();
        }

        if (getState() == State.OPEN) {
            throw new CannotPickCardWhenIsNotPlayingException();
        }



        Card newPickedCard = deck.pickCard();

        pickedCardByUsername.put(username,newPickedCard);

        return newPickedCard;
    }

    public void discard(String username) {

        Card pickedCard = pickedCardByUsername.get(username);
        if (pickedCard == null) {
            throw new CannotDicardWithoutPickingACard();
        }

        countDiscard ++;

        if (countDiscard > 2) {
            throw new CannotDiscardMoreThan2Cards();
        }

        pickedCardByUsername.remove(username);
    }

    public List<Card> pickedCard (String username) {

        return List<Card> pickedCard(username);

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
