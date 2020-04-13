-- Spring Security 根据 org.springframework.security.core.userdetails.UserDetail 中的（username, password,getAuthorities,isAccountNonExpired等等信息）
-- 在登录验证时判断是否成功 ,所以表结构可以变化，能够构造一个UserDetail就好


-- 用户表
CREATE TABLE users (
    id bigint(20) NOT NULL AUTO_INCREMENT,
	username varchar (50) NOT NULL PRIMARY KEY,
	PASSWORD varchar (500) NOT NULL,
	enabled INT NOT NULL
);

-- 用户权限表
CREATE TABLE authorities (
	username varchar (50) NOT NULL,
	authority varchar (50) NOT NULL,
	CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);