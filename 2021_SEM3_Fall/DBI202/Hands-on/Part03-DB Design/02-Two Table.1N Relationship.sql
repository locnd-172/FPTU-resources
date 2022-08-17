CREATE DATABASE DBDESIGN_ONEMANY

USE DBDESIGN_ONEMANY

-- tạo 1 table trước, table N tạo sau

DROP TABLE MajorV1

CREATE TABLE MajorV1
(
	MajorID char(2) PRIMARY KEY, -- mặc định DBE tự tạo tên
	MajorName nvarchar(40)

)

INSERT INTO MajorV1 VALUES ('SE', N'Kỹ thuật phần mềm')
INSERT INTO MajorV1 VALUES ('IA', N'An toàn thông tin')

SELECT * FROM MajorV1

--------------------------------------------------------------

DROP TABLE StudentV1

CREATE TABLE StudentV1
(
	StudentID char(8) NOT NULL, 
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL, 
	MID char(2),
	CONSTRAINT PK_STUDENTV7 PRIMARY KEY (StudentID)
)

INSERT INTO StudentV1 VALUES ('SE1', N'Nguyễn', N'An', NULL, NULL, 'SE')

SELECT * FROM StudentV1

-- thiết kế DB trên là sai vì 2 TABLE độc lập

----------------------------------------------------------------

CREATE TABLE MajorV2
(
	MajorID char(2) PRIMARY KEY, -- mặc định DBE tự tạo tên
	MajorName nvarchar(40)
)

INSERT INTO MajorV2 VALUES ('SE', N'Kỹ thuật phần mềm')
INSERT INTO MajorV2 VALUES ('IA', N'An toàn thông tin')

CREATE TABLE StudentV2
(
	StudentID char(8) PRIMARY KEY, 
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL, 
	MajorID char(2) REFERENCES MajorV2(MajorID)
	-- MajorID char(2) FOREIGN KEY REFERENCES MajorV2(MajorID)
)

INSERT INTO StudentV2 VALUES ('SE1', N'Nguyễn', N'An', NULL, NULL, 'SE')
INSERT INTO StudentV2 VALUES ('SE2', N'Lê', N'Bình', NULL, NULL, 'HA')


SELECT * FROM MajorV2
SELECT * FROM StudentV2
-------------------------------------------------------------------

DROP TABLE MajorV3
CREATE TABLE MajorV3
(
	MajorID char(2) PRIMARY KEY, -- mặc định DBE tự tạo tên
	MajorName nvarchar(40)
)

DROP TABLE StudentV3 
/*
CREATE TABLE StudentV3
(
	StudentID char(8) PRIMARY KEY, 
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL, 
	--MajorID char(2) REFERENCES MajorV2(MajorID)
	MajorID char(2),

	CONSTRAINT FK_StudentV3_MajorV3 
	FOREIGN KEY (MajorID) REFERENCES MajorV3(MajorID)
)*/
CREATE TABLE StudentV3
(
	StudentID char(8) PRIMARY KEY, 
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL, 

	MajorID char(2) DEFAULT 'SE',
	CONSTRAINT FK_StudentV3_MajorV3 
	FOREIGN KEY (MajorID) REFERENCES MajorV3(MajorID)
	ON DELETE SET DEFAULT
	ON UPDATE CASCADE
)
-- ta có quyền gỡ rằng buộc đã thiết lập
ALTER TABLE StudentV3 DROP CONSTRAINT FK_StudentV3_MajorV3 

-- bổ sung lại 1 rằng buộc khác
ALTER TABLE StudentV3 ADD CONSTRAINT FK_StudentV3_MajorV3 
						  FOREIGN KEY (MajorID) REFERENCES MajorV3(MajorID)

INSERT INTO MajorV3 VALUES ('SE', N'Kỹ thuật phần mềm')
INSERT INTO MajorV3 VALUES ('AH', N'Ahihi đồ ngốc')

INSERT INTO StudentV3 VALUES ('SE1', N'Nguyễn', N'An', NULL, NULL, 'SE')
INSERT INTO StudentV3(StudentID, FirstName, LastName) VALUES ('SE2', N'Phạm', N'Bình')
INSERT INTO StudentV3 VALUES ('AH1', N'Lê', N'Vui Vẻ', NULL, NULL, 'AH')

SELECT * FROM MajorV3
SELECT * FROM StudentV3

-- thao tác trên data - DML (update/ delete)
DELETE FROM StudentV3 -- xóa hết data

DELETE FROM StudentV3 WHERE StudentID = 'ah1'

DELETE FROM MajorV3 WHERE MajorID = 'ah'

-- gàn thêm hành xử khi xóa, sửa data ở rằng buộc khóa ngoại/dính khóa chính luôn
-- hiệu ứng domino, sụp đổ dây chuyền, 1 xóa, N đi sạch ----> cascade delete
--                                     1 sửa, N bị sửa theo ------> cascade update
-- ngay lúc design TABLE/ create TABLE đã phải tính
-- vẫn có thể sửa sau khi có data, có thể có trouble

ALTER TABLE StudentV3 DROP CONSTRAINT FK_StudentV3_MajorV3 
-- xóa rằng buộc khóa ngoại
-- gài thêm rule nhỏ liên quan xóa sửa data

ALTER TABLE StudentV3 ADD CONSTRAINT FK_StudentV3_MajorV3 
						  FOREIGN KEY (MajorID) REFERENCES MajorV3(MajorID)
						  ON DELETE CASCADE
						  ON UPDATE CASCADE

SELECT * FROM MajorV3
SELECT * FROM StudentV3

-- update (DML) sửa data đang có
UPDATE MajorV3 SET MajorID = 'AK' -- cẩn thận nếu không có WHERE, toàn TABLE sẽ bị ảnh hưởng
								  -- trừ update KEY

UPDATE MajorV3 SET MajorID = 'AK' WHERE MajorID = 'AH'

-- xóa 1, N đi sạch sẽ
-- xóa chuyên ngành AHIHI, xem sao? còn sv nào k
DELETE FROM MajorV3 WHERE MajorID = 'AH'
DELETE FROM MajorV3 WHERE MajorID = 'AK'
DELETE FROM StudentV3 WHERE StudentID = 'SE2'
DELETE FROM StudentV3 WHERE StudentID = 'AH1'

ALTER TABLE StudentV3 DROP CONSTRAINT FK_StudentV3_MajorV3 

ALTER TABLE StudentV3 ADD CONSTRAINT FK_StudentV3_MajorV3 
						  FOREIGN KEY (MajorID) REFERENCES MajorV3(MajorID)
						  ON DELETE SET NULL -- xóa cho mồ côi, NULL
						  ON UPDATE CASCADE  -- sửa bị ảnh hưởng dây chuyền

UPDATE StudentV3 SET MajorID = 'SE' -- toàn trường học SE (sai)

UPDATE StudentV3 SET MajorID = 'SE' WHERE StudentID = 'AH1'
UPDATE StudentV3 SET MajorID = 'SE' WHERE MajorID IS NULL

