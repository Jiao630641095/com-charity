package com.charity.controller.api.paper;

import com.alibaba.fastjson.JSONObject;

import com.charity.common.util.FtpSender;
import com.charity.common.util.HttpClientUtil;
import com.charity.controller.ImgUploadController;
import com.charity.entity.Banner;
import com.charity.entity.UploadResult;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by XWD on 2017/10/11.
 * banner管理
 */
@Controller
@RequestMapping("/banner")
public class BannerController {


    @Value("${net.paper.host}")
    String paperUrl;

    /**
     * 获取banner列表  httpClint
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<Banner> getBannerList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(paperUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<Banner> list = JSONObject.parseArray(strResult, Banner.class);
        return list;
    }

    /**
     *  跳转到banner列表页面
     * */
    @RequiresPermissions("banner:list")
    @RequestMapping("/golist")
    public String goNavs(){
        return "paperbanner/banner";
    }

    /**
     *  跳转到添加banner页面
     * */
    @RequiresPermissions("banner:create")
    @RequestMapping("/gobannerAdd")
    public String gobannerAdd(){
        return "paperbanner/bannerAdd";
    }

    /**
     * 图片上传
     */
    @ResponseBody
    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public UploadResult upload(@RequestParam MultipartFile file){
        UploadResult result = ImgUploadController.upload(file,"bannerImage");
        if(result.getCode()==0){//将图片路径存数据库
            String imgurl = result.getData().getSrc();
            HttpGet httpGet = new HttpGet(paperUrl+"/banner/addBanner?imgurl="+imgurl);
            HttpClientUtil.sendHttpGet(httpGet);
        }
        return result;
    }

    /**
     * banner是否展示  httpClint
     */
    @RequiresPermissions("banner:status")
    @ResponseBody
    @RequestMapping(value = "/isShow" , method = RequestMethod.POST)
    public boolean updateShow(Banner banner,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("state",banner.getState());
        map.put("id",banner.getId().toString());
        String strResult = HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }

    }
    /**
     * 删除banner httpClint
     */
    @RequiresPermissions("banner:delete")
    @ResponseBody
    @RequestMapping(value = "/deleteBanner" , method = RequestMethod.POST)
    public boolean deleteBanner(String ids,HttpServletRequest request){
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
     * 通过id获取banner  httpClint
     */
    @ResponseBody    @RequestMapping(value = "/getbannerByid" , method = RequestMethod.GET)
    public Banner getbannerByid(Banner banner,HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(paperUrl+uri+"?id="+banner.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, Banner.class);
    }



    /**
     *  跳转到编辑banner页面
     * */
    @RequiresPermissions("banner:update")
    @RequestMapping("/gobannerEdit")
    public String gobannerEdit(){
        return "paperbanner/bannerEdit";
    }
    /**
     * 编辑banner路径 httpClint
     */
    @ResponseBody
    @RequestMapping(value = "/updateBanner", method = RequestMethod.POST)
    public boolean updateBanner(Banner banner,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("addressurl",banner.getAddressurl());
        map.put("id",banner.getId().toString());
        String strResult = HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }
    @Autowired
    public void setFtp(@Value("${net.ftp.host}") String host,
                       @Value("${net.ftp.username}") String name,
                       @Value("${net.ftp.password}") String passwd,
                       @Value("${net.ftp.encoding}") String encoding,
                       @Value("${net.ftp.imgurl}") String imgurl
    ){
        FtpSender.setconf(host,name,passwd,encoding,imgurl,"onepaper");
    }
}
