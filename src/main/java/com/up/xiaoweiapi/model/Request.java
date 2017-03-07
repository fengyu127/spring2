package com.up.xiaoweiapi.model;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/2.
 */
public class Request {
    private String version;
    private String encoding;
    private String signature;
    private String requestId;
    private String expandcode;
    private String signMethod;
    private JSONObject bizContent;

    public JSONObject getBizContent() {
        return bizContent;
    }

    public void setBizContent(JSONObject bizContent) {
        this.bizContent = bizContent;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getExpandcode() {
        return expandcode;
    }

    public void setExpandcode(String expandcode) {
        this.expandcode = expandcode;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }


}
