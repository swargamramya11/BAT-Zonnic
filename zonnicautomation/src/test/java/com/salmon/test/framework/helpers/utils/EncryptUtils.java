package com.salmon.test.framework.helpers.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Slf4j
public class EncryptUtils {
    @Test
    public void encryptPassword() throws Exception {
        String password = System.getProperty("password");
        if (password == null) {
            throw new IllegalArgumentException("Run with -Dpassword=<password>");
        }

        SecretKeySpec key = generateSecretKey(password);
        String originalPassword = "Summer2019";
        log.info("Original password: " + originalPassword);
        String encryptedPassword = encrypt(originalPassword, key);
        log.info("Encrypted password: " + encryptedPassword);
        String decryptedPassword = decrypt(encryptedPassword, key);
        log.info("Decrypted password: " + decryptedPassword);
    }

    public static SecretKeySpec generateSecretKey(String password) {
        // The salt (probably) can be stored along with the encrypted data
        byte[] salt = new String("12345678").getBytes();

        // Decreasing this speeds down startup time and can be useful during testing, but it also makes it easier for brute force attackers
        int iterationCount = 40000;
        // Other values give me java.security.InvalidKeyException: Illegal key size or default parameters
        int keyLength = 128;
        try {
            return createSecretKey(password.toCharArray(),
                    salt, iterationCount, keyLength);
        } catch (NoSuchAlgorithmException|InvalidKeySpecException e) {
           log.error("Unable to create secret key :> "+e.getMessage());
        }
        return null;
    }

    private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

    public static String encrypt(String property, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters parameters = pbeCipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }

    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String string, SecretKeySpec key) {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        try {
            Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
            return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
        } catch (GeneralSecurityException|IOException e) {
           log.error("Unable to decrypt :> "+e.getMessage());
        }
        return null;
    }

    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }
}
