package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        System.out.println("----------- Begin -------------");
        try {
            MyFile test = new MyFile("ciphers.txt");
            for (Cipher cipher: test.getCiphers()){
                cipher.decipher();
            }


            PrintWriter writer = new PrintWriter(test.getPath()+"result.txt", StandardCharsets.UTF_8);
            for (int i = 0; i< test.getCiphers().size() ; i++){
                Cipher cipher = test.getCiphers().get(i);
                if (cipher.isDeciphered()){
                    System.out.println(cipher.getDecryptedTxt());
                    writer.println(cipher.getDecryptedTxt());
                }else{
                    System.out.println("Cryptanalysis failed!");
                    writer.println("Cryptanalysis failed!");
                }
                if (i != test.getCiphers().size()-1){
                    System.out.println("#");
                    writer.println("#");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------------ END --------------");
    }


}
