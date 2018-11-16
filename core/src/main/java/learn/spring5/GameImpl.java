package learn.spring5;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
@Getter // lombok replaces all boilerplate code for getters accept numberGenerator
public class GameImpl implements Game {

    // == constants ==
    // lombok - private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    // == fields ==
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;

    private final int guessCount;

    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private int guess;

    // == constructors ==
    // removed constructor so as to use setter based dependency injection instead of constructor based dependency injection
    // setter based DI is accomplished by container calling setter methods on your beans after invoking a
    // no argument/default constructor
    // Constructor added in lecture 197 as Constructor based DI is recommened as best practice
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // == init ==
    @PostConstruct
    @Override
    public void reset() {
        //smallest =0;
        guess = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        smallest = numberGenerator.getMinNumber();
        number = numberGenerator.next();
        log.debug("the number is {} ", number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("in Game preDestroy() ");
    }

    // == public methods ==

//   Replaced by lombok @Override
//    public void setGuess(int guess) {
//        this.guess = guess;
//    }

    @Override
    public void check() {
        checkValidNumberRange();

        // adjust range
        if (validNumberRange){
            if(guess > number){
                biggest = guess -1;
            }
            if (guess < number) {
                smallest = guess +1;
            }
        }

        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == private methods ==
    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
