package com.up.utils;


import com.up.exceptions.AesEncyption;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Administrator on 2017/2/13.
 */
public class Encrypt {

    public static String AES_ALG = "AES";
    public static String AESEncrypt = "AES/CBC/PKCS5Padding";
    public static String RSAEncrypt = "RSA";
    public static String AES_IV = "0102030405060708";
    public static List<String> ArrayOutofReq;
    public static String SIGN_ALGORITHMS = "SHA1WithRSA";

    public static String encryptcontent(String content, String encrytType, String encryptKey, String encryptCharset) throws AesEncyption {
        if (encrytType.equals(AESEncrypt)) {
            return "";
            //  return aesEncrypt(content,encryptKey,encryptCharset);
        } else {
            throw new AesEncyption("当前不支持该算法");
        }
    }


    //aes加密
    public static String aesEncrypt(String content, String encryptKey, String encryptCharset) throws AesEncyption {
        try {

            Cipher cipher = Cipher.getInstance(AESEncrypt);
            IvParameterSpec IvParameterSpec = new IvParameterSpec(AES_IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(encryptKey.getBytes()), AES_ALG), IvParameterSpec);
            byte[] encryptByte = cipher.doFinal(content.getBytes(encryptCharset));
            return new String(Base64.encodeBase64(encryptByte));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesEncyption("加密失败" + e.getMessage() + e.getStackTrace());
        }
    }

    public static String aesUnEncrypt(String content, String encryptKey, String encryptCharset) throws AesEncyption {
        try {
            Cipher cipher = Cipher.getInstance(AESEncrypt);
            IvParameterSpec IvParameterSpec = new IvParameterSpec(AES_IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(encryptKey), AES_ALG), IvParameterSpec);
            byte[] encryptByte = cipher.doFinal(Base64.decodeBase64(content));

            return new String(encryptByte, encryptCharset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesEncyption("解密失败" + e.getMessage() + e.getStackTrace());
        }
    }


    public static HashMap<String, String> jsontoHashmap(Object object) {
        HashMap<String, String> data = new HashMap<>();
        JSONObject o = new JSONObject(object);
        Iterator Iterator = o.keys();
        while (Iterator.hasNext()) {
            String key = Iterator.next().toString();
            if (ArrayOutofReq.contains(key)) {
                JSONObject o1 = o.getJSONObject(key);
                data.put(key, o1.toString());
            } else {
                String value = o.getString(key);
                data.put(key, value);
            }
        }

        return data;
    }


    public static String rsaSign(String content, String encrytType, String encryptKey, String encryptCharset) throws AesEncyption {
        if (encrytType.equals(RSAEncrypt))
            return rsaSignEncypt(content, encryptKey, encryptCharset);
        else {
            throw new AesEncyption("当前不支持该算法");
        }
    }

    //RSA签名
    public static String rsaSignEncypt(String content, String encryptKey, String encryptCharset) throws AesEncyption {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(encryptKey));

            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes(encryptCharset));
            byte[] signed = signature.sign();
            return Base64.encodeBase64String(signed);
        } catch (Exception e) {
            throw new AesEncyption("RSA签名失败" + e.getMessage());
        }
    }

    public static boolean rsaUnSign(String content, String sign, String publicKey) throws AesEncyption {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decodeBase64(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes("utf-8"));

            boolean bverify = signature.verify(Base64.decodeBase64(sign));
            return bverify;

        } catch (Exception e) {
            throw new AesEncyption("验签失败" + e.getMessage());
        }
    }

}