Use FUH_COMPANY

--1. Cho biết ai đang quản lý phòng ban có tên: Phòng Nghiên cứu và phát triển. Thông tin yêu cầu:
--mã số,họ tên nhân viên, mã số phòng ban, tên phòng ban
select d.mgrSSN, e.empName, d.depNum, d.depName 
from  tblDepartment d join tblEmployee e 
on d.depNum = e.depNum
where e.empSSN = d.mgrSSN and d.depName = N'Phòng Nghiên cứu và phát triển'

--2. Cho phòng ban có tên: Phòng Nghiên cứu và phát triển hiện đang quản lý dự án nào. Thông tin
--yêu cầu: mã số dự án, tên dự án, tên phòng ban quản lý
select p.proNum, p.proName, d.depName from tblDepartment d join tblProject p 
on d.depNum = p.depNum 
where d.depName = N'Phòng Nghiên cứu và phát triển'

--3. Cho biết dự án có tên ProjectB hiện đang được quản lý bởi phòng ban nào. Thông tin yêu cầu:
--mã số dụ án, tên dự án, tên phòng ban quản lý
select p.proNum, p.proName, d.depName from tblProject p join tblDepartment d 
on p.depNum = d.depNum 
where p.proName = N'ProjectB'

--4. Cho biết những nhân viên nào đang bị giám sát bởi nhân viên có tên Mai Duy An. Thông tin yêu
--cầu: mã số nhân viên, họ tên nhân viên
select e.empSSN, e.empName from tblEmployee e
where e.supervisorSSN = (select empSSN from tblEmployee where empName = N'Mai Duy An')

--5. Cho biết ai hiện đang giám sát những nhân viên có tên Mai Duy An. Thông tin yêu cầu: mã số
--nhân viên, họ tên nhân viên giám sát.
select e.empSSN, e.empName from tblEmployee e 
where e.empSSN in (select supervisorSSN from tblEmployee where empName = N'Mai Duy An')

--6. Cho biết dự án có tên ProjectA hiện đang làm việc ở đâu. 
--Thông tin yêu cầu: mã số, tên vị trí làm việc.
select l.locNum, l.locName from tblProject p join tblLocation l 
on p.locNum = l.locNum 
where p.proName = N'ProjectA'

select p.proName, l.locNum, l.locName from tblProject p join tblLocation l 
on p.locNum = l.locNum 
where p.proName = N'ProjectA'

--7. Cho biết vị trí làm việc có tên Tp. HCM hiện đang là chỗ làm việc của những dự án nào. Thông tin
--yêu cầu: mã số, tên dự án
select p.proNum, p.proName from tblProject p join tblLocation l 
on p.locNum = l.locNum 
where l.locName = N'TP Hồ Chí Minh'

select p.proNum, p.proName, l.locName from tblProject p join tblLocation l 
on p.locNum = l.locNum 
where l.locName = N'TP Hồ Chí Minh'

--8. Cho biết những người phụ thuộc trên 18 tuổi.
--Thông tin yêu cầu: tên, ngày tháng năm sinh của người phụ thuộc, tên nhân viên phụ thuộc vào.
select d.depName, d.depBirthdate, e.empName from tblDependent d join tblEmployee e 
on d.empSSN = e.empSSN 
where YEAR(GETDATE()) - YEAR(d.depBirthdate) > 18

--9. Cho biết những người phụ thuộc là nam giới. 
-- Thông tin yêu cầu: tên, ngày tháng năm sinh của người phụ thuộc, tên nhân viên phụ thuộc vào
select d.depName, d.depBirthdate, e.empName from tblDependent d join tblEmployee e 
on d.empSSN = e.empSSN 
where d.depSex = 'M'

--10. Cho biết những nơi làm việc của phòng ban có tên : Phòng Nghiên cứu và phát triển. 
--Thông tin yêu cầu: mã phòng ban, tên phòng ban, tên nơi làm việc
select d.depNum, d.depName, l.locName
from tblDepLocation dl 
join tblDepartment d on dl.depNum = d.depNum
join tblLocation l on dl.locNum = l.locNum
where d.depName = N'Phòng Nghiên cứu và phát triển'

