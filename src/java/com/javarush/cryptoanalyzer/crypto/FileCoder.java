package com.javarush.cryptoanalyzer.crypto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCoder {
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
            e.printStackTrace();
            //Sorry, i remember of doing that way is bad practice
            //the other way, we are working in console so we would see the stacktrace if the problem occurs
            // i'll try to fix logging in next iteration
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
            e.printStackTrace();
        }

    }

    //this function will be helpfull during cracking, not to change native file bofore decoding
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
            e.printStackTrace();
        }
    }
    //we'll need the intermediate file name during cracking
    public static String getDecodedFilename(String fileName) {
        int index = fileName.lastIndexOf('.');
        return fileName.substring(0, index) + "_decoded" + fileName.substring(index);
    }

}
