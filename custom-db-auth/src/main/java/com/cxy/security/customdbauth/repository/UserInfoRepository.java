package com.cxy.security.customdbauth.repository;


import com.cxy.security.customdbauth.entity.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chengxinyu
 * @version 1.1.0
 * @description UserPO jpa接口
 * @date 2019年06月05日9:58
 **/
public interface UserInfoRepository extends JpaRepository<UserPO,Long> {

     /**
      * 根据登录信息（这里是username）读取数据库查出UserPO，以便填充我们定义的 UserDetail {@link com.cxy.security.customdbauth.entity.UserDTO}
      * @param username
      * @return UserPO
      */
     UserPO findUserInfoPOByUsername(String username);
}
