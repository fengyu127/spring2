package com.up.xiaoweiapi.service;


import com.up.utils.Encrypt;
import com.up.xiaoweiapi.model.Request;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by yl on 2017/3/4.
 */
public  class Baseapi implements IBaseApi {

    private static Log logger = LogFactory.getLog(Baseapi.class);
    private  String   privateKay;
    private  String   aesKey;
    private  Request  request;
    private JSONObject bitContent;
    private  String baseUrl="http://tdctest.95516.com/xwins/gateway/";
    private  String url;

    @Override
    public void sortAndSign() throws  Exception{
        HashMap<String, String> hashMap = Encrypt.jsontoHashmap(request);
        String ss=Encrypt.sort(hashMap);
        String signature = Encrypt.rsaSignEncypt(ss, privateKay, "utf-8");
        request.setSignature(signature);

    }

    public Baseapi(String privateKay, String aesKey, Request request, JSONObject bitContent,String url) {
        this.privateKay = privateKay;
        this.aesKey = aesKey;
        this.request = request;
        this.bitContent = bitContent;
        this.url=url;
    }

    public Baseapi(Request request, JSONObject bitContent,String url)
    {
        this.privateKay = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK6vfTpCl94zs9gn" +
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
        this.aesKey = "llv8IckTuVw1RqaD265wrw==";
        this.request = request;
        this.bitContent = bitContent;
        this.url=url;
    }

    @Override
    public void aesEncrpt()  throws Exception{
            String encryptedInfo = bitContent.get("encryptedInfo").toString();
            if(encryptedInfo==null||encryptedInfo.equals(""))            //表示没有加密的相关信息
            {
                 return ;
            }
            else
            {
                    JSONObject jsonEncrypt = new JSONObject(encryptedInfo);
                    String s=Encrypt.aesEncrypt(jsonEncrypt.toString(),aesKey,"utf-8");
                    bitContent.put("encryptedInfo",s);
            }
    }

    @Override
    public void setBitcontent() {
          request.setBizContent(bitContent);
    }


    public  String post() throws Exception
    {
        String currentUrl=baseUrl+url;
        CloseableHttpClient httpclient  = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(currentUrl);
        JSONObject jsonObject=new JSONObject(request);
        StringEntity StringEntity=new StringEntity(jsonObject.toString());
        httpPost.setEntity(StringEntity);

        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        return result;
    }

    public  String  allPost() throws Exception
    {
         aesEncrpt();
         setBitcontent();
         sortAndSign();
        System.out.println(new JSONObject(request).toString());
         return  post();
    }


}
