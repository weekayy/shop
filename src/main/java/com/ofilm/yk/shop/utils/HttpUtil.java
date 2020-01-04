package com.ofilm.yk.shop.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class HttpUtil {

    /**
     * 发送GET请求
     * @param url
     * @return
     */
    public static String httpGet(String url) {

        URL u = null;
        try {
          u  = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String str = "";
            StringBuffer sb = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null){

                sb.append(str);
                sb.append("\n");
            }
            str = sb.toString();
            return  str;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    String httpPost(String url, Map<String,String> params){
        URL u = null;
        HttpURLConnection connection = null;
        //1.请求参数
        StringBuffer sb = new StringBuffer();
        if(params != null) {
            for (Map.Entry<String, String> e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
        //2.尝试发送请求
        try {
            u = new URL(url);
            connection=(HttpURLConnection) u.openConnection();
            //3.设置
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(),"utf-8");
            osw.write(sb.toString());
            osw.flush();
            osw.close();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }

        //读取返回值
        StringBuffer buffer = new StringBuffer();
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String temp="";
            if ((temp=br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return buffer.toString();
    }
}
