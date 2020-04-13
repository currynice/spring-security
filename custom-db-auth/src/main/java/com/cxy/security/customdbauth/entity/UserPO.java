package com.cxy.security.customdbauth.entity;


import lombok.Data;


import javax.persistence.*;
import java.util.List;


/**
 * @author chengxinyu
 * @version 1.1.0
 * @description UserPO  只用来映射持久层,对应user_info表
 * @date 2020年04月13日10:29
 **/
@Entity
@Table(name = "user")
@Data
public class UserPO {

    @Id
    @GeneratedValue
    private long uid;

    private String username;

    private String password;

    //是否可用[0不可用，1可用]默认1
    private boolean enabled;


    @ManyToMany(fetch=FetchType.EAGER)//立即加载
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
    List<Role> roles;




}
