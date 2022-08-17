Use Northwind
-- Tính toán theo chiều dọc

SELECT MIN(BirthDate) FROM Employees

-- Ai sinh năm bé nhất, ai lớn tuổi nhất, in ra info
SELECT * FROM Employees WHERE BirthDate = (SELECT MIN(BirthDate) FROM Employees)

SELECT * FROM Employees WHERE BirthDate <= ALL(SELECT MIN(BirthDate) FROM Employees)
SELECT * FROM Employees WHERE BirthDate <= ALL(SELECT BirthDate FROM Employees)

SELECT * FROM Orders ORDER BY Freight DESC
SELECT MAX(Freight) FROM Orders
SELECT * FROM Orders WHERE Freight = (SELECT MAX(Freight) FROM Orders)
SELECT * FROM Orders WHERE Freight >= ALL(SELECT Freight FROM Orders)

SELECT SUM(Freight) AS [Freight in total] FROM Orders

SELECT AVG(Freight) AS [Average freight] FROM Orders

SELECT * FROM Orders WHERE Freight >= (SELECT AVG(Freight) AS [Average freight] FROM Orders)

SELECT COUNT(*) AS [No greater than average freight] FROM Orders WHERE Freight >= (SELECT AVG(Freight) AS [Average freight] FROM Orders)

SELECT COUNT(*) AS [No greater than average freight] 
				FROM (
					SELECT * FROM Orders 
					WHERE Freight >= (
						SELECT AVG(Freight) 
							AS [Average freight] 
							FROM Orders
						)
					) AS [AVG]


SELECT COUNT(*) AS [No greater than average freight] FROM (SELECT * FROM Orders WHERE Freight >= (SELECT AVG(Freight) AS [Average freight] FROM Orders)) AS [AVG]

SELECT * FROM Employees
SELECT Region, COUNT(*) FROM Employees GROUP BY Region
SELECT Region, COUNT(Region) FROM Employees GROUP BY Region
SELECT * FROM Orders
SELECT ShipCountry, COUNT(*) FROM Orders GROUP BY ShipCountry

SELECT ShipCountry, COUNT(*) AS [No Orders]
FROM Orders 
GROUP BY ShipCountry 
HAVING COUNT(*) >= 50

SELECT MAX([No Orders]) FROM 
(SELECT ShipCountry, COUNT(*) AS [No Orders]
FROM Orders 
GROUP BY ShipCountry) AS CTRY

SELECT * FROM Orders WHERE CustomerID = 'VINET'

SELECT CustomerID, COUNT(*) 
FROM Orders
WHERE CustomerID = 'VINET'
GROUP BY CustomerID 

SELECT CustomerID, COUNT(*) 
FROM Orders
GROUP BY CustomerID 
HAVING CustomerID = 'VINET'
