package com.charity.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.annotation.OperationLog;
import com.charity.common.util.HashMapUtil;
import com.charity.common.util.HttpClientUtil;
import com.charity.common.util.UploadUtil;
import com.charity.entity.News;
import com.charity.entity.Type;
import com.charity.entity.UploadResult;
import com.charity.entity.Website;
import com.charity.service.TypeService;
import com.charity.service.WebsiteService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @uthor：王思峰
 * @Description: 新闻管理
 * @Date: Created in 17:35 2017/10/11
 * @Modified By:
 */
@Controller
@RequestMapping("admin/news")
public class NewsController {

    @Autowired
    WebsiteService websiteService;
    @Autowired
    TypeService typeService;

    /**
     * 跳转新闻列表页面
     */
    @RequiresPermissions("news:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        //获取 网址列表
        List<Website> websites = this.websiteService.findAll();
        modelMap.put("model", websites);
        return "news/news-list";
    }

    /**
     * 获取新闻数据
     */
    @ResponseBody
    @RequestMapping(value = "/getNewsList", method = RequestMethod.POST)
    public List<News> getNewsList(String title, String createTime, String modifyTime, String websiteID) {
        String params = "?title=" + title + "&&createTime=" + createTime + "&&modifyTime=" + modifyTime;
        HttpGet httpGet;
        if (websiteID == null || websiteID == "" || websiteID.equals("")) {
            //获取 网址列表
            List<Website> websites = this.websiteService.findAll();
            //创建新闻集合
            List<News> list = new ArrayList<News>();
            //循环获取各网站新闻列表
            for (Website website : websites) {
                httpGet = new HttpGet(website.getUrl() + "/news/list.action" + params);
                String strResult = HttpClientUtil.sendHttpGet(httpGet);
                List<News> newsList = JSONObject.parseArray(strResult, News.class);
                for (News n : newsList) {
                    n.setWebsite(website.getName());
                    n.setWebsiteUrl(website.getUrl());
                    list.add(n);
                }
            }
            return list;
        } else {
            Website website =this.websiteService.findById(Long.valueOf(websiteID));
            httpGet = new HttpGet(website.getUrl() + "/news/list.action" + params);
            String strResult = HttpClientUtil.sendHttpGet(httpGet);
            List<News> newsList = JSONObject.parseArray(strResult, News.class);
            for (News n : newsList) {
                n.setWebsite(website.getName());
                n.setWebsiteUrl(website.getUrl());
            }
            return newsList;
        }

    }

    /**
     * 跳转新闻添加页面
     */
    @RequiresPermissions("news:create")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        //获取 网址列表
        List<Website> websites = this.websiteService.findAll();
        //获取新闻类型
        Type type = new Type();
        type.setType("2");
        List<Type> typeList = this.typeService.findListByWhere(type);
        modelMap.put("typeList", typeList);
        modelMap.put("websites", websites);
        return "news/news-add";
    }

    /**
     * 跳转新闻修改页面
     */
    @RequiresPermissions("news:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(ModelMap modelMap, String url, Long id) {
        HttpGet httpGet = new HttpGet(url + "/news/newsByID.action?id=" + id);
        String strResult = HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject = JSONObject.parseObject(strResult);
        News news = JSONObject.toJavaObject(jsonObject, News.class);
        news.setWebsiteUrl(url);
        //获取 网址列表
        List<Website> websites = this.websiteService.findAll();
        //获取新闻类型
        Type type = new Type();
        type.setType("2");
        List<Type> typeList = this.typeService.findListByWhere(type);
        modelMap.put("typeList", typeList);
        modelMap.put("websites", websites);
        modelMap.put("model", news);
        return "news/news-edit";
    }

    /**
     * 保存新闻信息
     */
    @OperationLog(value = "添加新闻")
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(News news) {
        Map<String, String> map = HashMapUtil.getNameValuePairs(news);
        if (news.getId() != null) {
            map.put("id", Long.toString(news.getId()));
        }
        String result = null;
        //多个网站同时发布
        if (news.getWebsiteUrl() == null || news.getWebsiteUrl().equals("") || news.getWebsiteUrl().equals("全部网站")) {
            //获取 网址列表
            List<Website> websites = this.websiteService.findAll();
            for (Website website : websites) {
                result = HttpClientUtil.sendHttpPost(website.getUrl() + "/news/save.action", map);
            }
            return result;
        }
        result = HttpClientUtil.sendHttpPost(news.getWebsiteUrl() + "/news/save.action", map);
        return result;
    }

    /**
     * 根据ID删除新闻
     */
    @RequiresPermissions("news:delete")
    @OperationLog(value = "删除新闻")
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean delete(String url, Long id) {
        HttpPost httpPost = new HttpPost(url + "/news/delete.action?id=" + id);
        String result = HttpClientUtil.sendHttpPost(httpPost);
        if (result.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 批量删除新闻
     */
    @RequiresPermissions("news:delete")
    @OperationLog(value = "删除新闻")
    @ResponseBody
    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    public boolean delete(String[] ids) {
        for (String id : ids) {
            System.out.println(id);
        }
//        HttpPost httpPost = new HttpPost(url + "/news/delete.action?id=" + id);
//        String result = HttpClientUtil.sendHttpPost(httpPost);
//        if (result.equals("true")) {
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }

    /**
     * 上传新闻配图
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UploadResult uploadImg(@RequestParam MultipartFile file, HttpServletRequest request) {
        UploadResult result = UploadUtil.upload(file, "news");
        return result;
    }
}
