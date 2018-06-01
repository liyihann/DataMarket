show databases;
#drop database if exists datahelper;
create database IF NOT EXISTS datahelper DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
use datahelper;
show tables;

drop table IF EXISTS administrators;
drop table IF EXISTS user_feedback;
drop table IF EXISTS feedback;
drop table IF EXISTS feedback_tags;
drop table IF EXISTS user_request;
drop table IF EXISTS requests;
drop table IF EXISTS request_state;
drop table IF EXISTS request_tags;
drop table IF EXISTS users;



create table users(userid int(10) not null primary key  AUTO_INCREMENT, username varchar(16) not null, upasswd varchar(32) not null, avatar varchar(256) not null, message text not null)ENGINE=InnoDB AUTO_INCREMENT=4;
insert into users(userid, username, upasswd, avatar,message) values(1,'test','98f6bcd4621d373cade4e832627b4f6','img/logo3.jpg','梦想还是要有的，万一实现了呢？');
insert into users(userid, username, upasswd, avatar,message)values(2,'guest','84e0343a0486ff05530df6c705c8bb4','img/logo3.jpg','如果你觉得今天好像上帝故意跟你作对，那就是了，回家睡觉吧。');
insert into users(userid, username, upasswd, avatar,message)values(3,'admin','21232f297a57a5a743894a0e4a801fc3','img/logo3.jpg','个性签名');
select * from users;

create table request_tags(request_tagid int(2) not null primary key,request_tagname varchar(10));
insert into request_tags(request_tagid,request_tagname)values(1,'科学');
insert into request_tags(request_tagid,request_tagname)values(2,'人文');
insert into request_tags(request_tagid,request_tagname)values(3,'艺术');
insert into request_tags(request_tagid,request_tagname)values(4,'经济');
insert into request_tags(request_tagid,request_tagname)values(5,'娱乐');
insert into request_tags(request_tagid,request_tagname)values(6,'学术');
insert into request_tags(request_tagid,request_tagname)values(7,'其他');
select * from request_tags;

create table request_state(stateid int(2) not null primary key, statename varchar(10) not null);
insert into request_state(stateid, statename)values(1,'未领取');
insert into request_state(stateid, statename)values(2,'已领取');
insert into request_state(stateid, statename)values(3,'已完成');
insert into request_state(stateid, statename)values(4,'已过期');
select * from request_state;


create table requests(requestid int(10) not null primary key AUTO_INCREMENT, state int(2)not null default 1,request_title text not null, request_content longtext not null, request_tag int(2) not null, foreign key(request_tag)references request_tags(request_tagid),foreign key(state)references request_state(stateid) on delete cascade)ENGINE=InnoDB AUTO_INCREMENT=8;
insert into requests(requestid, state,  request_title, request_content,request_tag)values(1,1,'国内高校学生对于创业的积极性','我最近正在做关于大学生创新创业调研，现在需要关于国内高校学生对于创业的积极性的调查问卷数据，主要问题包括对于国家创新创业政策的了解程度、是否打算本科期间或者是本科毕业后进行创业、对于创业环境的建议和意见等。理想的样本容量在30所高校，3000份调查数据左右。',6);#1,'未领取',2,'已领取',3,'已完成',4,'已过期'
insert into requests(requestid,state, request_title, request_content, request_tag)values(2,1,'关于画家温特哈尔特的国外研究资料','我正在做有关德国古典主义绘画大师温特哈尔特的研究工作，但是国内缺乏相关的资料，我访问外国资料的渠道有限，现需要国外的关于温特哈尔特的资料，要求翻译为中文（专业词汇还是英文表示），提供中英文两个版本，理想的资料容量在100000字左右。',3);
insert into requests(requestid,state, request_title, request_content, request_tag)values(3,1,'国内各大学大型课外活动量','求近年来国内各个大学的校级或者院级的课外活动的数量，至少2年，最好3年以上，谢谢！',2);
insert into requests(requestid,state, request_title, request_content, request_tag)values(4,1,'武汉商场数据需求','我想要武汉中、大型商场一个月内（学期内），每天人流量，商场位置、规模的数据统计。',4);
insert into requests(requestid,state, request_title, request_content, request_tag)values(5,1,'武汉大学食堂人流量','我需要一个学年内，武汉大学各个食堂，每个月的人流量。最好也能包含武大校内和周围的人比较多的餐馆的人流量数据，谢谢！！',2);
insert into requests(requestid,state, request_title, request_content, request_tag)values(6,1,'核相关新闻量','求最近两年来国内外发生的与核能（核电站、核武器等）相关的，各个国家官方发布的的新闻数量。例如，关于前几年日本福岛核泄漏这一件事，美国从事情发生到最后一共发布了多少声明（援助、隔离、拒绝进口等），CNN一共有几则新闻之类的。非常感谢！！',7);
insert into requests(requestid,state, request_title, request_content, request_tag)values(7,1,'游戏市场调查','统计最近一年，各大网游在线人数；各大主机游戏年、月、周销售量。对比网游和单机游戏用户群体数量，消费量、高消费时间段、消费内容。',5);
select * from requests;

