Use Northwind

SELECT * FROM Employees
SELECT FirstName FROM Employees WHERE EmployeeID = 1 -- return 1 cell
SELECT FirstName FROM Employees  -- phép chiếu/1 cột/1 tập giá trị

SELECT * FROM Employees WHERE City = 'London'

SELECT * FROM Employees WHERE City = (SELECT City FROM Employees WHERE FirstName = 'Robert') AND FirstName <> 'Robert'

SELECT * FROM Orders ORDER BY Freight DESC

SELECT * FROM Orders WHERE Freight > (SELECT Freight FROM Orders WHERE OrderID = 10555) AND OrderID != 10555

