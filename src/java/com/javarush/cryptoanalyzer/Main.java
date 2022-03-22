package com.javarush.cryptoanalyzer;

import com.javarush.cryptoanalyzer.crypto.BruteDecoder;
import com.javarush.cryptoanalyzer.crypto.FileCoder;
import com.javarush.cryptoanalyzer.dialog.Dialog;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello! This is a small programme for file encoding or decoding by Caesar's cipher\n" +
                "Pay attention:your file will be replaced by the resulting one! ");
        String fileName = Dialog.askFileName();
        switch (Dialog.askOperation()) {
            case 1:
                new FileCoder(fileName, Dialog.askKey()).encodeFile();
                break;
            case 2:
                new FileCoder(fileName, Dialog.askKey()).decodeFile();
                break;
            case 3:
                try {
                    new BruteDecoder().crack(fileName);
                } catch (IOException e) {
                    System.out.println("IOException during cracking");
                    System.err.println("Caught an exception" + e);
                }
        }
    }

    // "C:\\Users\\user\\IdeaProjects\\javarush-cryptoanalyser\\src\\java\\com\\javarush\\cryptoanalyzer\\dialog\\test.txt";
}
