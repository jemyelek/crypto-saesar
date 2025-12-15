package com.jemyelek.execute_modes;

import com.jemyelek.processes.Alphabet;
import com.jemyelek.processes.BruteForce;
import com.jemyelek.processes.Cipher;
import com.jemyelek.utils.FileManager;
import com.jemyelek.utils.Message;
import com.jemyelek.validations.Validator;

import java.io.File;
import java.util.Scanner;

import static java.lang.System.in;

public class DirectInputMode {
    private static final FileManager fileManager = new FileManager();
    private static final Alphabet alphabet = new Alphabet();
    private static final String  pathToTextForEncryption = System.getProperty("user.dir") + File.separator + "res" + File.separator + "Text.txt";
    private static final String  pathToEncryptedText = System.getProperty("user.dir") + File.separator + "res" + File.separator + "Encrypted.txt";
    private static final String  pathToTextForDecryptedData = System.getProperty("user.dir") + File.separator + "res" + File.separator + "Decrypted.txt";

    public void start() {
        Scanner scanner = new Scanner(in);
        System.out.println("Рабочие папки с файлами:");
        System.out.println(pathToTextForEncryption);
        System.out.println(pathToEncryptedText);
        System.out.println(pathToTextForDecryptedData);

        System.out.println(Message.HELLO_DIRECT_INPUT);
        int selectOperation = scanner.nextInt();
        switch (selectOperation) {
            case 1 -> startEncode();
            case 2 -> startDecode();
            case 3 -> startBruteForce();
            case 4 -> startAnalyze();
        }
    }

    /**
     *Запускается режим шифрования данных.
     * */
    private static void startEncode() {
        Scanner scanner = new Scanner(in);
        System.out.println("Введите путь к фалу:");
        String filePath = scanner.nextLine();

        if (!Validator.isPathValid(filePath))
            return;

        System.out.println("Введите ключ шифрования: (Допустимый диапазон 1-" + alphabet.alphabetSize() + ")");
        int inputKey = scanner.nextInt();
        if (!Validator.isKeyValid(inputKey, alphabet))
            return;

        String textFromFile = fileManager.readFile(filePath);
        Cipher cipherEncrypt = new Cipher(alphabet);
        String encryptedText = cipherEncrypt.encrypt(textFromFile, inputKey);
        fileManager.writeFile(encryptedText, pathToEncryptedText);
        System.out.println("Шифрование успешно завершено.\nПроверти путь: " + pathToEncryptedText);
    }

    /**
     *Запускается режим расшифровки данных.
     * */
    private static void startDecode() {
        Scanner scanner = new Scanner(in);

        System.out.println("Введите путь к зашифрованному фалу:");
        String encryptedTextFilePath = scanner.nextLine();
        if (!Validator.isPathValid(encryptedTextFilePath))
            return;

        System.out.println("Введите ключ расшифровки: (Допустимый диапазон 1-" + alphabet.alphabetSize() + ")");
        int inputKey = scanner.nextInt();
        if (!Validator.isKeyValid(inputKey, alphabet))
            return;

        String textFromDecryptedFile = fileManager.readFile(pathToEncryptedText);
        String decryptedTextFilePath = "";

        Cipher cipherDecrypt = new Cipher(alphabet);
        String decryptedText = cipherDecrypt.decrypt(textFromDecryptedFile, inputKey);
        fileManager.writeFile(decryptedText, decryptedTextFilePath);
        System.out.println("Расшифровка завершена, проверти результат.");
    }

    /**
     *Запускается режим взлома зашифрованных данных.
     * */
    private static void startBruteForce() {
        Scanner scanner = new Scanner(in);
        System.out.println("Введите путь к зашифрованному фалу:");
        String encryptedTextFilePath = scanner.nextLine();

        if (!Validator.isPathValid(encryptedTextFilePath))
            return;

        BruteForce bruteForce = new BruteForce(alphabet);
        String decryptedText = bruteForce.bruteForceDecryptor(encryptedTextFilePath);
        fileManager.writeFile(decryptedText, pathToTextForDecryptedData);

        System.out.println("Взлом шифрования завершен. Проверьте файл.");
    }

    /**
     *Эта функция не готова
     * */
    private static void startAnalyze() {
        System.out.println("Этот раздел еще не готов.");
    }

}
