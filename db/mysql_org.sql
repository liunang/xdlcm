/* 初始化auth_info */
INSERT INTO `auth_info` VALUES (100, '菜单管理', '查询', '/menu/queryMenuTreeAll.action');
INSERT INTO `auth_info` VALUES (101, '菜单管理', '修改', '/menu/updateMenu.action');
INSERT INTO `auth_info` VALUES (102, '菜单管理', '添加', '/menu/addMenu.action');
INSERT INTO `auth_info` VALUES (103, '菜单管理', '删除', '/menu/removeMenu.action');
INSERT INTO `auth_info` VALUES (110, '参数配置管理', '查询', '/param/findParam.action');
INSERT INTO `auth_info` VALUES (111, '参数配置管理', '查询', '/param/findParamById.action');
INSERT INTO `auth_info` VALUES (112, '参数配置管理', '添加', '/param/addParam.action');
INSERT INTO `auth_info` VALUES (113, '参数配置管理', '修改', '/param/updateParam.action');
INSERT INTO `auth_info` VALUES (114, '参数配置管理', '删除', '/param/removeParam.action');
INSERT INTO `auth_info` VALUES (115, '参数配置管理', '删除', '/param/removeParams.action');
INSERT INTO `auth_info` VALUES (120, '系统用户管理', '查询', '/user/findByCondition.action');
INSERT INTO `auth_info` VALUES (121, '系统用户管理', '查询', '/user/findById.action');
INSERT INTO `auth_info` VALUES (122, '系统用户管理', '添加', '/user/addUser.action');
INSERT INTO `auth_info` VALUES (123, '系统用户管理', '修改', '/user/updateUser.action');
INSERT INTO `auth_info` VALUES (124, '系统用户管理', '删除', '/user/delUser.action');
INSERT INTO `auth_info` VALUES (125, '系统用户管理', '查询', '/role/isExitRoleByCreator.action');
INSERT INTO `auth_info` VALUES (126, '系统用户管理', '查询', '/user/resetPassword.action');
INSERT INTO `auth_info` VALUES (127, '系统用户管理', '查询', '/role/queryRoleInfo.action');
INSERT INTO `auth_info` VALUES (130, '系统角色管理', '查询', '/role/findByCondition.action');
INSERT INTO `auth_info` VALUES (131, '系统角色管理', '查询', '/role/beforeUpdateRole.action');
INSERT INTO `auth_info` VALUES (132, '系统角色管理', '查询', '/role/checkCanRemoveRole.action');
INSERT INTO `auth_info` VALUES (133, '系统角色管理', '查询', '/role/beforeUpdateRole.action');
INSERT INTO `auth_info` VALUES (134, '系统角色管理', '查询', '/role/loadAuthorityCheckTree.action');
INSERT INTO `auth_info` VALUES (135, '系统角色管理', '添加', '/role/addRole.action');
INSERT INTO `auth_info` VALUES (136, '系统角色管理', '修改', '/role/updateRole.action');
INSERT INTO `auth_info` VALUES (137, '系统角色管理', '修改', '/role/updateRoleEditFlag.action');
INSERT INTO `auth_info` VALUES (138, '系统角色管理', '删除', '/role/delRole.action');
INSERT INTO `auth_info` VALUES (984, '厂商信息管理', '查询', '/firm/findByCondition.action');
INSERT INTO `auth_info` VALUES (985, '厂商信息管理', '查询', '/firm/findById.action');
INSERT INTO `auth_info` VALUES (986, '厂商信息管理', '添加', '/firm/addFirm.action');
INSERT INTO `auth_info` VALUES (987, '厂商信息管理', '修改', '/firm/updateFirm.action');
INSERT INTO `auth_info` VALUES (988, '厂商信息管理', '删除', '/firm/delFirm.action');
INSERT INTO `auth_info` VALUES (989, '厂商用户管理', '查询', '/firmUser/findByCondition.action');
INSERT INTO `auth_info` VALUES (990, '厂商用户管理', '查询', '/firmUser/findById.action');
INSERT INTO `auth_info` VALUES (991, '厂商用户管理', '添加', '/firmUser/addFirmUser.action');
INSERT INTO `auth_info` VALUES (992, '厂商用户管理', '修改', '/firmUser/updateFirmUser.action');
INSERT INTO `auth_info` VALUES (993, '厂商用户管理', '删除', '/firmUser/delFirmUser.action');
INSERT INTO `auth_info` VALUES (994, '厂商及产品信息管理', '查询', '/product/findByCondition.action');
INSERT INTO `auth_info` VALUES (995, '厂商及产品信息管理', '查询', '/product/findById.action');
INSERT INTO `auth_info` VALUES (996, '厂商及产品信息管理', '添加', '/product/addProduct.action');
INSERT INTO `auth_info` VALUES (997, '厂商及产品信息管理', '修改', '/product/updateProduct.action');
INSERT INTO `auth_info` VALUES (998, '厂商及产品信息管理', '删除', '/product/delProduct.action');
INSERT INTO `auth_info` VALUES (999, '标签批次信息查询', '查询', '/tag/findByCondition.action');
INSERT INTO `auth_info` VALUES (1000, '标签批次信息查询', '查询', '/tag/findById.action');
INSERT INTO `auth_info` VALUES (1001, '标签批次信息查询', '查询', '/tagBatch/findByCondition.action');
INSERT INTO `auth_info` VALUES (1002, '标签批次信息查询', '查询', '/tagBatch/findById.action');
INSERT INTO `auth_info` VALUES (1003, '产品信息管理', '查询', '/firm/queryFirmOptions.action');
INSERT INTO `auth_info` VALUES (1004, '厂商用户管理', '查询', '/firm/queryFirmOptions.action');
INSERT INTO `auth_info` VALUES (1005, '标签批次信息查询', '查询', '/firm/queryFirmOptions.action');
INSERT INTO `auth_info` VALUES (1006, '产品信息管理', '查询', '/product/findImageById.action');
INSERT INTO `auth_info` VALUES (1007, '产品信息管理', '修改', '/product/imageUpload.action');
INSERT INTO `auth_info` VALUES (1008, '产品信息管理', '查询', '/product/findMovieById.action');
INSERT INTO `auth_info` VALUES (1009, '产品信息管理', '修改', '/product/movieUpload.action');
INSERT INTO `auth_info` VALUES (1010, '产品信息管理', '删除', '/product/deleteImage.action');
INSERT INTO `auth_info` VALUES (1011, '产品信息管理', '删除', '/product/deleteMovie.action');



