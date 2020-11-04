package lk.abayafarm.pos.controller;

import java.util.Base64;

public class PasswordEncryption {
    public static String getEncodedString(String password) {
        return new String(Base64.getEncoder().encodeToString(password.getBytes()));
    }
}
