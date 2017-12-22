package Serie1.Exercicios.Ex3.domain;

import java.util.HashMap;

public class PercentageMap {
    private boolean inPercatages = false;
    private HashMap<String, HashMap<String, Double>> map;

    public PercentageMap(){
        map = new HashMap<String, HashMap<String, Double>>();

    }

    public boolean isInPercentage(){
        return inPercatages;
    }

    public void addEntry(String currentChar, String nextChar){
        if(isInPercentage())return;

        HashMap<String, Double> charMap = map.computeIfAbsent(currentChar, k -> new HashMap<>());

        Double d = charMap.get(nextChar);

        if(d==null)
            charMap.put(nextChar, 1d);
        else
            charMap.put(nextChar, ++d);

    }

    public String getEntry(String current, double randomNumberGenerated){
        if(!isInPercentage())convertToPercentage();
        if(randomNumberGenerated>1)randomNumberGenerated=1;

        if(!map.containsKey(current))return null;

        HashMap<String, Double> charMap = map.get(current);

        final String[] finalString = {null};
        final double[] finalPercentage = {randomNumberGenerated};

        charMap.forEach((s, d) -> { //todo(force to run by all entries, not very effective)
            if(finalString[0]!=null)return;
            finalPercentage[0]-=d;
            if(finalPercentage[0]<=0){
                finalString[0] = s;
            }
        });

        return finalString[0];
    }

    public void convertToPercentage(){
        inPercatages = true;

        map.forEach( (k, v) ->
                {
                    final double[] finalint = {0};
                    v.forEach((s, d) -> finalint[0]+=d);
                    v.forEach((s, d) -> v.put(s,d/=finalint[0]));
                }
        );
    }

}
