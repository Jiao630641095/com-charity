package com.charity.common.base.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 15:25 2017/9/28
 * @Modified By:
 */
@Data
public abstract class BaseEntity implements java.io.Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createTime;

    private Date modifyTime;
}
