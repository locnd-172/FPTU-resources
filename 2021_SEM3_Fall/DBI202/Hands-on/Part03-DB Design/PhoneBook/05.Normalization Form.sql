USE DBDESIGN_PHONEBOOK

CREATE TABLE PhoneBookV5_1_
(
	Nick nvarchar(30), 
	Phone varchar(11), 
	PhoneType nvarchar(20)   -- cho gõ trực tiếp loại PHONE, gây lộn xọn loại PHONE
							 -- thống kê khó khăn
							 -- hạn chê không cho gõ lộn xộn, tức là phải gõ/chọn theo ai đó
							 -- FK
)
----------------------------------------------------------
-- Table mới xuất hiện, lưu loại phone, k cho gõ lung tung
CREATE TABLE PhoneTypeV5_1
(
	TypeName nvarchar(20)
)

CREATE TABLE PersonV5_1
(
	Nick nvarchar(30),
	Title nvarchar(30),
	Company nvarchar(40)
)

-- Không muốn xóa TABLE mà vẫn thêm khóa chính
ALTER TABLE PhoneTypeV5_1 ALTER COLUMN TypeName nvarchar(20) NOT NULL

ALTER TABLE PhoneTypeV5_1 
ADD CONSTRAINT PK_PHONETYPEV51 PRIMARY KEY (TypeName)

ALTER TABLE PhoneTypeV5_1 
ADD PRIMARY KEY (TypeName)
----------------------------------------------

CREATE TABLE PhoneBookV5_1
(
	Nick nvarchar(30), 
	Phone varchar(11), 
	TypeName nvarchar(20) REFERENCES PhoneTypeV5_1(TypeName)
)

SELECT * FROM PersonV5_1
SELECT * FROM PhoneTypeV5_1
SELECT * FROM PhoneBookV5_1

DROP TABLE PersonV5_1
DROP TABLE PhoneTypeV5_1
DROP TABLE PhoneBookV5_1

-----------------------------------------------------
INSERT INTO PhoneTypeV5_1 VALUES (N'Di Động')
INSERT INTO PhoneTypeV5_1 VALUES (N'Để Bàn')
INSERT INTO PhoneTypeV5_1 VALUES (N'Công Ty')
-----------------------------------------------------
INSERT INTO PersonV5_1 VALUES (N'hoangnt', 'Lecturer', 'FPTU HCMC')
INSERT INTO PersonV5_1 VALUES (N'annguyen', 'Student', 'FPTU HCMC')
INSERT INTO PersonV5_1 VALUES (N'binhle', 'Student', 'FPTU HLL')
-----------------------------------------------------
INSERT INTO PhoneBookV5_1 VALUES (N'phamduc', '098x', N'Di Động')
INSERT INTO PhoneBookV5_1 VALUES (N'hoangnt', '095x', N'Công Ty')

INSERT INTO PhoneBookV5_1 VALUES (N'annguyen', '090x', N'Để Bàn')
INSERT INTO PhoneBookV5_1 VALUES (N'annguyen', '091x', N'Công Ty')

INSERT INTO PhoneBookV5_1 VALUES (N'binhle', '090x', N'Để Bàn')
INSERT INTO PhoneBookV5_1 VALUES (N'binhle', '091x', N'Di Động')
INSERT INTO PhoneBookV5_1 VALUES (N'binhle', '092x', N'Di Động')
-----------------------------------------------------
-----------------------------------------------------
-----------------------------------------------------
CREATE TABLE PhoneTypeV5
(
	TypeName nvarchar(20) NOT NULL,
	PRIMARY KEY (TypeName)
)

CREATE TABLE PersonV5
(
	Nick nvarchar(30) PRIMARY KEY,
	Title nvarchar(30),
	Company nvarchar(40)
)

CREATE TABLE PhoneBookV5
(
	Phone varchar(11),											-- sđt là ?
	TypeName nvarchar(20) REFERENCES PhoneTypeV5(TypeName),		-- thuộc loại gì?
	Nick nvarchar(30) REFERENCES PersonV5(Nick)					-- của ai?
	CONSTRAINT PK_PHONEBOOKV5 PRIMARY KEY(Phone)
)

SELECT * FROM PersonV5
SELECT * FROM PhoneTypeV5
SELECT * FROM PhoneBookV5