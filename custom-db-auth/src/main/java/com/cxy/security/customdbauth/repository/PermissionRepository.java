package com.cxy.security.customdbauth.repository;




import com.cxy.security.customdbauth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionRepository extends JpaRepository<Permission,Long> {

}
