package com.charity.controller.api.paper;

import com.alibaba.fastjson.JSONObject;

import com.charity.common.util.HttpClientUtil;
import com.charity.entity.Nav;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XWD on 2017/10/11.
 * 导航管理
 */
@Controller
@RequestMapping("/nav")
public class NavController {

    @Value("${net.paper.host}")
    String paperUrl;


    /**
    *
    * 获取导航栏集合
    *
    * */
    @ResponseBody
    @RequestMapping("/getNavs")
    public List<Nav> getNavs(HttpServletRequest request){
          String uri = request.getRequestURI();
          HttpGet httpGet = new HttpGet(paperUrl+uri);
          String strResult =  HttpClientUtil.sendHttpGet(httpGet);
          List<Nav> list = JSONObject.parseArray(strResult, Nav.class);
          return list;
    }

    /**
     * 到导航列表页面
     * @return
     */
    @RequiresPermissions("topNav:list")
    @RequestMapping("/goNavs")
    public String goNavs(){
        return "paperNav/topnav";
    }

    /**
    * 添加或修改导航
    * */
    @RequiresPermissions("topNav:update")
    @RequestMapping("/saveOrUpateNavs")
    @ResponseBody
    public Integer saveOrUpateNavs(Nav nav,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
       map.put("id",nav.getId().toString());
       map.put("pid",nav.getPid().toString());
       map.put("url",nav.getUrl());
       map.put("title",nav.getTitle());
        String strResult = HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        return  Integer.parseInt(strResult);
    }

    /**
    *  删除top导航
    * */
    @RequiresPermissions("topNav:delete")
    @RequestMapping("/deleteNav")
    @ResponseBody
    public boolean deleteNav(Nav nav,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("id",nav.getId().toString());
        String strResult =  HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
        *  制作top导航
        * */
    @RequestMapping("/makeNav")
    @ResponseBody
    public boolean makeNavJs(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(paperUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }




}
