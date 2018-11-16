package learn.spring5;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

// Every class annotated with the @Component will be managed by the container
@Component
public class NumberGeneratorImpl implements NumberGenerator{

    // == fields ==
    private final Random random = new Random();

    @Getter
    private final int maxNumber;
    @Getter
    private final int minNumber;


    // == constructor based dependency injection ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == public methods
    @Override
    public int next() {
        // ex: min=5 max=20 : max-min=15 => range=0--> 15. + 5 => range= 5-->20
        return random.nextInt(maxNumber-minNumber) + minNumber;
    }

    // == Replaced by lombok @Getter
//    @Override
//    public int getMaxNumber() {
//        return maxNumber;
//    }
//
//    @Override
//    public int getMinNumber() {
//        return minNumber;
//    }
}
