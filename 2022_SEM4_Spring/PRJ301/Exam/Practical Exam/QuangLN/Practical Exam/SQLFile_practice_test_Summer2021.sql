create database TestFinal
go

use TestFinal
go

create table Student (
	RollNo nvarchar(7) not null primary key,
	Major nvarchar(20) not null,
	FullName nvarchar(100) not null,
	City nvarchar(100),
	Male bit not null, 
)

insert into Student values('SB03901','Business','Hoang Lan Phuong','Phu Tho',0)
insert into Student values('SE03456','Engineering','Nguyen Thanh Tam','Hanoi',1)
select * from Student