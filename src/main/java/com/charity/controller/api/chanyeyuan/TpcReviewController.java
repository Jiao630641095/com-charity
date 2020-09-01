package com.charity.controller.api.chanyeyuan;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.entity.chanyeyuan.TpcReview;
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
 * Created by XWD on 2018/1/30.
 */
@Controller
@RequestMapping("/tpcreview")
public class TpcReviewController {

    @Value("${net.tpc.host}")
    String tpcUrl;

    /**
     *  跳转到服务审核列表页面
     * */
    @RequiresPermissions("tpcreview:list")
    @RequestMapping("/golist")
    public String golist(){
        return "chanyeyuan/review/reviewlist";
    }

    /**
     *  获取服务审核列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<TpcReview> getList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<TpcReview> list = JSONObject.parseArray(strResult, TpcReview.class);
        return list;
    }


    /**
     *  管理员 条件查询 获取列表
     * @param tpcReview
     * @return List<TpcReview>
     */
    @ResponseBody
    @RequestMapping(value = "/getReviewListWhere" , method = RequestMethod.POST)
    public List<TpcReview> getReviewListWhere(TpcReview tpcReview,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("realName",tpcReview.getRealName());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<TpcReview> list = JSONObject.parseArray(strResult, TpcReview.class);
        return list;
    }


    /**
     * 更改审核状态
     */
    @RequiresPermissions("tpcreview:check")
    @ResponseBody
    @RequestMapping(value = "/updateState" , method = RequestMethod.POST)
    public boolean updateState(String ids,HttpServletRequest request){
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
     *  跳转到查看页面
     * */
    @RequiresPermissions("tpcreview:view")
    @RequestMapping("/tcpReviewLook")
    public String tcpNewsLook(){
        return "chanyeyuan/review/reviewlook";
    }


    /**
     * 查看详情
     * @param tpcReview
     * @param request
     * @return TpcReview
     */
    @ResponseBody
    @RequestMapping(value = "/getReviewByid" , method = RequestMethod.GET)
    public TpcReview getReviewByid(TpcReview tpcReview, HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri+"?id="+tpcReview.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, TpcReview.class);
    }

    /**
     *  跳转到服务驳回页面
     * */
    @RequiresPermissions("tpcreview:check")
    @RequestMapping("/gobohui")
    public String gobohui(){
        return "chanyeyuan/review/bohui";
    }

    /**
     * 驳回
     * */
    @ResponseBody
    @RequestMapping(value = "/updateBohui" , method = RequestMethod.POST)
    public boolean updateBohui(TpcReview tpcReview,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("id",tpcReview.getId().toString());
        map.put("comment",tpcReview.getComment());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }
}
