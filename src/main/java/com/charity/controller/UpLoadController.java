package com.charity.controller;

import com.charity.common.util.UploadUtil;
import com.charity.entity.UploadResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:12 2017/10/20
 * @Modified By:
 */
@RequestMapping("/admin/upload")
@Controller
public class UpLoadController {
    /**
     * 图片上传
     */
    @ResponseBody
    @RequestMapping(value="/",method= RequestMethod.POST)
    public UploadResult uploadImg(@RequestParam MultipartFile file, HttpServletRequest request){
        UploadResult image = UploadUtil.upload(file, "xxx");
        return image;
    }

}
