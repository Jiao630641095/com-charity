package com.charity.entity;

import com.charity.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * Created by XWD on 2017/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_news_type")
public class Type extends BaseEntity {
    private String name;
    private String type;    //类型
}
