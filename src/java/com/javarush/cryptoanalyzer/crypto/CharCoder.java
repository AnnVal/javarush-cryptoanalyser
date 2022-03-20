package com.javarush.cryptoanalyzer.crypto;

import java.util.Arrays;

public class CharCoder {
    private static final char[] LOWER_ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '«', '»', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    private static final char[] UPPER_ALPHABET = {' ', '!', '"', '\'', ',', '.', ':', '?', '«', '»', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З',
            'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',};

    private char[] decodingAlphabet;
    private int key;
    static final int ALPHABET_LENGTH = LOWER_ALPHABET.length; //длина алфавита нам понадобиться при взломе

    public CharCoder(int key) {
        this.key = key % ALPHABET_LENGTH;  //поскольку неважно,сколько раз алфавит поместится в ключе,
        // то мы сохраняем только значащую часть ключа
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
                //для корректного разбиения кодируемого файла на строки знаки переноса и фформатирования лучше сохранять
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


        //ВОПРОС: объединения фенкций кодирования/декодирования символа не будет нарушением принципа Single Responsibility?
        //Интуитивно мне кажется, что это настолько близкие функции, что их логично объединить в одном классе. А как Вы думаете?

    }
}
