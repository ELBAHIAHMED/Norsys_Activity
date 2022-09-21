package com.norsys.activity.util;

import java.security.SecureRandom;
import java.util.Random;

public class SharedTools {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public  String generateKey(int stringSize) {
        StringBuilder returnValue = new StringBuilder("_");

        for (int i = 0; i < stringSize; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}
