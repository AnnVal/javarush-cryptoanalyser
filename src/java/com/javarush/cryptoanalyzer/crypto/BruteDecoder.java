package com.javarush.cryptoanalyzer.crypto;

import com.javarush.cryptoanalyzer.dialog.Dialog;

import java.nio.charset.Charset;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.*;
import java.io.*;
import java.nio.file.StandardCopyOption;

public class BruteDecoder {
    //этот класс-взломщик будет срабатывать на относительно литературных текстах, т.к.
    //ориентируетс€ на расстановку знаков пунктуации
    private static final String unpossibleStarts = ",:!?";
    private static final String possibleEndings = ".\"!?:ї";
    private static final String unpossibleEndings = ",-Ђ";

    private static boolean startMakesSense(String str) {
        String start = str.substring(0, 1);
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
                if (scanner.hasNextLine())//принима€ во внимание, что шифруют часто письма
                    // то последн€€ строка-часто подпись, котора€ не подчин€етс€ выбранным правилам пунктуации
                    // именно эта часть проверки можем вызывать проблемы, потому что после подиписи в файле
                    // часто оказываетс€ пуста€ строка и тогда строка с подписью невалидна
                    //пока в инструкции предлагаю провер€ть пустую строку в концце и убирать ее вручную
                    makesSense &= endingMakesSense(str);
                if (!makesSense)
                    return makesSense;
            }

        } catch (IOException e) {
            e.printStackTrace();
            //все та же проблема логгировани€, постараюсь исправить после проверки на 2й итерации
        }
        return makesSense;
    }

    public void crack(String fileName) throws IOException {
        String decodedFileName = FileCoder.getDecodedFilename(fileName);
        for (int i = 0; i < CharCoder.ALPHABET_LENGTH; i++) { //начинаю с нул€ на случай, если файл не зашифрован:)
            new FileCoder(fileName, i).decodeFileToOtherFile();
            if (fileMakesSence(decodedFileName)) {
                System.out.println(Files.readAllLines(Paths.get(decodedFileName), Charset.forName("windows-1251")).get(0));
                //кодировки-это ужасно! сначала все работало из »деи и без указани€ кодировки
                //потом не работало из консоли из-за кодировки
                // € помен€ла кодировки в »дее, но они не заработади, даже когда € все вернула как было
                //теперь работает только с указанием кодировке  в строке 64 при чтении файла
                //магии не бывает, но что-то не очень пон€тное все равно происходит=(
                //извините, кажетс€, € нытик, остальные с ними,видимо, легко разобрались,суд€ по отсутвию вопросов
                String answer = Dialog.askUserAboutStringSense();
                if ("y".equalsIgnoreCase(answer)) {
                    Files.move(Paths.get(decodedFileName), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File was successfully decoded");
                    break;
                }
            }
            if(i==CharCoder.ALPHABET_LENGTH -1) System.out.println("Unfortunately we were not able to crack the cipher");
        }

    }


}
