package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * Created by XWD on 2017/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Banner extends BaseEntity {

    private String imgurl;//图片路径

    private String addressurl;//对应的地址

    private Timestamp addtime;//上传时间

    private String state;//是否展示 0不展示 1展示
}