create table user_request(requestid int(10) not null, userid int(10) not null,request_type enum('1','2'), primary key(requestid, userid), foreign key(requestid)references requests(requestid),foreign key(userid)references users(userid));
#insert into user_request(requestid, userid,request_type)values(1,1,2);#2 released
#insert into user_request(requestid, userid,request_type)values(2,1,2);
#insert into user_request(requestid, userid,request_type)values(3,1,2);
#insert into user_request(requestid, userid,request_type)values(4,1,1);#1 accepted
select * from user_request;

create table feedback_tags(feedback_tagid int(2) not null primary key,feedback_tagname varchar(10));
insert into feedback_tags(feedback_tagid,feedback_tagname)values(1,'问卷定制');
insert into feedback_tags(feedback_tagid,feedback_tagname)values(2,'服务咨询');
insert into feedback_tags(feedback_tagid,feedback_tagname)values(3,'问题反馈');
insert into feedback_tags(feedback_tagid,feedback_tagname)values(4,'投诉');
insert into feedback_tags(feedback_tagid,feedback_tagname)values(5,'其他');
select * from feedback_tags;

create table feedback(feedbackid int(10) not null primary key  AUTO_INCREMENT,feedback_title text not null, feedback_tag int(2), feedback_content longtext not null, contact varchar(64) not null, foreign key(feedback_tag)references feedback_tags(feedback_tagid))ENGINE=InnoDB AUTO_INCREMENT=4;
insert into feedback(feedbackid, feedback_title,feedback_tag,feedback_content,contact)values(1,'需要马里亚纳海沟的5000米以下的海洋数据',1,'最近在做关于海底生物的调查研究，需要马里亚纳海沟5000米下以下的海洋数据以便于进行生物研究和地质考察','709493016@whu.edu.com');
insert into feedback(feedbackid, feedback_title,feedback_tag,feedback_content,contact)values(2,'需要企业长效定制服务',2,'本公司主要为各大科技公司提供咨询服务，有大量数据需要进行分析，需要建立长期合作，尽快联系','13387516666');
insert into feedback(feedbackid, feedback_title,feedback_tag,feedback_content,contact)values(3,'希望建立长期合作',3,'之前做的问卷数据很好，导致公司盈利100亿，现在希望建立长期合作','00000032');
select * from feedback;

create table user_feedback(feedbackid int(10) not null, userid int(10) not null, primary key(feedbackid, userid), foreign key(feedbackid) references feedback(feedbackid), foreign key(userid)references users(userid));

select * from user_feedback;

create table administrators(adminid int(10) not null primary key, aname varchar(16) not null, apasswd varchar(32) not null);
insert into administrators(adminid,aname,apasswd) values(1,'admin','21232f297a57a5a743894a0e4a801fc3');
insert into administrators(adminid,aname,apasswd) values(2,'datahelper','84fe10089b51d5ae15918e2c3c2bdab5');
select * from administrators;


