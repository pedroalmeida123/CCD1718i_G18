package Serie1.Exercicios.Ex7;

import Serie1.Exercicios.Ex4.IOUtils;
import com.jcraft.jzlib.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by Pedro on 18/11/2017.
 */
public class DeflaterCompression extends IOUtils {

    static HashMap<Integer,String> map = new HashMap<>();
    public static long initialBytes;
    public static long endBytes;
    static{
        map.put(JZlib.Z_DEFAULT_COMPRESSION,"Default Compression");
        map.put(JZlib.Z_NO_COMPRESSION,"No Compression");
        map.put(JZlib.Z_BEST_COMPRESSION,"Best Compression");
        map.put(JZlib.Z_BEST_SPEED,"Best Speed Compression");
    }

    public static void process(String inputfile){
        String text = readLineByLineJava8(inputfile);
        compressWithAllLevels(text);
    }

    public static void compressWithAllLevels(String text) {
        compressByLevel(text,JZlib.Z_NO_COMPRESSION,true);
        compressByLevel(text,JZlib.Z_DEFAULT_COMPRESSION,true);
        compressByLevel(text,JZlib.Z_BEST_COMPRESSION,true);
        compressByLevel(text,JZlib.Z_BEST_SPEED,true);
    }

    public static void compressByLevel(String text,int level,boolean toprint) {
        int err;
        byte[] bytes=text.getBytes();

        int comprLen = 40000;
        int uncomprLen = comprLen;
        byte[] compressed = new byte[comprLen];
        byte[] uncompresses = new byte[uncomprLen];

        Deflater deflater = null;
        try {
            deflater = new Deflater(level);
        } catch (GZIPException e) {
            e.printStackTrace();
        }


        deflater.setInput(bytes);
        deflater.setOutput(compressed);

        while (true) {

            err = deflater.deflate(JZlib.Z_FINISH);
            if (err == JZlib.Z_STREAM_END) break;

        }

        deflater.end();
        initialBytes = deflater.getTotalIn();
        while (!deflater.finished()){}
        endBytes = deflater.getTotalOut();

        if(toprint)System.out.println("For compression level:" + map.get(level));
        if(toprint)printStats(initialBytes,endBytes);


        //decompress for test purpose
        Inflater inflater = new Inflater();
        inflater.setInput(compressed);
        inflater.setOutput(uncompresses);
        inflater.init();
        err = inflater.inflate(JZlib.Z_NO_FLUSH);
        inflater.end();
        if(toprint)System.out.println("/***************************/");

        try {
            writeFile(uncompresses, "output");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void printStats(long original,long compressed){
        double removed = ((double)(original-compressed)/original)*100;
        double result = ((double)compressed/original)*100;
        double compressRatio = ((double)original/compressed);
        double compressRate = ((double)compressed/original);
        System.out.println("% removida -> " + removed + " %");
        System.out.println("% final -> "+ result + " %");
        System.out.println("Razão de Compressão -> " + compressRatio );
        System.out.println("Taxa de Compressão -> " + compressRate);
    }



}