--11. Cho biết các dự án làm việc tại Tp. HCM.
--Thông tin yêu cầu: mã dự án, tên dự án, tên phòng ban chịu trách nhiệm dự án.
select p.proNum, p.proName, d.depName
from tblProject p 
join tblLocation l on p.locNum = l.locNum 
join tblDepartment d on p.depNum = d.depNum
where l.locName = N'TP Hồ Chí Minh'

--12. Cho biết những người phụ thuộc là nữ giới, của nhân viên thuộc phòng ban có tên: Phòng Nghiên
--cứu và phát triển. Thông tin yêu cầu: tên nhân viên, tên người phụ thuộc, mối liên hệ giữa người
--phụ thuộc với nhân viên
select emp.empName, dpen.depName, dpen.depRelationship
from tblEmployee emp 
join tblDepartment dpat on emp.depNum = dpat.depNum
join tblDependent dpen on emp.empSSN = dpen.empSSN
where dpat.depName = N'Phòng Nghiên cứu và phát triển' and dpen.depSex = 'F'

--13. Cho biết những người phụ thuộc trên 18 tuổi, của nhân viên thuộc phòng ban có tên: Phòng
--Nghiên cứu và phát triển. Thông tin yêu cầu: tên nhân viên, tên người phụ thuộc, mối liên hệ
--giữa người phụ thuộc với nhân viên
select emp.empName, dpen.depName, dpen.depRelationship
from tblEmployee emp 
join tblDepartment dpat on emp.depNum = dpat.depNum
join tblDependent dpen on emp.empSSN = dpen.empSSN
where dpat.depName = N'Phòng Nghiên cứu và phát triển' 
and YEAR(GETDATE()) - YEAR(dpen.depBirthdate) > 18

--14. Cho biết số lượng người phụ thuộc theo giới tính. Thông tin yêu cầu: giới tính, số lượng người
--phụ thuộc
select depSex, count(depName) as [No depedents] from tblDependent group by depSex

--15. Cho biết số lượng người phụ thuộc theo mối liên hệ với nhân viên. 
--Thông tin yêu cầu: mối liên hệ, số lượng người phụ thuộc
select depRelationship, count(depName) as [No dependents]
from tblDependent group by depRelationship

--16. Cho biết số lượng người phụ thuộc theo mỗi phòng ban. 
--Thông tin yêu cầu: mã phòng ban, tên phòng ban, số lượng người phụ thuộc
select dpat.depNum, dpat.depName, count(dpen.depName) as [No dependents] 
from tblDepartment dpat 
join tblEmployee emp on dpat.depNum = emp.depNum
join tblDependent dpen on emp.empSSN = dpen.empSSN
group by dpat.depNum, dpat.depName

--17. Cho biết phòng ban nào có số lượng người phụ thuộc là ít nhất. Thông tin yêu cầu: mã phòng
--ban, tên phòng ban, số lượng người phụ thuộc
select dpat.depNum, dpat.depName, count(dpen.depName) as [No dependents] 
from tblDepartment dpat 
join tblEmployee emp on dpat.depNum = emp.depNum
join tblDependent dpen on emp.empSSN = dpen.empSSN
group by dpat.depNum, dpat.depName
having count(dpen.depName) <= ALL(
	select count(dpen.depName) as [No dependents] 
	from tblDepartment dpat 
	join tblEmployee emp on dpat.depNum = emp.depNum
	join tblDependent dpen on emp.empSSN = dpen.empSSN
	group by dpat.depNum, dpat.depName
)

