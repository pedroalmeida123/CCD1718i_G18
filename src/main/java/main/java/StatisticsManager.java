package main.java;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pedro on 10/11/2017.
 */
public class StatisticsManager extends IOUtils{

    static HashMap<Byte,Integer> map = new HashMap<>();
    static String info = "";
    static byte[] outbytes;
    public static void generate(String inputfile, String outputfile) throws FileNotFoundException {

        String textfile = readLineByLineJava8("C:\\Isell\\CCD\\CCD1718i_G18\\CCD1718_G18\\src\\main\\resources\\"+inputfile);
        byte[] bytes = textfile.getBytes();
        int size = bytes.length;

        for (byte b : bytes)
        {
            byte key = b; // chave do mapa
            int inc;
            if(map.containsKey(key)) {
                inc = map.get(key);
                map.put(key, ++inc);
            }
            else {
                map.put(key,1);
            }
        }

        float entropy = 0;
        int totalSymbols=0;

        for (Map.Entry<Byte,Integer> entry : map.entrySet()) {

            totalSymbols+=entry.getValue();

        }

        int count = 0;


        bytes = new byte[map.size()];
        for (Map.Entry<Byte,Integer> entry : map.entrySet()) {
            bytes[count]=entry.getKey();
            byte key = entry.getKey();
            int value = entry.getValue();
            float prob = (float)value/(float)totalSymbols;
            info+=key + " - " + value + " - " + prob+"\n";
            entropy += prob*(Math.log(1/prob) / Math.log(2));
            count++;
        }

        System.out.println("the entrophy is -> "+entropy);
        outbytes = new byte[size];
        double[] distribution = new double[map.size()];

        int counter = 0;

        for (Map.Entry<Byte,Integer> entry : map.entrySet()) {

            if(counter>0) {
                distribution[counter] = distribution[counter - 1] + (((double)entry.getValue()/(double)totalSymbols));
                counter++;
            }
            else distribution[counter++] = ((double)entry.getValue()/(double)totalSymbols);
        }



        for (int i = 0; i < outbytes.length; i++) {
            counter = 0;
            double rand = Math.random();
            for (int j = 0; j < distribution.length; j++) {
                if(distribution[j]<=rand)
                    counter++;
                else break;
            }
            outbytes[i] = bytes[counter];
        }

        writeFile(info.getBytes(),outputfile);
    }

    public static void generateOutputByFreq(String outfile) throws FileNotFoundException {
        writeFile(outbytes,outfile);
    }

}
