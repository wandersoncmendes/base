package com.base.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class TextEncriptor {

    @Value("${app.secret_reset_password}")
    private String secret;

    @Value("${app.salt_reset_password}")
    private String salt;

    /**
     * Gera texto encriptado
     * @param value
     * @return
     */
    public String encrypt(String value) {
        TextEncryptor encryptor = Encryptors.text(secret, salt);
        String encryptedText = encryptor.encrypt(value);
        return encryptedText;
    }

    /**
     * Desencripta texto
     * @param value
     * @return
     */
    public String decrypt(String value) {
        TextEncryptor decryptor = Encryptors.text(secret, salt);
        String decryptedText = decryptor.decrypt(value);
        return decryptedText;
    }

}
