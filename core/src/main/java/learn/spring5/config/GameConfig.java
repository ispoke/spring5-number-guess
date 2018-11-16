package learn.spring5.config;

import learn.spring5.GuessCount;
import learn.spring5.MaxNumber;
import learn.spring5.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "learn.spring5")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // == fields ==
    // Value annotation is used for expression driven dependency injection
    @Value("${game.maxNumber:20}") // 20 is the default value if none is found in properties file
    // Spring converts string values in properties to int for the app
    private int maxNumber;

    @Value("${game.guessCount:5}") // 5 is the default value.
    private int guessCount;

    @Value("${game.minNumber:0}")  // default min = 0
    private int minNumber;

    // == bean methods ==
    @Bean
    @MaxNumber  // Defined in this package
    public int maxNumber(){
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }

    @Bean
    @MinNumber
    public int minNumber(){
        return minNumber;
    }

    // A Qualifier is an annotation applied to a bean
    // Create a qualifier annotation and use it with a bean definition method and autowiring
    // In that way the container will know what needs to be auto-wired so we don't
    // have to depend specifically on a bean name.
    // Can name beans however you like. The container will resolve the beans by looking at the qualifier
    // It's also known as fine-tuning annotation based auto-wiring
}
