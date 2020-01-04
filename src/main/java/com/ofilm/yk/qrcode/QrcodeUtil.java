package com.ofilm.yk.qrcode;



import com.ofilm.yk.shop.utils.HttpUtil;
import com.ofilm.yk.shop.utils.ImgUtil;
import com.ofilm.yk.shop.utils.StringUtils;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 生成小程序对应的二维码
 */
public class QrcodeUtil {

    public static void main(String[] args) {

        String appid= "";
        String secret = "";
        try{
            appid = URLDecoder.decode("wxb02e586656822bc8","utf-8");
            secret = URLDecoder.decode("13b939c19cc5b4df9d5431f4aa1e345e","utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret+"";
        String accessToken = HttpUtil.httpGet(url);
        JSONObject jsonObject = JSONObject.fromObject(accessToken);
        if(StringUtils.isNotEmpty(accessToken)){
            String access_token = (String)jsonObject.get("access_token");
            String Img = getQrCode(access_token, "E:/", "yk.png", "pages/index/index", 300);
        }

       // String Img = ImgUtil.getImgByLoginid("NF1675");
       // System.out.println("图片地址："+Img);
    }

    /**
     *
     * @param accessToken 小程序token
     * @param folder 文件夹
     * @param fileName 文件名
     * @param path 扫码进入的小程序页面路径
     * @param width 二维码的宽度
     * @return 得到的图片路径
     */
    public  static String getQrCode(String accessToken,String folder,String fileName,String path,int width){

        String createwxaqrcodeStr = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=" + accessToken;
        InputStream is = null;

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("path",path);
            jsonObject.put("width",width);
            String params = jsonObject.toString();
            is = httpJson(createwxaqrcodeStr, params);
            boolean flag  = saveToImgByInputStream(is, folder, fileName);

             System.out.println("是否生成成功:"+flag+",图片路径:"+folder+"/"+fileName);
            if(flag){
                return folder+"/"+fileName;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Json参数的HTTP请求,返回一个InputStream流
     * @param url
     * @param param
     * @return
     */
    public static InputStream httpJson(String url, String param){

        URL u = null;
        HttpURLConnection con = null;

        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            //// POST 只能为大写，严格限制，post会不识别
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.append(param);
            osw.flush();
            osw.close();
            return con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPostHttpResponse(String url, Map<String,String> params){

        HttpClient httpclient = HttpClientBuilder.create().build();
        String ResponseStr="";
        /** */
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> list = new ArrayList();
        if(params != null){
            Set<String> keySet = params.keySet();
            for (String key: keySet) {
                list.add(new BasicNameValuePair(key,params.get(key)));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        /** */
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpclient.execute(httpPost);
        } catch (Exception e){
            e.printStackTrace();
        }
        HttpEntity httpEntity = httpResponse.getEntity();
        try {
            ResponseStr = EntityUtils.toString(httpEntity);
        }catch (ParseException | IOException e){
            e.printStackTrace();
        }
        return ResponseStr;
    }

    /**
     * 根据二进制转化为文件保存
     * @param inputStream
     * @param imgPath
     * @param imgName
     * @return
     */
    public static boolean saveToImgByInputStream(InputStream inputStream,String imgPath,String imgName){

        if(inputStream != null){
            FileOutputStream fos = null;
            try {
                createDir(imgPath);
                //
                File file = new File(imgPath,imgName);
                fos = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int n;
                while ((n = inputStream.read(bytes)) != -1){
                    fos.write(bytes,0,n);
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if (fos != null){
                        fos.flush();
                        fos.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public static void createDir(String destDirName){

        try {
            destDirName = URLDecoder.decode(destDirName,"GB2312");
        }catch (Exception e){
            e.printStackTrace();
        }
        File dir = new File(destDirName);
        if (dir.exists()){
            System.out.println("创建目录"+destDirName+"失败，该目录已经存在");
        }
        if (!destDirName.endsWith(File.separator)){
            destDirName = destDirName + File.separator;
        }

        /** 创建目录 */
        if(dir.mkdirs()){
            System.out.println("创建目录"+destDirName+"成功！");
        }else {
            System.out.println("创建目录"+destDirName+"失败！");
        }
    }

}
