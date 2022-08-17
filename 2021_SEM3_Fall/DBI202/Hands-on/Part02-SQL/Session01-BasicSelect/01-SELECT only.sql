USE Northwind --chọn để chơi với database nào đó
			  --tại 1 thời điểm chỉ chơi với 1 thùng chứa data
SELECT * FROM Customers
SELECT * FROM Employees
--------------------------------
-- LÝ THUYẾT
--------------------------------
SELECT GETDATE()

SELECT GETDATE() AS [Hôm nay là ngày]

SELECT MONTH(GETDATE()) as [Tháng]

SELECT ABS(-5) as [Abs Value]

SELECT 5 + 5 as [Sum]

SELECT N'Nguyễn Đăng Lộc' as [Full name]

SELECT N'Nguyễn ' + N'Lộc' as [Full name]

SELECT YEAR(GETDATE()) - 2002 as [Age]

--SELECT N'Nguyễn ' + N'Lộc' + CAST((YEAR(GETDATE() - 2002)) as varchar + 'years old' as [Full name]
--SELECT N'Nguyễn ' + N'Lộc' + (YEAR(GETDATE() - 2002)r + 'years old' as [Full name]

SELECT CONVERT(VARCHAR, YEAR(GETDATE()) - 2002)

SELECT CAST(YEAR(GETDATE()) - 2002 AS VARCHAR)

SELECT N'Nguyễn ' + N'Lộc' + CAST(YEAR(GETDATE()) - 2002 AS VARCHAR) + ' years old' as [Full name]

-- 7. Phép nhân 2 số
SELECT 10 * 10 AS [10 x 10 = ]