package main.java;

import Exercicios.Ex4.Compresser1;
import Exercicios.Ex4.DiceSource;
import Exercicios.Ex4.Emiter;
import Exercicios.Ex7.DeflaterCompression;
import Exercicios.Ex8.EmailCompression;

import java.io.*;

/**
 * Created by Pedro on 09/11/2017.
 */
public class App {


    public static void main(String[] args) throws FileNotFoundException {

        //Compresser1.processFile("inputfile","symbolinfo");
        //Emiter.process("output");
        DiceSource.process("symbolinfo", "output");
        //DeflaterCompression.process("inputfile");
        //EmailCompression.process();



    }


}
