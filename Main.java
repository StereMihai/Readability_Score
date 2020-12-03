package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static int numberOfSentences(String text){
        int count = 0;
        String[] words = text.split("\\s+");
        int x = text.length();
        for (int i=0; i<text.length(); i++){
            if (String.valueOf(text.charAt(i)).matches("!") ||
                    String.valueOf(text.charAt(i)).matches("\\?") ||
                    String.valueOf(text.charAt(i)).matches("\\.") ) {
                count++;
            }
        }
        String s = String.valueOf(text.charAt(x - 1));
        if(s.matches("\\w")) {
            count++;
        }
        return count;
    }

    public static int numberOfWords (String text) {
        int noOfWords = 0;
        String[] words = text.split("\\s+");
        noOfWords = words.length;
        return noOfWords;
    }


    public static int numberOfCharacters (String text) {
        int noOfCharacters = 0;
        for(int i = 0; i< text.length(); i++) {
            if(text.charAt(i) != ' ') {
                noOfCharacters++;
            }
        }
        return noOfCharacters;
    }

    public static boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||c == 'y') {
            return true;
        } else {
            return false;
        }
    }

    public static int numberOfSyllables (String text) {

        int x = 0;
        int syllables =0;

        String[] words = text.split("\\s+");
        for (int i=0; i<words.length; i++) {

            String faraE = words[i];

            if (faraE.charAt(faraE.length() - 1) == 'e') {
                words[i] = faraE.substring(0, faraE.length() - 1);
            }
        }

        for (int i=0; i<words.length; i++) {

            String faraE = words[i];

            for (int m = 0; m<faraE.length(); m++){
                if (isVowel(faraE.charAt(m))){
                    syllables++;
                }
            }

            for (int j = 0; j < faraE.length() - 1; j++) {
                if (isVowel(faraE.charAt(j)) && isVowel(faraE.charAt(j + 1))) {
                    syllables--;
                }
            }


            for (int k = 0; k < faraE.length(); k++) {
                if (!isVowel(faraE.charAt(k))) {
                    x++;
                    if ( (x == faraE.length()) ) {
                        syllables++;
                    }
                }
            }
            x = 0;

        }

        return syllables;
    }

    public static int numberOfPolySyllables (String text) {

        int x = 0;
        int polysyllables =0 ;
        int count = 0;

        String[] words = text.split("\\s+");
        for (int i=0; i<words.length; i++) {

            String faraE = words[i];

            if (faraE.charAt(faraE.length() - 1) == 'e') {
                words[i] = faraE.substring(0, faraE.length() - 1);
            }
        }

        for (int i=0; i<words.length; i++) {

            String faraE = words[i];

            for (int m = 0; m<faraE.length(); m++){
                if (isVowel(faraE.charAt(m))){
                    count++;
                }
            }

            for (int j = 0; j < faraE.length()-1; j++) {
                if (isVowel(faraE.charAt(j)) && isVowel(faraE.charAt(j + 1))) {
                    count--;
                }
            }


            for (int k = 0; k < faraE.length(); k++) {
                if (!isVowel(faraE.charAt(k))) {
                    x++;
                    if ( (x == faraE.length()) ) {
                        count++;
                    }
                }

            }
            x = 0;
            if (count > 2) {
                polysyllables++;
            }
            count = 0;
        }

        return polysyllables;
    }

    public static double automatedReadabilityIndex(int characters, int words, int sentences){
        double score = 0.0;
        double x = (double) characters / words;
        double y = (double) words / sentences;
        score = (4.71 * x + 0.5 * y) - 21.43;
        return score;
    }
    public static double fleschKincaid(int words, int sentences, int syllables){
        double score = 0.0;
        double x = (double) words / sentences;
        double y = (double) syllables / words;
        score = (0.39 * x) + (11.8*y) - 15.59;
        return score;
    }
    public static double simpleMeasureOfGobbledygook(int polysyllable, int sentences){
        double score = 0.0;
        double x = (double) 30 / sentences;
        double y = (double) polysyllable * x;
        double z =  Math.sqrt(y);
        score = (1.043 * z ) + 3.1291;
        return score;
    }

    public static double colemanLiauIndex(int characters, int sentences, int words) {
        double score = 0.0;
        double L = (double) characters / words * 100;
        double S = (double) sentences / words * 100;
        score = (0.0588 * L) - (0.296 * S) - 15.8;
        return score;
    }

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private static int averageAge(double score) {
        int x = 0;
        int y = (int) score;
        if (score - y <0.5) {
            x = (int) Math.round(score);
        } else {
            x = (int) Math.ceil(score);
        }
        int output = 0;
        switch (x) {
            case 1: output = 6;
                break;
            case 2: output = 7;
                break;
            case 3: output =  9;
                break;
            case 4: output = 10;
                break;
            case 5: output = 11 ;
                break;
            case 6: output = 12;
                break;
            case 7: output = 13;
                break;
            case 8: output = 14;
                break;
            case 9: output = 15;
                break;
            case 10: output = 16;
                break;
            case 11: output = 17;
                break;
            case 12: output = 18;
                break;
            case 13: output = 24;
                break;
            case 14: output = 25;
                break;
        }
        return output;

    }

    private static String checkingText(double score) {
        int x = 0;
        int y = (int) score;
        if (score - y <0.5) {
            x = (int) Math.round(score);
        } else {
            x = (int) Math.ceil(score);
        }
        String output = null;
        switch (x) {
            case 1: output = "about 6 year olds";
            break;
            case 2: output = "about 7 year olds";
            break;
            case 3: output = "about 9 year olds";
            break;
            case 4: output = "about 10 year olds";
            break;
            case 5: output = "about 11 year olds";
            break;
            case 6: output = "about 12 year olds";
            break;
            case 7: output = "about 13 year olds";
            break;
            case 8: output = "about 14 year olds";
            break;
            case 9: output = "about 15 year olds";
            break;
            case 10: output = "about 16 year olds";
            break;
            case 11: output = "about 17 year olds";
            break;
            case 12: output = "about 18 year olds";
            break;
            case 13: output = "about 24 year olds";
            break;
            case 14: output = "about 24+ year olds";
            break;
        }
        return output;

    }
    public static void main(String[] args) throws FileNotFoundException {

        String pathToFile = "D://Java//Readability Score//Readability Score//in.txt";
        File file = new File(pathToFile);
        Scanner scanner = new Scanner(file);
        String text = null;
        while (scanner.hasNext()){
            text = scanner.nextLine();
        }
        scanner.close();

//        Scanner scanner = new Scanner(System.in);
//        String text = scanner.nextLine();
        int sentences = numberOfSentences(text);
        int words = numberOfWords(text);
        int characters = numberOfCharacters(text);
        int syllables = numberOfSyllables(text);
        int polysyllable = numberOfPolySyllables(text);

        double ARI = automatedReadabilityIndex(characters, words, sentences);
        double FK = fleschKincaid(words, sentences, syllables);
        double SMOG = simpleMeasureOfGobbledygook(polysyllable,sentences);
        double CL = colemanLiauIndex(characters, sentences, words);

        int first = averageAge(ARI);
        int second =averageAge(FK);
        int third = averageAge(SMOG);
        int forth = averageAge(CL);
        double average = (double)(first + second + third + forth)/4;
        char x = 8211;
        System.out.println("Text is:");
        System.out.println(text);
        System.out.println();
        System.out.println("Words: " + words);
        System.out.println("Sentences: " +sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllable);
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String option = scanner1.next();
//        scanner1.close();
        switch(option){
            case "ARI" :
                System.out.println("Automated Readability Index: " + df2.format(ARI) + " ("+checkingText(ARI)+").");
                break;
            case "FK":
                System.out.println("Flesch" + x+ "Kincaid readability tests: " + df2.format(FK) + " ("+checkingText(FK)+").");
                break;
            case "SMOG":
                System.out.println("Simple Measure of Gobbledygook: " + df2.format(SMOG) + " ("+checkingText(SMOG)+").");
                break;
            case "CL":
                System.out.println("Coleman"+x+"Liau index: " + df2.format(CL) + " ("+checkingText(CL)+").");
                break;
            case "all" :
                System.out.println("Automated Readability Index: " + df2.format(ARI) + " ("+checkingText(ARI)+")");
                System.out.println("Flesch" + x+"Kincaid readability tests: " + df2.format(FK) + " ("+checkingText(FK)+")");
                System.out.println("Simple Measure of Gobbledygook: " + df2.format(SMOG) + " ("+checkingText(SMOG)+")");
                System.out.println("Coleman" + x + "Liau index: " + df2.format(CL) + " ("+checkingText(CL)+")");
                break;
        }

        System.out.println("This text should be understood in average by " + df2.format(average) + " years olds");


    }
}
