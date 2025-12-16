package com.jemyelek.utils;

import java.io.File;

public class Constants {
    public static final String PATH_TO_TEXT_FOR_ENCRYPTION = System.getProperty("user.dir") + File.separator + "res" + File.separator + "Text.txt";
    public static final String PATH_TO_ENCRYPTED_TEXT = System.getProperty("user.dir") + File.separator + "res" + File.separator + "Encrypted.txt";
    public static final String PATH_TO_TEXT_FOR_DECRYPTED_DATA = System.getProperty("user.dir") + File.separator + "res" + File.separator + "Decrypted.txt";
    public static final String PATH_TO_SAMPLE_TEXT_FOR_DECRYPTION = System.getProperty("user.dir") + File.separator + "res" + File.separator + "Sample.txt";
}
