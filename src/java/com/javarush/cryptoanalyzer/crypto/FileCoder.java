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
            //извините, я помню что e.printStackTrace()- нехорошая система, но мы пока не разбирали как это делать
            //а я боюсь закопаться, изобретая свою систему,постараюсь исправить на 2й итерации
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

    //метод, который пригодиться при взломе,чтобы не менять исходный файл, пока не дешифруем
    // если бы была задача определить ключ, то,расшифровывая в отдельный файл,
    // мы всегда можем сказать значение итератора-ключа, при котором расшифровка была успешной
    // а если бы прибывляли по 1 к ключу и каждый раз меняли исходный файл, то прилось бы подсчитывать и ключ=)
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
    //имя промежуточного файла понадобится при взломе, поэтому вынесено в отдельный метод
    public static String getDecodedFilename(String fileName) {
        int index = fileName.lastIndexOf('.');
        return fileName.substring(0, index) + "_decoded" + fileName.substring(index);
    }

}
