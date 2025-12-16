package com.jemyelek;

import com.jemyelek.execution_modes.CommandLineInputMode;
import com.jemyelek.execution_modes.DirectInputMode;
import com.jemyelek.execution_modes.JavaFXMode;
import com.jemyelek.utils.Message;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Message.HELLO_MAIN);
        
        int modeNumber = scanner.nextInt();
        switch (modeNumber) {
            case 1 -> new DirectInputMode().start();
            case 2 -> new JavaFXMode().start();
            case 3 -> new CommandLineInputMode().start();
            case 4 -> System.out.println(Message.BYE);
            default -> System.out.println(Message.INCORRECT_NUMBER_BYE);
        }
    }
    
}
