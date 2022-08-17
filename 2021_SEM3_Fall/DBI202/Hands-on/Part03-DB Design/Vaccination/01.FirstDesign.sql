CREATE DATABASE DBDESIGN_VACCINATION

USE DBDESIGN_VACCINATION

CREATE TABLE VaccinationV1
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30), -- sort, FullName là sort theo họ
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE, -- constraint, cấm trùng nhưng k phải PK
									   -- key ứng viên, CANDIDATE KEY
	InjectionInfo nvarchar(255)
)

SELECT * FROM VaccinationV1


INSERT INTO VaccinationV1 VALUES('0000000001', N'Nguyễn', N'An', '090x', N'AZ Ngày 28.09.2021 ĐH FPT | AZ Ngày 28.09.2021 BV Lê Văn Thịnh Q.TĐ')

DROP TABLE VaccinationV1

-- Nhược: thống kê không được, ít nhất đi cắt chuỗi, căng! do đa trị

-- Solution: Cần quan tâm thống kê, tính toán số liệu (? mũi, AZ có bao nhiêu người...)
-- tách cột, tách bảng

CREATE TABLE VaccinationV2
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30), 
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE,
	Dose1 nvarchar(100), -- AZ, Astra, Astra... 25.9.2021, ĐH FPT -- COMPOSITE (phức hợp)
	Dose2 nvarchar(100)  -- AZ, Astra, Astra... 25.9.2021, ĐH FPT -- COMPOSITE (phức hợp)
)
-- PHÂN TÍCH
-- Ưu: Gọn gàng, select gọn gàng
-- Nhược: NULL!! Thêm mũi 3, 4 hàng năm thì sao???
-- chỉ vì vài người mà ta phải chừa chỗ NULL
-- thống kê!! cột composite chưa cho mình được thống kê

-- Vì số lần chích còn có thể gia tăng cho từng người, mũi 2, mũi nhắc, mũi thường niên
------------------------------------------------

CREATE TABLE PersonV2_
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30), 
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE,
)
CREATE TABLE VaccinationV2_
(
	Dose1 nvarchar(100), -- AZ, Astra, Astra... 25.9.2021, ĐH FPT -- COMPOSITE (phức hợp)
	Dose2 nvarchar(100)  -- AZ, Astra, Astra... 25.9.2021, ĐH FPT -- COMPOSITE (phức hợp)
)
------------------------------------------------
-- Composite tách thành dòng
CREATE TABLE PersonV3
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30), 
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE
)

CREATE TABLE VaccinationV3
(
	Dose nvarchar(100), -- AZ, Astra, Astra... 25.9.2021, ĐH FPT -- COMPOSITE (phức hợp
	PersonID char(11) REFERENCES PersonV3(ID)
)
-- PHÂN TÍCH
-- Ưu: chích thêm nhát nào. thêm dòng nhát đó, chấp 10 mũi đủ chỗ lưu, không ảnh hường người chích
-- Nhược: Không thống kê được
-- Composite tách tiếp thành cột, trả lại đúng ý nghĩa cho miếng info nhỏ
------------------------------------------------
-- Composite tách thành cột
-- thống kê được
CREATE TABLE PersonV4
(
	ID char(11) PRIMARY KEY,
	LastName nvarchar(30), 
	FirstName nvarchar(10),
	Phone varchar(11) NOT NULL UNIQUE
)

CREATE TABLE VaccinationV4
(
	-- AZ, Astra, Astra... 25.9.2021, ĐH FPT -- COMPOSITE (phức hợp
	Dose int, -- liều chích số 1
	InjDate datetime, -- giờ chích
	Vaccine nvarchar(50),
	Lot nvarchar(20),
	[Location] nvarchar(50),
	PersonID char(11) REFERENCES PersonV4(ID)
)

SELECT * FROM PersonV4

INSERT INTO PersonV4 VALUES('000000001', N'Nguyễn', N'An', '091x')
INSERT INTO PersonV4 VALUES('000000002', N'Lê', N'Bình', '090x')

SELECT * FROM VaccinationV4

INSERT INTO VaccinationV4 VALUES(1, GETDATE(), 'AZ', NULL, NULL, '000000002')

-- in ra xanh vàng cho mỗi người
SELECT * FROM PersonV4 p LEFT JOIN VaccinationV4 v
ON p.ID = v.PersonID

SELECT p.ID, p.FirstName, COUNT(v.Dose) AS 'No does'
FROM PersonV4 p LEFT JOIN VaccinationV4 v
ON p.ID = v.PersonID
GROUP BY p.ID, p.FirstName

SELECT p.ID, p.FirstName, IIF(COUNT(v.Dose) = 0, 'NOOP', 
							IIF(COUNT(v.Dose) = 1, 'Yellow', 'Green')) AS 'Status'
FROM PersonV4 p LEFT JOIN VaccinationV4 v
ON p.ID = v.PersonID
GROUP BY p.ID, p.FirstName

INSERT INTO VaccinationV4 VALUES(2, GETDATE(), 'AZ', NULL, NULL, '000000002')

SELECT p.ID, p.FirstName, IIF(COUNT(v.Dose) = 0, 'NOOP', 
							IIF(COUNT(v.Dose) = 1, 'Yellow', 'Green')) AS 'Status'
FROM PersonV4 p LEFT JOIN VaccinationV4 v
ON p.ID = v.PersonID
GROUP BY p.ID, p.FirstName

SELECT * FROM PersonV4
SELECT * FROM VaccinationV4

DROP TABLE VaccinationV4