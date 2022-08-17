-- Thí nghiệm các loại ràng buộc - CONSTRAINTS - Quy tắc gài trên data

USE DBDESIGN_ONETABLE

/*
CREATE TABLE Registration
(
	SEQ int PRIMARY KEY, -- phải tự nhập số thứ tự
	FirstName nvarchar(10),
	LastName nvarchar(30), 
	Email varchar(50), -- cấm trùng
	Phone varchar(11),
	RegDate datetime DEFAULT GETDATE()
)*/

CREATE TABLE Registration
(
	SEQ int PRIMARY KEY IDENTITY, -- mặc định đi từ 1, nhảy ++ cho cái sau
								  -- ghi rõ IDENTITY(1, 1)
								  -- IDENTITY)1, 5) --> 1, 6, 11, 16,...
	FirstName nvarchar(10),
	LastName nvarchar(30), 
	Email varchar(50), 
	Phone varchar(11),
	RegDate datetime DEFAULT GETDATE()
)
-- đăng kí event
INSERT INTO Registration VALUES (N'An', N'Nguyễn', 'an@...', '090x') 
-- lỗi: map các cột k rõ

INSERT INTO Registration VALUES (N'An', N'Nguyễn', 'an@...', '090x', null)

INSERT INTO Registration(FirstName, LastName, Email, Phone)
VALUES (N'Bình', N'Lê', 'binh@...', '091x')

SELECT * FROM Registration
---------------------------------
---------------------------------
