	USE [master]
GO
drop database UserManagement
go
/****** Object:  Database [UserManagement]    Script Date: 11/27/2021 13:04:02 ******/
CREATE DATABASE [UserManagement] ON  PRIMARY  
( NAME = N'UserManagement', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.DOLPHIN\MSSQL\DATA\UserManagement.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'UserManagement_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.DOLPHIN\MSSQL\DATA\UserManagement_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [UserManagement] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [UserManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [UserManagement] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [UserManagement] SET ANSI_NULLS OFF
GO
ALTER DATABASE [UserManagement] SET ANSI_PADDING OFF
GO
ALTER DATABASE [UserManagement] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [UserManagement] SET ARITHABORT OFF
GO
ALTER DATABASE [UserManagement] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [UserManagement] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [UserManagement] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [UserManagement] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [UserManagement] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [UserManagement] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [UserManagement] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [UserManagement] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [UserManagement] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [UserManagement] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [UserManagement] SET  DISABLE_BROKER
GO
ALTER DATABASE [UserManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [UserManagement] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [UserManagement] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [UserManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [UserManagement] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [UserManagement] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [UserManagement] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [UserManagement] SET  READ_WRITE
GO
ALTER DATABASE [UserManagement] SET RECOVERY SIMPLE
GO
ALTER DATABASE [UserManagement] SET  MULTI_USER
GO
ALTER DATABASE [UserManagement] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [UserManagement] SET DB_CHAINING OFF
GO
USE [UserManagement]
GO
drop table tblUsers
go

/****** Object:  Table [dbo].[tblUsers]    Script Date: 11/27/2021 13:04:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [nvarchar](50) NOT NULL,
	[fullName] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[roleID] [nvarchar](50) NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

USE [UserManagement]
GO
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'admin', N'Toi la admin', N'1', N'AD', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'Hoadnt', N'Hoa Doan', N'1', N'US', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'SE130363', N'Ngo Ha Tri Bao', N'1', N'AD', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'SE140103', N'Phuoc Ha', N'1', N'US', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'SE140119', N'Trai Nguyen', N'1', N'AD', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'SE140130', N'Tam Tran', N'1', N'AD', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'SE140201', N'PHAM HOANG TU', N'1', N'AD', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'SE140969', N'Nguyen Gia Tin', N'123', N'US', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) VALUES (N'SE150443', N'LE MINH KHOA', N'1', N'US', 1)
