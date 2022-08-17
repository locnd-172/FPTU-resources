USE DBDESIGN_PROGRAMMING
CREATE TABLE [Event]
(
	ID int IDENTITY PRIMARY KEY,
	Name nvarchar(30) not null
)

SELECT * FROM Event

GO
CREATE TRIGGER TR_CheckInsertionOnEvent ON Event
FOR INSERT
AS
BEGIN
	PRINT 'You have just inserted a record in Event table'
END

GO
EXEC PR_InsertEvent N'Blockchain & Game' -- kiểm tra xem có thông báo 1 câu khi insert Event

-- KHÔNG CHO INSERT VÀO TABLE EVENT
GO
CREATE TRIGGER TR_ForbidInsertionOnEvent ON Event
FOR INSERT
AS
BEGIN
	PRINT 'You have just inserted a record in Event table'
	ROLLBACK -- cấm, undo những gì đã xảy ra khi insert 
END

GO
EXEC PR_InsertEvent N'Thổi nến và Tài chính 4.0' 

DROP TRIGGER TR_ForbidInsertionOnEvent
DROP TRIGGER TR_CheckInsertionOnEvent
DROP TRIGGER TR_CheckInsertionLimitationEvent
-- Kiếm tra không cho insert quá 5 records/events 
-- SQL có thể đếm, quyết định đếm xong làm gì tiếp -> lập trình!!!
-- -> TRIGGER chặn không cho vào

GO
CREATE TRIGGER TR_CheckInsertionLimitationEvent_2 ON Event
FOR INSERT 
AS
BEGIN
	SELECT * FROM INSERTED 
	ROLLBACK
END

-- Lưu ý khi chơi với TRIGGER
-- DB Engine sẽ tự tạo ra 2 TABLE "Ảo" lúc runtime liên quan đến TRIGGER

-- câu lệnh Insert vào TABLE -> DB ENGINE tạo ra 1 table ảo tên là Inserted 
-- Chứa records vừa đưa vào từ câu lệnh insert

-- câu lệnh Delete from TABLE -> DB ENGINE tạo ra 1 table ảo tên là Deleted
-- Chứa những dòng vừa bị xóa

-- câu lệnh Update Event Set Name = 'Đổi tên sự kiện' -> DB ENGINE tạo ra 2 table ảo
-- inserted chưa value mới đưa vào
-- deleted chứa value cũ bị ghi đè
GO
EXEC PR_InsertEvent N'Thổi nến và Tài chính 4.0' 

GO
CREATE TRIGGER TR_CheckInsertionLimitationEvent ON Event
FOR INSERT 
AS
BEGIN
	-- kiểm tra xem trong table Event ko cho vượt quá 5 sự kiện
	-- if số sự kiện > 5 thì ROLLBACK!
	-- phải đếm số sự kiện đang có
	-- lấy được số sk ra để if, tức là khai báo biến
	-- lệnh COUNT(*) trong SELECT trả về 1 TABLE, k trả về 1 biến
	DECLARE @noEvents int
	SELECT @noEvents = COUNT(*) FROM Event
	--PRINT @noEvents
	IF @noEvents > 5
	BEGIN
		PRINT N'Too much events. No more than 5 events!'
		ROLLBACK
	END
	--SELECT * FROM INSERTED 
	
END

-- Liên quan đến TABLE, có 2 loại TRIGGER cơ bản
-- chặn trước khi dữ liệu đưa vào TABLE, lúc này dữ liệu mới vào inserted (BEFORE) 
-- chặn sau khi đã vào inserted và đồng thời vào luôn TABLE rồi (AFTER)

SELECT * FROM Event
EXEC PR_InsertEvent N'Thổi nến và Tài chính 4.0' 