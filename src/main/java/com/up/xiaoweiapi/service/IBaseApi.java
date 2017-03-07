package com.up.xiaoweiapi.service;

/**
 * Created by yl on 2017/3/4.
 */
public interface IBaseApi {
    public void aesEncrpt() throws Exception;

    public void setBitcontent();

    public void sortAndSign() throws Exception;//排序签名

    public String post() throws Exception;
}
