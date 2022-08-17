CREATE DATABASE DBDESIGN_VNLOCATIONS

USE DBDESIGN_VNLOCATIONS

-- Thiết kế csdl lưu đc thông tin phường/xã, quận/huyện, tỉnh/tp
-- nó là 1 phần của Composite field
-- SEQ | Dose | InjDate | Vaccine (FK LK) | Lot | Số nhà | Phường - Quận - Tỉnh

CREATE TABLE Locations
(
	province nvarchar(30),
	district nvarchar(30),
	ward nvarchar(30)
)

SELECT * FROM Locations


CREATE TABLE Province
(
	PName nvarchar(30)
)

Select distinct province from Locations

-- insert 1
Insert into Province values(N'Thành phố Cần Thơ')
Insert into Province values(N'Tỉnh Vĩnh Long')

-- insert 2
Insert into Province values (N'Thành phố Cần Thơ'), (N'Tỉnh Vĩnh Long')

-- Insert 3
-- Copy/paster 

-- Insert 4 -> có 63 tỉnh - dùng kiểu sub-query
Insert into Province select distinct province from Locations

Select * from Province

Select count(*) from Locations
Select count(*) from 

-- Tạo table lookup quận/huyện

CREATE TABLE District 
(
	DName nvarchar(30)
)

Select distinct District from Locations

Insert into District select distinct District from Locations
Select * from District

DROP TABLE Province
DROP TABLE District

-- Phiên bản đẹp nhưng vẫn còn chút Châu Thành
CREATE TABLE Province 
(
	PName nvarchar(30) PRIMARY KEY
)
Insert into Province select distinct province from Locations
Select * from Province

CREATE TABLE District 
(
	DName nvarchar(30) NOT NULL,
	PName nvarchar(30) NOT NULL REFERENCES Province(PName)
	PRIMARY KEY (DName, PName)
)

Insert into District 
select distinct District, Province 
from Locations order by District

Select * from District

-- thành phần đông data nhất là ward, có 10581 dòng
-- ứng với vô số lặp lại các quận, FK
CREATE TABLE Ward
(
	WName nvarchar(30),
	DName nvarchar(30) REFERENCES District(DName)
)