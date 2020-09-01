package com.charity.controller.api.paper;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.common.util.UploadUtil;
import com.charity.controller.ImgUploadController;
import com.charity.entity.Network;
import com.charity.entity.Type;
import com.charity.entity.UploadResult;
import com.charity.service.TypeService;
import org.apache.http.client.methods.HttpGet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：xwd
 * @Description: 爱心网络
 * @Date: Created in 15:24 2017/10/11
 */
@Controller
@RequestMapping("/network")
public class NetworkController {

    @Value("${net.paper.host}")
    String paperUrl;

    @Autowired
    TypeService typeService;
    /**
     * 获取爱心网络信息
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<Network> getNetworkList(Network network, HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet;
        if(network.getId()==null){
            httpGet = new HttpGet(paperUrl+uri);
        }else{
            httpGet = new HttpGet(paperUrl+uri+"?id="+network.getId());
        }
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<Network> list = JSONObject.parseArray(strResult, Network.class);
        return list;
    }

    /**
     *  跳转到爱心网络列表页面
     * */
    @RequiresPermissions("network:list")
    @RequestMapping("/golist")
    public String gonetworkList(){
        return "network/networkList";
    }


    /**
     * 获取爱心网络类型
     */
    @ResponseBody
    @RequestMapping("/typeList")
    public List<Type> getTypeList(HttpServletRequest request){
        Type type = new Type();
        type.setType("1");
        List<Type> list = this.typeService.findListByWhere(type);
        return list;
    }
    /**
     *  跳转到添加爱心网络页面
     * */
    @RequiresPermissions("network:create")
    @RequestMapping("/gonetworkAdd")
    public String gonetworkAdd(HttpServletRequest request,ModelMap modelMap){
        return "network/networkAdd";
    }


    /**
     *  跳转到编辑爱心网络页面
     * */
    @RequiresPermissions("network:update")
    @RequestMapping("/gonetworkEdit")
    public String gonetworkEdit(){
        return "network/networkEdit";
    }

    /**
     * 新增 修改
     */
    @ResponseBody
    @RequestMapping(value = "edit" , method = RequestMethod.POST)
    public boolean addNetWork(Network network,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        if(network.getId()!=null){
            map.put("id",network.getId().toString());
        }
        map.put("name",network.getName());
        map.put("logo",network.getLogo());
        map.put("link",network.getLink());
        map.put("tid",network.getTid().toString());
        String strResult = HttpClientUtil.sendHttpPost(paperUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 分页获取爱心网络信息
     */
    @ResponseBody
    @RequestMapping("/getList")
    public List<Network> getList(Integer tid,Integer page,HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet  httpGet = new HttpGet(paperUrl+uri+"?tid="+tid+"&page="+page);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<Network> list = JSONObject.parseArray(strResult, Network.class);
        return list;
    }
    /**
     * 获取页数
     */
    @ResponseBody
    @RequestMapping("/getCount")
    public Integer getCount(Integer id,HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(paperUrl+uri+"?id"+id);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        return Integer.parseInt(strResult);
    }
    /**
     * 上传LOGO
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UploadResult uploadImg(@RequestParam MultipartFile file, HttpServletRequest request) {
        UploadResult result = UploadUtil.upload(file, "logo");
        return result;
    }

    /**
     * 删除
     */
    @RequiresPermissions("network:delete")
    @ResponseBody
    @RequestMapping(value = "/deleteNetwork" , method = RequestMethod.POST)
    public boolean deleteNetwork(String ids,HttpServletRequest request){
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


}