/* 初始化menu_tree */
INSERT INTO `menu_tree` VALUES (1, NULL, '监控', 'r', 0, NULL, 1);
INSERT INTO `menu_tree` VALUES (2, 1, '开发维护', 'fa-tasks', 0, NULL, 2);
INSERT INTO `menu_tree` VALUES (3, 2, '菜单管理', '', 1, '/nfcm/menu/menu.html', 4);
INSERT INTO `menu_tree` VALUES (4, 2, '参数配置管理', '', 1, '/nfcm/param/param.html', 5);
INSERT INTO `menu_tree` VALUES (5, 1, '人员管理', 'icon-group', 0, NULL, 3);
INSERT INTO `menu_tree` VALUES (6, 5, '系统角色管理', '', 1, '/nfcm/role/role.html', 6);
INSERT INTO `menu_tree` VALUES (7, 5, '系统用户管理', '', 1, '/nfcm/user/user.html', 7);
INSERT INTO `menu_tree` VALUES (431, 1, '厂商及产品信息管理', 'icon-shopping-cart', 0, '', null);
INSERT INTO `menu_tree` VALUES (432, 431, '厂商信息管理', 'icon-shopping-bag', 1, '/nfcm/firm/firm.html', null);
INSERT INTO `menu_tree` VALUES (433, 431, '厂商用户管理', 'icon-user-circle', 1, '/nfcm/firmUser/firmUser.html', null);
INSERT INTO `menu_tree` VALUES (434, 1, '标签信息查询', 'icon-search', 0, '', null);
INSERT INTO `menu_tree` VALUES (435, 431, '产品信息管理', '', 1, '/nfcm/product/product.html', null);
INSERT INTO `menu_tree` VALUES (437, 434, '标签批次信息查询', '', 1, '/nfcm/tag/tagBatch.html', null);


