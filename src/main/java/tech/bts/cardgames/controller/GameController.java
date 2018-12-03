package tech.bts.cardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.bts.cardgames.model.Card;
import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.service.GameService;
import tech.bts.cardgames.service.GameUser;

import java.util.List;

@RestController //tell Springboot this is a Controller
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/games")
    public long createGame() {

        Game game = gameService.createGame();
        return game.getId();
    }

    @RequestMapping (method = RequestMethod.GET, path = "/games")
    public List<Game> getAllGames() {

        return gameService.getAllGames();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/games/{gameId}/join")
    public void joinGame(@RequestBody GameUser gameUser, @PathVariable("gameId") long gameId) {

        gameUser.setGameId(gameId);
        gameService.joinGame(gameUser);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/games/{gameId}/pick")
    public Card pickCard(@RequestBody GameUser gameUser, @PathVariable("gameId") long gameId) {

        gameUser.setGameId(gameId);
        return gameService.pickCard(gameUser);
    }
}
