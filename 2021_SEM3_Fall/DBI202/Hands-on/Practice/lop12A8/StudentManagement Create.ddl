CREATE TABLE Groups (
  GroupID int IDENTITY NOT NULL, 
  PRIMARY KEY (GroupID));
CREATE TABLE Leaders (
  GroupID int NOT NULL, 
  Leader  char(8) NOT NULL);
CREATE TABLE Students (
  StudentID char(8) NOT NULL, 
  FirstName varchar(10) NOT NULL, 
  LastName  varchar(30) NOT NULL, 
  Phone     char(10) NOT NULL, 
  LeadBy    char(8) NULL, 
  GroupID   int NOT NULL, 
  PRIMARY KEY (StudentID));
ALTER TABLE Students ADD CONSTRAINT FKStudents735599 FOREIGN KEY (GroupID) REFERENCES Groups (GroupID) ON UPDATE Cascade ON DELETE Set null;
ALTER TABLE Leaders ADD CONSTRAINT FKLeaders389919 FOREIGN KEY (GroupID) REFERENCES Groups (GroupID);
ALTER TABLE Leaders ADD CONSTRAINT FKLeaders786560 FOREIGN KEY (Leader) REFERENCES Students (StudentID);

