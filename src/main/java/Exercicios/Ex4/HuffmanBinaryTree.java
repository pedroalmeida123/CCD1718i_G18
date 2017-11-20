package Exercicios.Ex4;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Pedro on 18/11/2017.
 */
public class HuffmanBinaryTree extends IOUtils{

    static ArrayList<Node> arrayList;
    public static void processSymbolInfo() {

        String text = readLineByLineJava8("symbolinfo");
        String[] lines = text.split(" ");
        double[] sorted = new double[lines.length];
        arrayList = new ArrayList<>();
        boolean direction = false; //false - left ; true - right;
        //sort array by probabilities
        for (int i = 0; i < lines.length; i++) {

            String[] columns = lines[i].split("-");
            sorted[i]=Double.parseDouble(columns[2]);

        }

        Arrays.sort(sorted);

        for (Double d:sorted) {
            arrayList.add(new Node(d.doubleValue(),null,null,null));
        }

        //build HuffmanTree
        for (int i = 1; i < arrayList.size(); i++) {
            Node nodeToConect = null;


            for (int j = i-1; j >= 0; j--) {
                if(arrayList.get(j).parent==null)
                    nodeToConect=arrayList.get(j);
                else{
                    nodeToConect=arrayList.get(j).parent;
                    break;
                }
            }

            if(i+1<arrayList.size()&&arrayList.get(i+1).probability<nodeToConect.probability) {
                nodeToConect = arrayList.get(i + 1);
                direction=true;
            }

            //connect node
            Node parent = new Node(arrayList.get(i).probability+nodeToConect.probability,
                    null,
                    direction?nodeToConect:arrayList.get(i)
                    ,direction?arrayList.get(i):nodeToConect
            );

            arrayList.get(i).parent=parent;
            nodeToConect.parent=parent;



        }


    }

    public static double getHuffmanAverageLength(){
        int[] numberofparents = new int[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            Node cur = arrayList.get(i);
            int count = 0;
            while(cur.parent!=null){
                count++;
                cur=cur.parent;
            }
            numberofparents[i]=count;
        }
        double length = 0;
        for (int i = 0; i < numberofparents.length; i++) {
            length+=numberofparents[i]*arrayList.get(i).probability;
        }
        return length;
    }
}
