DROP DATABASE ClubManagement

CREATE DATABASE ClubManagement

USE ClubManagement

DROP TABLE Club
CREATE TABLE Club
(
	ClubID char(5) PRIMARY KEY,         -- PK Primary Key - Khóa chính
	ClubName nvarchar(50),
	Hotline varchar(11)
)

INSERT INTO Club VALUES('SiTi', N'Cộng đồng Sinh viên Tình nguyện', '090x')
INSERT INTO Club VALUES('SkllC', N'Skillcetera', '091x')
INSERT INTO Club VALUES('CSG', N'CLB Truyền thông Cóc Sài Gòn', '092x')
INSERT INTO Club VALUES('FEV', N'FPT Event Club', '093x')
INSERT INTO Club VALUES('FCode', N'FPT Code', '094x')

SELECT * FROM Club

DROP TABLE Student
CREATE TABLE Student
(
	StudentID char(8) PRIMARY KEY,          -- PK Primary Key - Khóa chính
	LastName nvarchar(30),
	FirstName nvarchar(10),
	DOB date,
	Address nvarchar(50)	
)

INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE1', N'Nguyễn', N'Một');
INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE2', N'Lê', N'Hai');
INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE3', N'Trần', N'Ba');
INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE4', N'Phạm', N'Bốn');
INSERT INTO Student(StudentID, LastName, FirstName) VALUES('SE5', N'Lý', N'Năm');

SELECT * FROM Student

DROP TABLE Registration
CREATE TABLE Registration
(
	RegID int IDENTITY(1, 1) PRIMARY KEY,   -- PK Primary Key - Khóa chính - Tăng tự động từ 1	      
	StudentID char(8),
	ClubID char(5),    
	JoinedDate date,
	LeavedDate date
	CONSTRAINT FK_Reg_Club FOREIGN KEY (ClubID) REFERENCES Club(ClubID),                -- FK Foreign Key - Khóa ngoại
	CONSTRAINT FK_Reg_Student FOREIGN KEY (StudentID) REFERENCES Student(StudentID)    -- FK Foreign Key - Khóa ngoại
)


-- SiTi 3, SkillC 2, CSG 2, FEV 0, FCODE 0
-- SE1 3, SE2 3, SE3 1, SE4 0, SE5 0
INSERT INTO Registration(StudentID, ClubID) VALUES('SE1', 'SiTi')
INSERT INTO Registration(StudentID, ClubID) VALUES('SE1', 'SkllC')
INSERT INTO Registration(StudentID, ClubID) VALUES('SE1', 'CSG')


INSERT INTO Registration(StudentID, ClubID) VALUES('SE2', 'SiTi')
INSERT INTO Registration(StudentID, ClubID) VALUES('SE2', 'SkllC')
INSERT INTO Registration(StudentID, ClubID) VALUES('SE2', 'CSG')

INSERT INTO Registration(StudentID, ClubID) VALUES('SE3', 'SiTi')

SELECT * FROM Registration


-- 1. Liệt kê thông tin sv đang theo học
SELECT * FROM Student

-- 2. Kèm clb
SELECT s.StudentID, s.FirstName, r.ClubID 
FROM Student AS s JOIN Registration AS r 
ON s.StudentID = r.StudentID

SELECT s.StudentID, s.FirstName + ' ' + s.LastName AS FullName, r.ClubID 
FROM Student AS s JOIN Registration AS r 
ON s.StudentID = r.StudentID 
-- thiếu sv 4, 5 vì JOIN =

SELECT s.StudentID, s.FirstName + ' ' + s.LastName AS FullName, r.ClubID 
FROM Student AS s LEFT JOIN Registration AS r 
ON s.StudentID = r.StudentID 

-- 3. Kèm id, tên clb
SELECT * FROM Student s JOIN Registration r 
ON s.StudentID = r.StudentID 
JOIN Club c ON c.ClubID = r.ClubID

SELECT s.StudentID, s.FirstName + ' ' + s.LastName AS FullName, r.ClubID, c.ClubName, r.JoinedDate
FROM Student s JOIN Registration r 
ON s.StudentID = r.StudentID 
JOIN Club c ON c.ClubID = r.ClubID

