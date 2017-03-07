package com.up.xiaoweiapi.service;


import com.up.utils.Encrypt;
import com.up.xiaoweiapi.model.Request;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 使用方法：初始化构造函数，执行allpost即可
 * <p>
 * Created by yl on 2017/3/4.
 */
public class Baseapi implements IBaseApi {

    private static Log logger = LogFactory.getLog(Baseapi.class);
    private String privateKay;
    private String aesKey;
    private Request request;
    private JSONObject bitContent;
    private String url;

    /* 测试地址*/
    private String baseUrl = "http://tdctest.95516.com/xwins/gateway/";
    // private String baseUrl="http://127.0.0.1:3000/";
    private String testAeseyConst = "llv8IckTuVw1RqaD265wrw==";
    private String PrivatekeyConst = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK6vfTpCl94zs9gn" +
            "L9TGx9GBvORoduqr00MJ46wn6Y0HeCpwyBf1XelOyOM18GvKUOIgtm1CzyJ4fk5Y" +
            "3R1Jv1JdOCDM+AZCwuVvYzc1yoEpbx01v7G2VM3cJNLFj/xCAVMrW0Jq02gty5WX" +
            "LL9s3B5AYJOfTHvnqAfreogcx2UdAgMBAAECgYBpTG2PPe8vyTjCtjR17XEr0Cne" +
            "AT6VjQfnJEHyV4jIM1VzN9LV5lk6tvwPQkdvomAJPzDYQep8W+e/MH/xFLBXZTnQ" +
            "H0z6a8rG5F+Gtpg96rPYN+LZmXh3CvwiTEHimMqVEX7G/97ZUAbpKYzE6FX344QC" +
            "mIMlHUHkHsNkLtLNYQJBAOLH8Gc/eXAqok6zMqGA5W/4+ND0+Q2r2mRToIY1z75J" +
            "PfGgbZsfE/VSI+3Mm73RJkBzwfuS73qXs+TCc6mXehkCQQDFMUBMi3F6S10jYXBC" +
            "g6eQauFOWBO8YPc66KB3bj511LNKNBXf8DLDoOuLaV09enCHNMsukImuq1GrcTZ9" +
            "L6ulAkAhngfqFkO3N+q1heTkggoA7kRcHWRp/WazZp4uJv3ztEHFdsWosBOyUwnW" +
            "b3VKzx0/gqln1KFBaAmXyKeCpVzJAkA5bimq4WXQV0it+D/or01LC0XJOm+tCpSW" +
            "jI/HmM0KJkN9VgQU73DpduGC/dHRCOrjBeYzDpd6zpx/kP5soUidAkEAuyExg2te" +
            "vtJ/EA87G3/EgIpFSxzD25mGAzKC0H7nyXbuO7Ho4WX5tN2ong7ifxgJjJvv6Zrt" +
            "mg0oK+lMlnmpGg==";



   /* private String baseUrl = "https://smbp.95516.com/xwins/gateway/";
    private  String testAeseyConst="Uh2uG00UsAF3HYcg3UxJkQ==";

    private  String PrivatekeyConst="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMadogk6mCAU+kyU" +
            "7GddbPq6F5PJ7+0CgVkdRzB7pM6gP65ZMGBebGlZOGI5UQiA7376+oc8tirZ6mNW" +
            "B1r9AnPQXe1RDluyIdwYr/+cl7BVlx15PLnkgTAg4biT784PwVKsdpW/FShcodv2" +
            "lLY1bMhWCRAD0em77NFf75dnW1AzAgMBAAECgYB5c2zhLdZWT92atLo2nteEHgVK" +
            "1wICsTR9VLzydbkarL0kFN/THgo3BVrIHXpis80/naNSzRor6+M66SFIT0yOh3ej" +
            "aCgmVjvO6QXjk4MDLzi3MQsN+HxLAFiiVzv+JB92C6kkZvlYeGLpw7h134206Xr2" +
            "H2tLeUZNrNWgDKylWQJBAOZFCZ8yHsedgtOjidTZOIuZolp/KQNpIVHrGoK5Ccbg" +
            "u/GZ4Esd0lk75m4w88UYtr8dRNKM6fYsA5hmvmYkaN0CQQDczx8Q/LfH5jzjiWsy" +
            "+dpZLAPZ0YwuPY1r3kf1IUtQX3xcAVS36ssgaQScYFnVtyM4Iusi84iJUUaR1b20" +
            "8YRPAkEAhyD+a4CjFWs2ifIPgWvTxgBriQXUUJ8SIZuBHDNwLFhXkolFWW9vsFP5" +
            "frKVaxV8sJyBdbmh1oevuHCazSAStQJAOEeSY4IGVa0ZV+VurU+Bl7jgQmRmlmKZ" +
            "6SR+srwJtjKgP9MXBMmTfG9H/ZORUiebknNT+1SXOGi+0+zmrXfhgQJAKaAbMvkW" +
            "o+CAoUOM/B+0iFzozUFiiebA9bIuCGq9u5EP9HKR6uATD0gTTFcwk0pJ+3uhF8Bp" +
            "ExKwLW1/5xJVnA==";*/


