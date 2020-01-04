package com.ofilm.yk.shop.utils;

import java.security.MessageDigest;

public class MD5Utils {
    public static String encodeByMD5(String string) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bs = digest.digest(string.getBytes("utf-8"));
            for (byte b : bs) {
                int x = b & 255;
                //把取出的数据转成十六进制数
                String s = Integer.toHexString(x);
                if (x < 16) {
                    sb.append("0");
                }
                sb.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
