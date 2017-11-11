package main.java;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pedro on 10/11/2017.
 */
public class DiceSource extends IOUtils {

    static int alphabetSize = 15;
    static double entrophy;
    static HashMap<String,Integer> map = new HashMap<>();

    public static void process(String outputfile){

        String input = readLineByLineJava8("C:\\Isell\\CCD\\CCDProjecto1\\src\\main\\resources\\dicestatistics");
        String[] values = input.split(" ");

        for (String b : values)
        {
            String key = b; // chave do mapa
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

        for (Map.Entry<String,Integer> entry : map.entrySet()) {

            totalSymbols+=entry.getValue();

        }

        int count = 0;

        String info="";
        byte[][] bytes = new byte[map.size()][4];
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            byte[] aux = new byte[4];
            aux = entry.getKey().getBytes();
            for (int i = 0; i < 4; i++) {
                bytes[count][i]=aux[i];
            }
            String key = entry.getKey();
            int value = entry.getValue();
            float prob = (float)value/(float)totalSymbols;
            info+=key + " - " + value + " - " + prob+"\n";
            entropy += prob*(Math.log(1/prob) / Math.log(2));
            count++;
        }

        System.out.println("the entrophy is -> "+entropy);
        byte[][] outbytes = new byte[map.size()][4];
        double[] distribution = new double[map.size()];

        int counter = 0;

        for (Map.Entry<String,Integer> entry : map.entrySet()) {

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

            for (int j = 0; j < 4; j++) {
                outbytes[i][j] = bytes[counter][j];
            }

        }

        try {
            writeFile(info.getBytes(),outputfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
