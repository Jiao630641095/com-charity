package com.charity.controller.api.chanyeyuan;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.entity.chanyeyuan.CompanyJob;
import com.charity.entity.chanyeyuan.TpcRental;
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
 * Created by XWD on 2018/3/7.
 */
@Controller
@RequestMapping("/tpccompany")
public class CompanyJobController {

    @Value("${net.tpc.host}")
    String tpcUrl;


    /**
     *  跳转到列表页面
     * */
    @RequiresPermissions("tpccompany:list")
    @RequestMapping("/golist")
    public String golist(){
        return "chanyeyuan/company/companyList";
    }

    /**
     * 获取列表
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<CompanyJob> getList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<CompanyJob> list = JSONObject.parseArray(strResult, CompanyJob.class);
        return list;
    }
    /**
     *  管理员 条件查询 获取列表
     */
    @ResponseBody
    @RequestMapping(value = "/getListWhere" , method = RequestMethod.POST)
    public List<CompanyJob> getListWhere(CompanyJob companyJob,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("cname",companyJob.getCname());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<CompanyJob> list = JSONObject.parseArray(strResult, CompanyJob.class);
        return list;
    }


    /**
     * 更改审核状态
     */
    @RequiresPermissions("tpccompany:check")
    @ResponseBody
    @RequestMapping(value = "/updateIsShow" , method = RequestMethod.POST)
    public boolean updateIsShow(String ids,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("ids",ids);
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     * 更改推荐状态
     */
    @RequiresPermissions("tpccompany:isUp")
    @ResponseBody
    @RequestMapping(value = "/updateIsUp" , method = RequestMethod.POST)
    public boolean updateIsUp(String ids,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("ids",ids);
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }


    /**
     *  跳转到详情页面
     * */
    @RequiresPermissions("tpccompany:view")
    @RequestMapping("/tpcCompanyJobLook")
    public String tpcCompanyJobLook(){
        return "chanyeyuan/company/companyJobLook";
    }

    /**
     *  获取详情信息
     * @param companyJob
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCompanyJobById" , method = RequestMethod.GET)
    public CompanyJob getCompanyJobById(CompanyJob companyJob, HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri+"?id="+companyJob.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, CompanyJob.class);
    }


}
