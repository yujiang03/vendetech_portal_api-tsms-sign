
drop database if exists vendetech_portal;
create database vendetech_portal;

use vendetech_portal;

-- ----------------------------
-- 系统用户表
-- ----------------------------
drop table if exists sys_user;
create table sys_user
(
  user_id     bigint auto_increment             comment '用户ID'    primary key,
  user_name   varchar(30)             not null  comment '用户账号',
  dd_user_id  varchar(45)             null      comment '内部用户必填',
  nick_name   varchar(30)             null      comment '用户昵称',
  error_count int(1) default '0'      null      comment '登录次数',
  user_type   varchar(2) default '01' null      comment '用户类型（00系统用户，01内部用户，02外部用户）',
  email       varchar(50) default ''  null      comment '用户邮箱',
  mobile      varchar(20)             null      comment '手机号码',
  sex         char default '0'        null      comment '用户性别（0男 1女 2未知）',
  avatar      varchar(100) default '' null      comment '头像地址',
  password    varchar(100) default '' null      comment '密码',
  status      char default '0'        null      comment '帐号状态（0正常 1停用）',
  login_ip    varchar(50) default ''  null      comment '最后登陆IP',
  login_date  datetime                null      comment '最后登陆时间',
  create_by   varchar(64) default ''  null      comment '创建者',
  create_time datetime                null      comment '创建时间',
  update_by   varchar(64) default ''  null      comment '更新者',
  update_time datetime                null      comment '更新时间',
  remark      varchar(500)            null      comment '备注',
  constraint user_name
  unique (user_name)
) comment '用户信息表';


-- ----------------------------
-- 操作日志表
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log
(
  oper_id        bigint auto_increment          comment '日志主键'  primary key,
  title          varchar(50) default ''   null  comment '模块标题',
  business_type  int(2) default '0'       null  comment '业务类型（0其它 1新增 2修改 3删除）',
  method         varchar(100) default ''  null  comment '方法名称',
  request_method varchar(10) default ''   null  comment '请求方式',
  operator_type  int(1) default '0'       null  comment '操作类别（0其它 1后台用户 2手机端用户）',
  user_agent     varchar(50)              null  comment '浏览器标识',
  oper_name      varchar(50) default ''   null  comment '操作人员',
  dept_name      varchar(50) default ''   null  comment '部门名称',
  oper_url       varchar(255) default ''  null  comment '请求URL',
  oper_ip        varchar(50) default ''   null  comment '主机地址',
  oper_location  varchar(255) default ''  null  comment '操作地点',
  oper_param     varchar(2000) default '' null  comment '请求参数',
  json_result    varchar(2000) default '' null  comment '返回参数',
  status         int(1) default '0'       null  comment '操作状态（0正常 1异常）',
  error_msg      varchar(2000) default '' null  comment '错误消息',
  oper_time      datetime                 null  comment '操作时间'
) comment '操作日志记录';


-- ----------------------------
-- 应用配置表
-- ----------------------------
drop table if exists sys_app_config;
create table sys_app_config
(
  app_id      bigint auto_increment          comment '应用id'  primary key,
  app_name    varchar(30) default ''   null  comment '应用名',
  short_name  varchar(20) default ''   null  comment '短应用名',
  app_url     varchar(100) default ''  null  comment '应用url',
  icon        varchar(100) default ''  null  comment '应用图标',
  status      char default ''          null  comment '应用状态',
  app_type    char default ''          null  comment '应用类型（0内部应用，1外部应用）',
  create_by   varchar(64) default ''   null  comment '创建者',
  create_time datetime                 null  comment '创建时间',
  update_by   varchar(64) default ''   null  comment '更新者',
  update_time datetime                 null  comment '更新时间',
  remark      varchar(500)             null  comment '备注'
) comment '操作日志记录';


-- ----------------------------
-- 登录日志
-- ----------------------------
drop table if exists sys_login_info;
create table sys_login_info
(
  info_id        bigint auto_increment          comment '访问ID'  primary key,
  user_name      varchar(50) default ''  null   comment '用户账号',
  ip_addr         varchar(50) default '' null   comment '登录IP地址',
  login_location varchar(255) default '' null   comment '登录地点',
  browser        varchar(50) default ''  null   comment '浏览器类型',
  os             varchar(50) default ''  null   comment '操作系统',
  status         char default '0'        null   comment '登录状态（0成功 1失败）',
  msg            varchar(255) default '' null   comment '提示消息',
  login_time     datetime                null   comment '访问时间'
) comment '系统访问记录';


-- ----------------------------
-- 系统通知
-- ----------------------------
drop table if exists sys_notice;
create table sys_notice
(
  notice_id      int(4) auto_increment            comment '公告ID' primary key,
  notice_title   varchar(50)            not null  comment '公告标题',
  notice_type    char                   not null  comment '公告类型（1通知 2公告）',
  notice_content varchar(2000)          null      comment '公告内容',
  status         char default '0'       null      comment '公告状态（0正常 1关闭）',
  create_by      varchar(64) default '' null      comment '创建者',
  create_time    datetime               null      comment '创建时间',
  update_by      varchar(64) default '' null      comment '更新者',
  update_time    datetime               null      comment '更新时间',
  remark         varchar(255)           null      comment '备注'
) comment '通知公告表';
