package tech.bts.cardgames.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.service.GameService;
import tech.bts.cardgames.service.GameUser;
import tech.bts.cardgames.util.HandlebarsUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/games")
public class GameWebController {

    private GameService gameService;
    private Handlebars handlebars;

    @Autowired
    public GameWebController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = GET)
    public String getAllGames() throws IOException {

        Template template = HandlebarsUtil.compile("game-list");

        Map<String,Object> values = new HashMap<>();
        values.put("games",gameService.getAllGames());

        return template.apply(values);
    }

    @RequestMapping(method = GET, path = "/{gameId}")
    public String getGameById(@PathVariable long gameId) throws IOException {

        Game game = gameService.getGameById(gameId);

        //create handlebars processor

        Template template = HandlebarsUtil.compile("game-detail");

        Map<String,Object> values = new HashMap<>();
        values.put("game",game);
        values.put("gameIsOpen", game.getState() == Game.State.OPEN);

        return template.apply(values);

        /*
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
        */
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
