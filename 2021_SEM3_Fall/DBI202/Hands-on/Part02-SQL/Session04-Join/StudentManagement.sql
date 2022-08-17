DROP DATABASE StudentManagement

CREATE DATABASE StudentManagement

USE StudentManagement

CREATE TABLE Major
(
	MajorID char(2) PRIMARY KEY,         -- PK Primary Key - Khóa chính
	MajorName varchar(30),
	Hotline varchar(11)
)

INSERT INTO Major VALUES('SE', 'Software Engineering', '090x')
INSERT INTO Major VALUES('IA', 'Information Assurance', '091x')
INSERT INTO Major VALUES('GD', 'Graphic Design', '092x')
INSERT INTO Major VALUES('JP', 'Japanese', '093x')
INSERT INTO Major VALUES('KR', 'Korean', '094x')

SELECT * FROM Major

CREATE TABLE Student
(
	StudentID char(8) PRIMARY KEY,          -- PK Primary Key - Khóa chính
	LastName nvarchar(30),
	FirstName nvarchar(10),
	DOB date,
	Address nvarchar(50), 
	MajorID char(2) REFERENCES Major(MajorID)  -- FK Foreign Key - Khóa ngoại
)

INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('SE1', N'Nguyễn', N'Một', 'SE');
INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('SE2', N'Lê', N'Hai', 'SE');
INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('SE3', N'Trần', N'Ba', 'SE');

INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('SE4', N'Phạm', N'Bốn', 'IA');
INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('SE5', N'Lý', N'Năm', 'IA');
INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('SE6', N'Võ', N'Sáu', 'IA');

INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('GD7', N'Đinh', N'Bảy', 'GD');
INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('GD8', N'Huỳnh', N'Tám', 'GD');

INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('JP9', N'Ngô', N'Chín', 'JP');

SELECT * FROM Student

-- TỪ TỪ HÃY THÊM VÀO ĐỂ XEM FULL-OUTER JOIN RA SAO
INSERT INTO Student(StudentID, LastName, FirstName, MajorID) VALUES('UNK', N'Đặng', N'Mười', NULL);

-- 1. In ra thông tin của sinh viên
SELECT * FROM Student -- info tắt của chuyên ngành
SELECT * FROM Major   -- info chuyên ngành, thiếu info sinh viên

-- JOIN lấy info đang nằm bên kia ghép thêm theo chiều ngang
SELECT * FROM Student s, Major m WHERE s.MajorID = m.MajorID
SELECT s.*, m.MajorName FROM Student s, Major m WHERE s.MajorID = m.MajorID
SELECT s.*, m.MajorName FROM Student s INNER JOIN Major m ON s.MajorID = m.MajorID

SELECT s.*, m.MajorName 
FROM Student s INNER JOIN Major m 
ON s.MajorID = m.MajorID
WHERE m.MajorID = 'SE' OR m.MajorID = 'IA'

SELECT s.*, m.MajorName 
FROM Student s INNER JOIN Major m 
ON s.MajorID = m.MajorID
WHERE m.MajorID IN ('SE', 'IA')

SELECT s.*, m.MajorID, m.MajorName, m.Hotline
FROM Student s, Major m
WHERE s.MajorID = m.MajorID AND m.MajorID IN ('SE', 'IA')
--------------------------------
SELECT s.*, m.*
FROM Student s RIGHT JOIN Major m 
ON s.MajorID = m.MajorID
--10

SELECT s.*, m.*
FROM Major m LEFT JOIN Student s
ON s.MajorID = m.MajorID 
-- 10

SELECT s.*, m.*
FROM Student s LEFT JOIN Major m
ON s.MajorID = m.MajorID
--9
---------------------------------
SELECT COUNT(*) FROM Major
SELECT COUNT(MajorID) AS [No major] FROM Major

SELECT MajorID, COUNT(*) AS [No student] FROM Student GROUP BY MajorID

SELECT MajorID, COUNT(*) AS [No student] FROM Student GROUP BY MajorID HAVING COUNT(*) >= 3

SELECT MajorID, COUNT*) AS [No student] 
FROM Student 
GROUP BY MajorID 
HAVING COUNT(*) <= ALL(SELECT COUNT(*) AS [No student] FROM Student GROUP BY MajorID)

SELECT MajorID, COUNT(*) AS [No student] FROM Student  WHERE MajorID = 'SE' GROUP BY MajorID

SELECT m.MajorID, m.MajorName, COUNT(*) AS [No student]
FROM Student s INNER JOIN Major m 
ON s.MajorID = m.MajorID
GROUP BY m.MajorID, m.MajorName

SELECT s.StudentID, s.FirstName, m.MajorID, m.MajorName 
FROM Student s RIGHT JOIN Major m 
ON s.MajorID = m.MajorID

SELECT m.MajorID, m.MajorName, COUNT(*) AS [No student]
FROM Student s RIGHT JOIN Major m 
ON s.MajorID = m.MajorID
GROUP BY m.MajorID, m.MajorName

SELECT m.MajorID, m.MajorName, COUNT(StudentID) AS [No student]
FROM Student s RIGHT JOIN Major m 
ON s.MajorID = m.MajorID
GROUP BY m.MajorID, m.MajorName


