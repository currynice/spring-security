package com.cxy.security.customdbauth.initData;



import com.cxy.security.customdbauth.entity.Permission;
import com.cxy.security.customdbauth.entity.Role;
import com.cxy.security.customdbauth.entity.UserPO;
import com.cxy.security.customdbauth.repository.PermissionRepository;
import com.cxy.security.customdbauth.repository.RoleRepository;
import com.cxy.security.customdbauth.repository.UserInfoRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * state :关闭（防止重复执行）
 * @author chengxinyu
 * @version 1.1.0
 * @description InitDatas 初始化,数据库插入，定义角色
 * attention:保存多对多或一对多之类的映射关系，关联的表的相关对象都要执行save操作
 * TODO name都是英文，没有使用code,未考虑唯一性，权限
 * @date 2019年06月05日
 **/
//@Component
public class InitDatas {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PermissionRepository permissionRepository;

    //密码编码器
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     *
     * @return
     */
    @Bean
    public InitializingBean initDatebase(){
        return ()->{
            Permission rootP = new Permission();
            rootP.setUrl("/root/**");
            rootP.setName("root:P");
            rootP.setDescription("root管理");
            rootP.setSuperId(-1);


            Permission dbP = new Permission();
            dbP.setUrl("/db/**");
            dbP.setName("db:P");
            dbP.setDescription("数据库管理的访问路径");
            dbP.setSuperId(-1);

            Permission userP = new Permission();
            userP.setUrl("/user/**");
            userP.setName("user:P");
            userP.setDescription("user的访问路径");
            userP.setSuperId(-1);
            permissionRepository.saveAll(Arrays.asList(rootP,dbP,userP));


            Role adminRole= new Role("ADMIN","系统管理员");
            Role dbaRole= new Role("DBA","数据库管理员");
            Role userRole = new Role("USER","用户");
            adminRole.setPermissions(Arrays.asList(rootP,dbP,userP));
            dbaRole.setPermissions(Arrays.asList(dbP,userP));
            userRole.setPermissions(Arrays.asList(userP));


            //记得下次注释掉
            roleRepository.saveAll(Arrays.asList(adminRole,dbaRole,userRole));

            List<Role> rootRoles = Arrays.asList(adminRole,dbaRole);
            UserPO userRoot = new UserPO();
            userRoot.setUsername("root");
            userRoot.setPassword(passwordEncoder.encode("123"));
            //一个用户可以配置多个角色
            userRoot.setRoles(rootRoles);
            userRoot.setEnabled(true);

            List<Role> dbaRoles = Collections.singletonList(dbaRole);
            UserPO userDBA = new UserPO();
            userDBA.setUsername("cxy");
            userDBA.setPassword(passwordEncoder.encode("123"));
            //一个用户可以1个角色
            userDBA.setRoles(dbaRoles);
            userDBA.setEnabled(true);

            List<Role> userRoles = Collections.singletonList(userRole);
            UserPO userUser= new UserPO();
            userUser.setUsername("xpy");
            userUser.setPassword(passwordEncoder.encode("123"));
            userUser.setRoles(userRoles);
            userUser.setEnabled(true);

            //记得下次注释掉
            userInfoRepository.saveAll(Arrays.asList(userRoot,userDBA,userUser));
        };
    }


}
