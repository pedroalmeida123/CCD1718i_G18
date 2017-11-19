package Exercicios.Ex1;


import Exercicios.Ex2.newFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rúben Miranda on 10/11/2017.
 */
public class Exercicio1 {
    private static int count;

    //Exercicio 1
    private static Map<Byte, Double> calculateFreq(Map<Byte, Integer> mapInfo){
        Map<Byte, Double> mapFreq = new HashMap<>();
        for (Map.Entry<Byte, Integer> entry : mapInfo.entrySet()) {
            mapFreq.put(entry.getKey(),(entry.getValue().doubleValue())/count);
        }
        return mapFreq;
    }


    //Exercicio 1
    private static float calculateEntorp(Map<Byte, Double> mapFreq) {
        float ret= 0;
        for (Byte c: mapFreq.keySet()) {
            double p = mapFreq.get(c);
            ret += (p*(Math.log(1/p)/Math.log(2)));
        }
        return ret;
    }


    //Exercicio 1
    public static Map<Byte, Integer> readFile(String fname) throws IOException {

        File fileDir = new File(fname);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileDir), "UTF8"));
        String str;

        Map<Byte,Integer> mapInfo = new HashMap<>();

        byte[] b;
        count=0;
        while ((str = in.readLine()) != null) {
            b = str.getBytes(Charset.forName("UTF-8"));
            for(byte aux : b){
                count++;
                if(!mapInfo.containsKey(aux)){
                    mapInfo.put(aux,0);
                }
                mapInfo.replace(aux,mapInfo.get(aux)+1);
            }

        }
        in.close();
        return mapInfo;
    }



    public static void main(String[] args) throws IOException {
        //exercicio 1
        Map<Byte, Integer> mapInfo = readFile("book1.txt");
        Map<Byte, Double> mapFreq = calculateFreq(mapInfo);
        double entorp = calculateEntorp(mapFreq);
        System.out.print(entorp);

        //exercicio 2
        newFile.makeOut(626598,mapFreq);

    }



}
