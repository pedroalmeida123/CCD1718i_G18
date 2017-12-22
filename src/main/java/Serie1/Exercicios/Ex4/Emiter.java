package Serie1.Exercicios.Ex4;

import java.io.FileNotFoundException;

/**
 * Created by Pedro on 10/11/2017.
 */
public class Emiter extends IOUtils {

    public static void process(String output) throws FileNotFoundException {

        Compresser1.processFile("inputfile","symbolinfo");

    }
}
