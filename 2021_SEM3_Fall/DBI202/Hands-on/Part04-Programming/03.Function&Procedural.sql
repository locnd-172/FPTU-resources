CREATE DATABASE DBDESIGN_PROGRAMMING

USE DBDESIGN_PROGRAMMING
-- Viết hàm in ra câu chào

-- CREATE PROCEDURE PR_Hello() {...code...}
GO
CREATE PROCEDURE PR_Hello
AS
	PRINT N'XIN CHÀO - Welcome to my own first procedure!'

GO
PR_Hello
EXECUTE PR_Hello
EXEC PR_Hello

GO
CREATE PROC PR_Hello_2
AS
	PRINT N'XIN CHÀO - Welcome to my own first procedure!'

EXEC PR_Hello_2

DROP PROC PR_Hello_2

GO
DROP FUNCTION FN_Hello

CREATE FUNCTION FN_Hello() 
RETURNS nvarchar(50)
AS
BEGIN
	RETURN N'XIN CHÀO - Welcome to my own first function!'
END

GO
SELECT dbo.FN_Hello() -- bắt buộc phải có dbo.[tên hàm]



-- Viết hàm - PROC đổi từ độ C -> F F = C * 1.8 + 32
-- tham số/đầu vào/argument
GO
DROP PROC PR_C2F

GO
CREATE PROC PR_C2F
@CDegree float
AS
BEGIN
	DECLARE @FDegree float = @CDegree * 1.8 + 32
	-- PRINT @FDegree
	PRINT N'37 độ C là ' + CAST(@FDegree AS nvarchar) + N' độ F'
END

-- vì có tham số, cần truyền vào
EXEC PR_C2F @CDegree = 37
EXEC PR_C2F 37


GO
-----------------------------------
CREATE FUNCTION FN_C2F(@CDegree float)
RETURNS float
AS
BEGIN 
	DECLARE @FDegree float = @CDegree * 1.8 + 32
	RETURN @FDegree
END

Go
PRINT dbo.FN_C2F(37)
PRINT N'37 độ C là ' + CAST(dbo.FN_C2F(37) AS nvarchar) + N' độ F'

GO
-- Viết 1 procedure in ra danh sách các nhân viên quê ở đâu đó, 
-- đâu đó đưa vào PROC
-- VIEW: In ra ai ở London
-- VIEW: In ra ai ở Kirdland
-- mỗi view là 1 select và là 1 table để Reuse
-- Procedure in ra kết quả như view, ko reuse lại (chỉ in ra) 
-- nhưng lại nhận được tham số
USE Northwind

GO
CREATE PROC PR_EmployeeListByCity
@city nvarchar(30)
AS
	SELECT * FROM Employees WHERE City = @city

GO
EXEC PR_EmployeeListByCity 'London'
EXEC PR_EmployeeListByCity 'Redmond'

-- Ứng dụng thêm của Proc
-- Viêt PROC insert data
USE DBDESIGN_PROGRAMMING
CREATE TABLE [Event]
(
	ID int IDENTITY PRIMARY KEY,
	Name nvarchar(30) not null
)

INSERT INTO [Event] VALUES(N'Lời nói dối chân thật')
SELECT * FROM Event

GO
CREATE PROC PR_InsertEvent
@name nvarchar(30)
AS
	INSERT INTO [Event] VALUES(@name)

GO
EXEC PR_InsertEvent @name = N'Bí quyết dùng source ở FE'
EXEC PR_InsertEvent N'Hồ Sen chờ ai'

