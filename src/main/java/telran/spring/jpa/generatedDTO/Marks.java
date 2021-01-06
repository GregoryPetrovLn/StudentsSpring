package telran.spring.jpa.generatedDTO;

import java.util.Random;
import static telran.spring.jpa.generatedDTO.GetRandomInt.*;

public class Marks {
    public static int getRandomMark(){
        return randomInt(60,100);
    }


}
