package com.ofilm.yk.demo1.controller;

import com.ofilm.yk.demo1.entity.Comment;
import com.ofilm.yk.demo1.service.impl.CommentServiceImpl;
import com.ofilm.yk.shop.utils.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;



@RequestMapping("small/demo1")
@Controller
public class AppController {

    @Autowired
    private CommentServiceImpl commentService;

    @ResponseBody
    @RequestMapping("/login")
    public AjaxResult getOpenInf(String code,String appid,String secret){

        String inf = SessionKeyUtil.getSessionKey(code, appid, secret);
        AjaxResult ajaxResult = AjaxResult.success(inf);
        return  ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/login/inf")
    public  AjaxResult getPsnInf(String msg){

        String info = ImgUtil.getInfoByLoginid(msg);
        AjaxResult ajaxResult = AjaxResult.success(JSONObject.fromObject(info));
        if (StringUtils.isNotEmpty(info)){
            return ajaxResult;
        }else {
            return AjaxResult.error("信息为空！");
        }
    }

    @ResponseBody
    @RequestMapping("/login/btn")
    public  AjaxResult setServerInfo(Comment comment){

        comment.setSmtdate(DateUtil.dateFormat(LocalDateTime.now()));
        commentService.addComment(comment);

        AjaxResult ajaxResult = AjaxResult.success("插入成功！");

            return ajaxResult;

    }
}
