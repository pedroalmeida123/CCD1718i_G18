package Serie2;

import Serie1.Exercicios.Ex4.IOUtils;
import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.GZIPException;
import com.jcraft.jzlib.JZlib;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Created by Pedro on 12/12/2017.
 */
public class App extends IOUtils{

    public static long[][] matrixCompression;

    public static void main(String[] args) throws IOException {

        String[] files = {"1"};
        matrixCompression = new long[files.length][files.length];
        processDeflate(files);

    }

    public static void processDeflate(String[] arrayoffiles) throws IOException {

        for (int i = 0; i < arrayoffiles.length; i++) {
            File file = new File("src\\main\\resources\\" + "_sent_mail\\" + arrayoffiles[i]);
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            int start = 0;
            int thismany = (int) file.length();

            if (thismany > 32000) {
                start = (int) file.length() - 32000;
                thismany = 32000;
            }
            byte[] dict = new byte[thismany];

            raf.seek(start);
            raf.read(dict, start, thismany);


            for (int j = 0; j < arrayoffiles.length; j++) {
                byte[] compressed = new byte[40000];
                String compr = new String(dict);
                String line = "";
                Deflater deflate = null;
                try {
                    deflate = new Deflater(JZlib.Z_BEST_COMPRESSION);
                    line = readLineByLineJava8("_sent_mail\\" + arrayoffiles[j]);
                    String val = compr.concat(line);

                    byte[] buffer = val.getBytes();
                    int err;

                    deflate.setInput(buffer);
                    deflate.setOutput(compressed);

                    while (true) {
                        err = deflate.deflate(JZlib.Z_FINISH);
                        if (err == JZlib.Z_STREAM_END) break;
                    }
                    compr = "";

                    System.out.println("file" + i + " " + j);
                    System.out.println(Arrays.toString(compressed));
                    double out = deflate.getTotalOut() - dict.length;
                    matrixCompression[i][j] = (long) (deflate.getTotalIn() - out);

                } catch (GZIPException e) {
                    e.printStackTrace();
                }

            }
        }
        int val=0;
        int bestDic=0;
        int dicID=0;

        int i,f;
        for(i=0;i<matrixCompression.length;i++){
            for(f=0;f<matrixCompression.length;f++){
                val+=matrixCompression[i][f];
            }
            if(val>bestDic){
                dicID=i;
                bestDic=val;
            }

            System.out.println("ID->"+arrayoffiles[i]+" reduction->"+val);
            val=0;
        }
        System.out.println();
        System.out.println("Best dictionary->"+arrayoffiles[dicID]);
    }

}