--18. Cho biết phòng ban nào có số lượng người phụ thuộc là nhiều nhất. Thông tin yêu cầu: mã
--phòng ban, tên phòng ban, số lượng người phụ thuộc
select dpat.depNum, dpat.depName, count(dpen.depName) as [No dependents] 
from tblDepartment dpat 
join tblEmployee emp on dpat.depNum = emp.depNum
join tblDependent dpen on emp.empSSN = dpen.empSSN
group by dpat.depNum, dpat.depName
having count(dpen.depName) >= ALL(
	select count(dpen.depName) as [No dependents] 
	from tblDepartment dpat 
	join tblEmployee emp on dpat.depNum = emp.depNum
	join tblDependent dpen on emp.empSSN = dpen.empSSN
	group by dpat.depNum, dpat.depName
)

--19. Cho biết tổng số giờ tham gia dự án của mỗi nhân viên. 
--Thông tin yêu cầu: mã nhân viên, tên nhân viên, tên phòng ban của nhân viên
select w.empSSN, e.empName, d.depName, sum(w.workHours) as [No working hours]
from tblEmployee e 
join tblDepartment d on e.depNum = d.depNum
join tblWorksOn w on e.empSSN = w.empSSN
group by w.empSSN, e.empName, d.depName

--*20. Cho biết tổng số giờ làm dự án của mỗi phòng ban.
--Thông tin yêu cầu: mã phòng ban, tên phòng ban, tổng số giờ
select d.depNum, d.depName, iif(sum(w.workHours) is null, 0, sum(w.workHours)) as [workHours]
from tblEmployee e 
join tblDepartment d on e.depNum = d.depNum
left join tblWorksOn w on e.empSSN = w.empSSN
group by d.depNum, d.depName

--*21. Cho biết nhân viên nào có số giờ tham gia dự án là ít nhất. Thông tin yêu cầu: mã nhân viên, tên
--nhân viên, tổng số giờ tham gia dự án
select e.empSSN, e.empName, sum(w.workHours)
from tblEmployee e 
join tblWorksOn w on e.empSSN=w.empSSN 
group by e.empSSN,e.empName 
having sum(w.workHours) <= all (
	select sum(w.workHours) 
	from tblEmployee e 
	join tblWorksOn w on e.empSSN=w.empSSN 
	group by e.empSSN,e.empName
	)

select e.empSSN, e.empName, sum(w.workHours) as [workHours]
from tblEmployee e 
join tblWorksOn w on e.empSSN= w.empSSN
group by e.empSSN, e.empName
having sum(w.workHours) <= all (
	select sum(w.workHours) 
	from tblEmployee e 
	join tblWorksOn w on e.empSSN= w.empSSN
	group by e.empSSN
	)

--22. Cho biết nhân viên nào có số giờ tham gia dự án là nhiều nhất. Thông tin yêu cầu: mã nhân viên,
--tên nhân viên, tổng số giờ tham gia dự án
select e.empSSN, e.empName, sum(w.workHours) as [workHours]
from tblEmployee e 
join tblWorksOn w on e.empSSN= w.empSSN
group by e.empSSN, e.empName
having sum(w.workHours) >= all (
	select sum(w.workHours) 
	from tblEmployee e 
	join tblWorksOn w on e.empSSN= w.empSSN
	group by e.empSSN
	)

--23. Cho biết những nhân viên nào lần đầu tiên tham gia dụ án.
--Thông tin yêu cầu: mã nhân viên, tên nhân viên, tên phòng ban của nhân viên
select e.empSSN, e.empName, d.depName, count(p.proNum) as [No projects]
from tblEmployee e 
join tblDepartment d on e.depNum = d.depNum 
join tblWorksOn p on e.empSSN = p.empSSN
group by e.empSSN, e.empName, d.depName
having count(p.proNum) = 1

--24. Cho biết những nhân viên nào lần thứ hai tham gia dụ án. 
--Thông tin yêu cầu: mã nhân viên, tên nhân viên, tên phòng ban của nhân viên
select e.empSSN, e.empName, d.depName, count(p.proNum) as [No projects]
from tblEmployee e 
join tblDepartment d on e.depNum = d.depNum 
join tblWorksOn p on e.empSSN = p.empSSN
group by e.empSSN, e.empName, d.depName
having count(p.proNum) = 2

