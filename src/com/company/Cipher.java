package com.company;

public class Cipher {
    private final String[] BIGRAMS = {"th", "he", "in", "er", "an", "re", "nd", "on", "en", "at", "ou", "ed", "ha", "to", "or", "it", "is", "hi", "es", "ng"};
    private final String[] TRIGRAMS = {"the", "and", "ing", "her", "hat", "his", "tha", "ere", "for", "ent", "ion", "ter", "was", "you", "ith", "ver", "all", "wit", "thi", "tio"};
    private final String[] QUADRIGRAMS = {"that", "ther", "with", "tion", "here", "ould", "ight", "have", "hich", "whic", "this", "thin", "they", "atio", "ever", "from", "ough", "were", "hing", "ment"};


    private boolean deciphered = false;
    private String decryptedTxt;


    public Cipher(String decryptedTxt) {
        this.decryptedTxt = decryptedTxt;
    }

    public void decipher() {
        double POLYALPHABETIC = 0.038;
        double MONOALPHABETIC = 0.068;

        double ioc = calculateIOC(this.decryptedTxt);

        double mono = Math.abs(MONOALPHABETIC - ioc);
        double poly = Math.abs(POLYALPHABETIC - ioc);
        if (mono < poly) {
            //TODO Cesar and substitution
            //Cesar
            String cipher = this.decryptedTxt.replaceAll("\\s+", "");
            if (cipher.length() > 400){
                cipher = cipher.substring(0,400);
            }
            for (int i = 0; i <= 26; i++){
                if(cesarDecryption(cipher, i)){
                    break;
                }
            }
//            System.out.println("Cesar/substitution");
        } else {
            //TODO Vigenere
//            System.out.println("Vigenere");
        }

    }


    public boolean validateDecryption(String cipher) {
        cipher = cipher.replaceAll("\\s+", "");
        cipher = cipher.toLowerCase();
        double sizeBefore = cipher.length();
//        System.out.println("Cypher size: " + cipher.length());
        for (String s : this.QUADRIGRAMS) {
            cipher = cipher.replaceAll(s, "");
        }
//        System.out.println("Cypher size after Q: " + cipher.length());
        for (String s : this.TRIGRAMS) {
            cipher = cipher.replaceAll(s, "");
        }
//        System.out.println("Cypher size after T: " + cipher.length());
        for (String s : this.BIGRAMS) {
            cipher = cipher.replaceAll(s, "");
        }
//        System.out.println("Cypher size after B: " + cipher.length());

        double per = (cipher.length()/sizeBefore)*100;
        if (per < 65){
           return true;
        }
        return false;
    }

    public boolean cesarDecryption(String cipher, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++) {
            char ch = (char) (((int)cipher.charAt(i) + shift - 97) % 26 + 97);
            result.append(ch);
        }
        if (validateDecryption(result.toString())){
            this.decryptedTxt = result.toString();
            this.deciphered = true;
            return true;
        }
        return false;
    }

    /**
     * IOC => index of coincidence
     *
     * @param cipher
     * @return
     */
    private double calculateIOC(String cipher) {
        cipher = cipher.replaceAll("\\s+", "");
        cipher = cipher.toLowerCase();
        char c;
        double ioc = 0;
        int cipherLength = cipher.length();
        for (c = 'a'; c <= 'z'; c++) {
            int count = 0;
            for (int i = 0; i < cipher.length(); i++) {
                if (cipher.charAt(i) != ' ') {
                    if (cipher.charAt(i) == c) {
                        count++;
                    }
                }
            }

            ioc += (count / (double) cipherLength) * (count - 1) / (double) (cipherLength - 1);

        }
        return ioc;
    }

    public boolean isDeciphered() {
        return deciphered;
    }

    public String getDecryptedTxt() {
        return decryptedTxt;
    }

}
