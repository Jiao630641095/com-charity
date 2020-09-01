package com.charity.controller.api.chanyeyuan;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.common.util.UploadUtil;
import com.charity.entity.UploadResult;
import com.charity.entity.chanyeyuan.TpcPolicies;
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
 * Created by XWD on 2017/12/1.
 */
@Controller
@RequestMapping("/tpcpolicies")
public class TpcPoliciesController {

    @Value("${net.tpc.host}")
    String tpcUrl;

    /**
     *  跳转到园区政策列表页面
     * */
    @RequiresPermissions("tpcpolicies:list")
    @RequestMapping("/golist")
    public String golist(){
        return "chanyeyuan/fspolicies/tpcPoliciesList";
    }


    /**
     * 获取园区政策列表  httpClint
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<TpcPolicies> getList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<TpcPolicies> list = JSONObject.parseArray(strResult, TpcPolicies.class);
        return list;
    }

    /**
     *  管理员 条件查询 获取园区政策列表
     * @param tpcPolicies
     * @return List<TpcPolicies>
     */
    @ResponseBody
    @RequestMapping(value = "/getPoliciesListWhere" , method = RequestMethod.POST)
    public List<TpcPolicies> getPoliciesListWhere(TpcPolicies tpcPolicies,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",tpcPolicies.getTitle());
        map.put("createTime",tpcPolicies.getCreateTime());
        map.put("modifyTime",tpcPolicies.getModifyTime());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<TpcPolicies> list = JSONObject.parseArray(strResult, TpcPolicies.class);
        return list;
    }

    /**
     *  跳转到添加页面
     * */
    @RequiresPermissions("tpcpolicies:create")
    @RequestMapping("/goAdd")
    public String goAdd(){
        return "chanyeyuan/fspolicies/tpcPoliciesAdd";
    }


    /**
     * 管理员 添加 政策
     * @param tpcPolicies
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addPolicies" , method = RequestMethod.POST)
    public boolean addPolicies(TpcPolicies tpcPolicies, HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",tpcPolicies.getTitle());
        map.put("content",tpcPolicies.getContent());
        map.put("author",tpcPolicies.getAuthor());
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
        UploadResult result = UploadUtil.upload(file, "tpcpolicies");
        return result;
    }


    /**
     * 跳转到政策编辑页面
     * @return
     */
    @RequiresPermissions("tpcpolicies:update")
    @RequestMapping("/goupdate")
    public String goupdate(){
        return "chanyeyuan/fspolicies/tpcPoliciesEdit";
    }

    /**
     * 编辑政策回显
     * 查看政策详情
     * @param tpcPolicies
     * @param request
     * @return TpcPolicies
     */
    @ResponseBody
    @RequestMapping(value = "/getPoliciesByid" , method = RequestMethod.GET)
    public TpcPolicies getPoliciesByid(TpcPolicies tpcPolicies, HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri+"?id="+tpcPolicies.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, TpcPolicies.class);
    }


    /**
     * 编辑政策(修改)
     * */
    @ResponseBody
    @RequestMapping(value = "/updateTpcPolicies" , method = RequestMethod.POST)
    public boolean updateTpcPolicies(TpcPolicies tpcPolicies,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",tpcPolicies.getTitle());
        map.put("content",tpcPolicies.getContent());
        map.put("id",tpcPolicies.getId().toString());
        map.put("author",tpcPolicies.getAuthor());
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
    @RequiresPermissions("tpcpolicies:check")
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
    @RequiresPermissions("tpcpolicies:delete")
    @ResponseBody
    @RequestMapping(value = "/deleteTpcPolicies" , method = RequestMethod.POST)
    public boolean deleteTpcPolicies(String ids,HttpServletRequest request){
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
    @RequiresPermissions("tpcpolicies:view")
    @RequestMapping("/tpcPoliciesLook")
    public String tcpPoliciesLook(){
        return "chanyeyuan/fspolicies/tpcPoliciesLook";
    }

}
