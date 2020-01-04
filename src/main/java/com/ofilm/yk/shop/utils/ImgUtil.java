package com.ofilm.yk.shop.utils;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgUtil {

   /* public static String getImgByLoginid(String loginid) {

        String touxiang = "";
        try {
            String result = PostStr("https://fwgj.ofilm.com/image/newgetimage", "{\"code\":\"" + loginid + "\"}");
            if (result != null && !result.equals("")) {
                JSONObject resultJson = JSONObject.fromObject(result);
                resultJson.get("msg");
                JSONObject msgJson = (JSONObject) resultJson.get("msg");
                if (msgJson != null) {
                    touxiang = (String) msgJson.get("touxiang");
                    if (touxiang != null && !touxiang.equals("")) {
                        touxiang = "https://fwgj.ofilm.com/image/" + touxiang;
                    }
                }
            }
            return touxiang;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }*/


    public  static  String getInfoByLoginid(String loginid){

        String result = "";
        String touxiang = "";
        String usercode = "";
        String username = "";
        String path = "";
        try {
             result = PostStr("https://fwgj.ofilm.com/image/newgetimage", "{\"code\":\"" + loginid + "\"}");
            if (result != null && !result.equals("")) {
                JSONObject resultJson = JSONObject.fromObject(result);
                resultJson.get("msg");
                JSONObject msgJson = (JSONObject) resultJson.get("msg");
                if (msgJson != null) {
                    touxiang = (String) msgJson.get("touxiang");
                    usercode  = (String) msgJson.get("usercode");
                    username = (String) msgJson.get("username");
                    path   = (String) msgJson.get("path");
                    if (touxiang != null && !touxiang.equals("")) {
                        touxiang = "https://fwgj.ofilm.com/image/" + touxiang;
                    }
                }
                JSONObject josn = new JSONObject();
                josn.put("touxiang",touxiang);
                josn.put("usercode",usercode);
                josn.put("username",username);
                josn.put("path",path);
                return josn.toString();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return  null;
    }

    /**
     *
     * @param urlPath
     * @param Params
     * @return
     */
    public static String PostStr(String urlPath, String Params) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection httpc = (HttpURLConnection) url.openConnection();
            httpc.setRequestMethod("POST");
            httpc.setRequestProperty("content-type", "application/json");
            httpc.setRequestProperty("accept", "*/*");
            httpc.setRequestProperty("connection", "Keep-Alive");
            httpc.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            httpc.setConnectTimeout(60 * 1000);
            httpc.setReadTimeout(60 * 1000);
            httpc.setDoInput(true);
            httpc.setDoOutput(true);
            OutputStream out = httpc.getOutputStream();// 获取上传参数的输出流
            out.write(Params.getBytes());
            out.flush();
            out.close();
            httpc.connect();
            //System.out.println("得到的响应状态："+httpc.getResponseCode());
            StringBuffer strbuffer = new StringBuffer();
            if (httpc.getResponseCode() == 200) {
                InputStream is = httpc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String str = "";
                while ((str = br.readLine()) != null) {
                    str = new String(str.getBytes(), "UTF-8");
                    strbuffer.append(str);
                    //System.out.println("响应数据："+str);
                }
                is.close();
                httpc.disconnect();
            }
            return strbuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
