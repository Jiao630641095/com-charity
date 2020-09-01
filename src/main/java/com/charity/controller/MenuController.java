package com.charity.controller;

import com.charity.common.annotation.OperationLog;
import com.charity.entity.Menu;
import com.charity.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author：王思峰
 * @Description: 左侧导航栏
 * @Date: Created in 12:03 2017/9/27
 * @Modified By:
 */
@RequestMapping("/menu")
@Controller
public class MenuController {
    @Autowired
    MenuService menuService;
    /**
     * 获取导航栏列表
     */
    @OperationLog(value = "获取菜单")
    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<Menu> list(){
        System.out.println("---------------");
        List<Menu> list = this.menuService.findAll();
        return list;
    }
}
