USE Northwind

SELECT * FROM Customers
INSERT INTO Customers(CustomerID, CompanyName, ContactName) 
	   VALUES('ALFKI', 'FPT University', 'Thanh Nguyen Khac')
INSERT INTO Customers(CustomerID, CompanyName, ContactName) 
	   VALUES('FPTU', 'FPT University', 'Thanh Nguyen Khac')

SELECT CustomerID, CompanyName, ContactName, Country FROM Customers

SELECT * FROM Employees
SELECT EmployeeID, LastName, FirstName, Title, BirthDate, YEAR(GETDATE()) - YEAR(BirthDate) AS Age FROM Employees

SELECT EmployeeID, LastName + ' ' + FirstName AS [Full name], Title, BirthDate FROM Employees

SELECT * FROM Products

SELECT * FROM Orders

SELECT * FROM Shippers
INSERT INTO Shippers(CompanyName, Phone) VALUES ('Fedex Vietnam', '(084)90...')

SELECT * FROM Orders			--PHẦN TRÊN CỦA BILL
SELECT * FROM [Order Details]	--PHẦN TABLE KẺ GIÓNG LỀ NHỮNG MÓN ĐÃ MUA

SELECT *, UnitPrice * Quantity FROM [Order Details]
SELECT *, UnitPrice * Quantity * (1 - Discount) AS SubTotal FROM [Order Details]