package learn.spring5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    private static final String MAIN_MESSAGE = "game.main.message"; // represents key in messages.properties file
    private static final String WIN = "game.win";
    private static final String LOSE = "game.lose";
    private static final String INVALID_RANGE = "game.invalid.range";
    private static final String FIRST_GUESS = "game.first.guess";
    private static final String HIGHER = "game.higher";
    private static final String LOWER = "game.lower";
    private static final String REMAINING = "game.remaining";
    private static final String REMAINING_SPECIAL = "game.remaining.special";

    // == private fields ==
    private final Game game;
    // defines getMessage() that can be used to get msg by key from the source
    // In this case source is messages.properties file. Spring loads this by default
    private final MessageSource messageSource;

    // Need locale for the messages. To do this Spring provides a class called LocaleContextHolder which is
    // used as a central holder for current locale in spring. This enables us to get or set default locale

    //== Constructor ==
    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // == init ==
    @PostConstruct
    public void init(){
        log.info("game is {} ",game);
    }

    // == public methods ==
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {
        String message = null;

        if(game.isGameWon()){
           return getMessage(WIN, game.getNumber());

        } else if (game.isGameLost()){
            return getMessage(LOSE, game.getNumber());

        }else if( !game.isValidNumberRange()){
            return getMessage(INVALID_RANGE);

        }else if (game.getRemainingGuesses() == game.getGuessCount()){
            return getMessage(FIRST_GUESS);

        }else if(game.getRemainingGuesses() == 1){  // special case for one guess left output message
            String direction = getMessage(LOWER);

            if (game.getGuess() < game.getNumber()) {
                direction = getMessage(HIGHER);
            }
            return getMessage(REMAINING_SPECIAL, direction, game.getRemainingGuesses());

        } else{
            String direction = getMessage(LOWER);

            if (game.getGuess() < game.getNumber()) {
                direction = getMessage(HIGHER);
            }
            return getMessage(REMAINING, direction, game.getRemainingGuesses());
        }

    }

    // == private methods ==

    // getMessage() will give us the message from message source
    // code argument represents property key e.g. game.main.message
    // "Object... args" ellipsis ... allows for a variable number of arguments
    // With LocaleContextHolder.getLocale(), the method will return the message in the specified locale
    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }




}
