-- JdbcUserDetailsManager设定了一个默认的数据库模型,复制来的。

-- 用户表
CREATE TABLE users (
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