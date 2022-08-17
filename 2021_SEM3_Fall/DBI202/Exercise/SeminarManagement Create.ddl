CREATE TABLE Lecturers (
  LecturerID char(4) NOT NULL, 
  FirstName  varchar(10) NOT NULL, 
  LastName   varchar(30) NOT NULL, 
  Email      varchar(30) NOT NULL, 
  Phone      char(10) NOT NULL, 
  Subject    char(3) NOT NULL, 
  PRIMARY KEY (LecturerID)
);
CREATE TABLE Seminars (
  ID                  int IDENTITY NOT NULL, 
  Schedule            varchar(20) NOT NULL, 
  Type                varchar(10) NOT NULL, 
  Topic               varchar(50) NOT NULL, 
  Room                int NULL, 
  OnlineLink          varchar(255) NULL, 
  EstimatedAttendance int NOT NULL, 
  LecturerID          char(4) NOT NULL, 
  PRIMARY KEY (ID)
);
ALTER TABLE Seminars ADD CONSTRAINT FKSeminars246452 
FOREIGN KEY (LecturerID) REFERENCES Lecturers (LecturerID) 
ON UPDATE Cascade 
ON DELETE Set null;

