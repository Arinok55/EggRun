package com.example.eggrun.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    public String getPassHash(String name, String password) {
        return getHash(getHash(name) + password);
    }

    private String getHash(String word) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(word.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
