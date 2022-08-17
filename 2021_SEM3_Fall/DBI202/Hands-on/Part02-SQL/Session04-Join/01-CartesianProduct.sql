CREATE DATABASE Cartesian

CREATE TABLE EnDict -- DDL một nhánh của SQL - DATA DEFINITION LANGUAGE
(
	Nmbr int,
	EnDesc varchar(30)
)

DROP TABLE EnDict

SELECT * FROM EnDict

INSERT INTO EnDict VALUES(1, 'One')
INSERT INTO EnDict VALUES(2, 'Two')
INSERT INTO EnDict VALUES(3, 'Three')
-------------------------------------
-- Thêm  cho OUTER JOIN
INSERT INTO EnDict VALUES(4, 'Four')

CREATE TABLE VnDict 
(
	Nmbr int,
	VnDesc nvarchar(30)
)

DROP TABLE VnDict
SELECT * FROM VnDict

INSERT INTO VnDict VALUES(1, N'Một')
INSERT INTO VnDict VALUES(2, N'Hai')
INSERT INTO VnDict VALUES(3, N'Ba')
INSERT INTO VnDict VALUES(5, N'Năm')

SELECT * FROM EnDict
SELECT * FROM VnDict
-- bôi đen cả 2 lệnh trên cùng chạy -> không phải là JOIN, 
-- cho 2 tập kết quả riêng biệt

SELECT * FROM VnDict, EnDict
SELECT * FROM VnDict, EnDict ORDER BY Nmbr
SELECT * FROM VnDict, EnDict ORDER BY VnDesc

SELECT * FROM VnDict, EnDict ORDER BY VnDict.Nmbr

SELECT * FROM VnDict vn, EnDict en ORDER BY vi.Nmbr

SELECT vn.Nmbr, vn.VnDesc, en.EnDesc FROM VnDict vn, EnDict en ORDER BY vn.Nmbr

SELECT vn.*, en.EnDesc FROM VnDict vn, EnDict en ORDER BY vn.Nmbr

SELECT vn.*, en.EnDesc FROM VnDict vn CROSS JOIN EnDict en ORDER BY vn.Nmbr

SELECT * FROM VnDict vn, EnDict en WHERE vn.Nmbr = en.Nmbr

Use Cartesian