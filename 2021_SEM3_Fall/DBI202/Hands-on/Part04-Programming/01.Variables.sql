-- Kiểu dữ liệu - data type là cách ta lưu loại dữ liệu nào đó
-- số, chữ, câu, ngày tháng, tiền ($...)
-- 1 NNLT sẽ hỗ trợ nhiều loại dữ liệu khác nhau - data type

-- Khai báo biến
GO
DECLARE @msg1 AS nvarchar(30)

DECLARE @msg nvarchar(30) = N'Xin chào - Welcome to T-SQL'

-- in biến: có 2 lệnh
PRINT @msg  -- in ra kết quả bên cửa sổ console
SELECT @msg -- in ra kết quả dưới dạng table

DECLARE @yob int -- = 2003
-- Gán giá trị cho biến
SET @yob = 2003
SELECT @yob = 2004  -- seclect dùng 2 cách: gán giá trị cho biến, 
					-- in giá trị của biến
PRINT @YOB

IF @YOB > 2003
	BEGIN 
		PRINT 'HEY, BOY/GIRL'
		PRINT 'HELLO GEN Z'
	END
ELSE 
	PRINT 'HELLO, LADY & GENTLEMENT'

GO

