package com.charity.controller.api.chanyeyuan;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.common.util.UploadUtil;
import com.charity.entity.UploadResult;
import com.charity.entity.chanyeyuan.AlreadyRental;
import com.charity.entity.chanyeyuan.ExpireRental;
import com.charity.entity.chanyeyuan.TpcRental;
import com.charity.entity.chanyeyuan.UserPermission;
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
 * Created by XWD on 2017/12/8.
 */
@Controller
@RequestMapping("/tpcrental")
public class TpcRentalController {

    @Value("${net.tpc.host}")
    String tpcUrl;


    /**
     *  跳转到房屋服务列表页面
     * */
    @RequiresPermissions("tpcrental:list")
    @RequestMapping("/golist")
    public String golist(){
        return "chanyeyuan/fsservice/rental/tpcRentalList";
    }

    /**
     * 获取房屋服务列表  httpClint
     */
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<TpcRental> getList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<TpcRental> list = JSONObject.parseArray(strResult, TpcRental.class);
        return list;
    }

    /**
     *  管理员 条件查询 获取房屋列表
     * @param tpcRental
     * @return List<TpcRental>
     */
    @ResponseBody
    @RequestMapping(value = "/getRentalListWhere" , method = RequestMethod.POST)
    public List<TpcRental> getRentalListWhere(TpcRental tpcRental,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("roomnum",tpcRental.getRoomnum());
        map.put("floor",tpcRental.getFloor());
        map.put("passageway",tpcRental.getPassageway());
        map.put("rent",String.valueOf(tpcRental.getRent()));
        map.put("torent",String.valueOf(tpcRental.getTorent()));
        map.put("area",tpcRental.getArea().toString());
        map.put("toarea",tpcRental.getToarea().toString());
        map.put("face",tpcRental.getFace());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<TpcRental> list = JSONObject.parseArray(strResult, TpcRental.class);
        return list;
    }

    /**
     *  跳转到添加页面
     * */
    @RequiresPermissions("tpcrental:create")
    @RequestMapping("/goAdd")
    public String goAdd(){
        return "chanyeyuan/fsservice/rental/tpcRentalAdd";
    }

    /**
     *  编辑器上传图片
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UploadResult uploadImg(@RequestParam MultipartFile file, HttpServletRequest request) {
        UploadResult result = UploadUtil.upload(file, "tpcRental");
        return result;
    }


    /**
     * 管理员 添加 房屋信息
     * @param tpcRental
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addRental" , method = RequestMethod.POST)
    public boolean addRental(TpcRental tpcRental, HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("roomnum",tpcRental.getRoomnum());
        map.put("floor",tpcRental.getFloor());
        map.put("passageway",tpcRental.getPassageway());
        map.put("rent",String.valueOf(tpcRental.getRent()));
        map.put("area",tpcRental.getArea().toString());
        map.put("face",tpcRental.getFace());
        map.put("photo1",tpcRental.getPhoto1());
        map.put("photo2",tpcRental.getPhoto2());
        map.put("photo3",tpcRental.getPhoto3());
        map.put("photo4",tpcRental.getPhoto4());
        map.put("photo5",tpcRental.getPhoto5());
        map.put("info",tpcRental.getInfo());
        String strResult = HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     * 跳转到房屋编辑页面
     * @return
     */
    @RequiresPermissions("tpcrental:update")
    @RequestMapping("/goupdate")
    public String goupdate(){
        return "chanyeyuan/fsservice/rental/tpcRentalEdit";
    }


    /**
     * 编辑房屋回显
     * 查看房屋详情
     * @param tpcRental
     * @param request
     * @return TpcRental
     */
    @ResponseBody
    @RequestMapping(value = "/getRentalByid" , method = RequestMethod.GET)
    public TpcRental getRentalByid(TpcRental tpcRental, HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri+"?id="+tpcRental.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        return JSONObject.toJavaObject(jsonObject, TpcRental.class);
    }

    /**
     * 编辑房屋(修改)
     * */
    @ResponseBody
    @RequestMapping(value = "/updateTpcRental" , method = RequestMethod.POST)
    public boolean updateTpcRental(TpcRental tpcRental,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("id",tpcRental.getId().toString());
        map.put("roomnum",tpcRental.getRoomnum());
        map.put("floor",tpcRental.getFloor());
        map.put("passageway",tpcRental.getPassageway());
        map.put("rent",String.valueOf(tpcRental.getRent()));
        map.put("area",tpcRental.getArea().toString());
        map.put("face",tpcRental.getFace());
        map.put("photo1",tpcRental.getPhoto1());
        map.put("photo2",tpcRental.getPhoto2());
        map.put("photo3",tpcRental.getPhoto3());
        map.put("photo4",tpcRental.getPhoto4());
        map.put("photo5",tpcRental.getPhoto5());
        map.put("info",tpcRental.getInfo());
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
    @RequiresPermissions("tpcrental:check")
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
     * 更改推荐状态
     */
    @RequiresPermissions("tpcrental:isUp")
    @ResponseBody
    @RequestMapping(value = "/updateIsUp" , method = RequestMethod.POST)
    public boolean updateIsUp(String ids,HttpServletRequest request){
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
     * 删除房屋信息
     */
    @RequiresPermissions("tpcrental:delete")
    @ResponseBody
    @RequestMapping(value = "/deleteTpcRental" , method = RequestMethod.POST)
    public boolean deleteTpcRental(String ids,HttpServletRequest request){
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
     *  跳转到详情页面
     * */
    @RequiresPermissions("tpcrental:view")
    @RequestMapping("/tpcRentalLook")
    public String tcpPoliciesLook(){
        return "chanyeyuan/fsservice/rental/tpcRentalLook";
    }



    /**
     *  跳转到本月房屋到期统计列表页面
     * */
    @RequiresPermissions("tpcrental:list2")
    @RequestMapping("/golist2")
    public String golist2(){
        return "chanyeyuan/fsservice/rental/list";
    }


    /**
     * 获取本月房屋到期统计列表  httpClint
     */
    @ResponseBody
    @RequestMapping(value = "/getExpireRentalList" , method = RequestMethod.GET)
    public List<ExpireRental> getExpireRentalList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<ExpireRental> list = JSONObject.parseArray(strResult, ExpireRental.class);
        return list;
    }

    /**
     *  管理员 条件查询 获取本月房屋到期统计列表
     * @param expireRental
     * @return List<ExpireRental>
     */
    @ResponseBody
    @RequestMapping(value = "/getExpireRentalListWhere" , method = RequestMethod.POST)
    public List<ExpireRental> getExpireRentalListWhere(ExpireRental expireRental,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("name",expireRental.getName());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<ExpireRental> list = JSONObject.parseArray(strResult, ExpireRental.class);
        return list;
    }

    /**
     * 发送短信提醒
     */
    @ResponseBody
    @RequestMapping(value = "/sendsms" , method = RequestMethod.POST)
    public boolean sendsms(ExpireRental expireRental,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("phone",expireRental.getPhone());
        map.put("roomnum",expireRental.getRoomnum());
        map.put("name",expireRental.getName());
        map.put("pay_date",expireRental.getPay_date());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     * 修改上次付款时间
     * @param alreadyRental
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePayDate" , method = RequestMethod.POST)
    public boolean  updatePayDate(AlreadyRental alreadyRental,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("id",alreadyRental.getId().toString());
        map.put("pay_time",alreadyRental.getPay_time());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }


    /**
     *  跳转到已出租房屋统计列表页面
     * */
    @RequiresPermissions("tpcrental:list3")
    @RequestMapping("/golist3")
    public String golist3(){
        return "chanyeyuan/fsservice/rental/alreadyRentallist";
    }


    /**
     * 获取已租房屋统计列表
     */
    @ResponseBody
    @RequestMapping(value = "/getAlreadyRentalList" , method = RequestMethod.GET)
    public List<AlreadyRental> getAlreadyRentalList(HttpServletRequest request){
        String uri = request.getRequestURI();
        HttpGet httpGet = new HttpGet(tpcUrl+uri);
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<AlreadyRental> list = JSONObject.parseArray(strResult, AlreadyRental.class);
        return list;
    }

    /**
     * 条件获取已租房屋统计列表
     */
    @ResponseBody
    @RequestMapping(value = "/getAlreadyRentalListWhere" , method = RequestMethod.POST)
    public List<AlreadyRental> getAlreadyRentalListWhere(AlreadyRental alreadyRental,HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("name",alreadyRental.getName());
        String strResult =  HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        List<AlreadyRental> list = JSONObject.parseArray(strResult, AlreadyRental.class);
        return list;
    }

    /**
     *  跳转到添加已租房屋信息页面
     * */
    @RequiresPermissions("tpcrental:create3")
    @RequestMapping("/goAddalready")
    public String goAddalready(ModelMap modelMap){
        List<TpcRental> tpcRentals = this.getTpcRental();
        modelMap.put("rentals", tpcRentals);
        return "chanyeyuan/fsservice/rental/alreadyRentalAdd";
    }

    /**
     * 获取注册用户列表
     */
//    public List<UserPermission> getUser(){
//        HttpGet httpGet = new HttpGet(tpcUrl+"/tpcrental/getUser");
//        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
//        List<UserPermission> list = JSONObject.parseArray(strResult, UserPermission.class);
//        return list;
//    }

    /**
     * 获取已租房屋信息(添加用)
     */
    public List<TpcRental> getTpcRental(){
        HttpGet httpGet = new HttpGet(tpcUrl+"/tpcrental/getTpcRental");
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<TpcRental> list = JSONObject.parseArray(strResult, TpcRental.class);
        return list;
    }

    /**
     * 获取已租房屋信息(修改用)
     */
    public List<TpcRental> getTpcRental2(){
        HttpGet httpGet = new HttpGet(tpcUrl+"/tpcrental/getTpcRental2");
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        List<TpcRental> list = JSONObject.parseArray(strResult, TpcRental.class);
        return list;
    }

    /**
     * 添加已租房屋信息
     */
    @ResponseBody
    @RequestMapping(value = "/addAlreadyRental" , method = RequestMethod.POST)
    public boolean addAlreadyRental(AlreadyRental alreadyRental, HttpServletRequest request){
        String uri = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put("id",alreadyRental.getId().toString());
        map.put("rid",alreadyRental.getRid().toString());
        map.put("roomnum",alreadyRental.getRoomnum());
        map.put("qyname",alreadyRental.getQyname());
        map.put("name",alreadyRental.getName());
        map.put("phone",alreadyRental.getPhone());
        map.put("paytype",alreadyRental.getPaytype().toString());
        map.put("create_time",alreadyRental.getCreate_time());
        map.put("pay_time",alreadyRental.getPay_time());
        map.put("end_time",alreadyRental.getEnd_time());
        String strResult = HttpClientUtil.sendHttpPost(tpcUrl+uri,map);
        if(strResult.equals("true")){
            return true;
        }else {
            return  false;
        }
    }

    /**
     *  跳转到编辑已租房屋信息页面
     * */
    @RequiresPermissions("tpcrental:update3")
    @RequestMapping("/goUpdatealready")
    public String goUpdatealready(ModelMap modelMap,AlreadyRental alreadyRental){
        HttpGet httpGet = new HttpGet(tpcUrl+"/tpcrental/getAlreadyRentalByid"+"?id="+alreadyRental.getId());
        String strResult =  HttpClientUtil.sendHttpGet(httpGet);
        JSONObject jsonObject= JSONObject.parseObject(strResult);
        AlreadyRental alreadyRental1 = JSONObject.toJavaObject(jsonObject, AlreadyRental.class);
        List<TpcRental> tpcRentals = this.getTpcRental2();
        modelMap.put("rentals", tpcRentals);
        modelMap.put("alreadyRental1", alreadyRental1);
        return "chanyeyuan/fsservice/rental/alreadyRentalEdit";
    }

    /**
     * 删除已出租房屋记录
     */
    @RequiresPermissions("tpcrental:delete3")
    @ResponseBody
    @RequestMapping(value = "/deleteAlreadyRental" , method = RequestMethod.POST)
    public boolean  deleteAlreadyRental(String ids,HttpServletRequest request){
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
}
