package com.javarush.cryptoanalyzer.crypto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCoder {
    public static final String SUFFIX_FOR_DECODED_FILES = "_decoded";
    private String fileName;
    private int key;
    private StringCoder stringCoder;

    public FileCoder(String fileName, int key) {
        this.fileName = fileName;
        this.key = key;
        this.stringCoder = new StringCoder(key);
    }

    public void encodeFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(stringCoder.encodeString(line));
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String str : lines) {
                    writer.write(str + "\n");
                }
            }

        } catch (IOException e) {
            System.out.println("There are some problems with access to file " + fileName);
            System.err.println("Caught an exception" + e);
        }


    }

    public void decodeFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(stringCoder.decodeString(line));
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String str : lines) {
                    writer.write(str + "\n");
                }
            }

        } catch (IOException e) {
            System.out.println("There are some problems with access to file " + fileName);
            System.err.println("Caught an exception" + e);
        }

    }

    //this function will be helpful during cracking, not to change native file before decoding
    public void decodeFileToOtherFile() {
        String decodedFileName = getDecodedFilename(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(stringCoder.decodeString(line));
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(decodedFileName))) {
                for (String str : lines) {
                    writer.write(str + "\n");
                }
            }

        } catch (IOException e) {
            System.out.println("There are some problems with access to file " + fileName + "or" + decodedFileName);
            System.err.println("Caught an exception" + e);
        }
    }

    //we'll need the intermediate file name during cracking
    public static String getDecodedFilename(String fileName) {
        int index = fileName.lastIndexOf('.');
        return fileName.substring(0, index) + SUFFIX_FOR_DECODED_FILES + fileName.substring(index);
    }

}
