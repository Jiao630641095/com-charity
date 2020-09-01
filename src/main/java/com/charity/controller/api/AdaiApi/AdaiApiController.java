package com.charity.controller.api.AdaiApi;

import com.alibaba.fastjson.JSONObject;
import com.charity.common.util.PrintCenterUtil;
import com.charity.entity.adai.Qrcode;
import com.charity.entity.canteen.Commodity;
import com.charity.entity.canteen.DeliveryInfo;
import com.charity.entity.canteen.Order;
import com.charity.entity.canteen.OrderDetail;
import com.charity.service.*;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("adaiApi")
public class AdaiApiController {


    @Autowired
    private QrcodeService qrcodeService;

    @RequestMapping("/show/{id}")
    public String show(ModelMap modelMap,@PathVariable("id") Integer id){
        Qrcode qrcode = qrcodeService.findQrcodeForShow(id);
        if (qrcode!=null){
            if (qrcode.getName()!=null)
                qrcode.setName(qrcode.getName().replaceAll("\n","<br>").replaceAll("\t",""));
            if (qrcode.getWord()!=null)
                qrcode.setWord(qrcode.getWord().replaceAll("\n","<br>").replaceAll("\t",""));
        }
        modelMap.put("qrcode",qrcode);
        if (qrcode.getStatus() == 1){
            return "redirect:" + qrcode.getUrl();
        }
        return "adai/扫码注册下载";
    }

}
