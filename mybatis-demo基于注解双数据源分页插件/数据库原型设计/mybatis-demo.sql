/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/7/11 10:48:02                           */
/*==============================================================*/


drop table if exists department;

drop table if exists employee;

drop table if exists role;

drop table if exists t_blog;

drop table if exists t_tag;

drop table if exists t_tag_blog;

drop table if exists user;

/*==============================================================*/
/* Table: department                                            */
/*==============================================================*/
create table department
(
   id                   INT8 not null,
   dept_name            varchar(20) comment '部门名称',
   descr                varchar(100) comment '部门描述',
   create_time          timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   id                   INT8 not null,
   name                 varchar(20) comment '部门名称',
   age                  varchar(100) comment '部门描述',
   gender               INT2,
   dept_id              INT8,
   address              varchar(100),
   create_time          timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   INT8 not null,
   role_name            varchar(20) comment '角色名称',
   primary key (id)
);

/*==============================================================*/
/* Table: t_blog                                                */
/*==============================================================*/
create table t_blog
(
   id                   INT8 not null,
   title                varchar(20) comment '标题',
   user_id              INT8 comment '用户ID',
   content              varchar(200) comment '内容',
   create_time          timestamp comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: t_tag                                                 */
/*==============================================================*/
create table t_tag
(
   id                   INT8 not null,
   name                 varchar(20) comment '标签名',
   primary key (id)
);

/*==============================================================*/
/* Table: t_tag_blog                                            */
/*==============================================================*/
create table t_tag_blog
(
   id                   INT8 not null,
   blog_id              INT8 comment 'blogId',
   tag_id               INT8 comment 'tagId',
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   INT8 not null,
   nick_name            varchar(30) comment '昵称',
   login_name           varchar(40) comment '登录名',
   password             varchar(20) comment '密码',
   create_time          timestamp comment '注册时间',
   role_id              INT8,
   primary key (id)
);

