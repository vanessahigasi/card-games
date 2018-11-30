package tech.bts.cardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgames.service.GameService;
import tech.bts.cardgames.service.JoinGame;

@RestController //tell Springboot this is a Controller
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/games")
    public void createGame() {

        gameService.createGame();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/games")
    public void joinGame(@RequestBody JoinGame joinGame) {

        gameService.joinGame(joinGame);

    }
}