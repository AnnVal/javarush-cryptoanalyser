package com.javarush.cryptoanalyzer.crypto;

import java.util.Arrays;

public class CharCoder {
    private static final char[] LOWER_ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�',
            '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�',
            '�', '�', '�', '�', '�', '�'};
    private static final char[] UPPER_ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�',
            '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�',
            '�', '�', '�', '�', '�', '�',};

    private char[] decodingAlphabet;
    private int key;
    static final int ALPHABET_LENGTH = LOWER_ALPHABET.length; //����� �������� ��� ������������ ��� ������

    public CharCoder(int key) {
        this.key = key % ALPHABET_LENGTH;  //��������� �������,������� ��� ������� ���������� � �����,
        // �� �� ��������� ������ �������� ����� �����
    }

    public void indentifyDecodingAlphabet(char charToEncode) {
        if (Character.isUpperCase(charToEncode))
            decodingAlphabet = UPPER_ALPHABET;
        else
            decodingAlphabet = LOWER_ALPHABET;
    }

    public char encodeChar(char charToEncode) {
        indentifyDecodingAlphabet(charToEncode);
        int index = Arrays.binarySearch(decodingAlphabet, charToEncode);
        if (index < 0) {
            switch (charToEncode) {
                //��� ����������� ��������� ����������� ����� �� ������ ����� �������� � ��������������� ����� ���������
                case '\n':
                    return '\n';
                case '\r':
                    return 'r';
                case '\t':
                    return '\t';
                default:
                    return '*';
            }

        } else {
            index = (index + key) % ALPHABET_LENGTH;
            return decodingAlphabet[index];
        }
    }

    public char decodeChar(char charToDecode) {
        indentifyDecodingAlphabet(charToDecode);
        int index = Arrays.binarySearch(decodingAlphabet, charToDecode);
        if (index < 0) {
            switch (charToDecode) {
                case '\n':
                    return '\n';
                case '\r':
                    return 'r';
                case '\t':
                    return '\t';
                default:
                    return '*';
            }

        } else {
            index = (index - key) >= 0 ? (index - key) : ALPHABET_LENGTH + index - key;
            return decodingAlphabet[index];
        }


        //������: ����������� ������� �����������/������������� ������� �� ����� ���������� �������� Single Responsibility?
        //���������� ��� �������, ��� ��� ��������� ������� �������, ��� �� ������� ���������� � ����� ������. � ��� �� �������?

    }
}
