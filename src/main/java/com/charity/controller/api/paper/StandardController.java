package com.charity.controller.api.paper;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.controller.ImgUploadController;
import com.charity.entity.Standard;
import com.charity.entity.UploadResult;
import org.apache.http.client.methods.HttpGet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XWD on 2017/10/17.
 */
@Controller
@RequestMapping("/standard")
public class StandardController {


    @Value("${net.paper.host}")
    String paperUrl;


    /**
    * 添加行动规范
    * */
    @ResponseBody
    @RequestMapping(value = "/addStandard" , method = RequestMethod.POST)
    public boolean addStandard(Standard standard, HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("standardtitle",standard.getStandardtitle());
        map.put("standardcontent",standard.getStandardcontent());
        String strResult = HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     * 图片上传
     */
    @ResponseBody
    @RequestMapping(value="/uploadImage",method= RequestMethod.POST)
    public UploadResult uploadImg(@RequestParam MultipartFile file, HttpServletRequest request){
        return ImgUploadController.upload(file,"policiesImage");
    }

    /**
     *  后台CMS获取行动规范列表
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<Standard> getStandardList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(paperUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<Standard> list = JSONObject.parseArray(strResult, Standard.class);
        return list;
    }


    /**
     * 行动规范是否展示
     */
    @RequiresPermissions("standard:status")
    @ResponseBody
    @RequestMapping(value = "/isShow" , method = RequestMethod.POST)
    public boolean updateShow(Standard standard,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("state",standard.getState());
        map.put("standardid",standard.getStandardid().toString());
        String strResult = HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }


    /**
     * 删除行动规范
     */
    @RequiresPermissions("standard:delete")
    @ResponseBody
    @RequestMapping(value = "/deleteStandard" , method = RequestMethod.POST)
    public boolean deleteStandard(String ids,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("ids",ids);
        String strResult =  HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
       * 编辑行动规范 回显
       *
       * 前台查看规范详情
       * */
    @ResponseBody
    @RequestMapping(value = "/getStandardByid" , method = RequestMethod.GET)
    public Standard getStandardByid(Standard standard,HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(paperUrl+uri+"?standardid="+standard.getStandardid());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, Standard.class);
    }


   /**
    * 编辑行动规范
    * */
    @ResponseBody
    @RequestMapping(value = "/updateStandard" , method = RequestMethod.POST)
    public boolean updateStandard(Standard standard,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("standardtitle",standard.getStandardtitle());
        map.put("standardcontent",standard.getStandardcontent());
        map.put("standardid",standard.getStandardid().toString());
        String strResult =  HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     *  跳转到行动规范列表页面
     * */
    @RequiresPermissions("standard:list")
    @RequestMapping("/gostandardList")
    public String gostandardList(){
        return "standard/standardList";
    }

    /**
     *  跳转到行动规范编辑页面
     * */
    @RequiresPermissions("standard:update")
    @RequestMapping("/standardEdit")
    public String standardEdit(){
        return "standard/standardEdit";
    }

    /**
     *  跳转到行动规范添加页面
     * */
    @RequiresPermissions("standard:create")
    @RequestMapping("/standardAdd")
    public String standardAdd(){
        return "standard/standardAdd";
    }

    /**
     *  跳转到行动规范查看页面
     * */
    @RequiresPermissions("standard:view")
    @RequestMapping("/standardLook")
    public String standardLook(){
        return "standard/standardLook";
    }

}