    /**
     * 排序和签名用
     *
     * @throws Exception
     */
    @Override
    public void sortAndSign() throws Exception {
        HashMap<String, String> hashMap = Encrypt.jsontoHashmap(request);
        String ss = Encrypt.sort(hashMap);
        System.out.println(ss);
        String signature = Encrypt.rsaSignEncypt(ss, privateKay, "utf-8");
        request.setSignature(signature);

    }

    /**
     * @param privateKay RSA公钥
     * @param aesKey     AES秘钥
     * @param request
     * @param bitContent
     * @param url        发送的url(此处url为接口最后一个字符串)
     */
    public Baseapi(String privateKay, String aesKey, Request request, JSONObject bitContent, String url) {
        this.privateKay = privateKay;
        this.aesKey = aesKey;
        this.request = request;
        this.bitContent = bitContent;
        this.url = url;
    }

    /**
     * 以指定公私钥的构造函数
     *
     * @param request
     * @param bitContent
     * @param url
     */
    public Baseapi(Request request, JSONObject bitContent, String url) {
        this.aesKey = testAeseyConst;
        this.privateKay = PrivatekeyConst;
        this.request = request;
        this.bitContent = bitContent;
        this.url = url;
    }

    /**
     * 判断是否需要加密
     *
     * @throws Exception
     */
    @Override
    public void aesEncrpt() throws Exception {
        if (!bitContent.has("encryptedInfo"))            //表示没有加密的相关信息
        {
            return;
        } else {
            String encryptedInfo = bitContent.get("encryptedInfo").toString();
            JSONObject jsonEncrypt = new JSONObject(encryptedInfo);
            String s = Encrypt.aesEncrypt(jsonEncrypt.toString(), aesKey, "utf-8");
            bitContent.put("encryptedInfo", s);
        }
    }

    /**
     * 放入bitcontent
     */
    @Override
    public void setBitcontent() {
        request.setBizContent(bitContent);
    }


    /**
     * 发送post请求
     *
     * @return
     * @throws Exception
     */
    public String post() throws Exception {
        String currentUrl = baseUrl + url;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(currentUrl);
        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
        pairList.add(new BasicNameValuePair("version", request.getVersion()));
        pairList.add(new BasicNameValuePair("encoding", request.getEncoding()));
        pairList.add(new BasicNameValuePair("signature", request.getSignature()));
        pairList.add(new BasicNameValuePair("requestId", request.getRequestId()));
        pairList.add(new BasicNameValuePair("expandcode", request.getExpandcode()));
        pairList.add(new BasicNameValuePair("signMethod", request.getSignMethod()));
        pairList.add(new BasicNameValuePair("bizContent", request.getBizContent().toString()));
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        return result;
    }

    public boolean checkSignature(String s) throws Exception {
        JSONObject jsonObject = new JSONObject(s);
        String signature = jsonObject.get("signature").toString();
        jsonObject.remove("signature");
        return Encrypt.rsaUnSign(jsonObject.toString(), signature, privateKay);
    }


    /**
     * 所有操作的组合
     *
     * @return
     * @throws Exception
     */
    public String allPost() throws Exception {
        aesEncrpt();
        setBitcontent();
        sortAndSign();
        JSONObject jsonObject = new JSONObject(request);
        System.out.println(jsonObject.toString());
        String s = post();
        System.out.println(s);
        System.out.println(checkSignature(s));
        return "";
    }


}
