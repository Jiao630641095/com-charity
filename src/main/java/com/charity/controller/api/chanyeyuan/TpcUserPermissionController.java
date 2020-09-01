package com.charity.controller.api.chanyeyuan;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.entity.chanyeyuan.UserPermission;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XWD on 2018/3/5.
 */
@Controller
@RequestMapping("/tpcuserpermission")
public class TpcUserPermissionController {

    @Value("${net.tpc.host}")
    String tpcUrl;

    /**
     *  跳转到列表页面
     * */
    @RequiresPermissions("tpcuserpermission:list")
    @RequestMapping("/golist")
    public String golist(){
        return "chanyeyuan/userpermission/permissionlist";
    }

    /**
     * 获取列表
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<UserPermission> getList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<UserPermission> list = JSONObject.parseArray(strResult, UserPermission.class);
        return list;
    }

    /**
     *  管理员 条件查询 获取列表
     */
    @ResponseBody
    @RequestMapping(value = "/getListWhere" , method = RequestMethod.POST)
    public List<UserPermission> getListWhere(UserPermission userPermission,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("name",userPermission.getName());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<UserPermission> list = JSONObject.parseArray(strResult, UserPermission.class);
        return list;
    }

    /**
     * 授权/夺权
     */
    @RequiresPermissions("tpcuserpermission:create")
    @ResponseBody
    @RequestMapping(value = "/updateIsShow" , method = RequestMethod.POST)
    public boolean updateIsShow(String uids,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("uids",uids);
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        return strResult.equals("true");
    }
}
