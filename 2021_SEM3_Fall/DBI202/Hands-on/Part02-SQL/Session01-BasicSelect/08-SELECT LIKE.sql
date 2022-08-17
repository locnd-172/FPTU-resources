USE Northwind

SELECT * FROM Employees
SELECT * FROM Employees WHERE FirstName LIKE 'A%'

SELECT EmployeeID, FirstName + ' ' + LastName AS FullName, Title FROM Employees WHERE FirstName LIKE 'A%'
SELECT EmployeeID, CONCAT(FirstName, ' ', LastName) AS FullName, Title FROM Employees WHERE FirstName LIKE 'A%'

SELECT EmployeeID, CONCAT(FirstName, ' ', LastName) AS FullName, Title FROM Employees WHERE FirstName LIKE '%A'

-- homework
SELECT * FROM Employees WHERE LEN(FirstName) = 4
SELECT * FROM Employees WHERE FirstName LIKE '____'
SELECT * FROM Products WHERE ProductName LIKE 'ch%'
SELECT * FROM Products WHERE ProductName LIKE '%ch%'


SELECT * FROM Products WHERE ProductName LIKE '_____' -- 3
SELECT * FROM Products WHERE ProductName LIKE '% _____' OR ProductName LIKE '_____'