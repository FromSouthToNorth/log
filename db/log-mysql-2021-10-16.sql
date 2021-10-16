create
database `log`;
use
`log`;
-- 角色表
drop table if exists `sys_role`;
create table `sys_role`
(
    `role_id`     varchar(32) character set utf8mb4 collate utf8mb4_general_ci  not null comment '主键',
    `role_name`   varchar(32) character set utf8mb4 collate utf8mb4_general_ci  not null comment '角色名称',
    `role_key`    varchar(100) character set utf8mb4 collate utf8mb4_general_ci not null comment '角色权限字符串',
    `status`      tinyint(1) null default null comment '角色状态（0正常 1停用）',
    `data_scope`  tinyint(1) null default null comment '数据范围（1：全部数据权限 2: 自定义数据权限 2：仅自己数据权限）',
    `del_flag`    tinyint(1) null default null comment '删除标志（0代表存在 1代表删除）',
    `create_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci  not null comment '创建人',
    `create_time` datetime(0) null default null comment '创建日期',
    `update_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci  not null comment '更新人',
    `update_time` datetime(0) null default null comment '更新日期',
    primary key (`role_id`) using btree,
    unique index `uniq_sys_role_role_key` (`role_key`) using btree,
    index         `idx_sr_role_key` (`role_key`) using btree
) engine = innodb
  character set = utf8
  collate = utf8_general_ci comment = '角色表'
  row_format = Dynamic;

-- 用户表
drop table if exists `sys_user`;
create table `sys_user`
(
    `user_id`     varchar(32) character set utf8mb4 collate utf8mb4_general_ci not null comment '主键',
    `user_name`   varchar(32) character set utf8mb4 collate utf8mb4_general_ci not null comment '角色名称',
    `real_name`   varchar(32) character set utf8mb4 collate utf8mb4_general_ci null default null comment '真实名称',
    `password`    varchar(255) character set utf8mb4 collate utf8mb4_general_ci null default null comment '密码',
    `avatar`      varchar(255) character set utf8mb4 collate utf8mb4_general_ci null default null comment '头像',
    `birthday`    datetime(0) null default null comment '生日',
    `sex`         tinyint(1) null default null comment '性别(0-默认未知,1-男,2-女)',
    `email`       varchar(50) character set utf8mb4 collate utf8mb4_general_ci null default null comment '用户邮箱',
    `phone`       varchar(50) character set utf8mb4 collate utf8mb4_general_ci null default null comment '电话',
    `status`      tinyint(1) null default null comment '用户状态（0正常 1停用）',
    `del_flag`    tinyint(1) null default null comment '删除标志（0代表存在 1代表删除）',
    `create_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci not null comment '创建人',
    `create_time` datetime(0) null default null comment '创建日期',
    `update_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci not null comment '更新人',
    `update_time` datetime(0) null default null comment '更新日期',
    primary key (`user_id`) using btree,
    unique index `index_user_name` (`user_name`) using btree,
    unique index `uniq_sys_user_phone` (`phone`) using btree,
    unique index `uniq_sys_user_email` (`email`) using btree,
    index         `idx_us_user_name` (`user_name`) using btree,
    index         `idx_su_status` (`status`) using btree,
    index         `idx_su_del_flag` (`del_flag`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '用户表'
  row_format = dynamic;

-- 用户角色表
drop table if exists `sys_user_role`;
create table `sys_user_role`
(
    `id`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci not null comment '主键id',
    `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci null default null comment '用户id',
    `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci null default null comment '角色id',
    primary key (`id`) using btree,
    INDEX     `index2_groupuu_user_id` (`user_id`) using btree,
    INDEX     `index2_groupuu_ole_id` (`role_id`) using btree,
    INDEX     `index2_groupuu_useridandroleid` (`user_id`, `role_id`) using btree,
    INDEX     `idx_sur_user_id` (`user_id`) using btree,
    INDEX     `idx_sur_role_id` (`role_id`) using btree,
    INDEX     `idx_sur_user_role_id` (`user_id`, `role_id`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '用户角色表'
  row_format = dynamic;

-- 权限菜单表
drop table if exists `sys_permission_menu`;
create table `sys_permission_menu`
(
    `menu_id`     varchar(32) character set utf8 collate utf8_general_ci       not null comment '主键',
    `menu_name`   varchar(50) character set utf8 collate utf8_general_ci       not null comment '菜单名称',
    `parent_id`   varchar(32) character set utf8 collate utf8_general_ci null default null comment '父菜单id',
    `order_num`   int(4) default 0 comment '显示顺序',
    `path`        varchar(200) character set utf8 collate utf8_general_ci null default null comment '路由地址',
    `component`   varchar(255) character set utf8 collate utf8_general_ci null default null comment '组件地址',
    `is_frame`    tinyint(1) null default null comment '是否为外链（0是 1否）',
    `is_cache`    tinyint(1) null default null comment '是否缓存（0缓存 1不缓存）',
    `menu_type`   varchar(2) character set utf8 collate utf8_general_ci null default null comment '菜单类型（M目录 C菜单 F按钮）',
    `visible`     tinyint(1) null default null comment '菜单状态（0显示 1隐藏）',
    `status`      tinyint(1) null default null comment '菜单状态（0正常 1停用）',
    `perms`       varchar(100) character set utf8 collate utf8_general_ci null default null comment '权限标识',
    `icon`        varchar(100) character set utf8 collate utf8_general_ci null default null comment '菜单图标',
    `del_flag`    tinyint(1) null default null comment '删除标志（0代表存在 1代表删除）',
    `create_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci not null comment '创建人',
    `create_time` datetime(0) null default null comment '创建日期',
    `update_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci not null comment '更新人',
    `update_time` datetime(0) null default null comment '更新日期',
    primary key (`menu_id`) using btree,
    index         `index_prem_pid` (`parent_id`) using btree,
    index         `index_prem_del_flag` (`del_flag`) using btree,
    index         `index_menu_type` (`menu_type`) using btree,
    index         `index_menu_visible` (`visible`) using btree,
    index         `index_menu_status` (`status`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '权限菜单表'
  row_format = dynamic;

-- 角色权限表
drop table if exists `sys_role_permission`;
create table `sys_role_permission`
(
    `id`            varchar(32) character set utf8 collate utf8_general_ci not null comment '主键',
    `role_id`       varchar(32) character set utf8 collate utf8_general_ci null default null comment '角色id',
    `permission_id` varchar(32) character set utf8 collate utf8_general_ci null default null comment '权限id',
    primary key (`id`) using btree,
    index           `index_group_role_per_id` (`role_id`, `permission_id`) using btree,
    index           `index_group_role_id` (`role_id`) using btree,
    index           `index_group_per_id` (`permission_id`) using btree,
    INDEX           `idx_srp_role_id`(`role_id`) using btree,
    INDEX           `idx_srp_permission_id`(`permission_id`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '角色权限表'
  row_format = dynamic;