--25. Cho biết những nhân viên nào tham gia tối thiểu hai dụ án. 
--Thông tin yêu cầu: mã nhân viên, tên nhân viên, tên phòng ban của nhân viên
select e.empSSN, e.empName, d.depName, count(p.proNum) as [No projects]
from tblEmployee e 
join tblDepartment d on e.depNum = d.depNum 
join tblWorksOn p on e.empSSN = p.empSSN
group by e.empSSN, e.empName, d.depName
having count(p.proNum) >= 2

--26. Cho biết số lượng thành viên của mỗi dự án. Thông tin yêu cầu: mã dự án, tên dự án, số lượng
--thành viên
select w.proNum, p.proName, count(w.empSSN) as [No emps]
from tblWorksOn w join tblProject p on w.proNum = p.proNum
group by w.proNum, p.proName

--27. Cho biết tổng số giờ làm của mỗi dự án. Thông tin yêu cầu: mã dự án, tên dự án, tổng số giờ làm
select w.proNum, p.proName, sum(w.workHours) as [workHours]
from tblWorksOn w join tblProject p on w.proNum = p.proNum
group by w.proNum, p.proName

--28. Cho biết dự án nào có số lượng thành viên là ít nhất. Thông tin yêu cầu: mã dự án, tên dự án, số
--lượng thành viên
select p.proNum, p.proName, count(w.empSSN) as [No emps]
from tblProject p join tblWorksOn w on p.proNum = w.proNum
group by p.proNum, p.proName
having count(w.empSSN) <= all( 
	select count(w.empSSN)
	from tblProject p join tblWorksOn w on p.proNum = w.proNum
	group by p.proNum
	)

--29. Cho biết dự án nào có số lượng thành viên là nhiều nhất. Thông tin yêu cầu: mã dự án, tên dự
--án, số lượng thành viên
select p.proNum, p.proName, count(w.empSSN) as [No emps]
from tblProject p join tblWorksOn w on p.proNum = w.proNum
group by p.proNum, p.proName
having count(w.empSSN) >= all( 
	select count(w.empSSN)
	from tblProject p join tblWorksOn w on p.proNum = w.proNum
	group by p.proNum
	)

--30. Cho biết dự án nào có tổng số giờ làm là ít nhất. Thông tin yêu cầu: mã dự án, tên dự án, tổng số
--giờ làm
select w.proNum, p.proName, sum(w.workHours) as [No working hours]
from tblProject p join tblWorksOn w on p.proNum = w.proNum 
group by w.proNum, p.proName
having sum(w.workHours) <= ALL(
	select sum(w.workHours) as [No working hours]
	from tblProject p join tblWorksOn w on p.proNum = w.proNum 
	group by w.proNum
)

--31. Cho biết dự án nào có tổng số giờ làm là nhiều nhất. Thông tin yêu cầu: mã dự án, tên dự án,
--tổng số giờ làm
select w.proNum, p.proName, sum(w.workHours) as [No working hours]
from tblProject p join tblWorksOn w on p.proNum = w.proNum 
group by w.proNum, p.proName
having sum(w.workHours) >= ALL(
	select sum(w.workHours) as [No working hours]
	from tblProject p join tblWorksOn w on p.proNum = w.proNum 
	group by w.proNum
)

--32. Cho biết số lượng phòng ban làm việc theo mỗi nơi làm việc. Thông tin yêu cầu: tên nơi làm việc,
--số lượng phòng ban
select l.locName, count(d.depName) as [No departments]
from tblDepLocation dl
join tblDepartment d on dl.depNum = d.depNum
join tblLocation l on l.locNum = dl.locNum
group by l.locName

--33. Cho biết số lượng chỗ làm việc theo mỗi phòng ban. 
--Thông tin yêu cầu: mã phòng ban, tên phòng ban, số lượng chỗ làm việc
select d.depNum, d.depName, count(dl.locNum) as [No locations]
from tblDepLocation dl
join tblDepartment d on dl.depNum = d.depNum
join tblLocation l on l.locNum = dl.locNum
group by d.depNum, d.depName

