package main.java;

import java.io.FileNotFoundException;

/**
 * Created by Pedro on 10/11/2017.
 */
public class DiceSource extends IOUtils {


    public static void process(String outputfile) throws FileNotFoundException {

        Compresser1.processFile("diceinput",outputfile);
        StatisticsManager.generateOutputByFreq(outputfile);


    }
}
