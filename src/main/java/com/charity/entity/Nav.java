package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * Created by XWD on 2017/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Nav extends BaseEntity{
    Integer pid;//上级id
    String title;//导航名称
    String url;//导航路径
    ArrayList<Nav> child = new ArrayList<>();
}
