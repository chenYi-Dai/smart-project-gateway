package com.example.project.util;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: md5加密解密
 */
public class MD5Util {

    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    private static final String slat = "1a2b3c4d";

    public static String inputPassToFormPass(String inputForm){
        String md5SlatStr = slat.charAt(0) + slat.charAt(2) + inputForm + slat.charAt(5) + slat.charAt(4);
        return md5(md5SlatStr);
    }

    public static String formPassToDBPass(String formPass,String slat){
        String str = slat.charAt(0) + slat.charAt(2) + formPass + slat.charAt(5) + slat.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass){
        String fromPass = inputPassToFormPass(inputPass);
        String dbPass =formPassToDBPass(fromPass, slat);
        return dbPass;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //ce21b747de5af71ab5c2e20ff0a60eea
        System.out.println(inputPassToFormPass("123456"));
        //1897a69ef451f0991bb85c6e7c35aa31
        String pas = formPassToDBPass("ce21b747de5af71ab5c2e20ff0a60eea", "1a2b3c4d");
        System.out.println(formPassToDBPass("ce21b747de5af71ab5c2e20ff0a60eea","1a2b3c4d"));
        //System.out.println(inputPassToDBPass("12345611","1a2b3c4d"));
        //System.out.println(inputPassToDBPass("12345611","1a2b3c4d"));


    }
}