--34. Cho biết phòng ban nào có nhiều chỗ làm việc nhất. 
--Thông tin yêu cầu: mã phòng ban, tên phòng ban, số lượng chỗ làm việc
select d.depNum, d.depName, count(dl.locNum) as [No locations]
from tblDepLocation dl
join tblDepartment d on dl.depNum = d.depNum
join tblLocation l on l.locNum = dl.locNum
group by d.depNum, d.depName 
having count(dl.locNum) >= ALL(
	select count(dl.locNum)
	from tblDepLocation dl
	join tblDepartment d on dl.depNum = d.depNum
	join tblLocation l on l.locNum = dl.locNum
	group by d.depName 
)

--35. Cho biết phòng ban nào có it chỗ làm việc nhất. 
--Thông tin yêu cầu: mã phòng ban, tên phòng ban, số lượng chỗ làm việc
select d.depNum, d.depName, count(dl.locNum) as [No locations]
from tblDepLocation dl
join tblDepartment d on dl.depNum = d.depNum
join tblLocation l on l.locNum = dl.locNum
group by d.depNum, d.depName 
having count(dl.locNum) <= ALL(
	select count(dl.locNum)
	from tblDepLocation dl
	join tblDepartment d on dl.depNum = d.depNum
	join tblLocation l on l.locNum = dl.locNum
	group by d.depName 
)

--36. Cho biết địa điểm nào có nhiều phòng ban làm việc nhất. Thông tin yêu cầu: tên nơi làm việc, số
--lượng phòng ban
select l.locName, count(d.depNum) as [No departments]
from tblDepLocation dl
join tblDepartment d on dl.depNum = d.depNum
join tblLocation l on l.locNum = dl.locNum
group by l.locName 
having count(d.depNum) >= ALL(
	select count(d.depNum)
	from tblDepLocation dl
	join tblDepartment d on dl.depNum = d.depNum
	join tblLocation l on l.locNum = dl.locNum
	group by l.locName 
)

--37. Cho biết địa điểm nào có ít phòng ban làm việc nhất. Thông tin yêu cầu: tên nơi làm việc, số
--lượng phòng ban
--lượng phòng ban
select l.locName, count(d.depNum) as [No departments]
from tblDepLocation dl
join tblDepartment d on dl.depNum = d.depNum
join tblLocation l on l.locNum = dl.locNum
group by l.locName 
having count(d.depNum) <= ALL(
	select count(d.depNum)
	from tblDepLocation dl
	join tblDepartment d on dl.depNum = d.depNum
	join tblLocation l on l.locNum = dl.locNum
	group by l.locName 
)

--38. Cho biết nhân viên nào có nhiều người phụ thuộc nhất. Thông tin yêu cầu: mã số, họ tên nhân
--viên, số lượng người phụ thuộc
select e.empSSN, e.empName, count(d.depName) as [No dependents]
from tblEmployee e join tblDependent d on e.empSSN = d.empSSN
group by e.empSSN, e.empName
having count(d.depName) >= ALL(
	select count(d.depName) 
	from tblEmployee e join tblDependent d on e.empSSN = d.empSSN
	group by e.empSSN
)

--39. Cho biết nhân viên nào có ít người phụ thuộc nhất. Thông tin yêu cầu: mã số, họ tên nhân viên,
--số lượng người phụ thuộc
select e.empSSN, e.empName, count(d.depName) as [No dependents]
from tblEmployee e join tblDependent d on e.empSSN = d.empSSN
group by e.empSSN, e.empName
having count(d.depName) <= ALL(
	select count(d.depName) 
	from tblEmployee e join tblDependent d on e.empSSN = d.empSSN
	group by e.empSSN
)

--40. Cho biết nhân viên nào không có người phụ thuộc. Thông tin yêu cầu: mã số nhân viên, họ tên
--nhân viên, tên phòng ban của nhân viên
select e.empSSN, e.empName, dp.depName
from tblEmployee e 
join tblDepartment dp on e.depNum = dp.depNum
where e.empSSN not in (select d.empSSN from tblEmployee em join tblDependent d on em.empSSN = d.empSSN)


