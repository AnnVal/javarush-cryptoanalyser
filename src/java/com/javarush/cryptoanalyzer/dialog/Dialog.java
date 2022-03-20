package com.javarush.cryptoanalyzer.dialog;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dialog {
    //используем разные сканнеры для инт и строк, чтобы избежать попадания в строковый сканер пустой строки,
    // оставшей после введения числа
    public static Scanner scanner = new Scanner(System.in);
    public static Scanner intScanner = new Scanner(System.in);

    public static String askUserAboutStringSense() {
        System.out.println("Check the string above. Does it make sense? enter y/n");
        String answer = scanner.nextLine();
        if ("y".equalsIgnoreCase(answer) || "n".equalsIgnoreCase(answer))
            return answer;
        else
            askUserAboutStringSense();
        return "";
    }

    public static String askFileName() {
        System.out.println("Enter full file path for encodind or decoding");
        String fileName = scanner.nextLine();
        if (Files.notExists(Paths.get(fileName))) {
            System.out.printf("File witn name %s doesn't exist.Try again.\n", fileName);
            askFileName();
        }
        return fileName;
    }

    public static int askKey() {
        System.out.println("Enter a positive integer - a key for cipher");
        int key = intScanner.nextInt();
        if (key < 0) {
            System.out.println("try again,the number should be positive");
            askKey();
        }
        return key;
    }

    public static int askOperation() {
        System.out.println("choose an operation: 1 - encode/2 - decode/3-crack (for 1 or 2 you should know the key)");
        int action = intScanner.nextInt();
        if (action == 1 || action == 2 || action == 3) {
            return action;
        } else {
            System.out.println("there is no such an option, be attentive when enter");
            askOperation();
        }
        return 0;
    }

}
