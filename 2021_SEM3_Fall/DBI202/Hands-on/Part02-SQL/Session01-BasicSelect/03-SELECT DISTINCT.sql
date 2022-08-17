USE Northwind

SELECT * FROM Employees
--9 người nhưng chỉ có 4 title
SELECT TitleOfCourtesy FROM Employees --9 danh xưng
SELECT DISTINCT EmployeeID, TitleOfCourtesy FROM Employees --4 danh xưng

SELECT * FROM Customers

SELECT DISTINCT Country FROM Customers