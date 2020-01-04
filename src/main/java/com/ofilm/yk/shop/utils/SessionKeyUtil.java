package com.ofilm.yk.shop.utils;

import java.net.URLDecoder;

public class SessionKeyUtil {


    public static String getSessionKey(String code,String appid,String secret){

        try {
            code = URLDecoder.decode(code,"utf-8");
            appid = URLDecoder.decode(appid,"utf-8");
            secret = URLDecoder.decode(secret,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+ appid +"&secret=" + secret + "&js_code="+ code + "&grant_type=authorization_code";
        String param = HttpUtil.httpGet(url);

        return param;

    }
}
