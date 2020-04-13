package com.cxy.security.customdbauth.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author chengxinyu TODO url为 xxx.html等资源
 * @version 1.1.0
 * @description Permission
 * @date 2019年06月05日15:59
 **/
@Entity
@Data
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long permission_id;

    //权限名称.
    private String name;

    //权限描述.
    private String description;
    /**
     *  url下一级路由通配符为一颗星， /user/info,/user/add ， /user/*;
     * url任意字符通配符为两颗星， /user下的所有url， /user/**;
     */
    //授权链接
    private String url;

    //父节点id.(-1表示没有父节点)
    private long superId;




}
