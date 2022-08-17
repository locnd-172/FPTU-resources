Use Northwind

-- 1. Thành phố nào có nhiều nhân viên nhất?  
SELECT City, COUNT(*) AS [No emps] FROM Employees 
GROUP BY City 
HAVING COUNT(*) >= ALL(SELECT COUNT(*) AS [No emps] FROM Employees GROUP BY City)

-- 2. Đếm xem có bao nhiêu mặt hàng có trong kho
SELECT COUNT(*) AS [No product] 
FROM Products

-- 3. Đếm xem có bao nhiêu lượt quốc gia đã mua hàng
SELECT COUNT(ShipCountry) 
FROM Orders

-- 4. Đếm xem có bao nhiêu quốc gia đã mua hàng (mỗi quốc gia đếm một lần)
SELECT COUNT(DISTINCT ShipCountry) 
FROM Orders

-- 5. Đếm số lượng đơn hàng của mỗi quốc gia
SELECT ShipCountry, COUNT(*) 
FROM Orders 
GROUP BY ShipCountry

-- 6. Quốc gia nào có từ 10 đơn hàng trở lên
SELECT ShipCountry, COUNT(*) 
FROM Orders
GROUP BY ShipCountry 
HAVING COUNT(*) >= 10

-- 7. Đếm xem mỗi chủng loại hàng có bao nhiều mặt hàng (bia rượu có 5 sản phẩm, thủy sản 10 sản phẩm)
SELECT CategoryID, COUNT(*) AS [No category] 
FROM Products 
GROUP BY CategoryID

-- 8. Trong 3 quốc gia Anh, Pháp, Mỹ, quốc gia nào có nhiều đơn hàng nhất?
SELECT ShipCountry, COUNT(*) AS [No orders] 
FROM Orders 
WHERE ShipCountry IN ('UK', 'France', 'USA') 
GROUP BY ShipCountry 
HAVING COUNT(*) >= ALL(SELECT COUNT(*) FROM Orders GROUP BY ShipCountry)

-- 9. Quốc gia nào có nhiều đơn hàng nhất?
SELECT ShipCountry, COUNT(*) AS [No orders] 
FROM Orders 
GROUP BY ShipCountry 
HAVING COUNT(*) >= ALL(SELECT COUNT(*) FROM Orders GROUP BY ShipCountry)

-- 10. Thành phố nào có nhiều nhân viên nhất?
SELECT City, COUNT(*) AS [No emps] 
FROM Employees 
GROUP BY City
HAVING COUNT(*) >= ALL(SELECT COUNT(*) AS [No emps] FROM Employees GROUP BY City)