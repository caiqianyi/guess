DROP TABLE IF EXISTS `paste_replay_000`;
CREATE TABLE `paste_replay_000` (
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

DROP TABLE IF EXISTS `paste_replay_001`;
CREATE TABLE `paste_replay_001` (
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

DROP TABLE IF EXISTS `paste_replay_002`;
CREATE TABLE `paste_replay_002` (
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

DROP TABLE IF EXISTS `paste_replay_003`;
CREATE TABLE `paste_replay_003` (
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

DROP TABLE IF EXISTS `paste_replay_004`;
CREATE TABLE `paste_replay_004` (
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

DROP TABLE IF EXISTS `paste_replay_005`;
CREATE TABLE `paste_replay_005` (
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

DROP TABLE IF EXISTS `paste_replay_006`;
CREATE TABLE `paste_replay_006` (
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

DROP TABLE IF EXISTS `paste_replay_007`;
CREATE TABLE `paste_replay_007` (
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

DROP TABLE IF EXISTS `paste_replay_008`;
CREATE TABLE `paste_replay_008` (
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

DROP TABLE IF EXISTS `paste_replay_009`;
CREATE TABLE `paste_replay_009` (
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

DROP TABLE IF EXISTS `paste_replay_010`;
CREATE TABLE `paste_replay_010` (
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

DROP TABLE IF EXISTS `paste_replay_011`;
CREATE TABLE `paste_replay_011` (
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

DROP TABLE IF EXISTS `paste_replay_012`;
CREATE TABLE `paste_replay_012` (
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

DROP TABLE IF EXISTS `paste_replay_013`;
CREATE TABLE `paste_replay_013` (
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

DROP TABLE IF EXISTS `paste_replay_014`;
CREATE TABLE `paste_replay_014` (
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

DROP TABLE IF EXISTS `paste_replay_015`;
CREATE TABLE `paste_replay_015` (
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

DROP TABLE IF EXISTS `paste_replay_016`;
CREATE TABLE `paste_replay_016` (
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

DROP TABLE IF EXISTS `paste_replay_017`;
CREATE TABLE `paste_replay_017` (
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

DROP TABLE IF EXISTS `paste_replay_018`;
CREATE TABLE `paste_replay_018` (
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

DROP TABLE IF EXISTS `paste_replay_019`;
CREATE TABLE `paste_replay_019` (
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

DROP TABLE IF EXISTS `paste_replay_020`;
CREATE TABLE `paste_replay_020` (
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

DROP TABLE IF EXISTS `paste_replay_021`;
CREATE TABLE `paste_replay_021` (
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

DROP TABLE IF EXISTS `paste_replay_022`;
CREATE TABLE `paste_replay_022` (
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

DROP TABLE IF EXISTS `paste_replay_023`;
CREATE TABLE `paste_replay_023` (
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

DROP TABLE IF EXISTS `paste_replay_024`;
CREATE TABLE `paste_replay_024` (
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

DROP TABLE IF EXISTS `paste_replay_025`;
CREATE TABLE `paste_replay_025` (
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

DROP TABLE IF EXISTS `paste_replay_026`;
CREATE TABLE `paste_replay_026` (
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

DROP TABLE IF EXISTS `paste_replay_027`;
CREATE TABLE `paste_replay_027` (
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

DROP TABLE IF EXISTS `paste_replay_028`;
CREATE TABLE `paste_replay_028` (
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

DROP TABLE IF EXISTS `paste_replay_029`;
CREATE TABLE `paste_replay_029` (
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

DROP TABLE IF EXISTS `paste_replay_030`;
CREATE TABLE `paste_replay_030` (
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

DROP TABLE IF EXISTS `paste_replay_031`;
CREATE TABLE `paste_replay_031` (
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

DROP TABLE IF EXISTS `paste_replay_032`;
CREATE TABLE `paste_replay_032` (
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

DROP TABLE IF EXISTS `paste_replay_033`;
CREATE TABLE `paste_replay_033` (
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

DROP TABLE IF EXISTS `paste_replay_034`;
CREATE TABLE `paste_replay_034` (
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

DROP TABLE IF EXISTS `paste_replay_035`;
CREATE TABLE `paste_replay_035` (
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

DROP TABLE IF EXISTS `paste_replay_036`;
CREATE TABLE `paste_replay_036` (
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

DROP TABLE IF EXISTS `paste_replay_037`;
CREATE TABLE `paste_replay_037` (
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

DROP TABLE IF EXISTS `paste_replay_038`;
CREATE TABLE `paste_replay_038` (
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

DROP TABLE IF EXISTS `paste_replay_039`;
CREATE TABLE `paste_replay_039` (
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

DROP TABLE IF EXISTS `paste_replay_040`;
CREATE TABLE `paste_replay_040` (
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

DROP TABLE IF EXISTS `paste_replay_041`;
CREATE TABLE `paste_replay_041` (
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

DROP TABLE IF EXISTS `paste_replay_042`;
CREATE TABLE `paste_replay_042` (
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

DROP TABLE IF EXISTS `paste_replay_043`;
CREATE TABLE `paste_replay_043` (
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

DROP TABLE IF EXISTS `paste_replay_044`;
CREATE TABLE `paste_replay_044` (
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

DROP TABLE IF EXISTS `paste_replay_045`;
CREATE TABLE `paste_replay_045` (
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

DROP TABLE IF EXISTS `paste_replay_046`;
CREATE TABLE `paste_replay_046` (
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

DROP TABLE IF EXISTS `paste_replay_047`;
CREATE TABLE `paste_replay_047` (
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

DROP TABLE IF EXISTS `paste_replay_048`;
CREATE TABLE `paste_replay_048` (
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

DROP TABLE IF EXISTS `paste_replay_049`;
CREATE TABLE `paste_replay_049` (
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

DROP TABLE IF EXISTS `paste_replay_050`;
CREATE TABLE `paste_replay_050` (
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

DROP TABLE IF EXISTS `paste_replay_051`;
CREATE TABLE `paste_replay_051` (
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

DROP TABLE IF EXISTS `paste_replay_052`;
CREATE TABLE `paste_replay_052` (
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

DROP TABLE IF EXISTS `paste_replay_053`;
CREATE TABLE `paste_replay_053` (
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

DROP TABLE IF EXISTS `paste_replay_054`;
CREATE TABLE `paste_replay_054` (
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

DROP TABLE IF EXISTS `paste_replay_055`;
CREATE TABLE `paste_replay_055` (
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

DROP TABLE IF EXISTS `paste_replay_056`;
CREATE TABLE `paste_replay_056` (
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

DROP TABLE IF EXISTS `paste_replay_057`;
CREATE TABLE `paste_replay_057` (
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

DROP TABLE IF EXISTS `paste_replay_058`;
CREATE TABLE `paste_replay_058` (
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

DROP TABLE IF EXISTS `paste_replay_059`;
CREATE TABLE `paste_replay_059` (
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

DROP TABLE IF EXISTS `paste_replay_060`;
CREATE TABLE `paste_replay_060` (
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

DROP TABLE IF EXISTS `paste_replay_061`;
CREATE TABLE `paste_replay_061` (
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

DROP TABLE IF EXISTS `paste_replay_062`;
CREATE TABLE `paste_replay_062` (
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

DROP TABLE IF EXISTS `paste_replay_063`;
CREATE TABLE `paste_replay_063` (
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

DROP TABLE IF EXISTS `paste_replay_064`;
CREATE TABLE `paste_replay_064` (
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

DROP TABLE IF EXISTS `paste_replay_065`;
CREATE TABLE `paste_replay_065` (
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

DROP TABLE IF EXISTS `paste_replay_066`;
CREATE TABLE `paste_replay_066` (
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

DROP TABLE IF EXISTS `paste_replay_067`;
CREATE TABLE `paste_replay_067` (
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

DROP TABLE IF EXISTS `paste_replay_068`;
CREATE TABLE `paste_replay_068` (
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

DROP TABLE IF EXISTS `paste_replay_069`;
CREATE TABLE `paste_replay_069` (
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

DROP TABLE IF EXISTS `paste_replay_070`;
CREATE TABLE `paste_replay_070` (
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

DROP TABLE IF EXISTS `paste_replay_071`;
CREATE TABLE `paste_replay_071` (
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

DROP TABLE IF EXISTS `paste_replay_072`;
CREATE TABLE `paste_replay_072` (
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

DROP TABLE IF EXISTS `paste_replay_073`;
CREATE TABLE `paste_replay_073` (
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

DROP TABLE IF EXISTS `paste_replay_074`;
CREATE TABLE `paste_replay_074` (
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

DROP TABLE IF EXISTS `paste_replay_075`;
CREATE TABLE `paste_replay_075` (
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

DROP TABLE IF EXISTS `paste_replay_076`;
CREATE TABLE `paste_replay_076` (
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

DROP TABLE IF EXISTS `paste_replay_077`;
CREATE TABLE `paste_replay_077` (
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

DROP TABLE IF EXISTS `paste_replay_078`;
CREATE TABLE `paste_replay_078` (
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

DROP TABLE IF EXISTS `paste_replay_079`;
CREATE TABLE `paste_replay_079` (
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

DROP TABLE IF EXISTS `paste_replay_080`;
CREATE TABLE `paste_replay_080` (
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

DROP TABLE IF EXISTS `paste_replay_081`;
CREATE TABLE `paste_replay_081` (
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

DROP TABLE IF EXISTS `paste_replay_082`;
CREATE TABLE `paste_replay_082` (
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

DROP TABLE IF EXISTS `paste_replay_083`;
CREATE TABLE `paste_replay_083` (
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

DROP TABLE IF EXISTS `paste_replay_084`;
CREATE TABLE `paste_replay_084` (
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

DROP TABLE IF EXISTS `paste_replay_085`;
CREATE TABLE `paste_replay_085` (
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

DROP TABLE IF EXISTS `paste_replay_086`;
CREATE TABLE `paste_replay_086` (
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

DROP TABLE IF EXISTS `paste_replay_087`;
CREATE TABLE `paste_replay_087` (
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

DROP TABLE IF EXISTS `paste_replay_088`;
CREATE TABLE `paste_replay_088` (
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

DROP TABLE IF EXISTS `paste_replay_089`;
CREATE TABLE `paste_replay_089` (
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

DROP TABLE IF EXISTS `paste_replay_090`;
CREATE TABLE `paste_replay_090` (
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

DROP TABLE IF EXISTS `paste_replay_091`;
CREATE TABLE `paste_replay_091` (
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

DROP TABLE IF EXISTS `paste_replay_092`;
CREATE TABLE `paste_replay_092` (
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

DROP TABLE IF EXISTS `paste_replay_093`;
CREATE TABLE `paste_replay_093` (
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

DROP TABLE IF EXISTS `paste_replay_094`;
CREATE TABLE `paste_replay_094` (
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

DROP TABLE IF EXISTS `paste_replay_095`;
CREATE TABLE `paste_replay_095` (
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

DROP TABLE IF EXISTS `paste_replay_096`;
CREATE TABLE `paste_replay_096` (
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

DROP TABLE IF EXISTS `paste_replay_097`;
CREATE TABLE `paste_replay_097` (
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

DROP TABLE IF EXISTS `paste_replay_098`;
CREATE TABLE `paste_replay_098` (
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

DROP TABLE IF EXISTS `paste_replay_099`;
CREATE TABLE `paste_replay_099` (
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

