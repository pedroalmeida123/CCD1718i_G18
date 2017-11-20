package Exercicios.Ex8;

import Exercicios.Ex4.IOUtils;
import Exercicios.Ex7.DeflaterCompression;
import com.jcraft.jzlib.JZlib;

/**
 * Created by Pedro on 19/11/2017.
 */
public class EmailCompression extends IOUtils {

public static void process(){
    String[] files = {"1","2","3","4","5","6","7","8","9"};
    colletiveCompression(files);
    singleCompression(files);

}

    public static void colletiveCompression(String[] arrayoffiles){
        String text = "";
        for (int i = 0; i < arrayoffiles.length; i++) {
            text+=readLineByLineJava8("_sent_mail\\"+arrayoffiles[i]);
        }
        System.out.println("Colletive compression");
        DeflaterCompression.compressByLevel(text, JZlib.Z_BEST_COMPRESSION,true);

    }

    public static void singleCompression(String[] arrayoffiles){
        String text = "";
        long initialBytes=0;
        long endBytes=0;
        System.out.println("Compress email individually");
        for (int i = 0; i < arrayoffiles.length; i++) {
            text=readLineByLineJava8("_sent_mail\\"+arrayoffiles[i]);
            DeflaterCompression.compressByLevel(text, JZlib.Z_BEST_COMPRESSION,false);
            initialBytes+=DeflaterCompression.initialBytes;
            endBytes+=DeflaterCompression.endBytes;
        }
        DeflaterCompression.printStats(initialBytes,endBytes);
    }

}
