package Exercicios.Ex4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Pedro on 10/11/2017.
 */
public class IOUtils {

    /*method to read from input file the string it contains*/

    public static String readLineByLineJava8(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get("C:\\Isell\\CCD\\CCD1718i_G18\\CCD1718_G18\\src\\main\\resources\\"+filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s));
        }
        catch (IOException e)
        {
            System.out.println("problem reading file");
        }
        return contentBuilder.toString();
    }

    /*method to write in an output file the byte passed as parameter*/

    public static void writeFile(byte[] outbytes, String outfile) throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream("C:\\Isell\\CCD\\CCD1718i_G18\\CCD1718_G18\\src\\main\\resources\\"+ outfile);
        try {
            out.write(outbytes);
            out.close();
        } catch (IOException e) {
            System.out.println("problem writing to file");
        }
    }



}
