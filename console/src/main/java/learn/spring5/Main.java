package learn.spring5;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j // replaces: private static final Logger log = LoggerFactory.getLogger(Main.class);

/*  To enable Spring Boot must enable Spring Boot Application annotation which will
    then enable component scanning and Auto configuration */
@SpringBootApplication
public class Main {

    /*  Create context (container)
        Fire Event: ConsoleNumberGuess class gets the event via EventListener and starts the game
        Close context
     */

    public static void main(String[] args) {
        log.info("Guess The Number Game");

        // create context (container
        /* With Spring Boot, we don't have to create context manually. SprBt does that automatically
        *  Instead, use the run() method from Spring application class*/

        /*  ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(GameConfig.class);  //replaces ClassPathXmlApplicationContext(CONFIG_LOCATION);

            // close context (container)
            context.close();
        */

        // run Spring Boot application
        SpringApplication.run(Main.class, args);

        /* game bean is at a higher level than main() which is in console.
           This will lead to an error at runtime of description
           Parameter 0 of constructor in learn.spring5.console.
           ConsoleNumberGuess required a bean of type 'learn.spring5.Game' that could not be found.
           Could add componentScan annotations to scan different pkgs.
           In this case, can move  Main class into root package learn.spring5 and leave Spr Boot to do rest
         */

    }
}
