package com.internet.shop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String HASHING_ALGORITHM = "SHA-512";
    private static final String FORMAT_PATTERN = "%02x";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                hashPassword.append(String.format(FORMAT_PATTERN, b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password hashing algorithm not working", e);
        }
        return hashPassword.toString();
    }
}
