USE DBDESIGN_PHONEBOOK

CREATE TABLE PhoneBookV3_1
(
	Nick nvarchar(30),
	Phone varchar(11) -- chỉ 1 số phone 
)

SELECT * FROM PhoneBookV3_1

INSERT INTO PhoneBookV3_1 VALUES (N'phamduc', '098x')

-- An có 2 số phone
INSERT INTO PhoneBookV3_1 VALUES (N'annguyen', '090x')
INSERT INTO PhoneBookV3_1 VALUES (N'annguyen', '091x')

INSERT INTO PhoneBookV3_1 VALUES (N'binhle', '090x')
INSERT INTO PhoneBookV3_1 VALUES (N'binhle', '091x')
INSERT INTO PhoneBookV3_1 VALUES (N'binhle', '092x')

-- Nhược điểm: không biết số phone X nào đó thuộc loại nào

SELECT Nick, COUNT(*) AS [No Phones] FROM PhoneBookV3_1 GROUP BY (Nick)
-----------------------------------------------------
-- ta cần giải quyết việc phone này thuộc loại nào
CREATE TABLE PhoneBookV3_2
(
	Nick nvarchar(30),
	Phone varchar(11), -- chỉ 1 số phone, cần giải nghĩa thêm
	PhoneType nvarchar(20)
)

SELECT * FROM PhoneBookV3_2

INSERT INTO PhoneBookV3_2 VALUES (N'phamduc', '098x', 'Cell')

INSERT INTO PhoneBookV3_2 VALUES (N'annguyen', '090x', 'Cell')
INSERT INTO PhoneBookV3_2 VALUES (N'annguyen', '091x', 'Home')

INSERT INTO PhoneBookV3_2 VALUES (N'binhle', '090x', 'Work')
INSERT INTO PhoneBookV3_2 VALUES (N'binhle', '091x', 'Cell')
INSERT INTO PhoneBookV3_2 VALUES (N'binhle', '092x', 'Cell')