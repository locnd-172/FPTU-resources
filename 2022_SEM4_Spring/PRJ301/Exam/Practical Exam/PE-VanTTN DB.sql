create database FoodManagement
go

use FoodManagement
go

create table [User] (
	userID nvarchar(4) not null primary key,
	fullname nvarchar(100) not null,
	[password] varchar(30) not null,
	roleID bit not null,
	[status] bit not null
)

DROP TABLE [User]
insert into [User] values('U001', 'Nguyen Van An', '123456', 1, 1)
insert into [User] values('U002', 'Nguyen Thi Binh', '269853', 0, 1)
insert into [User] values('U003', 'Nguyen Chi Cuong', '759852', 0, 1)
insert into [User] values('U004', 'Nguyen Quang Dong', '956475', 0, 1)

select * from [User]

SELECT userID, fullname, [password], roleID, [status] FROM [User]
WHERE fullname='Nguyen Van An' and password='123456' COLLATE Latin1_General_CS_AS

create table Food (
	foodID nvarchar(4) not null primary key,
	foodName nvarchar(100) not null,
	cookingTime int not null,
	status bit not null
)

insert into Food values('F001', 'Thit ga', 30, 1)
insert into Food values('F002', 'Xuong bo', 45, 1)
insert into Food values('F003', 'Dau phu', 15, 1)
insert into Food values('F004', 'Thit de', 60, 0)

select * from Food

DROP TABLE Food