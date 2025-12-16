package com.jemyelek;

import com.jemyelek.execution_modes.CLIMode;
import com.jemyelek.execution_modes.DirectInputMode;
import com.jemyelek.execution_modes.JavaFXMode;
import com.jemyelek.utils.Message;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Message.HELLO_MAIN);

        while (scanner.hasNextInt()) {
            int modeNumber = scanner.nextInt();
            switch (modeNumber) {
                case 1 -> new DirectInputMode().start();
                case 2 -> new JavaFXMode().start();
                case 3 -> new CLIMode().start();
                default -> System.out.println(Message.TRY_AGAIN);
            }
        }
        System.out.println(Message.BYE);
    }
}
