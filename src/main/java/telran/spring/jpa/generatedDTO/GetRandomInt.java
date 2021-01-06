package telran.spring.jpa.generatedDTO;

import java.util.Random;

public class GetRandomInt {
    public static int randomInt(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }
}
