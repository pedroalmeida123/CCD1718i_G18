package main.java;


import java.io.FileNotFoundException;


/**
 * Created by Pedro on 10/11/2017.
 */
public class DiceSource extends IOUtils {


    public static void process(String outputStats,String out) throws FileNotFoundException {

        Compresser1.processFile("diceinput",outputStats);
        StatisticsManager.generateOutputByFreq(out);
        HuffmanBinaryTree.processSymbolInfo();
        System.out.println("Comprimento médio do código de Huffman -> " + HuffmanBinaryTree.getHuffmanAverageLength());


    }
}
