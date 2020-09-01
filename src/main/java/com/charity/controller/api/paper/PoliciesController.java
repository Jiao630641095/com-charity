package com.charity.controller.api.paper;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.controller.ImgUploadController;
import com.charity.entity.Policies;
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
 * Created by XWD on 2017/10/11.
 */
@Controller
@RequestMapping("/policies")
public class PoliciesController {

    @Value("${net.paper.host}")
    String paperUrl;

    /**
    * 添加政策法规
    * */
    @ResponseBody
    @RequestMapping(value = "/addPolicies" , method = RequestMethod.POST)
    public boolean addPolicies(Policies policies, HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("policiestitle",policies.getPoliciestitle());
        map.put("policiescontent",policies.getPoliciescontent());
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
     * 后台CMS获取政策法规列表
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<Policies> getPoliciesList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(paperUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<Policies> list = JSONObject.parseArray(strResult, Policies.class);
        return list;
    }




    /**
     * 政策法规是否展示
     */
    @RequiresPermissions("policies:status")
    @ResponseBody
    @RequestMapping(value = "/isShow" , method = RequestMethod.POST)
    public boolean updateShow(Policies policies,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("state",policies.getState());
        map.put("policiesid",policies.getPoliciesid().toString());
        String strResult = HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }


    /**
     * 删除政策法规
     */
    @RequiresPermissions("policies:delete")
    @ResponseBody
    @RequestMapping(value = "/deletePolicies" , method = RequestMethod.POST)
    public boolean deletePolicies(String ids,HttpServletRequest request){
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
       * 后台编辑政策法规 回显
       *
       * 前台查看法规详情
       * */
    @ResponseBody
    @RequestMapping(value = "/getPoliciesByid" , method = RequestMethod.GET)
    public Policies getPoliciesByid(Policies policies,HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(paperUrl+uri+"?policiesid="+policies.getPoliciesid());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, Policies.class);
    }


   /**
    * 编辑政策法规
    * */
    @ResponseBody
    @RequestMapping(value = "/updatePolicies" , method = RequestMethod.POST)
    public boolean updatePolicies(Policies policies,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("policiestitle",policies.getPoliciestitle());
        map.put("policiescontent",policies.getPoliciescontent());
        map.put("policiesid",policies.getPoliciesid().toString());
        String strResult =  HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     *  跳转到政策法规列表页面
     * */
    @RequiresPermissions("policies:list")
    @RequestMapping("/gopoliciesList")
    public String gopoliciesList(){
        return "policies/policiesList";
    }

    /**
     *  跳转到政策法规编辑页面
     * */
    @RequiresPermissions("policies:update")
    @RequestMapping("/policiesEdit")
    public String policiesEdit(){
        return "policies/policiesEdit";
    }

    /**
     *  跳转到政策法规添加页面
     * */
    @RequiresPermissions("policies:create")
    @RequestMapping("/policiesAdd")
    public String policiesAdd(){
        return "policies/policiesAdd";
    }

    /**
     *  跳转到政策法规查看页面
     * */
    @RequiresPermissions("policies:view")
    @RequestMapping("/policiesLook")
    public String policiesLook(){
        return "policies/policiesLook";
    }


}
