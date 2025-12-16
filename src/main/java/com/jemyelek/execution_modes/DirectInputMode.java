package com.jemyelek.execution_modes;

import com.jemyelek.processes.BruteForce;
import com.jemyelek.processes.Decode;
import com.jemyelek.processes.Encode;
import com.jemyelek.processes.StatisticalAnalyzer;
import com.jemyelek.utils.Message;

import java.util.Scanner;

import static com.jemyelek.utils.Constants.*;
import static java.lang.System.in;

public class DirectInputMode {
    private static boolean isContinue = true;
    
    public void start() {
        Scanner scanner = new Scanner(in);
        System.out.println("По желанию, можно использовать рабочую папку с файлами:");
        System.out.println(PATH_TO_TEXT_FOR_ENCRYPTION);
        System.out.println(PATH_TO_ENCRYPTED_TEXT);
        System.out.println(PATH_TO_TEXT_FOR_DECRYPTED_DATA);
        System.out.println(Message.HELLO_DIRECT_INPUT);
        
        if (isContinue) {
            do {
                String input = scanner.nextLine();
                int optionNumber = 0;
                try {
                    optionNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println(Message.INCORRECT_NUMBER_TRY_AGAIN);
                    continue;
                }
                
                switch (optionNumber) {
                    case 1 -> Encode.startEncode();
                    case 2 -> Decode.startDecode();
                    case 3 -> BruteForce.startBruteForce();
                    case 4 -> StatisticalAnalyzer.startAnalyze();
                    case 5 -> exitProgram();
                    default -> System.out.println(Message.TRY_AGAIN);
                }
            } while (isContinue);
        }
    }
    
    private static void exitProgram() {
        isContinue = false;
        System.out.println(Message.BYE);
    }
}
