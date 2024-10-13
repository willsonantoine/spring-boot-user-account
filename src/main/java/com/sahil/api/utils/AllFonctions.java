package com.sahil.api.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AllFonctions {
    public static String decryptPassword(String password) {
        String decryptedPassword = "";
        try {
            String[] passwordParts = password.split("\\$");
            String salt = passwordParts[3];
            String hashedPassword = passwordParts[4];
            String key = hashedPassword.substring(0, 32);
            String iv = hashedPassword.substring(32, 48);
            String encryptedPassword = hashedPassword.substring(48);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));

            byte[] decrypted = cipher.doFinal(encryptedBytes);
            decryptedPassword = new String(decrypted);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return decryptedPassword;
    }

    public static String encryptPassword(String password) {
        String encryptedPassword = "";
        try {
            String salt = password.substring(0, 8);
            String key = password.substring(8, 40);
            String iv = password.substring(40, 56);
            String plainPassword = password.substring(56);

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));

            byte[] encryptedBytes = cipher.doFinal(plainPassword.getBytes());
            encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return encryptedPassword;

    }
}
