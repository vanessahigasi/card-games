package tech.bts.cardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.bts.cardgames.controller.errors.GameNotExistException;
import tech.bts.cardgames.model.Card;
import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.service.GameService;
import tech.bts.cardgames.service.GameUser;

import java.util.Collection;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping(path = "/api/games")
public class GameApiController {

    private GameService gameService;

    @Autowired
    public GameApiController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = POST)
    public long createGame() {

        Game game = gameService.createGame();
        return game.getId();
    }

    @RequestMapping (method = GET)
    public Collection<Game> getAllGames() {

        return gameService.getAllGames();
    }

    @RequestMapping(method = GET, path = "/{gameId}")
    public Game getGameById(@PathVariable long gameId) {

        Game game = gameService.getGameById(gameId);

        if (game != null) {
            return game;
        } else {
            throw new GameNotExistException();
        }
    }

    @RequestMapping(method = PUT, path = "/{gameId}/join")
    public void joinGame(@RequestBody GameUser gameUser, @PathVariable long gameId) {

        gameUser.setGameId(gameId);
        gameService.joinGame(gameUser);
    }

    @RequestMapping(method = PUT, path = "/{gameId}/pick")
    public Card pickCard(@RequestBody GameUser gameUser, @PathVariable long gameId) {

        gameUser.setGameId(gameId);
        return gameService.pickCard(gameUser);
    }
}
