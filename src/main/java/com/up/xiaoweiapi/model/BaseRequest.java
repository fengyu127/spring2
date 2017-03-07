package com.up.xiaoweiapi.model;

/**
 * Created by Administrator on 2017/3/7.
 */
public abstract class BaseRequest {
    public Request request;
    public String currentUrl;

    public abstract void setbitContent();
}
