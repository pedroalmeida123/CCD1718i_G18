package main.java;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pedro on 10/11/2017.
 */
public class DiceSource extends IOUtils {


    public static void process(String outputfile){

        try {
            StatisticsManager.generate("dicestatistics",outputfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
