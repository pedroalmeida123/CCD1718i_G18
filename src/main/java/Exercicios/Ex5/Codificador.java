package Exercicios.Ex5;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static Exercicios.Ex1.Exercicio1.*;
import static java.lang.Math.pow;

/**
 * Created by Rúben Miranda on 20/11/2017.
 */
public class Codificador {
    static Map<String,Domain> mapDomKeyBits;
    static Map<Byte,Domain> mapDomKeyByte;


    public static void  codif(String fileName){
        Map<Byte, Integer> mapInfo = null;
        try {
            mapInfo = readFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Byte, Double> mapFreq = calculateFreq(mapInfo);
        double entorp = calculateEntorp(mapFreq);
        System.out.print(entorp);
        mapCode(mapFreq);

        creatFile(fileName,"./files/codif.txt");



    }

    private static void creatFile(String rFile, String fname) {
        BufferedWriter bw=null;
        BufferedReader in = null;
        FileWriter fw=null;
        try {
            File fileDir = new File(rFile);
            in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF8"));
            String str;

            String wrt = "";

            byte[] b;
            while ((str = in.readLine()) != null) {
                b = str.getBytes(Charset.forName("UTF-8"));
                for(byte aux : b){
                    wrt+= mapDomKeyByte.get(aux).cod;
                }
            }
            in.close();

            fw = new FileWriter(fname);
            bw = new BufferedWriter(fw);
            bw.write(wrt);





        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }


    }

    private static void mapCode(Map<Byte, Double> mapFreq) {
        mapDomKeyBits = new HashMap<>();
        mapDomKeyByte = new HashMap<>();
        Double count = Double.valueOf(0);
        Domain curr;
        for(Map.Entry<Byte,Double> aux:mapFreq.entrySet()){
            curr = new Domain();
            curr.rangeMin=count;
            count+=aux.getValue();
            curr.rangeMax=count;
            curr.prob=aux.getValue();
            curr.cod= code(curr.rangeMax,aux.getValue());
            curr.inByt = aux.getKey();
            mapDomKeyBits.put(code(curr.rangeMax,aux.getValue()),curr);
            mapDomKeyByte.put(aux.getKey(),curr);
        }
    }

    private static String code( Double rangeMax, Double prob) {
        double med = rangeMax-(prob/2);
        int nBits = (int)Math.ceil((Math.log(1/prob)/Math.log(2))+1);
        String ret="";
        double val;
        for(int i = 1; i<=nBits; i++){
            val=pow(2,(0-i));
            if(val<med){
                med-=val;
                ret+="1";
            }
            else{ret+="0";}
        }
        return ret;
    }


}
