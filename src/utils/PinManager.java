package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PinManager {
    public static String hashPin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(pin.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString(); 
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String pin = "14682";  // test whatever pin you want
        String hashed = hashPin(pin);
        System.out.println("Hashed pin: " + hashed);
    }
}