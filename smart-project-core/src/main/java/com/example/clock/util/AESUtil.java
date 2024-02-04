package com.example.clock.util;

import com.example.clock.dao.model.CustomerInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES加密解密工具类
 */
@Slf4j
public class AESUtil {
    private static final String KEY_AES = "AES";
    public static final String KEY = "something4usade2";


    /**
     * <h2>加密 - 模式 ECB</h2>
     *
     * @param text 需要加密的文本内容
     */
    public static String encrypt(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(KEY_AES);

            SecretKeySpec secretKeySpec = getSecretKeySpec(KEY);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            // 加密字节数组
            byte[] encryptedBytes = cipher.doFinal(getBytes(text));

            // 将密文转换为 Base64 编码字符串
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setMobile("askjdhk");
        log.info("customerInfo | {}",customerInfo);
    }

    /***
     * <h2>获取一个 AES 密钥规范</h2>
     */
    public static SecretKeySpec getSecretKeySpec(String key) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(getBytes(key), "AES");
        return secretKeySpec;
    }

    /***
     * <h2>String 转 byte</h2>
     * @param str 需要转换的字符串
     */
    public static byte[] getBytes(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            return str.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <h2>解密 - 自定义加密模式</h2>
     *
     * @param text 需要解密的文本内容
     */
    public static String decryptTest(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        // 将密文转换为16字节的字节数组
        byte[] textBytes = Base64.getDecoder().decode(text);

        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(KEY_AES);

            SecretKeySpec secretKeySpec = getSecretKeySpec(KEY);

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // 解密字节数组
            byte[] decryptedBytes = cipher.doFinal(textBytes);

            // 将明文转换为字符串
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
