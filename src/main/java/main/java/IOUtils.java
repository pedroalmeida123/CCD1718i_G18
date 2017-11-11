package main.java;

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

    public static String readLineByLineJava8(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static void writeFile(byte[] outbytes, String outfile) throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream("C:\\Isell\\CCD\\CCDProjecto1\\src\\main\\resources\\"+ outfile);
        try {
            out.write(outbytes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