--41. Cho biết phòng ban nào không có người phụ thuộc. Thông tin yêu cầu: mã số phòng ban, tên
--phòng ban
select distinct dp.depNum, dp.depName
from tblEmployee e 
join tblDepartment dp on e.depNum = dp.depNum
where e.empSSN not in (select d.empSSN from tblEmployee em join tblDependent d on em.empSSN = d.empSSN)

select d.depNum, d.depName 
from tblEmployee e full 
join tblDependent dp on dp.empSSN=e.empSSN 
join tblDepartment d on d.depNum=e.depNum 
where dp.empSSN is null 
group by d.depNum,d.depName

--42. Cho biết những nhân viên nào chưa hề tham gia vào bất kỳ dự án nào. Thông tin yêu cầu: mã số,
--tên nhân viên, tên phòng ban của nhân viên
select e.empSSN, e.empName, d.depName
from tblDepartment d 
join tblEmployee e on e.depNum = d.depNum
where d.depNum not in (
	select dp.depNum 
	from tblProject p join tblDepartment dp on p.depNum = dp.depNum 
	)

SELECT e.empSSN, e.empName, d.depName
FROM tblEmployee e LEFT JOIN tblWorksOn w ON e.empSSN = w.empSSN JOIN tblDepartment d ON e.depNum = d.depNum
WHERE proNum IS NULL

select * from tblProject
select * from tblDepartment

--43. Cho biết phòng ban không có nhân viên nào tham gia (bất kỳ) dự án. 
--Thông tin yêu cầu: mã số phòng ban, tên phòng ban
select distinct d.depNum, d.depName
from tblDepartment d join tblEmployee e on e.depNum = d.depNum
where d.depNum not in (
	select dp.depNum 
	from tblProject p join tblDepartment dp on p.depNum = dp.depNum 
	)

SELECT d.depNum, d.depName
FROM tblDepartment d LEFT JOIN tblProject p ON d.depNum = p.depNum
WHERE p.depNum IS NULL

--44. Cho biết phòng ban không có nhân viên nào tham gia vào dự án có tên là ProjectA. Thông tin yêu
--cầu: mã số phòng ban, tên phòng ban
select distinct d.depNum, d.depName
from tblDepartment d join tblEmployee e on e.depNum = d.depNum
where d.depNum not in (
	select dp.depNum 
	from tblProject p join tblDepartment dp on p.depNum = dp.depNum 
	where p.proName = 'ProjectA'
	)
select * from tblProject

--*45. Cho biết số lượng dự án được quản lý theo mỗi phòng ban. Thông tin yêu cầu: mã phòng ban,
--tên phòng ban, số lượng dự án
select d.depNum, d.depName, count(p.proNum) as [No projects]
from tblDepartment d left join tblProject p on d.depNum = p.depNum
group by d.depNum, d.depName

--46. Cho biết phòng ban nào quản lý it dự án nhất. Thông tin yêu cầu: mã phòng ban, tên phòng ban,
--số lượng dự án
select d.depNum, d.depName, count(p.proNum) as [No projects]
from tblDepartment d left join tblProject p on d.depNum = p.depNum
group by d.depNum, d.depName
having count(p.proNum) <= ALL(
	select count(p.proNum) 
	from tblDepartment d left join tblProject p on d.depNum = p.depNum
	group by d.depNum
	)

--47. Cho biết phòng ban nào quản lý nhiều dự án nhất. Thông tin yêu cầu: mã phòng ban, tên phòng
--ban, số lượng dự án
select d.depNum, d.depName, count(p.proNum) as [No projects]
from tblDepartment d left join tblProject p on d.depNum = p.depNum
group by d.depNum, d.depName
having count(p.proNum) >= ALL(
	select count(p.proNum) 
	from tblDepartment d left join tblProject p on d.depNum = p.depNum
	group by d.depNum
	)

