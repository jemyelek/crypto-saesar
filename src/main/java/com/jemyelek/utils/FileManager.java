package com.jemyelek.utils;

import com.jemyelek.exceptions.CryptoException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FileManager {

    /**
     *Открывается и считывается файл. Если успешно, то возвращается текст,<br> учитываются обрывы абзацев {@code System}.{@code lineSeparator()}
     * */
    public String readFile(String filePath) {
        Path path = Path.of(filePath).normalize();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return reader
                    .lines()
                    .collect(Collectors
                            .joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new CryptoException("Ошибка чтения файла: ", e);
        }
    }

    public void writeFile(String text, String outputFile) {
        Path path = Path.of(outputFile).normalize();
        if (outputFile.isEmpty() || !Files.exists(path))
            path = createOutputFile();

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(text);
        } catch (IOException e) {
            throw new CryptoException("Ошибка записи в файл: ", e);
        }
    }

    /**
     *Если файла для сохранения зашифрованных данных нет или уже имеется, то создается новый (удалив старый).
     * */
    private static Path createOutputFile() {
        Path newPath = Path.of(System.getProperty("user.dir") + File.separator + "res" + File.separator + "Decrypted.txt");
        try {
            if (Files.exists(newPath)) {
                Files.delete(newPath);
            }
            Files.createFile(newPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create file in the createOutputFile() method");
        }
        return newPath;
    }
}
