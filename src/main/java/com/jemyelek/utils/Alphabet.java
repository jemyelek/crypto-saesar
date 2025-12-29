package com.jemyelek.utils;

import java.util.*;

public class Alphabet {

    private final List<Character> list;

    private static final Character[] RU = new Character[]{
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
            'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};

    private static final Character[] EN = new Character[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static final Character[] SYMBOLS = new Character[]{
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
            '_', '-', '=', '+', '[', ']', '{', '}', ':', ';',
            '"', '\\', '/', ',', '.', '?', '\'', '№', '«', '»', ' '
    };

    private static final Character[] NUMBERS = new Character[]{
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    };

    private static final Set<Character> alphabet = new HashSet<>();
    private static final Map<Character, Integer> charToIndex = new HashMap<>();
    private static final Map<Integer, Character> indexToChar = new HashMap<>();

    public int alphabetSize() {
        return list.size();
    }

    public Alphabet() {
        alphabet.addAll(Arrays.asList(RU));
        alphabet.addAll(Arrays.asList(EN));
        alphabet.addAll(Arrays.asList(SYMBOLS));
        alphabet.addAll(Arrays.asList(NUMBERS));

        list = new ArrayList<>(alphabet);
        for (int i = 0; i < alphabet.size(); i++) {
            Character character = list.get(i);
            charToIndex.put(character, i);
            indexToChar.put(i, character);
        }
    }

    /**
     *Здесь происходит сдвиг каждого символа переданного абзаца.
     * */
    public String shiftCharacters(String line, int key) {
        StringBuilder builder = new StringBuilder(line.length());
        for (char s : line.toLowerCase().toCharArray()) {
            if (alphabet.contains(s)) {
                int index = getCharacterIndex(s);
                builder.append(getCharacterByIndex((alphabet.size() + (index + key)) % alphabet.size()));
            } else {
                builder.append(s);
            }
        }
        return builder + System.lineSeparator();
    }

    /**
     * Эта функция очищает текст от символов и номеров непосредственно перед взломом (режим {@code BruteForce}) данных.
     * Это делается, чтобы при проверке теста исключить наличие символов и сравнивать только текст.
     * Очищенный текст от символов храниться временно. Когда система нашла адекватный текст,
     * расшифровка делается непосредственно текста с символами.
     * */
    public String clearSymbols(String text) {
        String textToClear = text;
        String[] symbols = new String[]{Arrays.toString(SYMBOLS)};

        for (String symbol : symbols){
            textToClear = textToClear.replace(symbol, "");
        }

        String[] numbers = new String[]{Arrays.toString(NUMBERS)};
        for (String number : numbers){
            textToClear = textToClear.replace(number, "");
        }
        return textToClear;
    }

    /**
     *Эта функция позволяет получить индекс символа текста.
     * */
    private int getCharacterIndex(char character) {
        return charToIndex.get(character);
    }

    /**
     *Эта функция позволяет получить символ текста по индексу.
     * */
    private char getCharacterByIndex(int index) {
        return indexToChar.get(index);
    }


}
