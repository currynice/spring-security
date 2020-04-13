package com.cxy.security.customdbauth.repository;




import com.cxy.security.customdbauth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role,Long> {
}
