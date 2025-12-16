package com.jemyelek.utils;

public class Message {
    public static final String HELLO_MAIN =
            """
                    Это программа Шифра Цезаря.
                    Введите номер, указывающий режим программы:
                        1 - Работа через командную строку
                        2 - Пользовательский интерфейс - JavaFX
                        3 - CLI - передача данных через String[] args (main)
                        4 - Выход
                    """;
    
    public static final String HELLO_DIRECT_INPUT =
            """
                    Теперь введите номер операции:
                        1 - Шифрование
                        2 - Расшифровка
                        3 - Взлом шифра (Brute Force)
                        4 - Анализатор
                        5 - Выход
                    """;
    
    public static final String BYE = "Программа завершена. Пока!";
    public static final String TRY_AGAIN = "Неверный номер. Попробуйте заново.";
    public static final String INCORRECT_NUMBER_TRY_AGAIN = "Неверный ввод. Попробуйте заново.";
    public static final String INCORRECT_NUMBER_BYE = "Введен неверный номер. " + BYE;
    public static final String INCORRECT_KEY = "Ошибка ввода ключа.";
    public static final String INCORRECT_FILE = "";
    public static final String INCORRECT_PATH = "";
    
}
