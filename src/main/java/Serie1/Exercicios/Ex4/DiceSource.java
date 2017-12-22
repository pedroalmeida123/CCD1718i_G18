package Serie1.Exercicios.Ex4;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Pedro on 10/11/2017.
 */
public class DiceSource extends IOUtils {


    public static void process(String outputStats,String out) throws FileNotFoundException {

        //Compresser1.processFile("diceinput",outputStats);
        sourceInfo("diceinput",outputStats,out);
        HuffmanBinaryTree.processSymbolInfo();
        System.out.println("Comprimento médio do código de Huffman -> " + HuffmanBinaryTree.getHuffmanAverageLength());


    }

    static HashMap<Short,Integer> map = new HashMap<>();
    static String info = "";
    static byte[] outbytes;
    public static void sourceInfo(String inputfile, String outputfile,String out){
        String textfile = readLineByLineJava8(inputfile);
        String[] values = textfile.split(" ");
        int size = values.length;
        short[] shorts;
        for (String s:values) {
            int inc;
            short val = Short.parseShort(s);
            if (map.containsKey(val)) {
                inc = map.get(val);
                map.put(val, ++inc);
            } else {
                map.put(val, 1);
            }
        }

        float entropy = 0;
        int totalSymbols=0;

        for (Map.Entry<Short,Integer> entry : map.entrySet()) {

            totalSymbols+=entry.getValue();

        }

        int count = 0;


        shorts = new short[map.size()];
        for (Map.Entry<Short,Integer> entry : map.entrySet()) {
            shorts[count]=entry.getKey();
            short key = entry.getKey();
            int value = entry.getValue();
            float prob = (float)value/(float)totalSymbols;
            info+=key + "-" + value + "-" + prob+" \n";
            entropy += prob*(Math.log(1/prob) / Math.log(2));
            count++;
        }

        System.out.println("the entrophy is -> "+entropy);
        outbytes = new byte[size*2];
        double[] distribution = new double[map.size()];

        int counter = 0;

        for (Map.Entry<Short,Integer> entry : map.entrySet()) {

            if(counter>0) {
                distribution[counter] = distribution[counter - 1] + (((double)entry.getValue()/(double)totalSymbols));
                counter++;
            }
            else distribution[counter++] = ((double)entry.getValue()/(double)totalSymbols);
        }
        String text = "";
        for (int i = 0; i < outbytes.length; i++) {
            counter = 0;
            double rand = Math.random();
            for (int j = 0; j < distribution.length; j++) {
                if(distribution[j]<=rand)
                    counter++;
                else break;
            }

            text+=shorts[counter]+ " ";
        }

        text = text.substring(0, text.length() - 1);
        outbytes = text.getBytes();
        try {
            StatisticsManager.generateOutputByFreq(outbytes,out);
            writeFile(info.getBytes(),outputfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


}
