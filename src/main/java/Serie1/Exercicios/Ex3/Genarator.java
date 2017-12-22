package Serie1.Exercicios.Ex3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Genarator {

    public static void main(String[] args){

        if(args.length < 1 ){
            System.out.println("add file to arguments");
            return;
        }

        File f = new File(args[0]);

        try {
            String str = Encode.getGetStringByPercentageMap(f, Charset.defaultCharset(), 100);
            System.out.println(str);
        } catch (IOException e) {
            System.out.println("cant find or read the file");
        }

    }

}
