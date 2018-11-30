package tech.bts.cardgames;

import org.junit.Test;
import tech.bts.cardgames.exceptions.*;
import tech.bts.cardgames.model.Card;
import tech.bts.cardgames.model.Deck;
import tech.bts.cardgames.model.Game;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Creating a game:
 * - A game is created with a deck of cards (each card has 3 numbers (>=1) that added make 10).
 *   - Note: the 3 numbers represent magic, strength, intelligence
 * - When a game is created, its state is OPEN.
 *
 * Joining a game:
 * - A player can join an OPEN game (for simplicity, a player is indicated by its username).
 * - When 2 players join the game, the state of the game changes to PLAYING.
 * - A player can't join if the game state is not OPEN (throw an exception if someone tries).
 *
 * Picking cards:
 * - When the game is PLAYING, any player that joined the game can pick a card.
 * - Picking is only allowed when PLAYING
 * - After picking a card, a player must keep it or discard it, before picking another one.
 * - A player can only discard 2 cards (i.e. must pick 3 cards).
 *
 * The battle (point calculation):
 * - When the 2 players have picked 3 cards, the winner of that round is calculated:
 *   - Each player adds all magics, all strengths and all intelligences
 *   - Totals of each category is compared between players
 *   - Player who wins in 2 categories earns a point (there may be no winner)
 *
 * - After the points are calculated, a new battle starts (players pick cards again)
 * - If there are less than 10 cards in the deck, the game changes to state FINISHED
 */

public class GameShould {

    @Test
    public void be_open_when_created() {

        //check if the state of the game is OPEN
        Game game = new Game(new Deck());

        assertThat(game.getState(), is(Game.State.OPEN));
    }

    @Test
    public void allow_joining_when_open() {

        // a player can join an OPEN game
        //(for simplicity, a player is indicated by its username)

        //1.creating the necessary objects
        Game game = new Game(new Deck());

        //2.calling some methods
        game.join("john");

        //3. assert/check something you expect
        assertThat(game.getState(), is(Game.State.OPEN));
        assertThat(game.getPlayerName(), is(new HashSet<>(Arrays.asList("john"))));


    }

    @Test
    public void be_playing_when_2_players_join() {

        Game game = new Game(new Deck());

        game.join("john");
        game.join("mary");

        assertThat(game.getState(),is(Game.State.PLAYING));
        assertThat(game.getPlayerName(), is(new HashSet<>(Arrays.asList("john", "mary"))));

    }

    @Test(expected = JoiningNotAllowedException.class)
    public void not_allow_joining_if_not_open() {

        Game game = new Game(new Deck());

        game.join("john");
        game.join("mary");
        game.join("alex");

        /**
        try {
            game.join("alex");
            fail();

        } catch  (RuntimeException e){
            //jumps here if an exception was thrown
        }
         */
    }

    @Test
    public void allow_a_player_pick_a_card_when_playing() {

        Card card = new Card(3,2,5);
        Deck deck = new Deck();
        deck.add(card);
        Game game = new Game(deck);

        game.join("john");
        game.join("mary");
        Card pickedCard = game.pickCard("john");

        assertThat(card, is(pickedCard));

    }

    @Test (expected = PlayerNotInTheGameException.class)
    public void not_allow_picking_card_if_player_didnt_join() {

        Game game = new Game(new Deck());

        game.join("john");
        game.join("mary");
        game.pickCard("alex");

    }

    //After picking a card, a player must keep it or discard it,
    // before picking another one


    @Test(expected = CannotPick2CardsInARowException.class)
    public void not_allow_picking_2_cards_in_a_row() {

        Deck deck = new Deck();
        deck.add(new Card(3,2,5));
        deck.add(new Card(3,2,5));
        Game game = new Game(deck);

        game.join("susan");
        game.join("peter");

        game.pickCard("susan");
        game.pickCard("susan");

    }

    @Test
    public void allow_picking_if_previous_card_was_discarded() {

        Deck deck = new Deck();
        Card card1 = new Card(3,2,5);
        Card card2 = new Card(2,7,1);
        deck.add(card1);
        deck.add(card2);
        Game game = new Game(deck);

        game.join("susan");
        game.join("peter");

        Card pickedCard1 = game.pickCard("susan");
        game.discard("susan");
        Card pickedCard2 = game.pickCard("susan");

        assertThat(pickedCard1, is(card2));
        assertThat(pickedCard2, is(card1));
    }

    @Test (expected = NotPlayingException.class)
    public void not_allow_picking_card_if_state_is_not_playing() {

        Game game = new Game(new Deck());

        assertThat(game.getState(),is(Game.State.OPEN));

        game.join("mary");

        game.pickCard("mary");
    }


    @Test (expected = TooManyDiscardsException.class)
    public void not_allow_more_than_2_discards() {

        Deck deck = new Deck();
        deck.add(new Card(3,2,5));
        deck.add(new Card(3,2,5));
        deck.add(new Card(3,2,5));

        Game game = new Game(deck);
        game.join("susan");
        game.join("ben");

        game.pickCard("susan");
        game.discard("susan");
        game.pickCard("susan");
        game.discard("susan");
        game.pickCard("susan");
        game.discard("susan");
    }


    @Test (expected = DidNotPickCardException.class)
    public void not_allow_discard_without_picking() {

        Game game = new Game(new Deck());

        game.join("susan");
        game.join("peter");

        game.discard("susan");

    }

    @Test
    public void pick_and_not_discarding_the_card() {

        Deck deck = new Deck();
        Card card1 = new Card(3,2,5);
        Card card2 = new Card(2,7,1);
        deck.add(card1);
        deck.add(card2);
        Game game = new Game(deck);
    }
}
