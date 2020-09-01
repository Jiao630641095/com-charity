package com.charity.controller;

import com.charity.common.util.FtpSender;
import com.charity.common.util.TimeFormat;
import com.charity.entity.UploadResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by xwd on 2017/10/12.
 */
public class ImgUploadController {
    public static UploadResult upload(@RequestParam MultipartFile file, String Directory){
        //获取图片的原名字
        String oldName=file.getOriginalFilename();
        //截取图片的类型
        String fileType=oldName.substring(oldName.lastIndexOf("."));
        //获取当前时间作为新的图片名字  并加上后缀
        String timeName= TimeFormat.uniquetime()+"";
        String newName=timeName+fileType;
        String childPathName=timeName.substring(0,5);
        UploadResult result = new UploadResult();
        try {
            //拼接图片的相对路径作为URL
            String src= FtpSender.SendDirectoryFile(newName,file.getInputStream(),Directory,childPathName);
            result.setCode(0);
            result.setMsg("上传成功！");
            result.getData().setSrc(src);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            result.setCode(-1);
            result.setMsg("上传失败！");
        }
        return result;
    }
}
