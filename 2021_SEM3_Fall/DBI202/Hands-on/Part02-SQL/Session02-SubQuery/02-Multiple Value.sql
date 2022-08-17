Use Northwind

SELECT * FROM Categories

SELECT * FROM Products WHERE CategoryID IN (1, 6, 8)

SELECT * FROM Products WHERE CategoryID IN (SELECT CategoryID FROM Categories WHERE CategoryName IN ('Beverages', 'Meat/Poultry', 'Seafood'))

SELECT * FROM Orders WHERE EmployeeID IN (SELECT EmployeeID FROM Employees WHERE City = 'London')

-- Tạo 1 table có 1 cột tên là Numbr, chỉ chứa 1 đống dòng các số nguyên

CREATE TABLE NUM
(
	Numbr int 
)

SELECT * FROM NUM

INSERT INTO NUM VALUES (1)
INSERT INTO NUM VALUES (1)
INSERT INTO NUM VALUES (2)
INSERT INTO NUM VALUES (9)
INSERT INTO NUM VALUES (5)
INSERT INTO NUM VALUES (100)
INSERT INTO NUM VALUES (101)

-- 1. In ra những số > 5
SELECT * FROM NUM WHERE Numbr > 5

-- 2. In ra số lớn nhất
SELECT * FROM NUM WHERE Numbr >= ALL(SELECT * FROM NUM)

-- 3. In ra số lớn nhất
SELECT * FROM NUM WHERE Numbr <= ALL(SELECT * FROM NUM)

-- 4. Nhân viên nào lớn tuổi nhất
SELECT * FROM Employees WHERE BirthDate <= ALL(SELECT BirthDate FROM Employees

-- 5. Đơn hàng nào có trọng lượng nặng nhất
SELECT * FROM Orders WHERE Freight >= ALL(SELECT Freight FROM Orders)