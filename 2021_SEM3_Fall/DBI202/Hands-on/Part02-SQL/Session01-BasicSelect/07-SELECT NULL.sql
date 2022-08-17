USE Northwind

SELECT * FROM Employees

SELECT * FROM Employees WHERE Region IS NULL
SELECT * FROM Employees WHERE Region IS NOT NULL
SELECT * FROM Employees WHERE NOT(Region IS NULL)

SELECT * FROM Employees WHERE Title = 'Sales Representative' AND Region IS NOT NULL
SELECT * FROM Customers WHERE Country IN ('UK', 'USA', 'France') AND Fax IS NOT NULL AND Region IS NOT NULL