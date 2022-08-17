CREATE DATABASE R1N_EX02

USE R1N_EX02

CREATE TABLE Groups (
  GroupID       int IDENTITY NOT NULL, 
  GroupName     nvarchar(20) NOT NULL, 
  LeaderOfGroup char(8) NULL, 
  PRIMARY KEY (GroupID)
);

CREATE TABLE Students (
  StudentID char(8) NOT NULL, 
  FirstName nvarchar(10) NOT NULL, 
  LastName  nvarchar(30) NOT NULL, 
  Phone     char(10) NOT NULL, 
  LeadBy    char(8) NULL, 
  GroupID   int NULL, 
  PRIMARY KEY (StudentID)
);

ALTER TABLE Students ADD CONSTRAINT FKStudents735599 
FOREIGN KEY (GroupID) REFERENCES Groups (GroupID) 
ON UPDATE Cascade 
ON DELETE Set null;

ALTER TABLE Groups ADD CONSTRAINT FKGroups800803 
FOREIGN KEY (LeaderOfGroup) REFERENCES Students (StudentID) 
ON UPDATE Cascade 
ON DELETE Cascade;

ALTER TABLE Students DROP CONSTRAINT FKStudents735599;
ALTER TABLE Groups DROP CONSTRAINT FKGroups800803;
DROP TABLE Groups;
DROP TABLE Students;

---------------------------------
SELECT * FROM Groups
SELECT GroupID, GroupName, LeaderOfGroup FROM Groups;

SELECT * FROM Students
SELECT StudentID, FirstName, LastName, Phone, LeadBy, GroupID FROM Students;

---------------------------------
INSERT INTO Groups(GroupName, LeaderOfGroup) VALUES(N'Tổ Một', NULL)
INSERT INTO Groups(GroupName, LeaderOfGroup) VALUES(N'Tổ Hai', NULL)
INSERT INTO Groups(GroupName, LeaderOfGroup) VALUES(N'Tổ Ba', NULL)
INSERT INTO Groups(GroupName, LeaderOfGroup) VALUES(N'Tổ Bốn', NULL)

INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160111', N'Nguyễn', N'Một', '0987654321')
INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160222', N'Vũ', N'Hai', '0963852741')
INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160333', N'Hoàng', N'Ba', '0951874632')
INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160444', N'Lê', N'Bốn', '0985632147')
INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160555', N'Phạm', N'Năm', '0965874123')

---------------------------------

INSERT INTO Students(StudentID, FirstName, LastName, Phone, GroupID) VALUES('SE160111', N'Nguyễn', N'Một', '0987654321', 1)
INSERT INTO Students(StudentID, FirstName, LastName, Phone, GroupID) VALUES('SE160222', N'Vũ', N'Hai', '0963852741', 2)
INSERT INTO Students(StudentID, FirstName, LastName, Phone, GroupID) VALUES('SE160333', N'Hoàng', N'Ba', '0951874632', 3)
INSERT INTO Students(StudentID, FirstName, LastName, Phone, GroupID) VALUES('SE160444', N'Lê', N'Bốn', '0985632147', 4)

INSERT INTO Students(StudentID, FirstName, LastName, Phone, GroupID) VALUES('SE160555', N'Phạm', N'Năm', '0965874123', 5) -- GroupID not exist 

INSERT INTO Students(StudentID, FirstName, LastName, Phone, LeadBy, GroupID) VALUES('SE160555', N'Phạm', N'Năm', '0965874123', 'SE160666', 5) -- LeaderID not exist
INSERT INTO Students(StudentID, FirstName, LastName, Phone, LeadBy, GroupID) VALUES('SE160555', N'Phạm', N'Năm', '0965874123', 'SE160111', 4)

DELETE FROM Students