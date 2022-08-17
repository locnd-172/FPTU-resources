
CREATE TABLE StudentV7
(
	StudentID char(8) NOT NULL, 
	LastName nvarchar(40) NOT NULL,
	FirstName nvarchar(10) NOT NULL,
	DOB datetime NULL,
	Address nvarchar(50) NULL, 
	--PRIMARY KEY(StudentID)
	--CONSTRAINT PK_STUDENTV7 PRIMARY KEY (StudentID)
)