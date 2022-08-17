﻿USE Northwind

SELECT * FROM Employees WHERE YEAR(BirthDate) BETWEEN 1960 AND 1970 ORDER BY BirthDate

SELECT * FROM Orders WHERE Freight BETWEEN 100 AND 500

SELECT * FROM Orders WHERE ShipCountry IN ('UK', 'USA', 'France')
SELECT * FROM Orders WHERE ShipCountry NOT IN ('UK', 'USA', 'France')

SELECT * FROM Orders WHERE YEAR(OrderDate) = 1996 AND MONTH(OrderDate) NOT BETWEEN 6 AND 9

SELECT * FROM Orders WHERE Freight BETWEEN 100 AND 110 ORDER BY Freight DESC