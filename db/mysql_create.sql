DROP TABLE IF EXISTS auth_info;
DROP TABLE IF EXISTS role_info;
DROP TABLE IF EXISTS menu_tree;
DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS role_menu;
DROP TABLE IF EXISTS role_auth;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS menu_auth;
DROP TABLE IF EXISTS bms_param;
DROP TABLE IF EXISTS firm_info;
DROP TABLE IF EXISTS firm_user_info;
DROP TABLE IF EXISTS product_info;
DROP TABLE IF EXISTS product_serial;
DROP TABLE IF EXISTS tag_batch;
DROP TABLE IF EXISTS tag_info;
DROP TABLE IF EXISTS comment_info;
DROP TABLE IF EXISTS key_info;

/*==============================================================*/
/* Table: auth_info 权限信息                                    */
/*==============================================================*/
CREATE TABLE auth_info
(
  auth_id     INT NOT NULL AUTO_INCREMENT,
  auth_class  VARCHAR(50),
  auth_cn     VARCHAR(50),
  server_path VARCHAR(256),
  PRIMARY KEY (auth_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: role_info 角色信息                                    */
/* 管理员角色id小于1000普通角色Id大于1000                       */
/*==============================================================*/
CREATE TABLE role_info
(
  role_id      INT NOT NULL AUTO_INCREMENT,
  role_name    VARCHAR(50),
  role_desc    VARCHAR(128),
  role_creator VARCHAR(32),
  PRIMARY KEY (role_id)
)
  AUTO_INCREMENT = 1000
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: menu_tree 菜单树                                      */
/*==============================================================*/
CREATE TABLE menu_tree
(
  menu_id    INT NOT NULL AUTO_INCREMENT,
  parent_id  INT,
  menu_text  VARCHAR(60),
  icon_text  VARCHAR(20),
  node_type  INT,
  url_text   VARCHAR(256),
  order_flag INT,
  PRIMARY KEY (menu_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: user_info 用户信息                                    */
/*==============================================================*/
CREATE TABLE user_info
(
  user_name     VARCHAR(32) NOT NULL,
  real_name     VARCHAR(32),
  pwd           VARCHAR(32),
  mobile_phone  VARCHAR(20),
  email         VARCHAR(32),
  sex           VARCHAR(1),
  state         VARCHAR(2),
  register_time VARCHAR(19),
  PRIMARY KEY (user_name)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: role_menu 角色目录关联表                              */
/*==============================================================*/
CREATE TABLE role_menu
(
  id      INT NOT NULL AUTO_INCREMENT,
  role_id INT NOT NULL,
  menu_id INT NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: role_auth 角色权限关联表                              */
/*==============================================================*/
CREATE TABLE role_auth
(
  id      INT NOT NULL AUTO_INCREMENT,
  role_id INT NOT NULL,
  auth_id INT NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: user_role 用户角色关联表                              */
/*==============================================================*/
CREATE TABLE user_role
(
  id        INT         NOT NULL AUTO_INCREMENT,
  role_id   INT         NOT NULL,
  user_name VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: menu_auth 菜单权限关联表                              */
/*==============================================================*/
CREATE TABLE menu_auth
(
  id      INT NOT NULL AUTO_INCREMENT,
  menu_id INT NOT NULL,
  auth_id INT,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: bms_param 参数信息                                    */
/*==============================================================*/
CREATE TABLE bms_param
(
  param_name  VARCHAR(32) NOT NULL,
  param_value VARCHAR(1024),
  param_desc  VARCHAR(256),
  PRIMARY KEY (param_name)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: firm_info 厂商信息                                    */
/*==============================================================*/
CREATE TABLE firm_info
(
  firm_num     VARCHAR(32) NOT NULL,
  firm_name    VARCHAR(64),
  contact      VARCHAR(24),
  telephone    VARCHAR(20),
  mobile_phone VARCHAR(20),
  fax          VARCHAR(20),
  email        VARCHAR(64),
  address      VARCHAR(256),
  firm_date    VARCHAR(20),
  remark       VARCHAR(256),
  PRIMARY KEY (firm_num)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: firm_user_info 厂商用户信息                           */
/*==============================================================*/
CREATE TABLE firm_user_info
(
  firm_usr_id   VARCHAR(32) NOT NULL,
  firm_num      VARCHAR(32),
  firm_username VARCHAR(32),
  mobile_phone  VARCHAR(32),
  email         VARCHAR(32),
  firm_pwd      VARCHAR(32),
  register_time VARCHAR(19),
  PRIMARY KEY (firm_usr_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*==============================================================*/
/* Table: product_info 产品信息                                 */
/*==============================================================*/
CREATE TABLE product_info
(
  product_num                   VARCHAR(32) NOT NULL,
  product_name                  VARCHAR(256),
  qs_num                        VARCHAR(256),
  gb_num                        VARCHAR(256),
  firm_num                      VARCHAR(32),
  firm_name                     VARCHAR(64),
  product_type                  VARCHAR(128),
  product_tech                  VARCHAR(128),
  shelf_life                    VARCHAR(32),
  quantity                      VARCHAR(32),
  ingredient                    VARCHAR(256),
  production_date               VARCHAR(32),
  movie_url                     VARCHAR(256),
  picture_url                   VARCHAR(256),
  quality_code                  VARCHAR(255),
  checkout_result               VARCHAR(255),
  checkout_id                   VARCHAR(255),
  pro_en_pname                  VARCHAR(255),
  pro_pin_tro                   VARCHAR(255),
  pro_note                      VARCHAR(255),
  commodity_codes               VARCHAR(255),
  product_images                VARCHAR(255),
  manufacture_address           VARCHAR(255),
  institution_code              VARCHAR(255),
  duty_person                   VARCHAR(255),
  manufacture_phone             VARCHAR(255),
  anticounterfeiting_technology VARCHAR(255),
  fw_msg                        VARCHAR(2048),
  check_out                     VARCHAR(255),
  spid                          VARCHAR(64),
  query_url                     VARCHAR(255),
  PRIMARY KEY (product_num)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*==============================================================*/
/* Table: product_serial 产品序列信息                           */
/*==============================================================*/
CREATE TABLE product_serial
(
  product_serial_num VARCHAR(256) NOT NULL,
  product_index      VARCHAR(256),
  serial_state       VARCHAR(1),
  serial_init_time   VARCHAR(19),
  tag_num            VARCHAR(32),
  firm_num           VARCHAR(32),
  product_num        VARCHAR(32),
  PRIMARY KEY (product_serial_num)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*==============================================================*/
/* Table: tag_batch 标签批次信息                                */
/*==============================================================*/
CREATE TABLE tag_batch
(
  batch_id       INT NOT NULL AUTO_INCREMENT,
  batch_time     VARCHAR(19),
  batch_sum      VARCHAR(10),
  batch_operator VARCHAR(20),
  firm_num       VARCHAR(32),
  PRIMARY KEY (batch_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*==============================================================*/
/* Table: tag_info 标签信息                                     */
/*==============================================================*/
CREATE TABLE tag_info
(
  tag_num           VARCHAR(32) NOT NULL,
  batch_id          INT,
  tag_sn            VARCHAR(256),
  tag_sp            VARCHAR(64),
  tag_state         VARCHAR(1),
  tag_init_time     VARCHAR(19),
  tag_allocate_time VARCHAR(19),
  tag_key           VARCHAR(256),
  tag_ciphertext    VARCHAR(256),
  tag_mac           VARCHAR(256),
  firm_num          VARCHAR(32),
  product_num       VARCHAR(256),
  product_serial    VARCHAR(256),
  PRIMARY KEY (tag_num)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*==============================================================*/
/* Table: comment_info 留言信息表                              */
/*==============================================================*/
CREATE TABLE comment_info
(
  id              INT NOT NULL AUTO_INCREMENT,
  telephone       VARCHAR(11),
  comment_content VARCHAR(225),
  position        VARCHAR(32),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: key_info                                              */
/*==============================================================*/
CREATE TABLE key_info
(
  key_id         INT NOT NULL,
  key_value      VARCHAR(1024),
  key_type       VARCHAR(64),
  tag_num        VARCHAR(32),
  batch_id       INT,
  key_ciphertext VARCHAR(1024),
  key_init_time  VARCHAR(19),
  PRIMARY KEY (key_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
