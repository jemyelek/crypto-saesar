package com.jemyelek.processes;

import com.jemyelek.exceptions.FileValidationException;
import com.jemyelek.utils.Alphabet;
import com.jemyelek.utils.FileManager;
import com.jemyelek.validations.FileValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.jemyelek.utils.Constants.PATH_TO_ENCRYPTED_TEXT;
import static java.lang.System.in;

public class Encode {
    private static final FileManager fileManager = new FileManager();
    private static final Alphabet alphabet = new Alphabet();
    
    /**
     * Запускается режим шифрования данных.
     *
     */
    public static void startEncode() {
        try {
            Scanner scanner = new Scanner(in);
            System.out.println("Введите путь к фалу:");
            String filePath = scanner.nextLine();
            FileValidator.validatePath(filePath);
            
            int inputKey = 0;
            try {
                System.out.println("Введите ключ шифрования: (Допустимый диапазон 1-" + alphabet.alphabetSize() + ")");
                inputKey = scanner.nextInt();
                FileValidator.isKeyValid(inputKey, alphabet);
                
            } catch (InputMismatchException e) {
                System.out.println("Введите для ключа число, а не текст.");
            }
            
            String textFromFile = fileManager.readFile(filePath);
            Cipher cipherEncrypt = new Cipher(alphabet);
            String encryptedText = cipherEncrypt.encrypt(textFromFile, inputKey);
            fileManager.writeFile(encryptedText, PATH_TO_ENCRYPTED_TEXT);
            System.out.println("Шифрование успешно завершено.\nПроверти путь: " + PATH_TO_ENCRYPTED_TEXT);
            
        } catch (FileValidationException e) {
            System.out.println(e.getMessage());
            
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка.");
        }
    }
}
