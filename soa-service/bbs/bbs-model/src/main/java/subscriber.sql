DROP TABLE IF EXISTS `subscriber`;
CREATE TABLE `subscriber` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`type` varchar(200) NOT NULL COMMENT '类型',
`userId` int(11) NOT NULL COMMENT '用户ID',
`themeId` int(11) DEFAULT NULL COMMENT '主题ID',
`score` int(11) NOT NULL COMMENT '积分',
`pos` int(11) DEFAULT '0' COMMENT '位置，身份 0=普通成员',
`pasteCount` int(11) DEFAULT '0' COMMENT '发帖数',
`isEnabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `type` (`type`) USING BTREE,
KEY `userId` (`userId`) USING BTREE,
KEY `themeId` (`themeId`) USING BTREE,
KEY `score` (`score`) USING BTREE,
KEY `pos` (`pos`) USING BTREE,
KEY `pasteCount` (`pasteCount`) USING BTREE,
KEY `isEnabled` (`isEnabled`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订阅者';