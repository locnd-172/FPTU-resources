USE DBDESIGN_PHONEBOOK

CREATE TABLE PhoneBookV2_
(
	Nick nvarchar(30),
	-- Phone varchar(50)
	Phone1 varchar(11),
	Phone2 varchar(11),
	Phone3 varchar(11) -- k biết cột nào là loại nào
)

CREATE TABLE PhoneBookV2
(
	Nick nvarchar(30),
	HomePhone varchar(11),
	WorkPhone varchar(11),
	CellPhone varchar(11)
)

SELECT * FROM PhoneBookV2

INSERT INTO PhoneBookV2 VALUES (N'phamduc', NULL, NULL, '098x')

-- An có 2 số phone
INSERT INTO PhoneBookV2 VALUES (N'annguyen', '090x', '091x', NULL)
INSERT INTO PhoneBookV2 VALUES (N'binhle', '090x', '091x', '092x')

-- 1. In ra số di động của mọi người?
SELECT Nick, CellPhone FROM PhoneBookV2
SELECT p.Nick, p.CellPhone FROM PhoneBookV2 p 

-- Nhược điểm!

-----------------------------------------------------

-- Phiên bản 3 - Ai nhiều phone thì nhiều dòng -> thêm dòng
-- COUNT ngon lành trả lời ngay được câu bao nhiêu sim
