package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.Menu;
import com.charity.mapper.MenuMapper;
import com.charity.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:08 2017/9/27
 * @Modified By:
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {
//    @Autowired
//    MenuMapper menuMapper;
//    /**
//     * 获取导航栏列表
//     */
//    @Override
//    public List<Menu> list() {
//        return this.menuMapper.list();
//    }
}
