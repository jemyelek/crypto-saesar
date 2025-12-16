package com.jemyelek.processes;

import com.jemyelek.exceptions.FileValidationException;
import com.jemyelek.utils.Alphabet;
import com.jemyelek.utils.FileManager;
import com.jemyelek.validations.FileValidator;

import java.util.List;
import java.util.Scanner;

import static com.jemyelek.utils.Constants.PATH_TO_SAMPLE_TEXT_FOR_DECRYPTION;
import static com.jemyelek.utils.Constants.PATH_TO_TEXT_FOR_DECRYPTED_DATA;
import static java.lang.System.in;

public class BruteForce {
    private static final FileManager fileManager = new FileManager();
    private static final Alphabet alphabet = new Alphabet();

    /**
     * Запускается режим взлома зашифрованных данных.
     *
     */
    public static void startBruteForce() {
        try {
            Scanner scanner = new Scanner(in);
            System.out.println("Введите путь к зашифрованному фалу:");
            String encryptedTextFilePath = scanner.nextLine();
            FileValidator.validatePath(encryptedTextFilePath);
            
            BruteForce bruteForce = new BruteForce();
            String decryptedText = bruteForce.bruteForceDecryptor(encryptedTextFilePath);
            fileManager.writeFile(decryptedText, PATH_TO_TEXT_FOR_DECRYPTED_DATA);
            
            System.out.println("Взлом шифрования завершен. Проверьте файл.");
        } catch (FileValidationException e) {
            System.out.println(e.getMessage());;
        }
    }

    private String bruteForceDecryptor(String encryptedTextPath) {
        String sampleText = fileManager.readFile(PATH_TO_SAMPLE_TEXT_FOR_DECRYPTION);
        String encryptedText = fileManager.readFile(encryptedTextPath);
        String decryptedText = "";

        int passKey = decryptProcess(sampleText, encryptedText);
        if (passKey != -1) {
            Cipher cipher = new Cipher(alphabet);
            decryptedText = cipher.decrypt(encryptedText, passKey);
        }
        return decryptedText;
    }

    /**
     * Эта функция проверяет отрезок (первый абзац) зашифрованного текста на сходство с текстом для примера
     * ({@code res/Sample.txt}), и возвращает ключ для адекватного варианта текста в вызвавшую функцию ({@code bruteForceDecryptor}).
     *
     */
    private int decryptProcess(String sample, String encryptedText) {
        List<String> list = encryptedText.lines().toList();
        Cipher cipher = new Cipher(alphabet);

        String line = list.get(0);
        String testResult = "";
        int key = -1;
        for (int i = 1; i <= alphabet.alphabetSize(); i++) {
            testResult = cipher.decrypt(line, i);
            if (testResult.contains(" ")) {
                if (checkEquality(testResult, sample)) {
                    key = i;
                }
            }
        }
        return key;
    }

    /**
     * Функция сравнения каждого слова зашифрованного текста с набором слов файла {@code res/Sample.txt}.
     * Если длина слово больше чем один символ, то проверяет на сходство. Иначе, пропускает этот символ.
     * Возвращает {@code true} если сходство найдено, сообщает об этом вызвавшей функции {@code decryptProcess}.
     *
     */
    private boolean checkEquality(String testResult, String sample) {
        testResult = alphabet.clearSymbols(testResult);
        String[] parts = testResult.split(" ");
        String[] sampleParts = sample.split(" ");

        for (String part : parts) {
            if (part.length() < 2) continue;
            for (String samplePart : sampleParts) {
                if (part.equals(samplePart)) {
                    return true;
                }
            }
        }
        return false;
    }
}
