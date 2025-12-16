package com.jemyelek.processes;

import com.jemyelek.utils.Alphabet;
import com.jemyelek.utils.FileManager;
import com.jemyelek.validations.FileValidator;

import java.util.Scanner;

import static com.jemyelek.utils.Constants.PATH_TO_ENCRYPTED_TEXT;
import static java.lang.System.in;

public class Encode {
    private static final FileManager FILE_MANAGER = new FileManager();
    private static final Alphabet ALPHABET = new Alphabet();

    /**
     * Запускается режим шифрования данных.
     *
     */
    public static void startEncode() {
        Scanner scanner = new Scanner(in);
        System.out.println("Введите путь к фалу:");

        String filePath = scanner.nextLine();

        if (!FileValidator.isPathValid(filePath))
            return;

        System.out.println("Введите ключ шифрования: (Допустимый диапазон 1-" + ALPHABET.alphabetSize() + ")");
        int inputKey = scanner.nextInt();
        if (!FileValidator.isKeyValid(inputKey, ALPHABET))
            return;

        String textFromFile = FILE_MANAGER.readFile(filePath);
        Cipher cipherEncrypt = new Cipher(ALPHABET);
        String encryptedText = cipherEncrypt.encrypt(textFromFile, inputKey);
        FILE_MANAGER.writeFile(encryptedText, PATH_TO_ENCRYPTED_TEXT);
        System.out.println("Шифрование успешно завершено.\nПроверти путь: " + PATH_TO_ENCRYPTED_TEXT);
    }
}
