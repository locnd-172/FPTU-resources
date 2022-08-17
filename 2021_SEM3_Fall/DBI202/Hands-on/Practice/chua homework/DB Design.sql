CREATE DATABASE DBDESIGN_ACTIVITIES_FIX

USE DBDESIGN_ACTIVITIES_FIX

-- Chiến lược: Gom 1 bảng
-- Xem: đa trị, composite, lookup, lặp lại trên 1 nhóm cột
--      tách thêm dòng tốt hơn thêm cột (khi cần thêm data)

CREATE TABLE ACTIVITY_V1
(
	LectID char(8),
	LectName nvarchar(30), -- composite 
	Email varchar(50),
	Phone char(11),
	Major varchar(30),
	StartDate datetime, -- ngày giờ
	ActType nvarchar(30), -- workshop, training, seminar, phụ đạo,.. -> lookup
	Topic nvarchar(30), -- giới thiệu arrayList
	Intro nvarchar(250),
	Room nvarchar(50), -- lưu hyper link của Zoom, Meet, phòng
	Seats int
)

INSERT INTO ACTIVITY_V1 VALUES('00000001', N'HÒA.ĐNT', 'hoadnt@', '090x', 'CF', '2021-11-3', 'Seminar', 
							   N'Nhập môn Machine Learning', N'...', N'Phòng Seminar Thư viện ĐH FPT HCM', 100)

INSERT INTO ACTIVITY_V1 VALUES('00000001', N'HÒA.ĐNT', 'hoadnt@', '090x', 'CF', '2021-11-3', 'Seminar', 
							   N'Giới thiệu về YOLO V4', N'...', N'Phòng Seminar Thư viện ĐH FPT HCM', 100)

INSERT INTO ACTIVITY_V1 VALUES('00000001', N'HÒA.ĐNT', 'hoadnt@', '090x', 'CF', '2021-12-3 08:00:00', 'Workshop', 
							   N'Giới thiệu về YOLO V4 (part 2)', N'...', N'Phòng Seminar Thư viện FUHCM', 100)

SELECT * FROM ACTIVITY_V1

-- Ưu điểm và nhược điểm

-------------------------------------------------------
CREATE TABLE LECTURER_V2
(
	LectID char(8) PRIMARY KEY,
	LectName nvarchar(30),
	Email varchar(50),
	Phone char(11),
	Major varchar(30),
)

CREATE TABLE ACTIVITY_V2
(
	SEQ int IDENTITY PRIMARY KEY,
	StartDate datetime,
	ActType nvarchar(30), -- lookup
	Topic nvarchar(30), 
	Intro nvarchar(250),
	Room nvarchar(50), 
	Seats int,
	LectID char(8)REFERENCES Lecturer_V2(LectID)
)
INSERT INTO LECTURER_V2 VALUES('00000001', N'HÒA.ĐNT', 'hoadnt@', '090x', 'CF')

INSERT INTO ACTIVITY_V2 VALUES('2021-11-3 08:30:00', 'Seminar', N'Nhập môn Machine Learning', 
							   N'...', N'Phòng Seminar Thư viện ĐH FPT HCM', 100, '00000001')

INSERT INTO ACTIVITY_V2 VALUES('2021-11-3 14:30:00', 'seminar', N'Giới thiệu về YOLO V4', 
							   N'...', N'Phòng Seminar Thư viện ĐH FPT HCM', 100, '00000001')

INSERT INTO ACTIVITY_V2 VALUES('2021-12-3 08:00:00', 'Workshop', N'Giới thiệu về YOLO V4 (part 2)', 
							   N'...', N'Phòng Seminar Thư viện FUHCM', 100, '00000001')

SELECT * FROM LECTURER_V2
SELECT * FROM ACTIVITY_V2
-- thêm table Major 1-N
