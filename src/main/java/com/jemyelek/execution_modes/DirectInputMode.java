package com.jemyelek.execution_modes;

import com.jemyelek.processes.*;
import com.jemyelek.utils.Alphabet;
import com.jemyelek.utils.FileManager;
import com.jemyelek.utils.Message;

import java.util.Scanner;

import static com.jemyelek.utils.Constants.*;
import static java.lang.System.in;

public class DirectInputMode {
    private static final FileManager fileManager = new FileManager();
    private static final Alphabet alphabet = new Alphabet();

    public void start() {
        Scanner scanner = new Scanner(in);
        System.out.println("По желанию, можно использовать рабочую папку с файлами:");
        System.out.println(PATH_TO_TEXT_FOR_ENCRYPTION);
        System.out.println(PATH_TO_ENCRYPTED_TEXT);
        System.out.println(PATH_TO_TEXT_FOR_DECRYPTED_DATA);

        System.out.println(Message.HELLO_DIRECT_INPUT);
        int selectOperation = scanner.nextInt();
        switch (selectOperation) {
            case 1 -> Encode.startEncode();
            case 2 -> Decode.startDecode();
            case 3 -> BruteForce.startBruteForce();
            case 4 -> StatisticalAnalyzer.startAnalyze();
        }
    }
}
