package Serie1.Exercicios.Ex4;

import java.io.FileNotFoundException;

/**
 * Created by Pedro on 09/11/2017.
 */
public class Compresser1 extends IOUtils{

    public static void processFile(String input, String output) throws FileNotFoundException{


        StatisticsManager.generate(input,output,"output");
    }
}
