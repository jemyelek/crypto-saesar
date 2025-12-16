package com.jemyelek.processes;

import com.jemyelek.utils.Alphabet;
import com.jemyelek.utils.FileManager;
import com.jemyelek.validations.FileValidator;

import java.util.Scanner;

import static com.jemyelek.utils.Constants.PATH_TO_ENCRYPTED_TEXT;
import static java.lang.System.in;

public class Decode {
    private static final FileManager fileManager = new FileManager();
    private static final Alphabet alphabet = new Alphabet();

    /**
     * Запускается режим расшифровки данных.
     *
     */
    public static void startDecode() {
        Scanner scanner = new Scanner(in);
        System.out.println("Введите путь к зашифрованному фалу:");

        String encryptedTextFilePath = scanner.nextLine();
        if (!FileValidator.isPathValid(encryptedTextFilePath))
            return;

        System.out.println("Введите ключ расшифровки: (Допустимый диапазон 1-" + alphabet.alphabetSize() + ")");
        int inputKey = scanner.nextInt();
        if (!FileValidator.isKeyValid(inputKey, alphabet))
            return;

        String textFromDecryptedFile = fileManager.readFile(PATH_TO_ENCRYPTED_TEXT);
        String decryptedTextFilePath = "";

        Cipher cipherDecrypt = new Cipher(alphabet);
        String decryptedText = cipherDecrypt.decrypt(textFromDecryptedFile, inputKey);
        fileManager.writeFile(decryptedText, decryptedTextFilePath);
        System.out.println("Расшифровка завершена, проверти результат.");
    }
}