SELECT s.StudentID, s.FirstName, r.ClubID, c.ClubName, r.JoinedDate
FROM Student s JOIN Registration r 
ON s.StudentID = r.StudentID 
JOIN Club c ON c.ClubID = r.ClubID

SELECT s.StudentID, s.FirstName, c.ClubID, c.ClubName, r.JoinedDate
FROM Student s, Registration r, Club c
WHERE s.StudentID = r.StudentID AND c.ClubID = r.ClubID
-- không lấy được phần hụt vì chỉ đi tìm đám bằng nhau common field ghép và in ra

SELECT s.StudentID, s.FirstName, c.ClubID, c.ClubName, r.JoinedDate
FROM Student s FULL JOIN Registration r
ON s.StudentID = r.StudentID 
FULL JOIN Club c ON c.ClubID = r.ClubID

----------------------------------------------------------------------------------------------------------------
--1. Đếm số clb mà mỗi sv đã tham gia
--output: mã sv, tên sv, số-clb-tham-gia
SELECT s.StudentID, s.FirstName + ' ' + s.LastName AS FullName, COUNT(r.ClubID) AS [No joined club]
FROM Student s FULL JOIN Registration r 
ON s.StudentID = r.StudentID 
GROUP BY s.StudentID, s.FirstName, s.LastName

--2. Sinh viên SE1 tham gia mấy clb
--output: mã sv, tên sv, số-clb-tham-gia
SELECT s.StudentID, s.FirstName + ' ' + s.LastName AS FullName, COUNT(r.ClubID) AS [No joined club]
FROM Student s FULL JOIN Registration r 
ON s.StudentID = r.StudentID 
WHERE s.StudentID = 'SE1'
GROUP BY s.StudentID, s.FirstName, s.LastName

--3. SV nào tham gia nhiều clb nhất??
SELECT s.StudentID, s.FirstName + ' ' + s.LastName AS FullName, COUNT(r.ClubID) AS [No joined club]
FROM Student s FULL JOIN Registration r 
ON s.StudentID = r.StudentID 
GROUP BY s.StudentID, s.FirstName, s.LastName
HAVING COUNT(*) >= ALL(
	SELECT COUNT(r.ClubID) 
	FROM Student s FULL JOIN Registration r 
	ON s.StudentID = r.StudentID 
	GROUP BY s.StudentID
)

--4. CLB cộng đồng Sinh viên Tình nguyện có những sv nào tham gia (gián tiếp)
--ko dùng sub cũng OK (không hỏi Siti)
--Dùng sub cũng OK
SELECT s.* FROM Student s JOIN Registration r ON s.StudentID = r.StudentID
WHERE r.ClubID = 'Siti'

--5. Mỗi clb có bao nhiêu thành viên
--output: mã clb, tên clb, số thành viên
SELECT c.ClubID, c.ClubName, COUNT(r.ClubID) AS [No student] FROM Club c FULL JOIN Registration r 
ON c.ClubID = r.ClubID 
GROUP BY c.ClubID, c.ClubName

--6. CLB nào đông member nhất
--output: mã clb, tên clb, số thành viên
SELECT c.ClubID, c.ClubName, COUNT(r.ClubID) AS [No student] FROM Club c FULL JOIN Registration r 
ON c.ClubID = r.ClubID 
GROUP BY c.ClubID, c.ClubName
HAVING COUNT(r.ClubID) >= ALL(
	SELECT COUNT(r.ClubID) FROM Club c FULL JOIN Registration r 
	ON c.ClubID = r.ClubID 
	GROUP BY c.ClubID
)

--7. CLB Siti và CSG có bao nhiêu member. Đếm riêng từng clb
--output: mã clb, tên clb, số thành viên (2 dòng)
SELECT c.ClubID, c.ClubName, COUNT(r.ClubID) AS [No student] FROM Club c FULL JOIN Registration r 
ON c.ClubID = r.ClubID 
WHERE c.ClubID IN ('Siti', 'CSG')
GROUP BY c.ClubID, c.ClubName

--8. Có tổng cộng bn lượt sv tham gia clb?
SELECT COUNT(*) FROM Registration


SELECT * FROM Club
SELECT * FROM Student
SELECT * FROM Registration