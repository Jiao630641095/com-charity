package com.charity.controller;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.HttpClientUtil;
import com.charity.common.util.ShiroUtil;
import com.charity.common.util.UploadUtil;
import com.charity.entity.Network;
import com.charity.entity.Type;
import com.charity.entity.UploadResult;
import com.charity.entity.adai.Qrcode;
import com.charity.service.QrcodeService;
import com.charity.service.TypeService;
import com.github.pagehelper.PageInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.http.client.methods.HttpGet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：xwd
 * @Description: 爱心网络
 * @Date: Created in 15:24 2017/10/11
 */
@Controller
@RequestMapping("/adai")
public class AdaiController {

    @Value("${net.paper.host}")
    String paperUrl;

    Integer PAGESIZE = 10;

    @Autowired
    TypeService typeService;

    @Autowired
    QrcodeService qrcodeService;
    /**
     * 获取爱心网络信息
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            String name, ModelMap modelMap) throws Exception {
            HashMap map = new HashMap();
            map.put("limit2",PAGESIZE);
            map.put("limit1",(pageNum-1)*PAGESIZE);
            map.put("name",name);
            map.put("userId",ShiroUtil.getUserId());
            PageInfo<Qrcode> pageInfo = qrcodeService.findPage(map);
            Map res = new HashMap<>();
            res.put("code",0);
            res.put("count",pageInfo==null?0:pageInfo.getTotal());
            res.put("data",pageInfo==null?"":pageInfo.getList());
            res.put("msg","");
            return res;
    }

    @RequestMapping("/download/{id}")
    public void dowanload(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") Integer id) throws Exception {
        Qrcode qrcode = qrcodeService.findQrcodeForShow(id);
        if (qrcode==null)
            return;
        //二维码中包含的信息
        String content = "http://www.jiaojustgo.top/adaiApi/show/" +id;
        //String content = "http://localhost:8081/adaiApi/show/" +id;
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 指定纠错级别(L--7%,M--15%,Q--25%,H--30%)
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 编码内容,编码类型(这里指定为二维码),生成图片宽度,生成图片高度,设置参数
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
        //设置请求头
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + "十二余的二维码.png");
        OutputStream outputStream = response.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     *  跳转到爱心网络列表页面
     * */
    @RequestMapping("/qrcodeList")
    public String gonetworkList(){
        return "adai/qrCodeList";
    }


    /**
     * 获取爱心网络类型
     */
   /* @ResponseBody
    @RequestMapping("/changeStutus")
    public boolean changeStutus(Qrcode qrcode){
        boolean b = qrcodeService.changeStutus(qrcode);
        return b;
    }*/
    /**
     *  跳转到添加页面
     * */
    @RequestMapping("/gonetworkAdd")
    public String gonetworkAdd(HttpServletRequest request,ModelMap modelMap){
        return "adai/networkAdd";
    }


    /**
     *  跳转到编辑页面
     * */
    @RequestMapping("/gonetworkEdit/{id}")
    public String gonetworkEdit(@PathVariable Integer id,ModelMap modelMap){
        Qrcode qrcode = qrcodeService.findQrcodeForShow(id);
        modelMap.put("qrcode",qrcode);
        return "adai/networkEdit";
    }

    /**
     * 新增 修改
     */
    @ResponseBody
    @RequestMapping(value = "edit" , method = RequestMethod.POST)
    public boolean addQrcode(Qrcode qrcode, HttpServletRequest request){
        if (qrcode!=null&&qrcode.getUrl()!=null){
            qrcode.setUserId(ShiroUtil.getUserId());
            qrcode.setCreateTime(new Date());
            qrcode.setModifyTime(new Date());
            return qrcodeService.addQrcode(qrcode);
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
        UploadResult result = UploadUtil.upload(file, "qecode");
        return result;
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public boolean delete(Long id){
       return qrcodeService.deleteById(id)>0;
    }


}
