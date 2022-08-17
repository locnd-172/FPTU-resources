USE Northwind

SELECT * FROM Employees
SELECT * FROM Employees ORDER BY BirthDate ASC
SELECT * FROM Employees ORDER BY BirthDate --- mặc định là ascending
SELECT * FROM Employees ORDER BY BirthDate DESC

SELECT * FROM [Order Details]
SELECT *, UnitPrice * Quantity * (1 - Discount) AS SubTotal FROM [Order Details]
SELECT *, UnitPrice * Quantity * (1 - Discount) AS SubTotal FROM [Order Details] ORDER BY SubTotal DESC

SELECT EmployeeID, FirstName, BirthDate, YEAR(GETDATE()) - YEAR(BirthDate) AS Age FROM Employees ORDER BY Age DESC