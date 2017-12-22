package Serie1.Exercicios.Ex5;

import static Serie1.Exercicios.Ex5.Codificador.codif;
import static Serie1.Exercicios.Ex5.Descodificador.descod;

/**
 * Created by Rúben Miranda on 20/11/2017.
 */
public class Main {
    public static void main(String[] args){
        codif("./files/test.txt");
        descod("./files/codif.txt");
    }


}
