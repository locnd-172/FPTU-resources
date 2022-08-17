Use Northwind

--1. Các nhà cung cấp đến từ Mĩ cung cấp những mặt hàng nào? -- 12
SELECT ProductName FROM Products WHERE SupplierID IN (SELECT SupplierID FROM Suppliers WHERE (Country = 'USA'))

--2. Các nhà cung cấp đến từ Mĩ cung cấp những nhóm hàng nào? -- 4
SELECT DISTINCT CategoryID FROM Products WHERE SupplierID IN (SELECT SupplierID FROM Suppliers WHERE (Country = 'USA'))

--3. Các đơn hàng vận chuyển tới thành phố Sao Paulo được vận chuyển bởi những hãng nào? -- 3
-- → Các công ty nào đã vận chuyển hàng tới Sao Paulo?
SELECT * FROM Shippers WHERE ShipperID IN (SELECT ShipVia FROM Orders WHERE ShipCity = 'Sao Paulo')

--4. Khách hàng đến từ thành phố Berlin, London, Madrid có những đơn hàng nào? -- 60
-- → Liệt kê các đơn hàng của khách hàng đến từ Berlin, London, Madrid
SELECT * FROM Orders WHERE CustomerID IN (SELECT CustomerID FROM Customers WHERE City IN ('Berlin', 'London', 'Madrid'))

--5. Nhân viên nào lớn tuổi nhất -- Peacock Margaret
SELECT * FROM Employees WHERE YEAR(BirthDate) <= ALL(SELECT YEAR(BirthDate) FROM Employees) 

--6. Đơn hàng nào có trọng lượng nặng nhất -- Quick - 1007.64
SELECT * FROM Orders WHERE Freight >= ALL(SELECT Freight FROM Orders)
