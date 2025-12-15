package com.jemyelek.processes;

public class Cipher {

    private final Alphabet alphabet;

    public Cipher(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    /**
     *Функция шифрования данных по ключу.
     * */
    public String encrypt(String textToEncrypt, int key) {
        StringBuilder encryptedText = new StringBuilder(textToEncrypt.length());

        for (String line : textToEncrypt.split(System.lineSeparator())) {
            encryptedText.append(alphabet.shiftCharacters(line, key));
        }
        return encryptedText.toString();
    }

    /**
     *Функция расшифровки данных по ключу. Значение ключа передается дальше в минусовом виде.
     * */
    public String decrypt(String encryptedText, int key) {
        StringBuilder decryptedText = new StringBuilder(encryptedText.length());

        for (String line : encryptedText.split(System.lineSeparator())) {
            decryptedText.append(alphabet.shiftCharacters(line, -key));
        }
        return decryptedText.toString();
    }

}
