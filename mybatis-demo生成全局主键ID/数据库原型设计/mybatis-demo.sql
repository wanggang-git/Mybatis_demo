/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/5/27 17:15:12                           */
/*==============================================================*/


drop table if exists department;

drop table if exists employee;

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

