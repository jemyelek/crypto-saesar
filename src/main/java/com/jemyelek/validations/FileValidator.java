package com.jemyelek.validations;

import com.jemyelek.exceptions.FileValidationException;
import com.jemyelek.utils.Alphabet;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileValidator {
    private static final Set<Path> DANGEROUS_PATHS = Stream.of(
                    /* --- Windows --- */
                    "C:\\Windows",
                    "C:\\Program Files",
                    "C:\\Program Files (x86)",
                    "C:\\Users\\Default",

                    /* --- Linux/macOS --- */
                    "/",
                    "/etc",
                    "/bin",
                    "/sbin",
                    "/usr",
                    "/var",
                    "/proc",
                    "/System",
                    "/Library").map(path -> Path.of(path).normalize())
            .collect(Collectors.toUnmodifiableSet());
    
    public static void validatePath(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new FileValidationException("Переданный путь пуст.");
        }
        
        Path file;
        try {
            file = Path.of(path).normalize();
        } catch (InvalidPathException e) {
            throw new FileValidationException("Указанный путь недействителен.", e);
        }
        
        if (!Files.exists(file) || !Files.isRegularFile(file)) {
            throw new FileValidationException("Файл не существует или это не файл.");
        }
        
        if (isSystemPath(file)) {
            throw new FileValidationException("Указанный путь недопустим.");
        }
    }

    public static boolean isKeyValid(int key, Alphabet alphabet) {
        if (alphabet.alphabetSize() < key) {
            System.out.println("Введенный ключ [" + key + "] не в диапазоне допустимого числа.");
            return false;
        }
        return true;
    }

    /**
     * Проверка директории на наличие системных папок или директорий.<br>
     * Если {@code true}, то процесс прерывается.
     *
     */
    private static boolean isSystemPath(Path file) {
        Path path = file.normalize();
        boolean isDanger = false;

        for (Path pathPart : DANGEROUS_PATHS) {
            if (path.startsWith(pathPart)) {
                isDanger = true;
                System.out.println("Указанный путь недопустим.");
                break;
            }
        }
        return isDanger;
    }
}
