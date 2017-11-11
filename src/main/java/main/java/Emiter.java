package main.java;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by Pedro on 10/11/2017.
 */
public class Emiter extends IOUtils{

    public static void process(String output) throws FileNotFoundException {

        Compresser1.processFile("inputfile","symbolinfo");
        StatisticsManager.generateOutputByFreq(output);

    }
}
