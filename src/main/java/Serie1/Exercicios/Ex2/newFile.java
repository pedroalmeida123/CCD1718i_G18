package Serie1.Exercicios.Ex2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * Created by Rúben Miranda on 10/11/2017.
 */
public class newFile {

    public static void makeOut(int size, Map<Byte, Double> info){
        byte[] ret = new byte[size];
        Random r = new Random();
        double random;
        for(int i = 1; i<=size;i++){
            random = r.nextDouble();
            double count = 0;
            for(Map.Entry<Byte,Double> aux : info.entrySet()){
                count+=aux.getValue();
                if(count>=random){
                    ret[i-1]=aux.getKey();
                    break;
                }
            }
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream("textout.txt");
            fOut.write(ret);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
