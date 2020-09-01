package com.charity.controller.api.chanyeyuan;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.common.util.UploadUtil;
import com.charity.entity.UploadResult;
import com.charity.entity.chanyeyuan.TpcNews;
import com.charity.entity.chanyeyuan.TpcNewsType;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XWD on 2017/11/28.
 */
@Controller
@RequestMapping("/tpcnews")
public class TpcNewsController {

    @Value("${net.tpc.host}")
    String tpcUrl;


    /**
     *  跳转到新闻列表页面
     * */
    @RequiresPermissions("tpcnews:list")
    @RequestMapping("/golist")
    public String golist(){
        return "chanyeyuan/news/newslist";
    }


    /**
     * 获取新闻列表  httpClint
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<TpcNews> getList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<TpcNews> list = JSONObject.parseArray(strResult, TpcNews.class);
        return list;
    }

    /**
     * 获取新闻类型列表  httpClint
     */
    public List<TpcNewsType> getNewsType(){
        HttpGet httpGet = new HttpGet(tpcUrl+"/tpcnews/getNewsType");
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<TpcNewsType> list = JSONObject.parseArray(strResult, TpcNewsType.class);
        return list;
    }

    /**
     *  管理员 条件查询 获取新闻列表
     * @param tpcNews
     * @return List<TpcNews>
     */
    @ResponseBody
    @RequestMapping(value = "/getNewsListWhere" , method = RequestMethod.POST)
    public List<TpcNews> getNewsListWhere(TpcNews tpcNews,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",tpcNews.getTitle());
        map.put("createTime",tpcNews.getCreateTime());
        map.put("modifyTime",tpcNews.getModifyTime());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<TpcNews> list = JSONObject.parseArray(strResult, TpcNews.class);
        return list;
    }

    /**
     *  跳转到新闻添加页面
     * */
    @RequiresPermissions("tpcnews:create")
    @RequestMapping("/goAdd")
    public String goAdd(ModelMap modelMap){
        List<TpcNewsType> list = this.getNewsType();
        modelMap.put("typeList", list);
        return "chanyeyuan/news/newsAdd";
    }

    /**
     * 管理员 添加 新闻
     * @param tpcNews
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addNews" , method = RequestMethod.POST)
    public boolean addNews(TpcNews tpcNews, HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",tpcNews.getTitle());
        map.put("img",tpcNews.getImg());
        map.put("abstracted",tpcNews.getAbstracted());
        map.put("content",tpcNews.getContent());
        map.put("author",tpcNews.getAuthor());
        map.put("tid",tpcNews.getTid().toString());
        String strResult = HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     *  上传新闻配图
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UploadResult uploadImg(@RequestParam MultipartFile file,HttpServletRequest request) {
        UploadResult result = UploadUtil.upload(file, "tpcnews");
        return result;
    }

    /**
     * 跳转到新闻编辑页面
     * @return
     */
    @RequiresPermissions("tpcnews:update")
    @RequestMapping("/goupdate")
    public String goupdate(ModelMap modelMap,TpcNews tpcNews){
        List<TpcNewsType> list = this.getNewsType();
        HttpGet httpGet = new HttpGet(tpcUrl+"/tpcnews/getNewsByid"+"?id="+tpcNews.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        TpcNews tpcNews1 = JSONObject.toJavaObject(jsonObject, TpcNews.class);
        modelMap.put("tpcNews1", tpcNews1);
        modelMap.put("typeList", list);
        return "chanyeyuan/news/newsEdit";
    }

    /**
     * 编辑新闻法规 回显
     * 查看新闻详情
     * @param tpcNews
     * @param request
     * @return TpcNews
     */
    @ResponseBody
    @RequestMapping(value = "/getNewsByid" , method = RequestMethod.GET)
    public TpcNews getNewsByid(TpcNews tpcNews, HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri+"?id="+tpcNews.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, TpcNews.class);
    }

    /**
     * 编辑新闻
     * */
    @ResponseBody
    @RequestMapping(value = "/updateTpcNews" , method = RequestMethod.POST)
    public boolean updateTpcNews(TpcNews tpcNews,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",tpcNews.getTitle());
        map.put("img",tpcNews.getImg());
        map.put("abstracted",tpcNews.getAbstracted());
        map.put("content",tpcNews.getContent());
        map.put("id",tpcNews.getId().toString());
        map.put("author",tpcNews.getAuthor());
        map.put("tid",tpcNews.getTid().toString());
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
    @RequiresPermissions("tpcnews:check")
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
     * 删除新闻
     */
    @RequiresPermissions("tpcnews:delete")
    @ResponseBody
    @RequestMapping(value = "/deleteTpcNews" , method = RequestMethod.POST)
    public boolean deleteTpcNews(String ids,HttpServletRequest request){
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
     *  跳转到新闻查看页面
     * */
    @RequiresPermissions("tpcnews:view")
    @RequestMapping("/tcpNewsLook")
    public String tcpNewsLook(){
        return "chanyeyuan/news/newsLook";
    }

}
