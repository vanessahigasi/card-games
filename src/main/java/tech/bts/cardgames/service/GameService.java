package tech.bts.cardgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.bts.cardgames.model.Card;
import tech.bts.cardgames.model.Deck;
import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.repository.GameRepository;

import java.util.List;

@Service
public class GameService {

    private GameRepository gameRepo;

    @Autowired
    public GameService(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/games")
    public Game createGame() {

        //create a game
        Deck deck = new Deck();
        deck.generate();
        deck.shuffle();
        Game game = new Game(deck);

        gameRepo.create(game);

        return game;
    }

    public void joinGame(GameUser gameUser) {

        Game game= gameRepo.getById(gameUser.getGameId());
        game.join(gameUser.getUsername());

    }

    public Card pickCard(GameUser gameUser) {

        Game game= gameRepo.getById(gameUser.getGameId());
        return game.pickCard(gameUser.getUsername());

    }

    public List<Game> getAllGames() {
        return gameRepo.getAll();
    }
}