--48. Cho biết những phòng ban nào có nhiểu hơn 5 nhân viên đang quản lý dự án gì. 
--Thông tin yêu cầu: mã phòng ban, tên phòng ban, số lượng nhân viên của phòng ban, 
--tên dự án quản lý
select d.depNum, d.depName, count(e.empSSN) as [No employees], p.proName
from tblDepartment d 
join tblProject p on d.depNum = p.depNum
join tblEmployee e on d.depNum = e.depNum
group by d.depNum, d.depName, p.proName
having count(e.empSSN) >= 5

--49. Cho biết những nhân viên thuộc phòng có tên là Phòng nghiên cứu, và không có người phụ
--thuộc. Thông tin yêu cầu: mã nhân viên,họ tên nhân viên
select e.empSSN, e.empName
from tblEmployee e 
join tblDepartment d on e.depNum = d.depNum
where d.depName = N'Phòng nghiên cứu và phát triển' 
  and e.empSSN not in (select em.empSSN from tblEmployee em 
					  join tblDependent dp on em.empSSN = dp.empSSN)


--50. Cho biết tổng số giờ làm của các nhân viên, mà các nhân viên này không có người phụ thuộc.
--Thông tin yêu cầu: mã nhân viên,họ tên nhân viên, tổng số giờ làm
select e.empSSN, e.empName, iif(sum(w.workHours) IS NULL, 0, sum(w.workHours)) as [No working hours]
from tblEmployee e 
left join tblWorksOn w on e.empSSN = w.empSSN
group by e.empSSN, e.empName
having e.empSSN not in (select em.empSSN from tblEmployee em 
					  join tblDependent dp on em.empSSN = dp.empSSN)

--*51. Cho biết tổng số giờ làm của các nhân viên, mà các nhân viên này có nhiều hơn 3 người phụ thuộc.
--Thông tin yêu cầu: mã nhân viên,họ tên nhân viên, số lượng người phụ thuộc, tổng số giờ làm
select e.empSSN, e.empName, count(distinct dp.depName) as [No depens], sum(w.workHours) as [workHours]
from tblEmployee e 
join tblDependent dp on e.empSSN = dp.empSSN
left join tblWorksOn w on dp.empSSN = w.empSSN
group by e.empSSN, e.empName
having count(distinct dp.depName) >= 3

select e.empSSN, e.empName, count(distinct dpen.empSSN) as [No depens], sum(w.workHours) as [workHours]
from tblEmployee e 
join tblWorksOn w on e.empSSN = w.empSSN
join tblDependent dpen on e.empSSN = dpen.empSSN
group by e.empSSN, e.empName
having count(distinct dpen.empSSN) >= 3

--52. Cho biết tổng số giờ làm việc của các nhân viên hiện đang dưới quyền giám sát (bị quản lý bởi)
--của nhân viên Mai Duy An. 
--Thông tin yêu cầu: mã nhân viên, họ tên nhân viên, tổng số giờ làm
select e.empSSN, e.empName, sum(w.workHours) as [No working hours]
from tblEmployee e 
join tblWorksOn w on e.empSSN = w.empSSN
where e.supervisorSSN = (select em.empSSN from tblEmployee em where em.empName = N'Mai Duy An')
group by e.empSSN, e.empName

-----------------------
select distinct w1.proNum as [projectNum]
from tblWorksOn w1, tblWorksOn w2
where w1.proNum = w2.proNum and w1.empSSN != w2.empSSN

-- find emp works on prjB and prjC
select empSSN from tblWorksOn w, tblProject p
where w.proNum = p.proNum and p.proName = 'ProjectB'
intersect 
select empSSN from tblWorksOn w, tblProject p
where w.proNum = p.proNum and p.proName = 'ProjectC'

select distinct w1.empSSN, w1.proNum, w2.proNum
from tblWorksOn w1 join tblWorksOn w2 on w1.empSSN = w2.empSSN
where w1.proNum in (
	select wo.proNum from tblWorksOn wo join tblProject pr on wo.proNum = pr.proNum 
	where pr.proName = 'ProjectA' 
	) 
	and
	w2.proNum in (
	select wo.proNum from tblWorksOn wo join tblProject pr on wo.proNum = pr.proNum 
	where pr.proName = 'ProjectB'
	)

