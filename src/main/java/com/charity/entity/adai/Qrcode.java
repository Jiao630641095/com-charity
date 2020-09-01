package com.charity.entity.adai;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JiaoZhipeng
 * @date 2019/10/23 16:19
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Qrcode extends BaseEntity {

    private String url;

    private String image;

    private String name;

    private String word;

    private Integer status;

    private Long userId;


}
