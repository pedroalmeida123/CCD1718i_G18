package Serie2;

import Serie1.Exercicios.Ex4.IOUtils;
import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.GZIPException;
import com.jcraft.jzlib.JZlib;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Pedro on 12/12/2017.
 */
public class App extends IOUtils{

    public static long[][] matrixCompression;

    public static void main(String[] args) throws IOException {

        String[] files = {"alice29.txt","arj241a.exe","asyoulik.txt","cp.htm","cp.html","fields.c","grammar.lsp","kennedy.xls",
        "lcet10.txt","plrabn12.txt","ptt5","sum","xargs.1"};
        matrixCompression = new long[files.length][files.length];
        processDeflate(files);

    }

    public static void processDeflate(String[] arrayoffiles) throws IOException {

        for (int i = 0; i < arrayoffiles.length; i++) {
            File file = new File("src\\main\\resources\\" + "_sent_mail\\" + arrayoffiles[i]);
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            int start = 1;
            int thismany = (int) file.length();

            if (thismany > 32000) {
                start = thismany - 32000;
                thismany = 32000;
            }
            byte[] dict = new byte[thismany];

            raf.seek(start);
            raf.read(dict,0, thismany);


            for (int j = 0; j < arrayoffiles.length; j++) {
                byte[] compressed;
                Deflater deflate = null;
                try {
                    deflate = new Deflater(JZlib.Z_BEST_COMPRESSION);
                    deflate.setDictionary(dict,dict.length);
                    InputStream inputStream = new FileInputStream("src\\main\\resources\\"+"_sent_mail\\" + arrayoffiles[j]);
                    Scanner sc = new Scanner(inputStream, "UTF-8");
                    while (sc.hasNextLine()) {


                        byte[] buffer;
                        buffer = sc.nextLine().getBytes();
                        compressed = new byte[buffer.length];
                        int err;

                        deflate.setInput(buffer);
                        deflate.setOutput(compressed);

                        while (true) {
                            err = deflate.deflate(JZlib.Z_FINISH);
                            if (err == JZlib.Z_STREAM_END) break;
                        }

                        System.out.println("file" + i + " " + j);
                        System.out.println(Arrays.toString(compressed));
                        double out = deflate.getTotalOut() - dict.length;
                        matrixCompression[i][j] = (long) (deflate.getTotalIn() - out) - thismany;
                        // System.out.println(line);
                    }


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
                if(f!=i)
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
