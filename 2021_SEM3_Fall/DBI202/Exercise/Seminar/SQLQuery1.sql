CREATE DATABASE Seminar

USE Seminar

CREATE TABLE Lecturers (
  LecturerID char(4) NOT NULL, 
  FirstName  varchar(10) NOT NULL, 
  LastName   varchar(30) NOT NULL, 
  Email      varchar(30) NOT NULL, 
  Phone      char(10) NOT NULL, 
  Subject    varchar(10) NOT NULL, 
  PRIMARY KEY (LecturerID)
);

CREATE TABLE Seminars (
  ID                  int IDENTITY NOT NULL, 
  Schedule            varchar(20) NOT NULL, 
  Type                varchar(10) NOT NULL, 
  Topic               varchar(50) NOT NULL, 
  Description         varchar(255) NULL, 
  Room                char(4) NULL, 
  OnlineLink          varchar(255) NULL, 
  EstimatedAttendance int NULL, 
  LecturerID          char(4) NULL, 
  PRIMARY KEY (ID)
);

ALTER TABLE Seminars ADD CONSTRAINT FKSeminars246452 
FOREIGN KEY (LecturerID) REFERENCES Lecturers (LecturerID) 
ON UPDATE Cascade 
ON DELETE Set null;

ALTER TABLE Seminars DROP CONSTRAINT FKSeminars246452;
DROP TABLE Lecturers;
DROP TABLE Seminars;

-------------------------------------------
INSERT INTO Lecturers VALUES('SE01', 'Nguyen', 'An', 'lc.nguyendang123@gmail.com', '0987654321', 'Incubator')

SELECT * FROM Lecturers

-------------------------------------------
INSERT INTO Seminars VALUES('05/12/2021 20:00:00', 'Seminar', 'Machine learning', NULL, 'SE11', NULL, 50, NULL)

SELECT * FROM Seminars

UPDATE Seminars SET LecturerID = 'SE01' WHERE ID = 1;