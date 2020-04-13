package com.cxy.security.customdbauth.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author chengxinyu
 * @version 1.1.0
 * @description Role
 * @date 2019年06月05日14:12
 **/
@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long role_id;

    //角色名称
    private String roleName;

    //角色描述
    private String description;


    //一个角色 可以对应多个权限
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="role_permissions",joinColumns= {@JoinColumn(name="role_id")} , inverseJoinColumns= {@JoinColumn(name="permission_id")})
    private List<Permission> permissions;

    public Role() {
       super();
    }

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }



}
