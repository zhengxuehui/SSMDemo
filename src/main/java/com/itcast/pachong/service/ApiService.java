package com.itcast.pachong.service;

public interface ApiService {

    //1. Get 请求获取页面数据
    public String getHtml(String url);


    /**
     *2. Get 请求下载图片
     */

    public String getImage(String url);


}
