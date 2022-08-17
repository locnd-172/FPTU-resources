CREATE TABLE BlogInfo (
  BlogID      char(5) NOT NULL, 
  Title       varchar(50) NOT NULL, 
  Summary     varchar(100) NOT NULL, 
  Content     varchar(255) NOT NULL, 
  DateCreated char(10) NOT NULL, 
  Creator     char(8) NULL, 
  Topic       varchar(30) NOT NULL, 
  Major       char(2) NULL, 
  Tags        varchar(30) NULL, 
  Status      varchar(20) NOT NULL, 
  PRIMARY KEY (BlogID)
);

CREATE TABLE Comments (
  ID      int IDENTITY NOT NULL, 
  BlogID  char(5) NOT NULL, 
  Comment varchar(255) NULL, 
  PRIMARY KEY (ID)
);

CREATE TABLE Majors (
  MajorID   char(2) NOT NULL, 
  MajorName varchar(30) NOT NULL, 
  PRIMARY KEY (MajorID)
);

CREATE TABLE MemberInfo (
  ID        int IDENTITY NOT NULL, 
  MemberID  char(8) NOT NULL UNIQUE, 
  LoginName varchar(50) NOT NULL, 
  Password  varchar(30) NOT NULL, 
  FirstName varchar(10) NOT NULL, 
  LastName  varchar(30) NOT NULL, 
  Email     varchar(50) NULL, 
  Avatar    varchar(255) NULL, 
  PRIMARY KEY (ID)
);

CREATE TABLE MemberList (
  MemberID char(8) NOT NULL, 
  PRIMARY KEY (MemberID)
);

ALTER TABLE MemberInfo ADD CONSTRAINT FKMemberInfo448176 
FOREIGN KEY (MemberID) REFERENCES MemberList (MemberID) 
ON UPDATE Cascade 
ON DELETE Cascade;

ALTER TABLE BlogInfo ADD CONSTRAINT FKBlogInfo737834 
FOREIGN KEY (Creator) REFERENCES MemberList (MemberID) 
ON UPDATE Cascade 
ON DELETE Set null;

ALTER TABLE BlogInfo ADD CONSTRAINT FKBlogInfo163457 
FOREIGN KEY (Major) REFERENCES Majors (MajorID) 
ON UPDATE Cascade 
ON DELETE Set null;

ALTER TABLE Comments ADD CONSTRAINT FKComments638534 
FOREIGN KEY (BlogID) REFERENCES BlogInfo (BlogID) 
ON UPDATE Cascade 
ON DELETE Set null;

ALTER TABLE MemberInfo DROP CONSTRAINT FKMemberInfo448176;
ALTER TABLE BlogInfo DROP CONSTRAINT FKBlogInfo737834;
ALTER TABLE BlogInfo DROP CONSTRAINT FKBlogInfo163457;
ALTER TABLE Comments DROP CONSTRAINT FKComments638534;
DROP TABLE BlogInfo;
DROP TABLE Comments;
DROP TABLE Majors;
DROP TABLE MemberInfo;
DROP TABLE MemberList;

