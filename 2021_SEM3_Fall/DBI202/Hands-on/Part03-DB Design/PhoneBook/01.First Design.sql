CREATE DATABASE DBDESIGN_PHONEBOOK

USE DBDESIGN_PHONEBOOK

CREATE TABLE PhoneBookV1
(
	Nick nvarchar(30),
	Phone varchar(50)
	-- 3 số phone gom vào 1 cột

)

SELECT * FROM PhoneBookV1

INSERT INTO PhoneBookV1 VALUES (N'phamduc', '090x')

-- An có 2 số phone
INSERT INTO PhoneBookV1 VALUES (N'annguyen','090x, 091x')
INSERT INTO PhoneBookV1 VALUES (N'binhle','090x, 091x, 092x')

-- Ưu điểm: SELECT Phone là ra được tất cả các số phone

-- HỎI: Số để bàn, số ở nhà của binhle? TOANG

-- QUY ƯỚC: số 1 là để bàn, số 2 là di động, số 3 là work
-- khó nhớ cho người nhập liệu: số nào là loại nào

-- HỎI: In ra số di động của mọi người?

INSERT INTO PhoneBookV1 VALUES (N'binhle','090x | 091x | 092x')
INSERT INTO PhoneBookV1 VALUES (N'cuongvo','090x, 091x. 092x')
INSERT INTO PhoneBookV1 VALUES (N'dungpham','090x - 091x - 092x')
-- tiêu chi cắt chuỗi - DELIMETER dấu phân cách không nhất quán

-- HỎI: đếm xem mỗi người có bao nhiêu số phone! COUNT() 
-- dấu phân cách khó khăn cho cắt để COUNT

-- KHÓ KHĂN XẢY RA KHI TA GOM NHIỀU VALUE CÙNG KIỂU NGỮ NGHĨA VÀO TRONG 1 COLUMN
-- cột PHONE lưu nhiều số PHONE cách nhau dấu cách
-- gây khó khăn cho nhập dữ liệu (không nhất quán dấu cách)
-- khi SELECT COUNT() thống kê theo tiêu chí 
-- UPDATE thêm phone mới, xóa phone cũ




