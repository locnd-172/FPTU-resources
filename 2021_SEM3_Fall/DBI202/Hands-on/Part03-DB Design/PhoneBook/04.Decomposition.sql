USE DBDESIGN_PHONEBOOK

CREATE TABLE PhoneBookV4_1
(
	Nick nvarchar(30), -- ngoài cột này bị lặp lại, còn cần thêm 
					   -- thông tin khác fullname, tên cty
	Phone varchar(11), 
	PhoneType nvarchar(20)
)
------------------------------------------------
-- tách bảng
-- thông tin bị phân mảnh, nằm nhiều nơi, phải join
-- nhập data bị vênh, xóa data coi chừng lạc trôi
-- phân mảnh phải có lúc tái nhập (JOIN), JOIN trên cột nào
-- FK xuất hiện
------------------------------------------------

CREATE TABLE PersonV4_1
(
	Nick nvarchar(30), -- không cần FK, chỉ cần JOIN vẫn OK
	Title nvarchar(30),
	Company nvarchar(40)
)

SELECT * FROM PersonV4_1
SELECT * FROM PhoneBookV4_1

DROP TABLE PersonV4_1

INSERT INTO PersonV4_1 VALUES (N'hoangnt', 'Lecturer', 'FPTU HCMC')
INSERT INTO PersonV4_1 VALUES (N'annguyen', 'Student', 'FPTU HCMC')
INSERT INTO PersonV4_1 VALUES (N'binhle', 'Student', 'FPTU HLL')

INSERT INTO PhoneBookV4_1 VALUES (N'phamduc', '098x', 'Cell')
INSERT INTO PhoneBookV4_1 VALUES (N'hoangnt', '095x', 'Cell')

INSERT INTO PhoneBookV4_1 VALUES (N'annguyen', '090x', 'Cell')
INSERT INTO PhoneBookV4_1 VALUES (N'annguyen', '091x', 'Home')

INSERT INTO PhoneBookV4_1 VALUES (N'binhle', '090x', 'Work')
INSERT INTO PhoneBookV4_1 VALUES (N'binhle', '091x', 'Cell')
INSERT INTO PhoneBookV4_1 VALUES (N'binhle', '092x', 'Cell')

-------------------------------------------------------
-- Nhược điểm: 
-- + Tính không nhất quán của loại phone, có người gõ cell, CELL,..
--													  di động, DĐ
--													  -> cả đám là 1, máy hiểu khác nhau

-- QUERY liệt kê các số di động của tất cả mọi người, toang khi WHERE
-- vì éo biết được có bao nhiêu loại chữ biểu diễn cho DI ĐỘNG
-- SQL/ Liệt kê các số di động của binh le
