package com.charity.service.impl;

import com.charity.common.base.service.impl.BaseServiceImpl;
import com.charity.entity.Type;
import com.charity.entity.Website;
import com.charity.service.TypeService;
import com.charity.service.WebsiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 12:09 2017/10/12
 * @Modified By:
 */
@Service
@Transactional
public class TypeServiceImpl extends BaseServiceImpl<Type> implements TypeService {
}
