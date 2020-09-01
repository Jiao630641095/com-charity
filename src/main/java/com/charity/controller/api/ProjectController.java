package com.charity.controller.api;

import com.alibaba.druid.wall.WallVisitor;
import com.alibaba.fastjson.JSONObject;
import com.charity.common.annotation.OperationLog;
import com.charity.common.util.HashMapUtil;
import com.charity.common.util.HttpClientUtil;
import com.charity.common.util.UploadUtil;
import com.charity.entity.*;
import com.charity.service.WebsiteService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：王思峰
 * @Description: 项目管理
 * @Date: Created in 9:38 2017/10/17
 * @Modified By:
 */
@Controller
@RequestMapping("/admin/project")
public class ProjectController {

    @Autowired
    WebsiteService websiteService;

    /**
     * 跳转项目列表页面
     */
    @RequiresPermissions("project:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        //获取 网址列表
        List<Website> websites = this.websiteService.findAll();
        modelMap.put("websites",websites);
        return "project/project-list";
    }
    /**
     * 获取项目列表数据
     */
    @RequiresPermissions("project:list")
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Project> getList() {
        //获取 网址列表
        List<Website> websites = this.websiteService.findAll();
        //创建新闻集合
        List<Project> list = new ArrayList<Project>();
        HttpGet httpGet;
        //循环获取各网站新闻列表
        for(Website website : websites){
            httpGet = new HttpGet(website.getUrl()+"/project/list.action");
            String strResult =  HttpClientUtil.sendHttpGet(httpGet);
            List<Project> project = JSONObject.parseArray(strResult, Project.class);
            for(Project  p: project){
                p.setWebsite(website.getName());
                p.setWebsiteUrl(website.getUrl());
                list.add(p);
            }
        }
        return list;
    }

    /**
     * 保存项目数据
     */
    @OperationLog(value = "保存项目")
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Project project) {
        Map<String, String> map = HashMapUtil.getNameValuePairs(project);
        if(project.getId()!=null){map.put("id",Long.toString(project.getId()));}
        String result = null;
        //多个网站同时发布
        if (project.getWebsiteUrl() == null || project.getWebsiteUrl().equals("")|| project.getWebsiteUrl().equals("全部网站")) {
            //获取 网址列表
            List<Website> websites = this.websiteService.findAll();
            for (Website website : websites) {
                result = HttpClientUtil.sendHttpPost(website.getUrl() + "/project/save.action", map);
            }
            return result;
        }
        result = HttpClientUtil.sendHttpPost(project.getWebsiteUrl() + "/project/save.action", map);
        return result;
    }
    /**
     * 跳转项目编辑页面
     */
    @RequiresPermissions("project:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateProject(String url,Long id,ModelMap modelMap) {
        HttpGet httpGet = new HttpGet(url+"/project/getProjectByID.action?id="+id);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject=JSONObject.parseObject(strResult);
        Project project = JSONObject.toJavaObject(jsonObject, Project.class);
        project.setWebsiteUrl(url);
        modelMap.put("model",project);
        return "project/project-edit";
    }

    /**
     * 跳转项目添加页面
     */
    @RequiresPermissions("project:create")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProject(ModelMap modelMap) {
        //获取 网址列表
        List<Website> websites = this.websiteService.findAll();
        modelMap.put("websites",websites);
        return "project/project-add";
    }

    /**
     * 根据ID删除新闻
     */
    @RequiresPermissions("project:delete")
    @OperationLog(value = "删除新闻")
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean delete(String url, Long id) {
        HttpPost httpPost = new HttpPost(url + "/project/delete.action?id=" + id);
        String result = HttpClientUtil.sendHttpPost(httpPost);
        if (result.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**      ===================  项目、事件分割线  =======================     */


    /**
     * 跳转项目事件列表页面
     */
    @RequiresPermissions("event:list")
    @GetMapping("/events")
    public String eventList(String url,Long id ,ModelMap modelMap){
        modelMap.put("url",url);
        modelMap.put("pid",id);
        return "project/event-list";
    }

    /**
     * 获取项目事件
     */
    @RequiresPermissions("event:list")
    @ResponseBody
    @GetMapping("/getEventList")
    public List<Event> getEventList(String url,Long id){
        HttpGet httpGet = new HttpGet(url+"/project/eventList.action?pid="+id);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<Event> events = JSONObject.parseArray(strResult, Event.class);
        for (Event event : events){
            event.setWebsiteUrl(url);
        }
        return events;
    }

    /**
     * 跳转事件添加页面
     */
    @RequiresPermissions("event:create")
    @GetMapping("/eventAdd")
    public String add(String url,String pid ,ModelMap modelMap){
        modelMap.put("url",url);
        modelMap.put("pid",pid);
        return "project/event-add";
    }

    /**
     * 跳转事件编辑页面
     */
    @RequiresPermissions("event:update")
    @GetMapping("/updateEvent")
    public String update(String url,String id ,ModelMap modelMap){
        HttpGet httpGet = new HttpGet(url+"/project/getEventByID.action?id="+id);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject=JSONObject.parseObject(strResult);
        Event event = JSONObject.toJavaObject(jsonObject, Event.class);
        event.setWebsiteUrl(url);
        modelMap.put("model",event);
        modelMap.put("id",id);
        return "project/event-edit";
    }

    /**
     * 添加或修改事件信息
     */
    @OperationLog(value = "保存项目事件")
    @ResponseBody
    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
    public String save(Event event) {
        String result = null;
        Map<String, String> map = HashMapUtil.getNameValuePairs(event);
        if (event.getId()!=null){map.put("id",Long.toString(event.getId()));}
        result = HttpClientUtil.sendHttpPost(event.getWebsiteUrl() + "/project/saveEvent.action", map);
        return result;
    }


    /**
     * 根据ID删除事件
     */
    @RequiresPermissions("event:delete")
    @OperationLog(value = "删除新闻")
    @ResponseBody
    @RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
    public boolean deleteEvent(String url, Long id) {
        HttpPost httpPost = new HttpPost(url + "/project/deleteEvent.action?id=" + id);
        String result = HttpClientUtil.sendHttpPost(httpPost);
        if (result.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 上传新闻配图
     */
    @ResponseBody
    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public UploadResult uploadImg(@RequestParam MultipartFile file, HttpServletRequest request){
        UploadResult result = UploadUtil.upload(file, "project");
        return result;
    }
}
