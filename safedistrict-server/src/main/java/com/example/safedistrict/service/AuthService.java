package com.example.safedistrict.service;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

//多个登录差不多都要共用这些方法，所以统一放到这个接口中
public interface AuthService {
    public abstract String getAccessToken(String code);
    public abstract String getOpenId(String accessToken);
    public abstract String refreshToken(String code);
    public abstract String getAuthorizationUrl() throws UnsupportedEncodingException;
    public abstract JSONObject getUserInfo(String accessToken, String openId);
}

