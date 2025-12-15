package com.jemyelek;

import com.jemyelek.execute_modes.CLIMode;
import com.jemyelek.execute_modes.DirectInputMode;
import com.jemyelek.execute_modes.JavaFXMode;
import com.jemyelek.utils.Message;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Message.HELLO_MAIN);

        while (scanner.hasNextInt()) {
            int modeNumber = scanner.nextInt();

            if (modeNumber == 1) {
                DirectInputMode directInputMode = new DirectInputMode();
                directInputMode.start();
                break;
            } else if (modeNumber == 2) {
                JavaFXMode javaFXMode = new JavaFXMode();
                javaFXMode.start();
                break;
            } else if (modeNumber == 3) {
                CLIMode cliMode = new CLIMode();
                cliMode.start();
            } else if (modeNumber == 4) {
                break;
            } else
                System.out.println(Message.TRY_AGAIN);
        }
        System.out.println(Message.BYE);
    }
}
