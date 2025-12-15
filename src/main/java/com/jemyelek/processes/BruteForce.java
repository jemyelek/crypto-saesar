package com.jemyelek.processes;

import com.jemyelek.utils.FileManager;

import java.io.File;
import java.util.List;

public class BruteForce {
    private static final FileManager fileManager = new FileManager();
    private static final String pathToSampleTextForDecryption = System.getProperty("user.dir") + File.separator + "res" + File.separator + "Sample.txt";
    private final Alphabet alphabet;

    public BruteForce(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public String bruteForceDecryptor(String encryptedTextPath) {
        String sampleText = fileManager.readFile(pathToSampleTextForDecryption);
        String encryptedText = fileManager.readFile(encryptedTextPath);
        String decryptedText = "";

        int passKey = forceDecryptProcess(sampleText, encryptedText);
        if (passKey != -1) {
            Cipher cipher = new Cipher(alphabet);
            decryptedText = cipher.decrypt(encryptedText, passKey);
        }
        return decryptedText;
    }

    /**
     *Эта функция проверяет отрезок (первый абзац) зашифрованного текста на сходство с текстом для примера
     * ({@code res/Sample.txt}), и возвращает ключ для адекватного варианта текста в вызвавшую функцию ({@code bruteForceDecryptor}).
     * */
    private int forceDecryptProcess(String sample, String encryptedText) {
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
     *Функция сравнения каждого слова зашифрованного текста с набором слов файла {@code res/Sample.txt}.
     * Если длина слово больше чем один символ, то проверяет на сходство. Иначе, пропускает этот символ.
     *Возвращает {@code true} если сходство найдено, сообщает об этом вызвавшей функции {@code forceDecryptProcess}.
     * */
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
