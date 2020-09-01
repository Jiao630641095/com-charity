package com.charity.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author：王思峰
 * @Description: tree
 * @Date: Created in 11:57 2017/9/29
 * @Modified By:
 */
@Data
public class TreeNode {
    /**
     * id
     */
    private Long id;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 是否展开
     */
    private Boolean open = true;

    /**
     * 儿子
     */
    private List<TreeNode> children;
}
