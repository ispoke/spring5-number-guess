package learn.spring5.controller;

import learn.spring5.service.GameService;
import learn.spring5.util.AttributeNames;
import learn.spring5.util.GameMappings;
import learn.spring5.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GameController {

    // == fields ==
    private final GameService gameService;

    // == constructor ==
    @Autowired
    public GameController(GameService gameService){
       this.gameService = gameService;
    }

    // == request methods ==
    // method for displaying play page
    @GetMapping(GameMappings.PLAY)
    public String play(Model model){
        // populate model with attributes
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        log.info("model = {}", model);

        if(gameService.isGameOver()){
            log.info("game is over {} ", gameService.isGameOver());
            return ViewNames.GAME_OVER;
        }

        return ViewNames.PLAY; // return play view
    }

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam int guess){
        log.info("guess = {}", guess);
        gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.RESTART)
    public String restart(){
        log.info("restart() called ");
        gameService.reset();
        return GameMappings.REDIRECT_PLAY;
    }
}
