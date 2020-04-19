-- 用户表
CREATE TABLE `user` (
  `id` bigint(50) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` varchar(255) NOT NULL DEFAULT '0' COMMENT '性别',
  `faculty` varchar(255) NOT NULL DEFAULT '' COMMENT '学院',
  `student_number` varchar(255) NOT NULL DEFAULT '' COMMENT '学号',
  `phone_number` varchar(255) NOT NULL DEFAULT '' COMMENT '手机号',
  `contact_information` varchar(255) NOT NULL DEFAULT '' COMMENT '默认联系方式',
  `nickname` varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar_url` varchar(255) NOT NULL DEFAULT '' COMMENT '头像URL',
  `open_id` varchar(255) NOT NULL DEFAULT '' COMMENT '小程序范围内唯一id',
  `credit` tinyint(10) NOT NULL DEFAULT '60' COMMENT '信誉值',
  `role` varchar(255) NOT NULL DEFAULT '' COMMENT '用户角色',
  `status` varchar(255) NOT NULL DEFAULT '0' COMMENT '用户账号状态',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 任务表
CREATE TABLE `task` (
  `id` bigint(50) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '内容',
  `transaction_means` varchar(255) DEFAULT '' COMMENT '交易方式',
  `sender_id` varchar(255) NOT NULL DEFAULT '' COMMENT '发送者id',
  `receiver_id` varchar(255) NOT NULL DEFAULT '' COMMENT '接受者id, 可能有多个',
  `deadline` datetime DEFAULT NULL COMMENT '截止时间',
  `status` varchar(255) NOT NULL DEFAULT '' COMMENT '任务状态',
  `max_receiver` tinyint(10) NOT NULL DEFAULT '1' COMMENT '最大可接该任务的人数',
  `comment_on_receiver` varchar(255) NOT NULL DEFAULT '' COMMENT '任务发送者对接受者的评价',
  `star_for_receiver` tinyint(10) DEFAULT '5' COMMENT '对接受者的评价星级',
  `comment_on_sender` varchar(255) NOT NULL DEFAULT '' COMMENT '任务接受者对发送者的评价',
  `star_for_sender` tinyint(10) NOT NULL DEFAULT '5' COMMENT '对发送者的评价星级',
  `quick_accept` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否一接受任务就自动确认',
  `receiver_confirmed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '接受者确认完成任务',
  `sender_confirmed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '发送者确认完成任务',
  `submit_time` datetime DEFAULT NULL COMMENT '任务提交时间',
  `receiver_notes` varchar(255) NOT NULL DEFAULT '' COMMENT '接受者留言',
  `sender_contact_info` varchar(255) NOT NULL DEFAULT '' COMMENT '发送者联系方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
