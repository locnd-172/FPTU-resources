create database Blog
use Blog

CREATE TABLE BlogInfo (
  blogID      varchar(10) NOT NULL, 
  Title       varchar(40) NOT NULL, 
  Summary     varchar(100) NOT NULL, 
  Content     varchar(200) NOT NULL, 
  DateCreated varchar(30) NOT NULL, 
  CreatorID   char(8) NOT NULL, 
  Topic       varchar(30) NOT NULL, 
  Majors      varchar(3) NOT NULL, 
  Tags        varchar(40) NULL, 
  Status      varchar(40) NOT NULL, 
  PRIMARY KEY (blogID));

CREATE TABLE Comment (
  ID         int IDENTITY NOT NULL, 
  InfoblogID varchar(10) NOT NULL, 
  Comment    varchar(255) NULL, 
  PRIMARY KEY (ID));

CREATE TABLE MajorsInfo (
  majorID   varchar(3) NOT NULL, 
  MajorName varchar(30) NOT NULL, 
  PRIMARY KEY (majorID));

CREATE TABLE Status (
  Status varchar(40) NOT NULL, 
  PRIMARY KEY (Status));

CREATE TABLE UserInfo (
  ID        int IDENTITY NOT NULL, 
  UserID    char(8) NOT NULL unique, 
  LoginName varchar(30) NOT NULL, 
  Password  varchar(30) NOT NULL, 
  FullName  varchar(50) NOT NULL, 
  Email     varchar(50) NOT NULL, 
  Avatar    varchar(100) NULL, 
  PRIMARY KEY (ID));

CREATE TABLE userList (
  InfoUserID char(8) NOT NULL, 
  PRIMARY KEY (InfoUserID));

ALTER TABLE BlogInfo ADD CONSTRAINT FKBlogInfo810413 FOREIGN KEY (Majors) REFERENCES MajorsInfo (majorID);
ALTER TABLE BlogInfo ADD CONSTRAINT FKBlogInfo120570 FOREIGN KEY (Status) REFERENCES Status (Status);
ALTER TABLE UserInfo ADD CONSTRAINT FKUserInfo662464 FOREIGN KEY (UserID) REFERENCES userList (InfoUserID);
ALTER TABLE BlogInfo ADD CONSTRAINT FKBlogInfo898839 FOREIGN KEY (CreatorID) REFERENCES userList (InfoUserID);
ALTER TABLE Comment ADD CONSTRAINT FKComment940921 FOREIGN KEY (InfoblogID) REFERENCES BlogInfo (blogID);

ALTER TABLE BlogInfo DROP CONSTRAINT FKBlogInfo810413;
ALTER TABLE BlogInfo DROP CONSTRAINT FKBlogInfo120570;
ALTER TABLE UserInfo DROP CONSTRAINT FKUserInfo662464;
ALTER TABLE BlogInfo DROP CONSTRAINT FKBlogInfo898839;
ALTER TABLE Comment DROP CONSTRAINT FKComment940921;
DROP TABLE BlogInfo;
DROP TABLE Comment;
DROP TABLE MajorsInfo;
DROP TABLE Status;
DROP TABLE UserInfo;
DROP TABLE userList;
select * from userList
select * from UserInfo
select * from MajorsInfo
select * from Status
select * from BlogInfo

insert into userList values ('SE160199')
insert into userList values ('SE160911')
insert into userList values ('SE160201')


insert into UserInfo values('SE160199','LocSimp','Locgaysimp','Nguyen Dang Loc','locdbrr@','bebe')
insert into UserInfo values('SE160111','LocSimp','Locgaysimp','Nguyen Dang Loc','locdbrr@','bebe')

insert into MajorsInfo values('SE','KTPM')
insert into MajorsInfo values('IA','ATTT')

insert into Status values('waiting')

insert into BlogInfo values('Blog000001','Block chain','ok ok o ko ko ko ko k ok','ok','11/11/2002',
		'SE160199','Block chain','SE','#maiyeuem','waiting')