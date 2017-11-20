package Exercicios.Ex4;

import Exercicios.Ex4.Compresser1;
import Exercicios.Ex4.IOUtils;
import Exercicios.Ex4.StatisticsManager;

import java.io.FileNotFoundException;

/**
 * Created by Pedro on 10/11/2017.
 */
public class Emiter extends IOUtils {

    public static void process(String output) throws FileNotFoundException {

        Compresser1.processFile("inputfile","symbolinfo");

    }
}
