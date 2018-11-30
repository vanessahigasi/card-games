package tech.bts.cardgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.bts.cardgames.model.Deck;
import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.repository.GameRepository;

@Service
public class GameService {

    private GameRepository gameRepo;

    @Autowired
    public GameService(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/games")
    public void createGame() {

        //create a game
        Deck deck = new Deck();
        deck.generate();
        deck.shuffle();
        Game game = new Game(deck);

        gameRepo.create(game);
    }

    public void joinGame(JoinGame joinGame) {

        Game game= gameRepo.getById(joinGame.getGameId());
        game.join(joinGame.getUsername());

    }
}
