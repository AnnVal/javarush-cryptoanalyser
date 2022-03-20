package com.javarush.cryptoanalyzer.crypto;

import com.javarush.cryptoanalyzer.dialog.Dialog;

import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.*;
import java.io.*;
import java.nio.file.StandardCopyOption;

public class BruteDecoder {
    //этот класс-взломщик будет срабатывать на относительно литературных текстах, т.к.
    //ориентируется на расстановку знаков пунктуации
    private static final String unpossibleStarts = ",:!?)";
    private static final String possibleEndings = ".\"!?):»";
    private static final String unpossibleEndings = ",(-«";

    private static boolean startMakesSense(String str) {
        String start = str.substring(0, 1);
        String ending = str.substring(str.length() - 1);
        return !unpossibleStarts.contains(start);
    }

    private static boolean endingMakesSense(String str) {
        String ending = str.substring(str.length() - 1);
        return possibleEndings.contains(ending);
    }

    private static boolean endingDoesntMakesSense(String str) {
        String ending = str.substring(str.length() - 1);
        return unpossibleEndings.contains(ending);
    }

    private static boolean fileMakesSence(String fileName) {
        boolean makesSense =true;
        try (Scanner scanner = new Scanner(Paths.get(fileName))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                makesSense = startMakesSense(str);
                makesSense &= !endingDoesntMakesSense(str);
                if (scanner.hasNextLine())//принимая во внимание, что шифруют часто письма
                    // то последняя строка-часто подпись, которая не подчиняется выбранным правилам пунктуации
                    // именно эта часть проверки можем вызывать проблемы, потому что после подиписи в файле
                    // часто оказывается пустая строка и тогда строка с подписью невалидна
                    //пока в инструкции предлагаю проверять пустую строку в концце и убирать ее вручную
                    makesSense &= endingMakesSense(str);
                if (!makesSense)
                    return makesSense;
            }

        } catch (IOException e) {
            e.printStackTrace();
            //все та же проблема логгирования, постараюсь исправить после проверки на 2й итерации
        }
        return makesSense;
    }

    public void crack(String fileName) throws IOException {
        String decodedFileName = FileCoder.getDecodedFilename(fileName);
        for (int i = 0; i < CharCoder.alphabetLength; i++) { //начинаю с нуля на случай, если файл не зашифрован:)
            new FileCoder(fileName, i).decodeFileToOtherFile();
            if (fileMakesSence(decodedFileName)) {
                System.out.println(Files.readAllLines(Paths.get(decodedFileName)).get(0));
                String answer = Dialog.askUserAboutStringSense();
                if ("y".equalsIgnoreCase(answer)) {
                    Files.move(Paths.get(decodedFileName), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File was successfully decoded");
                    break;
                }
            }
        }
    }


}
