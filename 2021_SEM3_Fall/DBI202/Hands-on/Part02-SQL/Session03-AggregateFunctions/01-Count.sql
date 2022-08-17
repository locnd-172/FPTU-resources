Use Northwind

SELECT * FROM Employees
SELECT COUNT(*) AS [Number of employees] FROM Employees
SELECT COUNT(*) AS [No emps in London] FROM Employees WHERE City = 'London'

SELECT COUNT(City) FROM Employees

SELECT COUNT(Region) FROM Employees

SELECT COUNT(*) FROM Employees WHERE Region IS NULL
SELECT * FROM Employees WHERE Region IS NULL

SELECT * FROM Employees
SELECT * FROM (SELECT DiSTINCT City FROM Employees) AS Cities
SELECT COUNT(*) FROM (SELECT DiSTINCT City FROM Employees) AS Cities

SELECT COUNT(*) FROM Employees
SELECT COUNT(City) FROM Employees -- 9
SELECT COUNT(DISTINCT City) FROM Employees -- 5

SELECT * FROM Employees
SELECT COUNT(City) FROM Employees GROUP BY City
-- đếm value của City, nhưng đếm theo nhóm
-- Chia City thành nhóm, rồi đếm trong nhóm
SELECT City, COUNT(City) AS [No emps] FROM Employees GROUP BY City

SELECT EmployeeID, City, Count(City) AS [No emps] FROM Employees GROUP BY City, EmployeeID

----------------------------------------

-- Thành phố có 2 nhân viên trở lên
SELECT City, COUNT(*) AS [No emps] FROM Employees GROUP BY City HAVING COUNT(*) >= 2

SELECT City, COUNT(*) AS [No emps]FROM Employees WHERE City IN ('London', 'Seattle') GROUP BY City

SELECT City, COUNT(*) AS [No emps] FROM Employees
								   WHERE City IN ('London', 'Seattle') 
								   GROUP BY City
								   HAVING COUNT(*) >= 3
--Đếm xem có bao nhiêu đơn hàng đã bán ra
SELECT * FROM Orders
SELECT COUNT(*) AS [No Orders] FROM Orders
SELECT COUNT(OrderID) AS [No Orders] FROM Orders WHERE ShipCountry = 'USA'

SELECT COUNT(*) AS [No Orders] FROM Orders WHERE ShipCountry IN ('USA', 'France', 'UK')
SELECT ShipCountry, COUNT(OrderID) AS [No Orders] FROM Orders WHERE ShipCountry IN ('USA', 'France', 'UK') GROUP BY ShipCountry

SELECT ShipCountry, COUNT(*) AS [No Orders] 
FROM Orders 
WHERE ShipCountry IN ('USA', 'France', 'UK') 
GROUP BY ShipCountry 
HAVING COUNT(*) >= ALL(SELECT COUNT(*) AS [No Orders] 
					   FROM Orders 
					   WHERE ShipCountry IN ('USA', 'France', 'UK') 
					   GROUP BY ShipCountry
					   )