/* 初始化menu_auth */
INSERT INTO `menu_auth` VALUES (1, 3, 100);
INSERT INTO `menu_auth` VALUES (2, 3, 101);
INSERT INTO `menu_auth` VALUES (3, 3, 102);
INSERT INTO `menu_auth` VALUES (4, 3, 103);
INSERT INTO `menu_auth` VALUES (5, 4, 110);
INSERT INTO `menu_auth` VALUES (6, 4, 111);
INSERT INTO `menu_auth` VALUES (7, 4, 112);
INSERT INTO `menu_auth` VALUES (8, 4, 113);
INSERT INTO `menu_auth` VALUES (9, 4, 114);
INSERT INTO `menu_auth` VALUES (10, 4, 115);
INSERT INTO `menu_auth` VALUES (11, 6, 130);
INSERT INTO `menu_auth` VALUES (12, 6, 131);
INSERT INTO `menu_auth` VALUES (13, 6, 132);
INSERT INTO `menu_auth` VALUES (14, 6, 133);
INSERT INTO `menu_auth` VALUES (15, 6, 134);
INSERT INTO `menu_auth` VALUES (16, 6, 135);
INSERT INTO `menu_auth` VALUES (17, 6, 136);
INSERT INTO `menu_auth` VALUES (18, 6, 137);
INSERT INTO `menu_auth` VALUES (19, 6, 138);
INSERT INTO `menu_auth` VALUES (20, 7, 120);
INSERT INTO `menu_auth` VALUES (21, 7, 121);
INSERT INTO `menu_auth` VALUES (22, 7, 122);
INSERT INTO `menu_auth` VALUES (23, 7, 123);
INSERT INTO `menu_auth` VALUES (24, 7, 124);
INSERT INTO `menu_auth` VALUES (25, 7, 125);
INSERT INTO `menu_auth` VALUES (26, 7, 126);
INSERT INTO `menu_auth` VALUES (27, 7, 127);
INSERT INTO `menu_auth` VALUES (242, 432, 984);
INSERT INTO `menu_auth` VALUES (243, 432, 985);
INSERT INTO `menu_auth` VALUES (244, 432, 986);
INSERT INTO `menu_auth` VALUES (245, 432, 987);
INSERT INTO `menu_auth` VALUES (246, 432, 988);
INSERT INTO `menu_auth` VALUES (247, 433, 989);
INSERT INTO `menu_auth` VALUES (248, 433, 990);
INSERT INTO `menu_auth` VALUES (249, 433, 991);
INSERT INTO `menu_auth` VALUES (250, 433, 992);
INSERT INTO `menu_auth` VALUES (251, 433, 993);
INSERT INTO `menu_auth` VALUES (252, 435, 994);
INSERT INTO `menu_auth` VALUES (253, 435, 995);
INSERT INTO `menu_auth` VALUES (254, 435, 996);
INSERT INTO `menu_auth` VALUES (255, 435, 997);
INSERT INTO `menu_auth` VALUES (256, 435, 998);
INSERT INTO `menu_auth` VALUES (257, 437, 999);
INSERT INTO `menu_auth` VALUES (258, 437, 1000);
INSERT INTO `menu_auth` VALUES (259, 437, 1001);
INSERT INTO `menu_auth` VALUES (260, 437, 1002);
INSERT INTO `menu_auth` VALUES (261, 435, 1003);
INSERT INTO `menu_auth` VALUES (262, 433, 1004);
INSERT INTO `menu_auth` VALUES (263, 437, 1005);
INSERT INTO `menu_auth` VALUES (264, 435, 1006);
INSERT INTO `menu_auth` VALUES (265, 435, 1007);
INSERT INTO `menu_auth` VALUES (266, 435, 1008);
INSERT INTO `menu_auth` VALUES (267, 435, 1009);
INSERT INTO `menu_auth` VALUES (268, 435, 1010);
INSERT INTO `menu_auth` VALUES (269, 435, 1011);

/* 初始化role_info */
INSERT INTO `role_info` VALUES (0, '开发者', '菜单管理参数管理', NULL);
INSERT INTO `role_info` VALUES (999, '权限管理员', '用户权限管理', NULL);

/* 初始化role_auth */
INSERT INTO `role_auth` VALUES (1, 0, 100);
INSERT INTO `role_auth` VALUES (2, 0, 101);
INSERT INTO `role_auth` VALUES (3, 0, 102);
INSERT INTO `role_auth` VALUES (4, 0, 103);
INSERT INTO `role_auth` VALUES (5, 0, 110);
INSERT INTO `role_auth` VALUES (6, 0, 111);
INSERT INTO `role_auth` VALUES (7, 0, 112);
INSERT INTO `role_auth` VALUES (8, 0, 113);
INSERT INTO `role_auth` VALUES (9, 0, 114);
INSERT INTO `role_auth` VALUES (10, 0, 115);
INSERT INTO `role_auth` VALUES (11, 999, 120);
INSERT INTO `role_auth` VALUES (12, 999, 121);
INSERT INTO `role_auth` VALUES (13, 999, 122);
INSERT INTO `role_auth` VALUES (14, 999, 123);
INSERT INTO `role_auth` VALUES (15, 999, 124);
INSERT INTO `role_auth` VALUES (16, 999, 125);
INSERT INTO `role_auth` VALUES (17, 999, 126);
INSERT INTO `role_auth` VALUES (18, 999, 127);
INSERT INTO `role_auth` VALUES (19, 999, 130);
INSERT INTO `role_auth` VALUES (20, 999, 131);
INSERT INTO `role_auth` VALUES (21, 999, 132);
INSERT INTO `role_auth` VALUES (22, 999, 133);
INSERT INTO `role_auth` VALUES (23, 999, 134);
INSERT INTO `role_auth` VALUES (24, 999, 135);
INSERT INTO `role_auth` VALUES (25, 999, 136);
INSERT INTO `role_auth` VALUES (26, 999, 137);
INSERT INTO `role_auth` VALUES (27, 999, 138);

