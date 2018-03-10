DROP TABLE IF EXISTS `paste_reply_000`;
CREATE TABLE `paste_reply_000` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_001`;
CREATE TABLE `paste_reply_001` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_002`;
CREATE TABLE `paste_reply_002` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_003`;
CREATE TABLE `paste_reply_003` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_004`;
CREATE TABLE `paste_reply_004` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_005`;
CREATE TABLE `paste_reply_005` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_006`;
CREATE TABLE `paste_reply_006` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_007`;
CREATE TABLE `paste_reply_007` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_008`;
CREATE TABLE `paste_reply_008` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_009`;
CREATE TABLE `paste_reply_009` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_010`;
CREATE TABLE `paste_reply_010` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_011`;
CREATE TABLE `paste_reply_011` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_012`;
CREATE TABLE `paste_reply_012` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_013`;
CREATE TABLE `paste_reply_013` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_014`;
CREATE TABLE `paste_reply_014` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_015`;
CREATE TABLE `paste_reply_015` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_016`;
CREATE TABLE `paste_reply_016` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_017`;
CREATE TABLE `paste_reply_017` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_018`;
CREATE TABLE `paste_reply_018` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_019`;
CREATE TABLE `paste_reply_019` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_020`;
CREATE TABLE `paste_reply_020` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_021`;
CREATE TABLE `paste_reply_021` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_022`;
CREATE TABLE `paste_reply_022` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_023`;
CREATE TABLE `paste_reply_023` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_024`;
CREATE TABLE `paste_reply_024` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_025`;
CREATE TABLE `paste_reply_025` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_026`;
CREATE TABLE `paste_reply_026` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_027`;
CREATE TABLE `paste_reply_027` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_028`;
CREATE TABLE `paste_reply_028` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_029`;
CREATE TABLE `paste_reply_029` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_030`;
CREATE TABLE `paste_reply_030` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_031`;
CREATE TABLE `paste_reply_031` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_032`;
CREATE TABLE `paste_reply_032` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_033`;
CREATE TABLE `paste_reply_033` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_034`;
CREATE TABLE `paste_reply_034` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_035`;
CREATE TABLE `paste_reply_035` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_036`;
CREATE TABLE `paste_reply_036` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_037`;
CREATE TABLE `paste_reply_037` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_038`;
CREATE TABLE `paste_reply_038` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_039`;
CREATE TABLE `paste_reply_039` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_040`;
CREATE TABLE `paste_reply_040` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_041`;
CREATE TABLE `paste_reply_041` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_042`;
CREATE TABLE `paste_reply_042` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_043`;
CREATE TABLE `paste_reply_043` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_044`;
CREATE TABLE `paste_reply_044` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_045`;
CREATE TABLE `paste_reply_045` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_046`;
CREATE TABLE `paste_reply_046` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_047`;
CREATE TABLE `paste_reply_047` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_048`;
CREATE TABLE `paste_reply_048` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_049`;
CREATE TABLE `paste_reply_049` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_050`;
CREATE TABLE `paste_reply_050` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_051`;
CREATE TABLE `paste_reply_051` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_052`;
CREATE TABLE `paste_reply_052` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_053`;
CREATE TABLE `paste_reply_053` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_054`;
CREATE TABLE `paste_reply_054` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_055`;
CREATE TABLE `paste_reply_055` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_056`;
CREATE TABLE `paste_reply_056` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_057`;
CREATE TABLE `paste_reply_057` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_058`;
CREATE TABLE `paste_reply_058` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_059`;
CREATE TABLE `paste_reply_059` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_060`;
CREATE TABLE `paste_reply_060` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_061`;
CREATE TABLE `paste_reply_061` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_062`;
CREATE TABLE `paste_reply_062` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_063`;
CREATE TABLE `paste_reply_063` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_064`;
CREATE TABLE `paste_reply_064` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_065`;
CREATE TABLE `paste_reply_065` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_066`;
CREATE TABLE `paste_reply_066` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_067`;
CREATE TABLE `paste_reply_067` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_068`;
CREATE TABLE `paste_reply_068` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_069`;
CREATE TABLE `paste_reply_069` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_070`;
CREATE TABLE `paste_reply_070` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_071`;
CREATE TABLE `paste_reply_071` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_072`;
CREATE TABLE `paste_reply_072` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_073`;
CREATE TABLE `paste_reply_073` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_074`;
CREATE TABLE `paste_reply_074` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_075`;
CREATE TABLE `paste_reply_075` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_076`;
CREATE TABLE `paste_reply_076` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_077`;
CREATE TABLE `paste_reply_077` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_078`;
CREATE TABLE `paste_reply_078` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_079`;
CREATE TABLE `paste_reply_079` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_080`;
CREATE TABLE `paste_reply_080` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_081`;
CREATE TABLE `paste_reply_081` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_082`;
CREATE TABLE `paste_reply_082` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_083`;
CREATE TABLE `paste_reply_083` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_084`;
CREATE TABLE `paste_reply_084` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_085`;
CREATE TABLE `paste_reply_085` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_086`;
CREATE TABLE `paste_reply_086` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_087`;
CREATE TABLE `paste_reply_087` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_088`;
CREATE TABLE `paste_reply_088` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_089`;
CREATE TABLE `paste_reply_089` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_090`;
CREATE TABLE `paste_reply_090` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_091`;
CREATE TABLE `paste_reply_091` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_092`;
CREATE TABLE `paste_reply_092` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_093`;
CREATE TABLE `paste_reply_093` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_094`;
CREATE TABLE `paste_reply_094` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_095`;
CREATE TABLE `paste_reply_095` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_096`;
CREATE TABLE `paste_reply_096` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_097`;
CREATE TABLE `paste_reply_097` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_098`;
CREATE TABLE `paste_reply_098` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

DROP TABLE IF EXISTS `paste_reply_099`;
CREATE TABLE `paste_reply_099` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(500) NOT NULL COMMENT '内容',
`userId` int(11) NOT NULL COMMENT '回复人',
`pasteId` int(11) NOT NULL COMMENT '原帖ID',
`postId` int(11) NOT NULL COMMENT '回帖ID',
`toReplyId` int(11) DEFAULT NULL COMMENT '给谁回复',
`source` varchar(500) NOT NULL COMMENT '来源',
`praiseCount` int(11) DEFAULT '0' COMMENT '点赞数',
`createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`id`),
KEY `userId` (`userId`) USING BTREE,
KEY `pasteId` (`pasteId`) USING BTREE,
KEY `postId` (`postId`) USING BTREE,
KEY `toReplyId` (`toReplyId`) USING BTREE,
KEY `source` (`source`) USING BTREE,
KEY `praiseCount` (`praiseCount`) USING BTREE,
KEY `createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复表';

