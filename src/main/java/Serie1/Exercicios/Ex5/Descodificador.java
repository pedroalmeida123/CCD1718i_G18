package Serie1.Exercicios.Ex5;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Rúben Miranda on 20/11/2017.
 */
public class Descodificador {

    public static void descod(String fileName){
        Map<Byte, Integer> mapInfo = null;
        BufferedReader br = null;
        FileReader fr = null;
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;
            ArrayList<Byte> result=new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                result = seeWhatBytes(sCurrentLine);
            }

            fw = new FileWriter("./files/descod.txt");
            bw = new BufferedWriter(fw);

            BufferedWriter finalBw = bw;

            result.forEach(m -> {
                try {
                    finalBw.write(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });



        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

    private static ArrayList<Byte> seeWhatBytes(String sCurrentLine) {
        ArrayList<Byte> ret=new ArrayList<>();
        String seq = "";
        for(char aux : sCurrentLine.toCharArray()){
            seq+=aux;
            if(Codificador.mapDomKeyBits.containsKey(seq)){
                ret.add(Codificador.mapDomKeyBits.get(seq).inByt);
                seq="";
            }
        }
        return ret;
    }


}
