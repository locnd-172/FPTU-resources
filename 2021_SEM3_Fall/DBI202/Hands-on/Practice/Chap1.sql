--Note that Use Northwind; changes the default database for all subsequent queries. You can still reference the
--database by using the fully qualified syntax in the form of [Database].[Schema].[Table]:
USE Northwind;
GO
SELECT TOP 10 * FROM Customers 
ORDER BY CompanyName

SELECT TOP 10 * FROM Northwind.dbo.Customers ORDER BY CompanyNameSELECT TOP 10 * FROM Pubs.dbo.Authors
ORDER BY CitySELECT TOP 10 [Date] FROM dbo.MyLogTable
ORDER BY [Date] DESC

SELECT TOP 10 * FROM Northwind.dbo.Customers
WHERE CompanyName LIKE N'AL%'
ORDER BY CompanyName

-- JOIN
--query fields which don't exist in one single table, but in multiple tables
SELECT TOP 5 Territories.*, Region.RegionDescription
FROM Territories INNER JOIN Region ON Territories.RegionID = Region.RegionID
ORDER BY TerritoryDescription-- TABLE ALIASES-- syntax: <TableName> [as] <alias>SELECT TOP 5 t.*, r.RegionDescription
FROM Territories t INNER JOIN Region r ON t.RegionID = r.RegionID
ORDER BY TerritoryDescription