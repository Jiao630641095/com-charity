package com.charity.controller.api.chanyeyuan;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.entity.chanyeyuan.Train;
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
 * Created by XWD on 2018/3/12.
 */
@Controller
@RequestMapping("/tpctrain")
public class TpcTrainController {

    @Value("${net.tpc.host}")
    String tpcUrl;

    /**
     *  跳转到列表页面
     * */
    @RequiresPermissions("tpctrain:list")
    @RequestMapping("/golist")
    public String golist(){
        return "chanyeyuan/train/trainlist";
    }


    /**
     * 获取列表  httpClint
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<Train> getList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<Train> list = JSONObject.parseArray(strResult, Train.class);
        return list;
    }

    /**
     * 更改审核状态
     */
    @RequiresPermissions("tpctrain:check")
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
     *  管理员 条件查询
     * @param train
     * @return List<Train>
     */
    @ResponseBody
    @RequestMapping(value = "/getListWhere" , method = RequestMethod.POST)
    public List<Train> getListWhere(Train train,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("realName",train.getRealName());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<Train> list = JSONObject.parseArray(strResult, Train.class);
        return list;
    }


}
