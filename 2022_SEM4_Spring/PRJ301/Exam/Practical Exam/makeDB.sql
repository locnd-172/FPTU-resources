Create database PlantShopPE
GO
use PlantShopPE
GO
Create table Categories(
      CateID int identity(1,1) primary key,
      CateName varchar(30)
)
GO
Create table Plants(
      PID int identity(1,1) primary key,
      PName varchar(30),
      price int check(price>=0),
      imgPath varchar(50),
      description text,
      status int,               --1:active, 0:inactive
      CateID int foreign key references Categories(CateID)
)
GO