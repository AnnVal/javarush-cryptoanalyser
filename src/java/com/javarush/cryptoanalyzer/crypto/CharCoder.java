package com.javarush.cryptoanalyzer.crypto;

import java.util.Arrays;

public class CharCoder {
    //chars sequence is in natural order for working binary search
    private static final char[] LOWER_ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '«', '»', 'à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç',
            'è', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù',
            'ú', 'û', 'ü', 'ý', 'þ', 'ÿ'};
    private static final char[] UPPER_ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '«', '»', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç',
            'È', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', '×', 'Ø', 'Ù',
            'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß',};
    static final int ALPHABET_LENGTH = LOWER_ALPHABET.length;
    private char[] decodingAlphabet;
    private int key;


    public CharCoder(int key) {
        this.key = key % ALPHABET_LENGTH;  //we leave only meaning part of key

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
                //for proper division out text to strings
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


        //Question: my class is codind and decoding at the same tame. Does is violate Single Responsibility principle?
        //As for me, this functions are very close and it is resonable to keep them in the same class

    }
}
