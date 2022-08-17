-- PRACTICAL EXAM - DBI202 
-- Nguyễn Đăng Lộc (MSSV: SE160199, LỚP: SE1616) 

Use FUH_COMPANY

--1. Display the employee list that belongs to the department of 'Phòng Phần mềm trong nước', and sort the result by EmpSSN 
SELECT * FROM tblEmployee e 
WHERE e.depNum IN (
	SELECT d.depNum FROM tblDepartment d 
	WHERE d.depName = N'Phòng Phần mềm trong nước'
)
ORDER BY e.empSSN

--2. Display the total number of employees of each department.
--Output: DepNum, DepName, NoEmployees
SELECT d.depNum, d.depName, COUNT(e.empSSN) AS [NoEmployees]
FROM tblEmployee e JOIN tblDepartment d ON e.depNum = d.depNum
GROUP BY d.depNum, d.depName

--3. Which department has the most employees?
--Output: DepNum, DepName, NoEmployees
SELECT d.depNum, d.depName, COUNT(e.empSSN) AS [NoEmployees]
FROM tblEmployee e JOIN tblDepartment d ON e.depNum = d.depNum
GROUP BY d.depNum, d.depName
HAVING COUNT(e.empSSN) >= ALL(
	SELECT COUNT(e.empSSN) 
	FROM tblEmployee e JOIN tblDepartment d ON e.depNum = d.depNum
	GROUP BY d.depNum
)

--4. Display the total number of projects of each department.
--Output: DepNum, DepName, NoProjects
SELECT d.depNum, d.depName, COUNT(p.proNum) AS [NoProjects]
FROM tblDepartment d LEFT JOIN tblProject p ON d.depNum = p.depNum
GROUP BY d.depNum, d.depName

--5. Display the project information regarding to all of employees that belong to the department of
--'Phòng Phần mềm trong nước' and 'Phòng Phần mềm nước ngoài', and sort the result by ProjectNumber
--Output: EmpSSN, EmpName, ProjectNumber, WorkHours, DepNum, DepName
SELECT w.empSSN, e.empName, w.proNum, w.workHours, d.depNum, d.depName
FROM tblWorksOn w JOIN tblEmployee e ON w.empSSN = e.empSSN
JOIN tblDepartment d ON e.depNum = d.depNum
WHERE d.depName IN (N'Phòng Phần mềm trong nước', N'Phòng Phần mềm nước ngoài')
ORDER BY w.proNum

--6. How many employees are working on the 'Project A'
--Output: ProjectNum, ProjectName, NoEmployees 
SELECT w.proNum, p.proName, COUNT(w.empSSN) AS [NoEmployees]
FROM tblWorksOn w JOIN tblProject p ON w.proNum = p.proNum
WHERE w.proNum IN (SELECT proNum FROM tblProject WHERE proName = N'ProjectA')
GROUP BY w.proNum, p.proName