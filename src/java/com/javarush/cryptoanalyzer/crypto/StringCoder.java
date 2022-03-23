package com.javarush.cryptoanalyzer.crypto;

public class StringCoder {
    private int key;
    private CharCoder charCoder;

    public StringCoder(int key) {
        this.key = key;
        this.charCoder = new CharCoder(key);
    }

    public String encodeString(String string) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = charCoder.encodeChar(chars[i]);
        }
        return String.valueOf(chars);
    }

    public String decodeString(String string) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = charCoder.decodeChar(chars[i]);
        }
        return String.valueOf(chars);
    }

}
