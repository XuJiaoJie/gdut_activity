package com.rdc.gdut_activity.utils;

import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 使用DES对称加密/解密
 */

public class EncryptUtil {
    private String key = "12345678910";  //要求key至少长度为8个字符
    private SecureRandom mRandom;
    private DESKeySpec mKeySpec ;
    private SecretKeyFactory mKeyFactory;
    private SecretKey mSecretKey;
    private static EncryptUtil sEncryptUtil;

    private EncryptUtil(){
        try {
            mRandom  = new SecureRandom();
            mKeySpec = new DESKeySpec(key.getBytes());
            mKeyFactory = SecretKeyFactory.getInstance("des");
            mSecretKey = mKeyFactory.generateSecret(mKeySpec);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static EncryptUtil getInstance(){
        if (sEncryptUtil == null){
            synchronized (EncryptUtil.class){
                if (sEncryptUtil == null){
                    sEncryptUtil = new EncryptUtil();
                }
            }
        }
        return sEncryptUtil;
    }

    /**
     * 加密
     * @param s
     * @return
     */
    public String encrypt(String s){
        String text = null;
        try {
            Cipher cipher = Cipher.getInstance("des");
            cipher.init(Cipher.ENCRYPT_MODE,mSecretKey,mRandom);
            byte[] cipherData = cipher.doFinal(s.getBytes());
            text = Base64.encodeToString(cipherData,Base64.DEFAULT);
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 解密
     * @param s
     * @return
     */
    public String decrypt(String s){
        String text = null;
        try {
            Cipher cipher = Cipher.getInstance("des");
            cipher.init(Cipher.DECRYPT_MODE,mSecretKey,mRandom);
            byte[] plainData = cipher.doFinal(Base64.decode(s,Base64.DEFAULT));
            text = new String(plainData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }


}
