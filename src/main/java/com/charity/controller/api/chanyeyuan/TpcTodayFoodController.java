package com.charity.controller.api.chanyeyuan;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.common.util.UploadUtil;
import com.charity.entity.UploadResult;
import com.charity.entity.chanyeyuan.TpcTodayFood;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XWD on 2018/5/15.
 */
@Controller
@RequestMapping("/tpctodayfood")
public class TpcTodayFoodController {

    @Value("${net.tpc.host}")
    String tpcUrl;

    /**
     *  跳转到菜品列表页面
     * */
    @RequiresPermissions("tpctodayfood:list")
    @RequestMapping("/golist")
    public String golist(){
        return "chanyeyuan/todayfood/todayfoodlist";
    }

    /**
     * 获取菜品列表  httpClint
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<TpcTodayFood> getList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<TpcTodayFood> list = JSONObject.parseArray(strResult, TpcTodayFood.class);
        return list;
    }

    /**
     *  管理员 条件查询 获取菜品列表
     * @param tpcTodayFood
     * @return List<TpcTodayFood>
     */
    @ResponseBody
    @RequestMapping(value = "/getFoodListWhere" , method = RequestMethod.POST)
    public List<TpcTodayFood> getNewsListWhere(TpcTodayFood tpcTodayFood,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("name",tpcTodayFood.getName());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<TpcTodayFood> list = JSONObject.parseArray(strResult, TpcTodayFood.class);
        return list;
    }

    /**
     *  跳转到新闻添加页面
     * */
    @RequiresPermissions("tpctodayfood:create")
    @RequestMapping("/goAdd")
    public String goAdd(){
        return "chanyeyuan/todayfood/todayfoodadd";
    }

    /**
     * 管理员 添加 今日菜品
     * @param tpcTodayFood
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addFood" , method = RequestMethod.POST)
    public boolean addFood(TpcTodayFood tpcTodayFood, HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("name",tpcTodayFood.getName());
        map.put("photo",tpcTodayFood.getPhoto());
        map.put("price",tpcTodayFood.getPrice().toString());
        map.put("unit",tpcTodayFood.getUnit());
        String strResult = HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     *  上传菜品配图
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UploadResult uploadImg(@RequestParam MultipartFile file, HttpServletRequest request) {
        UploadResult result = UploadUtil.upload(file, "tpctodayfood");
        return result;
    }

    /**
     * 跳转到政策编辑页面
     * @return
     */
    @RequiresPermissions("tpctodayfood:update")
    @RequestMapping("/goupdate")
    public String goupdate(){
        return "chanyeyuan/todayfood/todayfoodedit";
    }

    /**
     * 编辑菜品回显
     * @param tpcTodayFood
     * @param request
     * @return TpcTodayFood
     */
    @ResponseBody
    @RequestMapping(value = "/getFoodByid" , method = RequestMethod.GET)
    public TpcTodayFood getFoodByid(TpcTodayFood tpcTodayFood, HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri+"?id="+tpcTodayFood.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, TpcTodayFood.class);
    }

    /**
     * 编辑菜品
     * */
    @ResponseBody
    @RequestMapping(value = "/updateTpcTodayFood" , method = RequestMethod.POST)
    public boolean updateTpcTodayFood(TpcTodayFood tpcTodayFood,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("id",tpcTodayFood.getId().toString());
        map.put("name",tpcTodayFood.getName());
        map.put("photo",tpcTodayFood.getPhoto());
        map.put("price",tpcTodayFood.getPrice().toString());
        map.put("unit",tpcTodayFood.getUnit());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     * 删除菜品
     */
    @RequiresPermissions("tpctodayfood:delete")
    @ResponseBody
    @RequestMapping(value = "/deleteTpcFood" , method = RequestMethod.POST)
    public boolean deleteTpcFood(String ids,HttpServletRequest request){
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
     * 更改展示状态
     */
    @RequiresPermissions("tpctodayfood:status")
    @ResponseBody
    @RequestMapping(value = "/updateStatus" , method = RequestMethod.POST)
    public boolean updateStatus(Integer id,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("id",id.toString());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

}