-- emp whose name is begun by 'H' or salary exceed 80000
select * from tblEmployee where empName like 'H%'
union 
select * from tblEmployee where empSalary > 80000

select * from tblEmployee where empName like 'H%' or empSalary > 80000

-- find normal employees
select distinct empSSN from tblEmployee 
except
select supervisorSSN from tblEmployee

select * from tblEmployee e
where e.supervisorSSN is not null 
and e.empSSN not in (
	select em.supervisorSSN 
	from tblEmployee em 
	where em.supervisorSSN is not null
)
order by e.empSSN

-- emps of Phòng phần mềm trong nước
select * from tblEmployee where depNum in (select depNum from tblDepartment where depName = N'Phòng phần mềm trong nước')

select * from tblEmployee where depNum = (select depNum from tblDepartment where depName = N'Phòng phần mềm trong nước')
select * from tblEmployee where depNum = any(select depNum from tblDepartment where depName = N'Phòng phần mềm trong nước')

-- Find all those projects have the same location with projectA
select * from tblProject 
where locNum = (
	select p.locNum from tblProject p
	where p.proName = 'ProjectA'
	) and proName != 'ProjectA'

-- For each location, listing the projects that are processed in itselect * from tblLocation l left outer join tblProject p on l.locNum = p.locNumselect l.locNum, l.locName, p.proNum, p.proName from tblLocation l left join tblProject p on l.locNum = p.locNum-- For each department, listing the projects that it controlsselect d.depNum, d.depName, p.proNum, p.proNamefrom tblDepartment d left join tblProject p on d.depNum = p.depNum-- List all location in which the projects are processedselect distinct l.locNum, l.locNamefrom tblLocation l join tblProject p on l.locNum = p.locNum--Use ALL keyword after Union, Intersect, and Except to prevent elimination of duplicates
--Syntax:
--	R UNION ALL S;
--	R INTERSECT ALL S;
--	R EXCEPT ALL S;

-- Aggregation Operators
--Five aggregation operators
--◦ SUM acts on single numeric column
--◦ AVG acts on single numeric column
--◦ MIN acts on single numeric column
--◦ MAX acts on single numeric column
--◦ COUNT act on one or more columns or all of columns
--Eliminating duplicates from the column before
--applying the aggregation by DISTINCT keyword

-- Find average salary of all employees
select floor(avg(empSalary)) from tblEmployee
select avg(empSalary) from tblEmployee
select round(avg(empSalary), 0) from tblEmployee

SELECT cast(round(avg(empSalary), 2) as decimal(10,2)) from tblEmployee -- 80809.52
SELECT cast(avg(empSalary) as decimal(10,2)) from tblEmployee -- 80809.52
select convert(numeric(10,2), avg(empSalary)) as avgSalary from tblEmployee

SELECT CEILING(234.415)
SELECT ROUND(235.415, 2)

-- Find all dependents of the employees who work in department 1

--SELECT *
--FROM Employee_Dep1 ed1, tblDependent d
--WHERE ed1.empSSN=d.empSSN

SELECT d.depName, d.empSSN FROM (
	SELECT *
	FROM tblEmployee
	WHERE depNum=1
	) ed1, tblDependent d
WHERE ed1.empSSN = d.empSSN
ORDER BY d.empSSN

select dp.depName, dp.empSSN
from tblEmployee e join tblDepartment d on e.depNum = d.depNum 
join tblDependent dp on e.empSSN = dp.empSSN
where d.depNum = 1
order by dp.empSSN

select dp.depName, dp.empSSN
from (select * from tblEmployee where depNum = 1) as ed  
join tblDependent dp on ed.empSSN = dp.empSSN
order by dp.empSSN

select dp.depName, dp.empSSN 
from tblDependent dp 
where dp.empSSN in (select empSSN from tblEmployee where depNum = 1)

USE Northwind

SELECT * FROM Customers

SELECT * FROM Orders