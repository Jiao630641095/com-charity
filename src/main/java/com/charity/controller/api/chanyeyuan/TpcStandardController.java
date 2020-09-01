package com.charity.controller.api.chanyeyuan;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.common.util.UploadUtil;
import com.charity.entity.UploadResult;
import com.charity.entity.chanyeyuan.TpcStandard;
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
 * Created by XWD on 2017/12/4.
 */
@Controller
@RequestMapping("/tpcstandard")
public class TpcStandardController {

    @Value("${net.tpc.host}")
    String tpcUrl;

    /**
     *  跳转到优惠政策列表页面
     * */
    @RequiresPermissions("tpcstandard:list")
    @RequestMapping("/golist")
    public String golist(){
        return "chanyeyuan/fsstandard/tpcStandardList";
    }

    /**
     * 获取优惠政策列表  httpClint
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<TpcStandard> getList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<TpcStandard> list = JSONObject.parseArray(strResult, TpcStandard.class);
        return list;
    }

    /**
     *  管理员 条件查询 获取优惠政策列表
     * @param tpcStandard
     * @return List<TpcStandard>
     */
    @ResponseBody
    @RequestMapping(value = "/getStandardListWhere" , method = RequestMethod.POST)
    public List<TpcStandard> getStandardListWhere(TpcStandard tpcStandard,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",tpcStandard.getTitle());
        map.put("createTime",tpcStandard.getCreateTime());
        map.put("modifyTime",tpcStandard.getModifyTime());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<TpcStandard> list = JSONObject.parseArray(strResult, TpcStandard.class);
        return list;
    }

    /**
     *  跳转到添加页面
     * */
    @RequiresPermissions("tpcstandard:create")
    @RequestMapping("/goAdd")
    public String goAdd(){
        return "chanyeyuan/fsstandard/tpcStandardAdd";
    }

    /**
     * 管理员 添加 政策
     * @param tpcStandard
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addStandard" , method = RequestMethod.POST)
    public boolean addStandard(TpcStandard tpcStandard, HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",tpcStandard.getTitle());
        map.put("content",tpcStandard.getContent());
        map.put("author",tpcStandard.getAuthor());
        String strResult = HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     *  上传政策配图
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UploadResult uploadImg(@RequestParam MultipartFile file, HttpServletRequest request) {
        UploadResult result = UploadUtil.upload(file, "tpcstandard");
        return result;
    }

    /**
     * 跳转到政策编辑页面
     * @return
     */
    @RequiresPermissions("tpcstandard:update")
    @RequestMapping("/goupdate")
    public String goupdate(){
        return "chanyeyuan/fsstandard/tpcStandardEdit";
    }

    /**
     * 编辑政策 回显
     * 查看政策详情
     * @param tpcStandard
     * @param request
     * @return TpcStandard
     */
    @ResponseBody
    @RequestMapping(value = "/getStandardByid" , method = RequestMethod.GET)
    public TpcStandard getStandardByid(TpcStandard tpcStandard, HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri+"?id="+tpcStandard.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, TpcStandard.class);
    }

    /**
     * 编辑政策(修改)
     * */
    @ResponseBody
    @RequestMapping(value = "/updateTpcStandard" , method = RequestMethod.POST)
    public boolean updateTpcStandard(TpcStandard tpcStandard,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",tpcStandard.getTitle());
        map.put("content",tpcStandard.getContent());
        map.put("id",tpcStandard.getId().toString());
        map.put("author",tpcStandard.getAuthor());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     * 更改审核状态
     */
    @RequiresPermissions("tpcstandard:check")
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
     * 删除政策
     */
    @RequiresPermissions("tpcstandard:delete")
    @ResponseBody
    @RequestMapping(value = "/deleteTpcStandard" , method = RequestMethod.POST)
    public boolean deleteTpcStandard(String ids,HttpServletRequest request){
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
     *  跳转到政策查看页面
     * */
    @RequiresPermissions("tpcstandard:view")
    @RequestMapping("/tpcStandardLook")
    public String tpcStandardLook(){
        return "chanyeyuan/fsstandard/tpcStandardLook";
    }
}
