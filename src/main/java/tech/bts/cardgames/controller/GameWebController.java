package tech.bts.cardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.service.GameService;
import tech.bts.cardgames.service.GameUser;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        String result = "<h1>List of games</h1>\n";

        result += "<p><a href=\"/games/create\">Create game</a></p>\n";

        result += "<ol>\n";

        for (Game game : gameService.getAllGames()) {

            result += "<li><a target=\"_blank\" href=\"/games/"  +
                      + game.getId()   +  "\">game " + game.getId() + "</a> is " +
                    game.getState() +  "</li>\n";
        }

        result += "</ol>\n";

        return result;

    }

    @RequestMapping(method = GET, path = "/{gameId}")
    public String getGameById(@PathVariable long gameId) {

        Game game = gameService.getGameById(gameId);

        String result = "";

        result +=
                "<a href=\"/games\">Go back to game list</a>" +
                "<h1>Game " + game.getId() + "</h1>" +
                "<p>State " + game.getState() + "</p>" +
                "<p>Player " + game.getPlayerName() + "</p>";

        if(game.getState() == Game.State.OPEN) {
            result += "<a href=\"/games/" + game.getId() + "/join\">Join the game</a>";
        }

        return result;

    }


    @RequestMapping(method = GET, path = "/create")
    public  void createGame(HttpServletResponse response) throws IOException {

        gameService.createGame();

        response.sendRedirect("/games");

    }

    @RequestMapping(method = GET, path = "/{gameId}/join")
    public void joinGame(HttpServletResponse response, @PathVariable long gameId) throws IOException {

       gameService.joinGame(new GameUser(gameId,"Juan"));

       response.sendRedirect("/games/" + gameId);

    }

}
