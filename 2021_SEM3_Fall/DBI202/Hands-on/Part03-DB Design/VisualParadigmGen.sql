
CREATE TABLE Major (
  MajorID   char(2) NOT NULL, 
  MajorName varchar(40) NULL, 
  PRIMARY KEY (MajorID)
);

CREATE TABLE Student (
  StudentID char(8) NOT NULL, 
  FirstName varchar(10) NOT NULL, 
  LastName  varchar(30) NOT NULL, 
  MajorID   char(2) NOT NULL, 
  PRIMARY KEY (StudentID)
);
ALTER TABLE Student ADD CONSTRAINT FKStudent939401 
FOREIGN KEY (MajorID) REFERENCES Major (MajorID) ON UPDATE Cascade ON DELETE Set default;
---------------------------------
ALTER TABLE Student DROP CONSTRAINT FKStudent939401;
DROP TABLE Major;
DROP TABLE Student;

-------------------------------
MySQL 
