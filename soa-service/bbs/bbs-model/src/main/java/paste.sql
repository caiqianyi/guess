DROP TABLE IF EXISTS `paste`;
CREATE TABLE `paste` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(50) NOT NULL COMMENT '标题',
`content` varchar(500) NOT NULL COMMENT '内容',
`pictures` varchar(500) DEFAULT NULL COMMENT '封面图片',
`source` varchar(500) NOT NULL COMMENT '来源',
`userId` int(11) NOT NULL COMMENT '用户ID',
`lastPostId` int(11) DEFAULT NULL COMMENT '最后回帖',
`plateId` int(11) NOT NULL COMMENT '板块ID',
`collectCount` int(11) DEFAULT '0' COMMENT '收藏数',
`transpondCount` int(11) DEFAULT '0' COMMENT '转发数',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`replyPasteCount` int(11) DEFAULT '0' COMMENT '回贴数',
`scanCount` int(11) DEFAULT '0' COMMENT '浏览数',
`top` tinyint(1) DEFAULT '0' COMMENT '置顶',
`highlight` tinyint(1) DEFAULT '0' COMMENT '高亮加精',
`deleted` tinyint(1) DEFAULT '0' COMMENT '删除',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`lastUpdateTime` datetime DEFAULT NULL COMMENT '最后更新时间',
`lastPostTime` datetime DEFAULT NULL COMMENT '最后回贴时间',
`lastReplyTime` datetime DEFAULT NULL COMMENT '最后回复时间',
PRIMARY KEY (`id`),
KEY `source` (`source`) USING BTREE,
KEY `userId` (`userId`) USING BTREE,
KEY `lastPostId` (`lastPostId`) USING BTREE,
KEY `plateId` (`plateId`) USING BTREE,
KEY `collectCount` (`collectCount`) USING BTREE,
KEY `transpondCount` (`transpondCount`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `replyPasteCount` (`replyPasteCount`) USING BTREE,
KEY `scanCount` (`scanCount`) USING BTREE,
KEY `top` (`top`) USING BTREE,
KEY `highlight` (`highlight`) USING BTREE,
KEY `deleted` (`deleted`) USING BTREE,
KEY `lastUpdateTime` (`lastUpdateTime`) USING BTREE,
KEY `lastPostTime` (`lastPostTime`) USING BTREE,
KEY `lastReplyTime` (`lastReplyTime`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发帖表';