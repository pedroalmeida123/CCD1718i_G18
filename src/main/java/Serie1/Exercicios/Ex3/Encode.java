package Serie1.Exercicios.Ex3;


import Serie1.Exercicios.Ex3.domain.PercentageMap;
import Serie1.Exercicios.Ex3.domain.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Encode {

    public static String getGetStringByPercentageMap(File f, Charset charset, int charNumbers) throws IOException {
        String s = fileToString(f.getAbsolutePath(), charset);
        PercentageMap map = Service.toPercentageMap(s);
        return Service.generateStringByPercentageMap("a", charNumbers, map);
    }

    private static String fileToString(String path, Charset charset) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, charset);
    }




}
