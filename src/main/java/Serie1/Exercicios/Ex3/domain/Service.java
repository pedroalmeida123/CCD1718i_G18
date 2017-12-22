package Serie1.Exercicios.Ex3.domain;

import java.util.Random;

public class Service {
    public static PercentageMap toPercentageMap(String str) {
        PercentageMap map = new PercentageMap();

        final String[] finalString = {null};

        str.chars().mapToObj(i-> "" + ((char)i)).forEach(s ->{
            if(finalString[0]!=null)
                map.addEntry(finalString[0], s);
        finalString[0]= s;
        });

        map.convertToPercentage();
        return map;
    }

    public static String generateStringByPercentageMap(String inicialChar, int charNumbers, PercentageMap map) {
        Random r = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; inicialChar!= null && i < charNumbers; i++) {
            sb.append(inicialChar);
            inicialChar = map.getEntry(inicialChar, r.nextDouble());
        }

        return sb.toString();
    }
}