/* 初始化role_menu */
INSERT INTO `role_menu` VALUES (1, 0, 3);
INSERT INTO `role_menu` VALUES (2, 0, 2);
INSERT INTO `role_menu` VALUES (3, 0, 4);
INSERT INTO `role_menu` VALUES (4, 999, 5);
INSERT INTO `role_menu` VALUES (5, 999, 6);
INSERT INTO `role_menu` VALUES (6, 999, 7);

/* 初始化user_info */
INSERT INTO `user_info` VALUES ('admin', '管理员', '72axtmmITGvJO0kkRZmlVg==', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES ('developer', '开发者', 'RmHwWuxImWE1UQwwECFNkQ==', NULL, NULL, NULL, NULL, NULL);

/* 初始化user_role */
INSERT INTO `user_role` VALUES (1, 0, 'developer');
INSERT INTO `user_role` VALUES (2, 999, 'admin');

/* 初始化bms_param */
INSERT INTO `bms_param` VALUES ('fileSavePath', 'D:\\fileSavePath', '文件保存路径');
INSERT INTO `bms_param` VALUES ('productImageCountLimit', '10', '产品图片限制数量');
INSERT INTO `bms_param` VALUES ('productVideoCountLimit', '2', '产品视频限制数量');

/**
 * 报表
 */


INSERT INTO `auth_info` VALUES (1012, '厂商信息统计表', '报表', '/firmReport/poiFirmReport.action');
INSERT INTO `auth_info` VALUES (1013, '厂商信息统计表', '报表', '/firmReport/downloadExcel.action');

INSERT INTO `auth_info` VALUES (1014, '芯片状态信息统计表', '报表', '/tagReport/poiTagReport.action');
INSERT INTO `auth_info` VALUES (1015, '芯片状态信息统计表', '报表', '/tagReport/downloadExcel.action');

INSERT INTO `auth_info` VALUES (1016, '密钥信息统计表', '报表', '/keyReport/poiKeyReport.action');
INSERT INTO `auth_info` VALUES (1017, '密钥信息统计表', '报表', '/keyReport/downloadExcel.action');

INSERT INTO `auth_info` VALUES (1018, '厂商信息统计表', '查询', '/firm/findByCondition.action');
INSERT INTO `auth_info` VALUES (1019, '芯片状态信息统计表', '查询', '/tag/findByCondition.action');
INSERT INTO `auth_info` VALUES (1020, '芯片状态信息统计表', '查询', '/firm/queryFirmOptions.action');
INSERT INTO `auth_info` VALUES (1021, '密钥信息统计表', '查询', '/key/findByCondition.action');

INSERT INTO `menu_tree` VALUES (438, 1, '报表统计', 'icon-search', 0, '', null);
INSERT INTO `menu_tree` VALUES (439, 438, '厂商信息统计报表', '', 1, '/nfcm/report/firmReport/firmReport.html', null);
INSERT INTO `menu_tree` VALUES (440, 438, '芯片状态信息统计表', '', 1, '/nfcm/report/tagReport/tagReport.html', null);
INSERT INTO `menu_tree` VALUES (441, 438, '密钥信息统计表', '', 1, '/nfcm/report/keyReport/keyReport.html', null);


INSERT INTO `menu_auth` VALUES (270, 439, 1012);
INSERT INTO `menu_auth` VALUES (271, 439, 1013);
INSERT INTO `menu_auth` VALUES (276, 439, 1018);

INSERT INTO `menu_auth` VALUES (272, 440, 1014);
INSERT INTO `menu_auth` VALUES (273, 440, 1015);
INSERT INTO `menu_auth` VALUES (277, 440, 1019);
INSERT INTO `menu_auth` VALUES (278, 440, 1020);

INSERT INTO `menu_auth` VALUES (274, 441, 1016);
INSERT INTO `menu_auth` VALUES (275, 441, 1017);
INSERT INTO `menu_auth` VALUES (279, 441, 1021);

/**
 * user_info 添加org_id
 */
alter table user_info add org_id int not null; 
alter table user_info add org_path varchar(50); 

/**
 * 为用户管理添加查询机构权限
 */
INSERT INTO `auth_info` VALUES (1029, '系统用户管理', '查询', '/org/queryOrgInfoByParentId.action');
INSERT INTO `menu_auth` VALUES (287, 7, 1029);

/**
 * 添加初始化机构信息
 */
insert into ORG_INFO (org_id, parent_id, org_code, org_name, org_addr, org_manager, org_telephone, org_path, editor, approver, approval_time, last_edit_time, edit_flag)
values (1, null, '000000000', '总部', '', '', '', '00001', '', '', '', '', '0');
commit;
