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
    `status`      tinyint(1)                                                    null default null comment '角色状态（0正常 1停用）',
    `data_scope`  tinyint(1)                                                    null default null comment '数据范围（1：全部数据权限 2: 自定义数据权限 3：仅自己数据权限）',
    `del_flag`    tinyint(1)                                                    null default null comment '删除标志（0代表存在 1代表删除）',
    `create_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci  not null comment '创建人',
    `create_time` datetime(0)                                                   null default null comment '创建日期',
    `update_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci  not null comment '更新人',
    `update_time` datetime(0)                                                   null default null comment '更新日期',
    primary key (`role_id`) using btree,
    unique index `uniq_sys_role_role_key` (`role_key`) using btree,
    index `idx_sr_role_key` (`role_key`) using btree
) engine = innodb
  character set = utf8
  collate = utf8_general_ci comment = '角色表'
  row_format = Dynamic;

-- 初始化角色信息
insert into sys_role
values ('1', '超级管理员', 'admin', 0, 1, 0, 'admin', sysdate(), '', null);
insert into sys_role
values ('2', '普通用户', 'common', 0, 3, 0, 'admin', sysdate(), '', null);

-- 用户表
drop table if exists `sys_user`;
create table `sys_user`
(
    `user_id`     varchar(32) character set utf8mb4 collate utf8mb4_general_ci  not null comment '主键',
    `user_name`   varchar(32) character set utf8mb4 collate utf8mb4_general_ci  not null comment '角色名称',
    `real_name`   varchar(32) character set utf8mb4 collate utf8mb4_general_ci  null default null comment '真实名称',
    `user_type`   tinyint(1)                                                    null default 0 comment '用户类型（0系统用户）',
    `password`    varchar(255) character set utf8mb4 collate utf8mb4_general_ci null default null comment '密码',
    `avatar`      varchar(255) character set utf8mb4 collate utf8mb4_general_ci null default null comment '头像',
    `birthday`    datetime(0)                                                   null default null comment '生日',
    `sex`         tinyint(1)                                                    null default null comment '性别(0-默认未知,1-男,2-女)',
    `email`       varchar(50) character set utf8mb4 collate utf8mb4_general_ci  null default null comment '用户邮箱',
    `phone`       varchar(50) character set utf8mb4 collate utf8mb4_general_ci  null default null comment '电话',
    `status`      tinyint(1)                                                    null default null comment '用户状态（0正常 1停用）',
    `del_flag`    tinyint(1)                                                    null default null comment '删除标志（0代表存在 1代表删除）',
    `create_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci  not null comment '创建人',
    `create_time` datetime(0)                                                   null default null comment '创建日期',
    `update_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci  not null comment '更新人',
    `update_time` datetime(0)                                                   null default null comment '更新日期',
    primary key (`user_id`) using btree,
    unique index `index_user_name` (`user_name`) using btree,
    unique index `uniq_sys_user_phone` (`phone`) using btree,
    unique index `uniq_sys_user_email` (`email`) using btree,
    index `idx_us_user_name` (`user_name`) using btree,
    index `idx_su_status` (`status`) using btree,
    index `idx_su_del_flag` (`del_flag`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '用户表'
  row_format = dynamic;

-- 初始化用户
insert into sys_user
values (1, 'admin', 'hy', 0, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', null, null, 1,
        '598050554@qq.com', '18181795450', 0, 0, 'admin', sysdate(), '', null);
insert into sys_user
values (2, 'outsider', 'lqr', 0, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', null, null, 1,
        '1172484612@qq.com', '15982620714', 0, 0, 'admin', sysdate(), '', null);

-- 用户角色表
drop table if exists `sys_user_role`;
create table `sys_user_role`
(
    `id`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci not null comment '主键id',
    `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci null default null comment '用户id',
    `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci null default null comment '角色id',
    primary key (`id`) using btree,
    index `index2_groupuu_user_id` (`user_id`) using btree,
    index `index2_groupuu_ole_id` (`role_id`) using btree,
    index `index2_groupuu_useridandroleid` (`user_id`, `role_id`) using btree,
    index `idx_sur_user_id` (`user_id`) using btree,
    index `idx_sur_role_id` (`role_id`) using btree,
    index `idx_sur_user_role_id` (`user_id`, `role_id`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '用户角色表'
  row_format = dynamic;

-- 初始化用户角色表
insert into sys_user_role
values ('1', '1', '1');
insert into sys_user_role
values ('2', '2', '1');

-- 权限菜单表
drop table if exists `sys_permission_menu`;
create table `sys_permission_menu`
(
    `menu_id`     varchar(32) character set utf8 collate utf8_general_ci       not null comment '主键',
    `menu_name`   varchar(50) character set utf8 collate utf8_general_ci       not null comment '菜单名称',
    `parent_id`   varchar(32) character set utf8 collate utf8_general_ci       null default null comment '父菜单id',
    `order_num`   int(4)                                                            default 0 comment '显示顺序',
    `path`        varchar(200) character set utf8 collate utf8_general_ci      null default null comment '路由地址',
    `component`   varchar(255) character set utf8 collate utf8_general_ci      null default null comment '组件地址',
    `is_frame`    tinyint(1)                                                   null default null comment '是否为外链（0是 1否）',
    `is_cache`    tinyint(1)                                                   null default null comment '是否缓存（0缓存 1不缓存）',
    `menu_type`   varchar(2) character set utf8 collate utf8_general_ci        null default null comment '菜单类型（M目录 C菜单 F按钮）',
    `visible`     tinyint(1)                                                   null default null comment '菜单状态（0显示 1隐藏）',
    `status`      tinyint(1)                                                   null default null comment '菜单状态（0正常 1停用）',
    `perms`       varchar(100) character set utf8 collate utf8_general_ci      null default null comment '权限标识',
    `icon`        varchar(100) character set utf8 collate utf8_general_ci      null default null comment '菜单图标',
    `del_flag`    tinyint(1)                                                   null default null comment '删除标志（0代表存在 1代表删除）',
    `create_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci not null comment '创建人',
    `create_time` datetime(0)                                                  null default null comment '创建日期',
    `update_by`   varchar(50) character set utf8mb4 collate utf8mb4_general_ci not null comment '更新人',
    `update_time` datetime(0)                                                  null default null comment '更新日期',
    primary key (`menu_id`) using btree,
    index `index_prem_pid` (`parent_id`) using btree,
    index `index_prem_del_flag` (`del_flag`) using btree,
    index `index_menu_type` (`menu_type`) using btree,
    index `index_menu_visible` (`visible`) using btree,
    index `index_menu_status` (`status`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '权限菜单表'
  row_format = dynamic;

-- 一级目录
insert into sys_permission_menu
values ('1', '系统管理', null, '1', 'system', null, 1, 0, 'M', 0, 0, '', 'system', 0, 'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('2', '系统监控', null, '2', 'monitor', null, 1, 0, 'M', 0, 0, '', 'monitor', 0, 'admin', sysdate(), '', null);

-- 二级菜单
insert into sys_permission_menu
values ('100', '博客管理', '1', '1', 'blog', 'system/blog/index', 1, 0, 'C', '0', '0', 'system:blog:list', 'blog', 0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('101', '分类管理', '1', '2', 'type', 'system/blog/index', 1, 0, 'C', '0', '0', 'system:type:list', 'type', 0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('102', '标签管理', '1', '3', 'tag', 'system/tag/index', 1, 0, 'C', '0', '0', 'system:tag:list', 'tag', 0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('103', '用户管理', '1', '4', 'user', 'system/user/index', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('104', '角色管理', '1', '5', 'role', 'system/role/index', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('105', '菜单管理', '1', '6', 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('106', '字典管理', '1', '7', 'menu', 'system/dict/index', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('107', '日志管理', '1', '8', 'log', '', 1, 0, 'M', '0', '0', '', 'log', 0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('108', '在线用户', '2', '1', 'online', 'monitor/online/index', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online',
        0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('109', '数据监控', '2', '2', 'druid', 'monitor/druid/index', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('110', '服务监控', '2', '3', 'server', 'monitor/server/index', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server',
        0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('111', '服务监控', '2', '4', 'cache', 'monitor/cache/index', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'cache', 0,
        'admin', sysdate(), '', null);

-- 三级菜单
insert into sys_permission_menu
values ('500', '操作日志', '108', '1', 'log', 'monitor/operlog/index', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form',
        0,
        'admin', sysdate(), '', null);
insert into sys_permission_menu
values ('501', '登录日志', '108', '2', 'logininfor', 'monitor/operlog/index', 1, 0, 'C', '0', '0',
        'monitor:logininfor:list', 'logininfor', 0,
        'admin', sysdate(), '', null);

-- 博客管理按钮
insert into sys_permission_menu
values ('1001', '博客查询', '100', '1', '', '', 1, 0, 'F', '0', '0', 'system:blog:query', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1002', '博客新增', '100', '2', '', '', 1, 0, 'F', '0', '0', 'system:blog:add', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1003', '博客修改', '100', '3', '', '', 1, 0, 'F', '0', '0', 'system:blog:edit', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1004', '博客删除', '100', '4', '', '', 1, 0, 'F', '0', '0', 'system:blog:remove', '#', 0, 'admin', sysdate(), '',
        null);

-- 分类管理按钮
insert into sys_permission_menu
values ('1005', '分类查询', '101', '1', '', '', 1, 0, 'F', '0', '0', 'system:type:query', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1006', '分类新增', '101', '2', '', '', 1, 0, 'F', '0', '0', 'system:type:add', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1007', '分类修改', '101', '3', '', '', 1, 0, 'F', '0', '0', 'system:type:edit', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1008', '分类删除', '101', '4', '', '', 1, 0, 'F', '0', '0', 'system:type:remove', '#', 0, 'admin', sysdate(), '',
        null);

-- 标签管理按钮
insert into sys_permission_menu
values ('1009', '标签查询', '102', '1', '', '', 1, 0, 'F', '0', '0', 'system:tag:query', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1010', '标签新增', '102', '2', '', '', 1, 0, 'F', '0', '0', 'system:tag:add', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1011', '标签修改', '102', '3', '', '', 1, 0, 'F', '0', '0', 'system:tag:edit', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1012', '标签删除', '102', '4', '', '', 1, 0, 'F', '0', '0', 'system:tag:remove', '#', 0, 'admin', sysdate(), '',
        null);

-- 用户管理按钮
insert into sys_permission_menu
values ('1013', '用户查询', '103', '1', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1014', '用户新增', '103', '2', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1015', '用户修改', '103', '3', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1016', '用户删除', '103', '4', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 0, 'admin', sysdate(), '',
        null);

-- 角色管理按钮
insert into sys_permission_menu
values ('1017', '角色查询', '104', '1', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1018', '角色新增', '104', '2', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1019', '角色修改', '104', '3', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1020', '角色删除', '104', '4', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 0, 'admin', sysdate(), '',
        null);

-- 菜单管理按钮
insert into sys_permission_menu
values ('1021', '菜单查询', '106', '1', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1022', '菜单新增', '106', '2', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1023', '菜单修改', '106', '3', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1024', '菜单删除', '106', '4', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 0, 'admin', sysdate(), '',
        null);

-- 字典管理按钮
insert into sys_permission_menu
values ('1025', '菜单查询', '107', '1', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1026', '菜单新增', '107', '2', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1027', '菜单修改', '107', '3', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 0, 'admin', sysdate(), '',
        null);
insert into sys_permission_menu
values ('1028', '菜单删除', '107', '4', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 0, 'admin', sysdate(), '',
        null);

-- 操作日志按钮
insert into sys_permission_menu
values ('1029', '操作日志查询', '500', '1', '', '', 1, 0, 'F', '0', '0', 'system:operlog:query', '#', 0, 'admin', sysdate(),
        '', null);
insert into sys_permission_menu
values ('1030', '操作日志删除', '500', '2', '', '', 1, 0, 'F', '0', '0', 'system:operlog:remove', '#', 0, 'admin', sysdate(),
        '', null);

-- 登录日志按钮
insert into sys_permission_menu
values ('1031', '登录日志查询', '501', '1', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:query', '#', 0, 'admin',
        sysdate(), '', null);
insert into sys_permission_menu
values ('1032', '登录日志删除', '501', '2', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:remove', '#', 0, 'admin',
        sysdate(), '', null);

-- 在线用户按钮
insert into sys_permission_menu
values ('1033', '在线用户查询', '109', '1', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:query', '#', 0, 'admin',
        sysdate(), '', null);
insert into sys_permission_menu
values ('1034', '在线用户批量强退', '109', '1', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:query', '#', 0, 'admin',
        sysdate(), '', null);
insert into sys_permission_menu
values ('1035', '在线用户单条强退', '109', '2', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:remove', '#', 0, 'admin',
        sysdate(), '', null);

-- 角色权限表
drop table if exists `sys_role_permission`;
create table `sys_role_permission`
(
    `id`            varchar(32) character set utf8 collate utf8_general_ci not null comment '主键',
    `role_id`       varchar(32) character set utf8 collate utf8_general_ci null default null comment '角色id',
    `permission_id` varchar(32) character set utf8 collate utf8_general_ci null default null comment '权限id',
    primary key (`id`) using btree,
    index `index_group_role_per_id` (`role_id`, `permission_id`) using btree,
    index `index_group_role_id` (`role_id`) using btree,
    index `index_group_per_id` (`permission_id`) using btree,
    index `idx_srp_role_id` (`role_id`) using btree,
    index `idx_srp_permission_id` (`permission_id`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '角色权限表'
  row_format = dynamic;

-- 初始化角色权限表
insert into sys_role_permission
values ('1', '2', '1');
insert into sys_role_permission
values ('2', '2', '100');
insert into sys_role_permission
values ('3', '2', '101');
insert into sys_role_permission
values ('4', '2', '102');
insert into sys_role_permission
values ('5', '2', '1001');
insert into sys_role_permission
values ('6', '2', '1002');
insert into sys_role_permission
values ('7', '2', '1003');
insert into sys_role_permission
values ('8', '2', '1004');
insert into sys_role_permission
values ('9', '2', '1005');
insert into sys_role_permission
values ('10', '2', '1006');
insert into sys_role_permission
values ('11', '2', '1007');
insert into sys_role_permission
values ('12', '2', '1008');
insert into sys_role_permission
values ('13', '2', '1009');
insert into sys_role_permission
values ('14', '2', '1010');
insert into sys_role_permission
values ('15', '2', '1011');
insert into sys_role_permission
values ('16', '2', '1012');

-- 系统登录访问记录
drop table if exists sys_logininfor;
create table sys_login_info
(
    info_id        varchar(32) character set utf8 collate utf8_general_ci not null comment '访问ID',
    user_name      varchar(50) character set utf8 collate utf8_general_ci      default null comment '用户账号',
    ipaddr         varchar(128) character set utf8 collate utf8_general_ci     default null comment '登录IP地址',
    login_location varchar(255) character set utf8 collate utf8_general_ci     default null comment '登录地点',
    browser        varchar(50) character set utf8 collate utf8_general_ci      default null comment '浏览器类型',
    os             varchar(50) character set utf8 collate utf8_general_ci      default null comment '操作系统',
    status         tinyint(1)                                             null default null comment '登录状态（0成功 1失败）',
    msg            varchar(255) character set utf8 collate utf8_general_ci     default '' comment '提示消息',
    login_time     datetime comment '访问时间',
    primary key (info_id) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '系统登录访问记录'
  row_format = dynamic;

-- 操作日志表
drop table if exists `sys_oper_log`;
create table `sys_oper_log`
(
    `oper_id`        varchar(32) character set utf8 collate utf8_general_ci   not null comment '主键',
    `title`          varchar(50) character set utf8 collate utf8_general_ci   null default null comment '模块标题',
    `business_type`  tinyint(1)                                               null default null comment '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100) character set utf8 collate utf8_general_ci  null default null comment '方法名称',
    `request_method` varchar(10) character set utf8 collate utf8_general_ci   null default null comment '请求方式',
    `operator_type`  tinyint(1)                                               null default null comment '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50) character set utf8 collate utf8_general_ci   null default null comment '操作人员',
    `oper_url`       varchar(255) character set utf8 collate utf8_general_ci  null default null comment '请求URL',
    `oper_ip`        varchar(128) character set utf8 collate utf8_general_ci  null default null comment '主机地址',
    `oper_location`  varchar(255) character set utf8 collate utf8_general_ci  null default null comment '操作地点',
    `oper_param`       varchar(2000) character set utf8 collate utf8_general_ci null default null comment '请求参数',
    `json_result`      varchar(2000)                                                 default '' comment '返回参数',
    `status`           tinyint(1)                                               null default null comment '操作状态（0正常 1异常）',
    `error_msg`        varchar(2000) character set utf8 collate utf8_general_ci null default null comment '错误消息',
    `oper_time`        datetime comment '操作时间',
    primary key (`oper_id`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '操作日志表'
  row_format = dynamic;

-- 字典类型表
drop table if exists `sys_dict_type`;
create table `sys_dict_type`
(
    `dict_id`     varchar(32) character set utf8 collate utf8_general_ci not null comment '字典主键',
    `dict_name`   varchar(100) character set utf8 collate utf8_general_ci comment '字典名称',
    `dict_type`   varchar(100) character set utf8 collate utf8_general_ci comment '字典类型',
    `status`      tinyint(1)                                             null default null comment '状态（0正常 1停用）',
    `create_by`   varchar(64) character set utf8 collate utf8_general_ci comment '创建者',
    `create_time` datetime(0) comment '创建时间',
    `update_by`   varchar(64) character set utf8 collate utf8_general_ci comment '更新者',
    `update_time` datetime(0) comment '更新时间',
    `remark`      varchar(500)                                           null default null comment '备注',
    primary key (`dict_id`) using btree,
    unique index `uniq_dict_type` (`dict_type`) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '字典类型表'
  row_format = dynamic;

-- 初始化字典类型表
insert into sys_dict_type
values ('1', '用户性别', 'sys_user_sex', '0', 'admin', sysdate(), '', null, '用户性别列表');
insert into sys_dict_type
values ('2', '菜单状态', 'sys_show_hide', '0', 'admin', sysdate(), '', null, '菜单状态列表');
insert into sys_dict_type
values ('3', '系统开关', 'sys_normal_disable', '0', 'admin', sysdate(), '', null, '系统开关列表');

-- 字典数据表
drop table if exists `sys_dict_data`;
create table `sys_dict_data`
(
    dict_code   varchar(32) character set utf8 collate utf8_general_ci  not null comment '字典编码',
    dict_sort   int(4)                                                       default 0 comment '字典排序',
    dict_label  varchar(100) character set utf8 collate utf8_general_ci null default null comment '字典标签',
    dict_value  varchar(100) character set utf8 collate utf8_general_ci null default null comment '字典键值',
    dict_type   varchar(100) character set utf8 collate utf8_general_ci null default null comment '字典类型',
    css_class   varchar(100) character set utf8 collate utf8_general_ci null default null comment '样式属性（其他样式扩展）',
    list_class  varchar(100) character set utf8 collate utf8_general_ci null default null comment '表格回显样式',
    is_default  tinyint(1)                                              null default null comment '是否默认（0是 1否）',
    status      tinyint(1)                                              null default null comment '状态（0正常 1停用）',
    create_by   varchar(64) character set utf8 collate utf8_general_ci  null default null comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) character set utf8 collate utf8_general_ci  null default null comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) character set utf8 collate utf8_general_ci null default null comment '备注',
    primary key (dict_code) using btree
) engine = InnoDB
  character set = utf8
  collate = utf8_general_ci comment = '字典数据表'
  row_format = dynamic;

-- 初始化字典数据表
insert into sys_dict_data
values ('1', 1, '男', '0', 'sys_user_sex', '', '', 0, '0', 'admin', sysdate(), '', null, '性别男');
insert into sys_dict_data
values ('2', 2, '女', '1', 'sys_user_sex', '', '', 1, '0', 'admin', sysdate(), '', null, '性别女');
insert into sys_dict_data
values ('3', 3, '未知', '2', 'sys_user_sex', '', '', 1, '0', 'admin', sysdate(), '', null, '性别未知');
insert into sys_dict_data
values ('4', 1, '显示', '0', 'sys_show_hide', '', 'primary', 0, '0', 'admin', sysdate(), '', null, '显示菜单');
insert into sys_dict_data
values ('5', 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 1, '0', 'admin', sysdate(), '', null, '隐藏菜单');
insert into sys_dict_data
values ('6', 1, '正常', '0', 'sys_normal_disable', '', 'primary', 0, '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data
values ('7', 2, '停用', '1', 'sys_normal_disable', '', 'danger', 1, '0', 'admin', sysdate(), '', null, '停用状态');