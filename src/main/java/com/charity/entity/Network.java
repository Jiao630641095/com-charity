package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by XWD on 2017/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Network extends BaseEntity {
    private String name;    //分会、媒体、机构、合作伙伴
    private String logo;    //LOGO
    private String link;    //链接
    private String type;    //类型
    private Integer tid;    //类型ID


}
