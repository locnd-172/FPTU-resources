CREATE DATABASE DB_ALOSHOP

USE DB_ALOSHOP

CREATE TABLE Customers (
  CustomerID   char(5) NOT NULL, 
  CustomerName nvarchar(40) NOT NULL, 
  Phone        char(11) UNIQUE NOT NULL, 
  Address      nvarchar(100) NOT NULL, 
  PRIMARY KEY (CustomerID)
);

CREATE TABLE Transactions (
  OrderID     int IDENTITY NOT NULL, 
  OrderDate   datetime NOT NULL, 
  ShipAddress nvarchar(100) NOT NULL, 
  ShippedDate datetime NOT NULL, 
  TotalAmount float(10) NOT NULL, 
  CustomerID  char(5) NOT NULL, 
  PRIMARY KEY (OrderID)
);

ALTER TABLE Transactions ADD CONSTRAINT FKTransactio403795 
FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID) 
ON UPDATE Cascade ON DELETE Cascade;

ALTER TABLE Transactions DROP CONSTRAINT FKTransactio403795;

DROP TABLE Customers;
DROP TABLE Transactions;

SELECT CustomerID, CustomerName, Phone, [Address] FROM Customers

SELECT CustomerID, OrderID, OrderDate, ShipAddress, ShippedDate, TotalAmount  FROM Transactions
ORDER BY CustomerID

INSERT INTO Customers VALUES('C0001', N'Nguyễn Văn An', '09876543210', N'Quận 9, TP.HCM')
INSERT INTO Customers VALUES('C0002', N'Lê Văn Bình', '09876543211', N'Quận 2, TP.HCM')
INSERT INTO Customers VALUES('C0003', N'Phạm Văn Cường', '09876543210', N'Quận 1, TP.HCM')
INSERT INTO Customers VALUES('C0004', N'Vũ Văn Đức', '09876543211', N'Quận 9, TP.HCM')

INSERT INTO Transactions VALUES('2021-12-10', N'Quận 9, TP.HCM', '2021-12-13', 200000, 'C0001')
INSERT INTO Transactions VALUES('2021-11-29', N'Quận 2, TP.HCM', '2021-12-05', 1011111, 'C0002')

INSERT INTO Transactions VALUES('2021-09-25', N'Quận 2, TP.HCM', '2021-09-26', 98500, 'C0006') -- lỗi

INSERT INTO Transactions VALUES('2021-11-09', N'Quận 9, TP.HCM', '2021-11-10', 555000, 'C0004')
INSERT INTO Transactions VALUES('2021-12-15', N'Quận 9, TP.HCM', '2021-12-17', 90000.5, 'C0001')

UPDATE Customers SET [Address] = N'Quận Tân Bình, TP.HCM' WHERE CustomerID = 'C0003'
DELETE FROM Customers WHERE CustomerID = 'C0004'
