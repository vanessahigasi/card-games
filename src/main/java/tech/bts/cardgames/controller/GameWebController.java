package tech.bts.cardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.service.GameService;
import tech.bts.cardgames.service.GameUser;

import java.util.Collection;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/games")
public class GameWebController {

    private GameService gameService;

    @Autowired
    public GameWebController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = GET)
    public String getAllGames() {

        return buildGameList();
    }

    @RequestMapping(method = GET, path = "/{gameId}")
    public String getGameById(@PathVariable long gameId) {

        Game game = gameService.getGameById(gameId);

        String result = "<a href=\"/games\">Go back to the games</a>";

        result +=
                "<h1>Game " + game.getId() + "</h1>" +
                "<p>State " + game.getState() + "</p>" +
                "<p>Player " + game.getPlayerName() + "</p>";

        if(game.getState() == Game.State.OPEN) {
            result += "<a href=\"/games/"  +
                    + game.getId()   +  "/join/" + game.getId()+ "\">Join the game</a>";

        }

        return result;

    }


    @RequestMapping(method = GET, path = "/create")
    public  String createGame() {

        gameService.createGame();

        return buildGameList();

    }

    @RequestMapping(method = GET, path = "/join/{gameId}")
    public void joinGame(@PathVariable long gameId) {

       gameService.joinGame(new GameUser(gameId,"Juan"));

    }

    private String buildGameList() {

        String result = "<h1>List of games</h1>";

        result += "<p><a href=\"/games/create\">Create game</a></p>";

        for (Game game : gameService.getAllGames()) {

            result += "<ul><a target=\"_blank\" href=\"/games/"  +
                      + game.getId()   +  "\">game " + game.getId() + "</a> is " +
                    game.getState() +  "</ul>";
        }

        return result;




    }

}
