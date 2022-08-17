USE Northwind

SELECT * FROM Customers
SELECT * FROM Customers WHERE Country = 'Italy'
SELECT * FROM Customers WHERE Country = 'USA' OR Country = 'Italy'
SELECT * FROM Customers WHERE Country = 'USA' OR Country = 'Italy' ORDER BY Country
SELECT * FROM Customers WHERE Country = 'Germany' AND City = 'Berlin' 

SELECT * FROM Employees WHERE YEAR(BirthDate) >= 1960
SELECT YEAR(GETDATE()) - YEAR(BirthDate) as AGE,* FROM Employees WHERE YEAR(GETDATE()) - YEAR(BirthDate) >= 60
SELECT * FROM Employees WHERE City = 'London'
SELECT * FROM Employees WHERE NOT(City = 'London')

SELECT * FROM Employees WHERE EmployeeID = 1
SELECT DISTINCT EmployeeID, City FROM Employees WHERE EmployeeID = 1 ORDER BY EmployeeID

SELECT * FROM Orders
SELECT * FROM Orders ORDER BY Freight DESC
SELECT * FROM Orders WHERE Freight >= 500 ORDER BY Freight DESC
SELECT * FROM Orders WHERE 100 <= Freight AND Freight <= 500 ORDER BY Freight DESC
SELECT * FROM Orders WHERE Freight >= 100 AND Freight <= 500 AND ShipVia = 1
SELECT * FROM Orders WHERE Freight >= 100 AND Freight <= 500 AND ShipVia = 1 ORDER BY Freight DESC

SELECT * FROM Orders WHERE Freight >= 100 AND Freight <= 500 AND ShipVia = 1 AND ShipCity <> 'London' ORDER BY Freight DESC
SELECT * FROM Orders WHERE Freight >= 100 AND Freight <= 500 AND ShipVia = 1 AND NOT(ShipCity = 'London') ORDER BY Freight DESC

SELECT * FROM Customers WHERE (Country = 'USA' OR Country = 'Mexico')

SELECT * FROM Customers WHERE NOT(Country = 'USA' OR Country = 'Mexico')
SELECT * FROM Customers WHERE Country <> 'USA' AND Country <> 'Mexico'

SELECT * FROM Employees WHERE YEAR(BirthDate) >= 1960 AND YEAR(BirthDate) <= 1970 ORDER BY BirthDate