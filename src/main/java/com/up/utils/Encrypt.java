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
import java.util.*;


/**
 * Created by Administrator on 2017/2/13.
 */
public class Encrypt {

    public static String AES_ALG = "AES";
    public static String AESEncrypt = "AES/CBC/PKCS5Padding";
    public static String RSAEncrypt = "RSA";
    public static byte[] AES_IV;

    public static List<String> ArrayOutofReq;
    public static String SIGN_ALGORITHMS = "SHA1WithRSA";
    public static String out = "bizContent";

    public Encrypt() {
        //   ArrayOutofReq.add("bizContent");
    }

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
            AES_IV = initIv(AESEncrypt);
            IvParameterSpec IvParameterSpec = new IvParameterSpec(AES_IV);
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
            AES_IV = initIv(AESEncrypt);
            IvParameterSpec IvParameterSpec = new IvParameterSpec(AES_IV);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(encryptKey), AES_ALG), IvParameterSpec);
            byte[] encryptByte = cipher.doFinal(Base64.decodeBase64(content));
            return new String(encryptByte, encryptCharset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesEncyption("解密失败" + e.getMessage() + e.getStackTrace());
        }
    }


    public static HashMap<String, String> jsonToHashMap(Object object) {
        HashMap<String, String> data = new HashMap<String, String>();
        JSONObject o = new JSONObject(object);
        //    System.out.println(o.toString());
        Iterator Iterator = o.keys();
        while (Iterator.hasNext()) {
            String key = Iterator.next().toString();
            if (out.contains(key)) {
                JSONObject o1 = o.getJSONObject(key);
                Iterator iterator = o1.keys();
                while (iterator.hasNext()) {
                    String key1 = iterator.next().toString();
                    String value = o1.getString(key1);
                    data.put(key1, value);
                }
            } else if (!key.equals("signature")) {
                String value = o.getString(key);
                data.put(key, value);
            }
        }
        return data;
    }

    public static HashMap<String, String> jsonToHashMap(String object) {
        HashMap<String, String> data = new HashMap<String, String>();
        JSONObject o = new JSONObject(object);
        //    System.out.println(o.toString());
        Iterator Iterator = o.keys();
        while (Iterator.hasNext()) {
            String key = Iterator.next().toString();
            if (out.contains(key)) {
                JSONObject o1 = o.getJSONObject(key);
                Iterator iterator = o1.keys();
                while (iterator.hasNext()) {
                    String key1 = iterator.next().toString();
                    String value = o1.getString(key1);
                    data.put(key1, value);
                }
            } else if (!key.equals("signature")) {
                String value = o.getString(key);
                data.put(key, value);
            }
        }
        return data;
    }


    public static String sort(HashMap<String, String> hashmap) {
        String data = "";
        Iterator iterator = hashmap.keySet().iterator();
        List<String> list = sortIterator(iterator);
        for (int i = 0; i < list.size(); i++) {

            if (i < list.size() - 1)
                data = data + list.get(i) + "=" + hashmap.get(list.get(i)) + "&";
            else if (i == list.size() - 1)
                data = data + list.get(i) + "=" + hashmap.get(list.get(i));
        }
        return data;
    }


    public static List<String> sortIterator(Iterator iterator) {
        List<String> list = new ArrayList<String>();
        while (iterator.hasNext()) {
            list.add(iterator.next().toString());
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return list;
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
            throw new AesEncyption("验签失败" + e.getMessage() + e.getStackTrace());
        }
    }


    /**
     * 初始向量的方法, 全部为0. 这里的写法适合于其它算法,针对AES算法的话,IV值一定是128位的(16字节).
     *
     * @param fullAlg
     * @return
     * @throws
     */
    private static byte[] initIv(String fullAlg) {

        try {
            Cipher cipher = Cipher.getInstance(fullAlg);
            int blockSize = cipher.getBlockSize();

            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        } catch (Exception e) {

            int blockSize = 16;
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        }
    }


}