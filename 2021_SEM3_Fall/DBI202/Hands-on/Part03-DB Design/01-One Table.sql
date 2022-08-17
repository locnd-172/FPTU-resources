CREATE DATABASE DBDESIGN_ONETABLE
-- Create dùng để tạo cấu trúc lưu trữ, dàn khung/thùng chứa dùng để lưu trữ data/info
-- tương đương việc xây phòng chứa đồ - DATABASE
--			   mua tủ để trong phòng  - TABLE
-- 1 DB chứa nhiều TABLE - 1 phòng có nhiều tủ
--						 - 1 nhà có nhiều 
-- Tạo ra cấu trúc lưu trữ - chưa nói Data bỏ vào - DDL (phân nhánh của SQL)


USE DBDESIGN_ONETABLE

-- tạo TABLE lưu trữ hồ sơ sinh viên: mã số (phân biệt sv với nhau), tên, dob, địa chỉ...
-- SV ~~ OBJECT ~~~ ENTITY 
-- 1 TABLE dùng để lưu trữ nhiều ENTITY 

CREATE TABLE StudentV1
(
	StudentID char(8),
	LastName nvarchar(40),
	FirstName nvarchar(10),
	DOB datetime,
	Address nvarchar(50)
)

DROP TABLE StudentV1
-- thao tác trên data/món đồ trong tủ/TABLE -> DML/DQL (dành riêng cho SELECT)
SELECT * FROM StudentV1

-- đưa DATA vào TABLE
INSERT INTO StudentV1 VALUES('SE123456', N'Nguyễn', N'An', '2003-1-1', N'...TP.Hồ Chí Minh')

-- một số cột chưa nhập info, được quyền bỏ trống nếu cột cho phép trống VALUE
-- DEFAULT khi đóng cái tủ/mua tủ/thiết kế tủ, mặc định NULL
INSERT INTO StudentV1 VALUES('SE123457', N'Lê', N'Bình', '2003-2-1', NULL)
-- tên thành phố là NULL, WHERE = 'NULL' ok vì nó là data
-- NULL ở câu trên WHERE ADDRESS IS NULL
INSERT INTO StudentV1 VALUES('SE123458', N'Võ', N'Cường', '2003-3-1', NULL)

-- chỉ muốn lưu vài info, k đủ số cột, miễn cột còn lại cho phép trống
INSERT INTO StudentV1(StudentID, LastName, FirstName)
			   VALUES('SE123459', N'Trần', N'Dũng')

INSERT INTO StudentV1(LastName, FirstName, StudentID)
			   VALUES(N'Phạm', N'Em', 'SE123460')

INSERT INTO StudentV1(LastName, FirstName, StudentID)
			   VALUES(NULL, NULL, NULL)
-- nguy hiểm! sv toàn info bỏ trống
-- gàn cái cách đưa DATA vào các cột sao cho hợp lý
-- CONSTRAINT trên DATA/CELL/COLUMN

-- nguy hiểm hơn!
-- trùng mã số không chấp nhận được, không xác định được 1 sv
-- gài rằng buộc dữ liệu quan trọng này
-- cột mà value cấm trùng trên mọi CELL cùng cột
-- dùng làm khóa/key để tìm ra / mở ra / xác định
-- duy nhất 1 info, dòng, 1 sv, 1 entity, 1 object
-- cột này được gọi là primary key

INSERT INTO StudentV1(LastName, FirstName, StudentID)
			   VALUES(N'Đỗ', N'Giang', 'SE123460')

SELECT * FROM StudentV1
---------------------------------------
-- gài cách đưa DATA vào TABLE để không có những hiện tượng bất thường
-- KEY NULL - Default thiết kế cho phép NULL tất cả

CREATE TABLE StudentV2
(
	StudentID char(8) PRIMARY KEY, -- bao hàm luôn NOT NULL -- bắt buộc phải đưa DATA
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL
)

INSERT INTO StudentV2 VALUES('SE123456', N'Nguyễn', N'An', '2003-1-1', N'...TP.Hồ Chí Minh')

INSERT INTO StudentV2(StudentID, LastName, FirstName)
VALUES(NULL, NULL, NULL) -- gẫy


INSERT INTO StudentV2(StudentID, LastName, FirstName)
VALUES('AHIHI', NULL, NULL) -- gẫy

INSERT INTO StudentV2 
VALUES('SE123456', N'Nguyễn', N'An', '2003-1-1', N'...TP.Hồ Chí Minh') -- gẫy 

INSERT INTO StudentV2 
VALUES('GD123456', N'Nguyễn', N'An', '2003-1-1', N'...TP.Hồ Chí Minh') 

INSERT INTO StudentV2
VALUES('SE123457', N'Lê', N'Bình', '2003-2-1', NULL)

INSERT INTO StudentV2 
VALUES('SE123458', N'Võ', N'Cường', '2003-3-1', NULL)

-- --không INSERT hết số cột thì phải liệt kê tên cột
INSERT INTO StudentV2 
VALUES('SE123458', N'Võ', N'Cường')

INSERT INTO StudentV2(StudentID, LastName, FirstName)
VALUES('SE123459', N'Trần', N'Dũng')

SELECT * FROM StudentV2
----------------------------------------------------------------------
CREATE TABLE StudentV3
(
	StudentID char(8) PRIMARY KEY, -- bao hàm luôn NOT NULL -- bắt buộc phải đưa DATA
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL -- thừa từ NULL, do default là vậy
)
----------------------------------------------------------------------
CREATE TABLE StudentV4
(
	StudentID char(8) NOT NULL, 
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL, 
	PRIMARY KEY(StudentID)

)
INSERT INTO StudentV4 
VALUES('SE123456', N'Nguyễn', N'An', '2003-1-1', N'...TP.Hồ Chí Minh')

SELECT * FROM StudentV4


CREATE TABLE Student5 (
  StudentID char(8) NOT NULL, 
  LastName  varchar(50) NULL, 
  FirstName varchar(10) NULL, 
  PRIMARY KEY (StudentID)
);
SELECT * FROM StudentV5

CREATE TABLE StudentV6
(
	StudentID char(8) NOT NULL, 
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL, 
	--PRIMARY KEY(StudentID)
	CONSTRAINT PK_STUDENTS PRIMARY KEY (StudentID)
)


DROP TABLE StudentV7

CREATE TABLE StudentV7
(
	StudentID char(8) NOT NULL, 
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL, 
	--PRIMARY KEY(StudentID)
	--CONSTRAINT PK_STUDENTV7 PRIMARY KEY (StudentID)
)

ALTER TABLE StudentV7 ADD CONSTRAINT PK_STUDENTV7 PRIMARY KEY (StudentID)

-- xóa 1 ràng buộc
ALTER TABLE StudentV7 DROP CONSTRAINT PK_STUDENTV7
ALTER TABLE StudentV2 DROP CONSTRAINT PK__StudentV__32C52A79F4D9F98D