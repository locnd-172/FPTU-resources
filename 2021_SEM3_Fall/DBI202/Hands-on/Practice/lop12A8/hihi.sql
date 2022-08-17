CREATE DATABASE lop12A5

USE lop12A5

CREATE TABLE Groups (
  GroupID varchar(10) NOT NULL, 
  PRIMARY KEY (GroupID)
  );

CREATE TABLE Leaders (
  GroupID varchar(10) NULL UNIQUE, 
  Leader  char(8) NULL UNIQUE
  );

CREATE TABLE Students (
  StudentID char(8) NOT NULL, 
  FirstName varchar(10) NOT NULL, 
  LastName  varchar(30) NOT NULL, 
  Phone     char(10) NOT NULL, 
  GroupID   varchar(10) NULL, 
  PRIMARY KEY (StudentID)
  );

ALTER TABLE Students ADD CONSTRAINT FKStudents735599 
FOREIGN KEY (GroupID) REFERENCES Groups (GroupID) 
ON UPDATE Cascade 
ON DELETE Set null;

ALTER TABLE Leaders ADD CONSTRAINT FKLeaders389919 
FOREIGN KEY (GroupID) REFERENCES Groups (GroupID);

ALTER TABLE Leaders ADD CONSTRAINT FKLeaders786560 
FOREIGN KEY (Leader) REFERENCES Students (StudentID);

ALTER TABLE Students DROP CONSTRAINT FKStudents735599;
ALTER TABLE Leaders DROP CONSTRAINT FKLeaders389919;
ALTER TABLE Leaders DROP CONSTRAINT FKLeaders786560;
DROP TABLE Groups;
DROP TABLE Leaders;
DROP TABLE Students;

---------------------------------------

INSERT INTO Groups VALUES('To mot')
INSERT INTO Groups VALUES('To hai')
INSERT INTO Groups VALUES('To ba')
INSERT INTO Groups VALUES('To bon')

SELECT * FROM Groups

---------------------------------------

INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160111', 'Nguyen', 'Mot', '0987654321')
INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160222', 'Vu', 'Hai', '0963852741')
INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160333', 'Hoang', 'Ba', '0951874632')
INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160444', 'Le', 'Bon', '0985632147')
INSERT INTO Students(StudentID, FirstName, LastName, Phone) VALUES('SE160555', 'Pham', 'Nam', '0965874123')

SELECT * FROM Students

UPDATE Students SET GroupID = 'To mot' WHERE StudentID = 'SE160111';
UPDATE Students SET GroupID = 'To hai' WHERE StudentID = 'SE160222';
UPDATE Students SET GroupID = 'To ba' WHERE StudentID = 'SE160333';
UPDATE Students SET GroupID = 'To bon' WHERE StudentID = 'SE160444';
UPDATE Students SET GroupID = 'To mot' WHERE StudentID = 'SE160555';

---------------------------------------

INSERT INTO Leaders(GroupID, Leader) VALUES('To nam', 'SE160111')
INSERT INTO Leaders(GroupID, Leader) VALUES('To bon', 'SE160666')
INSERT INTO Leaders(GroupID, Leader) VALUES('To bon', 'SE160555')
INSERT INTO Leaders(GroupID, Leader) VALUES('To ba', 'SE160333')

INSERT INTO Leaders(GroupID, Leader) VALUES('To bon', 'SE160333')
INSERT INTO Leaders(GroupID, Leader) VALUES('To ba', 'SE160111')
INSERT INTO Leaders(GroupID, Leader) VALUES('To mot', 'SE160222')
INSERT INTO Leaders(GroupID, Leader) VALUES('To hai', 'SE160555')

SELECT * FROM Leaders

SELECT s.*, l.Leader AS [Lead by] FROM Students s JOIN Groups g ON s.GroupID = g.GroupID 
JOIN Leaders l ON l.GroupID = g.GroupID 
WHERE l.GroupID = s.GroupID
