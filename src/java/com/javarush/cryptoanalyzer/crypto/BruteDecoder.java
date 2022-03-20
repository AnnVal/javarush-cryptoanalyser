package com.javarush.cryptoanalyzer.crypto;

import com.javarush.cryptoanalyzer.dialog.Dialog;

import java.nio.charset.Charset;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.*;
import java.io.*;
import java.nio.file.StandardCopyOption;

public class BruteDecoder {
    //���� �����-�������� ����� ����������� �� ������������ ������������ �������, �.�.
    //������������� �� ����������� ������ ����������
    private static final String unpossibleStarts = ",:!?";
    private static final String possibleEndings = ".\"!?:�";
    private static final String unpossibleEndings = ",-�";

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
                if (scanner.hasNextLine())//�������� �� ��������, ��� ������� ����� ������
                    // �� ��������� ������-����� �������, ������� �� ����������� ��������� �������� ����������
                    // ������ ��� ����� �������� ����� �������� ��������, ������ ��� ����� �������� � �����
                    // ����� ����������� ������ ������ � ����� ������ � �������� ���������
                    //���� � ���������� ��������� ��������� ������ ������ � ������ � ������� �� �������
                    makesSense &= endingMakesSense(str);
                if (!makesSense)
                    return makesSense;
            }

        } catch (IOException e) {
            e.printStackTrace();
            //��� �� �� �������� ������������, ���������� ��������� ����� �������� �� 2� ��������
        }
        return makesSense;
    }

    public void crack(String fileName) throws IOException {
        String decodedFileName = FileCoder.getDecodedFilename(fileName);
        for (int i = 0; i < CharCoder.ALPHABET_LENGTH; i++) { //������� � ���� �� ������, ���� ���� �� ����������:)
            new FileCoder(fileName, i).decodeFileToOtherFile();
            if (fileMakesSence(decodedFileName)) {
                System.out.println(Files.readAllLines(Paths.get(decodedFileName), Charset.forName("windows-1251")).get(0));
                //���������-��� ������! ������� ��� �������� �� ���� � ��� �������� ���������
                //����� �� �������� �� ������� ��-�� ���������
                // � �������� ��������� � ����, �� ��� �� ����������, ���� ����� � ��� ������� ��� ����
                //������ �������� ������ � ��������� ���������  � ������ 64 ��� ������ �����
                //����� �� ������, �� ���-�� �� ����� �������� ��� ����� ����������=(
                //��������, �������, � �����, ��������� � ����,������, ����� �����������,���� �� �������� ��������
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
