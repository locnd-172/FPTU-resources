USE Northwind

SELECT * FROM Employees

-- liệt kê các nhân viên ở London
SELECT * FROM Employees WHERE City = 'London'

-- coi câu này là 1 table, cho nó 1 cái tên.
-- sau này muốn xem lại data, select cái tên
GO
CREATE VIEW VW_LondonEmployees
AS
SELECT * FROM Employees WHERE City = 'London'

GO
-- dùng VIEW, coi mày là TABLE, vì sau mày là 1 câu SELECT chống lưng
SELECT * FROM VW_LondonEmployees
