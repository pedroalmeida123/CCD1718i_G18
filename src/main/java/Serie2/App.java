package Serie2;

import Serie1.Exercicios.Ex4.IOUtils;
import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.GZIPException;
import com.jcraft.jzlib.JZlib;

/**
 * Created by Pedro on 12/12/2017.
 */
public class App extends IOUtils{

    public static long[][] matrixCompression;

    public static void main(String[] args) {

        String[] files = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        matrixCompression = new long[files.length][files.length];

        processDeflate(files);


    }

    public static void processDeflate(String[] arrayoffiles){

        String dicionary;
        for (int i = 0; i < arrayoffiles.length; i++) {

            dicionary = readLineByLineJava8("_sent_mail\\"+arrayoffiles[i]);

            for (int j = 0; j<arrayoffiles.length;j++){

                String file = readLineByLineJava8("_sent_mail\\"+arrayoffiles[j]);
                Deflater deflate=null;
                int comprLen = 40000;
                byte[] compressed = new byte[comprLen];
                byte[] bytes = file.getBytes();
                int err;

                try {
                    deflate = new Deflater(JZlib.Z_BEST_COMPRESSION);
                    deflate.setDictionary(dicionary.getBytes(),dicionary.getBytes().length);
                    deflate.setInput(bytes);
                    deflate.setOutput(compressed);

                    while(true) {
                        err = deflate.deflate(JZlib.Z_FINISH);
                        if (err == JZlib.Z_STREAM_END) break;
                    }
                    System.out.println("file" + i + " " + j);
                    double out = deflate.getTotalOut();
                    matrixCompression[i][j] = (long) ((out/deflate.getTotalIn())*100);


                } catch (GZIPException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("hello");
    }

}
