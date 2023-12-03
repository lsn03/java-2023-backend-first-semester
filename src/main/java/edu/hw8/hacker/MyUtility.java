package edu.hw8.hacker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public final class MyUtility {
    public static final int DEFAULT_LENGTH = 4;
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int MAX_PASSWORD_LENGTH = 6;

    private MyUtility() {

    }

    public static Map<String, String> fillWithDefaultLength() {
        Map<String, String> passwordDatabase = new HashMap<>();
        passwordDatabase.put("81dc9bdb52d04dc20036dbd8313ed055", "first_pass"); // 1234
        passwordDatabase.put("e2fc714c4727ee9395f324cd2e7f331f", "second_pass"); // abcd
        return passwordDatabase;
    }

    public static String hashMD5(String input) {
        StringBuilder md5Hash = new StringBuilder();
        try {
            byte[] b = MessageDigest.getInstance("MD5").digest(input.getBytes());

            for (byte value : b) {
                md5Hash.append(String.format("%02x", value));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return md5Hash.toString();
    }
}
