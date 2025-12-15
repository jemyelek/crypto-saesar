package com.jemyelek.validations;

import com.jemyelek.exceptions.CryptoException;
import com.jemyelek.processes.Alphabet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Validator {
    private static final Set<Path> DANGEROUS_PATHS = Stream.of(
                    /* --- Windows --- */
                    "C:\\Windows",
                    "C:\\Program Files",
                    "C:\\Program Files (x86)",
                    /*"C:\\Users\\Default",*/

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

    public static boolean isPathValid(String path) {
        if (!isPathEmpty(path)) return false;
        if (!isFileExists(path)) return false;
        return !isSystemPath(path);
    }

    public static boolean isKeyValid(int key, Alphabet alphabet) {
        if (alphabet.alphabetSize() < key) {
            System.out.println("Введенный ключ [" + key + "] не в диапазоне допустимого числа.");
            return false;
        }
        return true;
    }

    private static boolean isPathEmpty(String path) {
        if (path.trim().isEmpty()) {
            System.out.println("Введенная директория пуста.");
            return false;
        }
        return true;
    }

    private static boolean isFileExists(String filePath) {
        Path file = Path.of(filePath).normalize();
        if (Files.exists(file) || Files.isRegularFile(file))
            return true;

        throw new CryptoException("Указанного файла нет или она является директорией.");
    }

    /**
     *Проверка директории на наличие системных папок или директорий.<br>
     *Если {@code true}, то процесс прерывается.
     * */
    private static boolean isSystemPath(String filePath) {
        Path path = Path.of(filePath).normalize();
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
