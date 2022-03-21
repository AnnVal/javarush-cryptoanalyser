package com.javarush.cryptoanalyzer.crypto;

import java.util.Arrays;

public class CharCoder {
    //chars sequence is in natural order for working binary search
    private static final char[] LOWER_ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '«', '»', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    private static final char[] UPPER_ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '«', '»', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З',
            'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',};
    static final int ALPHABET_LENGTH = LOWER_ALPHABET.length; //длина алфавита нам понадобиться при взломе
    private char[] decodingAlphabet;
    private int key;


    public CharCoder(int key) {
        this.key = key % ALPHABET_LENGTH;  //we leave onle meaning part of key

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
