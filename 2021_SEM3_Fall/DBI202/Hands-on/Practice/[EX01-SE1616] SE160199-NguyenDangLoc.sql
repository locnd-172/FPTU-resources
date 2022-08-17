Use Northwind

-- 1. Liệt kê danh sách các công ty vận chuyển hàng 
SELECT * FROM Shippers

-- 2. Liệt kê danh sách các đơn hàng được vận chuyển bởi công ty giao vận có mã số là 1 
SELECT * FROM Orders WHERE ShipVia = (SELECT ShipperID FROM Shippers WHERE ShipperID = 1)

-- 3. Liệt kê danh sách các đơn hàng được vận chuyển bởi công ty giao vận có tên Seedy Express
SELECT * FROM Orders WHERE ShipVia = (SELECT ShipperID FROM Shippers WHERE CompanyName = 'Speedy Express')

-- 4. Liệt kê danh sách các đơn hàng được vận chuyển bởi công ty giao vận có tên Seedy Express và trọng lượng từ 50 - 100 pbs
SELECT * FROM Orders WHERE ShipVia = (SELECT ShipperID FROM Shippers WHERE CompanyName = 'Speedy Express') AND Freight BETWEEN 50 AND 100

-- 5. Liệt kê các mặt hàng cùng chủng loại với mặt hàng Filo Mix
SELECT * FROM Products WHERE CategoryID = (SELECT CategoryID FROM Products WHERE ProductName = 'Filo Mix') AND ProductName <> 'Filo Mix'

-- 6. Liệt kê các nhân viên trẻ tuổi hơn nhân viên Janet
SELECT * FROM Employees WHERE YEAR(BirthDate) > (SELECT YEAR(BirthDate) FROM Employees WHERE FirstName = 'Janet')
			