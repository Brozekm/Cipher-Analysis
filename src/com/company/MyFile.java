package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MyFile {

    private String path;
    private String name;
    private List<Cipher> ciphers;

    public MyFile(String path) throws FileNotFoundException {
        File file = new File(path);
        this.ciphers = new ArrayList<>();
        Scanner myReader = new Scanner(file);
        StringBuilder tmpCipher = new StringBuilder();

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (data.equals("#")){
                Cipher cipher = new Cipher(tmpCipher.toString().toLowerCase());
                this.ciphers.add(cipher);
                tmpCipher = new StringBuilder();
            }else{
                tmpCipher.append(data);
            }
        }
        if (tmpCipher.length() > 0){
            Cipher cipher = new Cipher(tmpCipher.toString().toLowerCase());
            this.ciphers.add(cipher);
        }
        myReader.close();
        dividePath(path);
    }

    private void dividePath(String path) {
        String[] parts = path.split("/");
        this.name = parts[parts.length - 1];
        StringBuilder tmpPath = new StringBuilder();
        if (parts.length > 1) {
            for (int i = 0; i < parts.length - 1; i++) {
                tmpPath.append(parts[i]);
                tmpPath.append("/");
            }
            this.path = tmpPath.toString();
        } else {
            this.path = "";
        }
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public List<Cipher> getCiphers() {
        return ciphers;
    }
}
