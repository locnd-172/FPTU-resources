Use Cartesian

SELECT * FROM EnDict e, VnDict v
WHERE e.Nmbr = v.Nmbr

SELECT * FROM EnDict e INNER JOIN VnDict v ON e.Nmbr = v.Nmbr

SELECT * FROM EnDict e JOIN VnDict v ON e.Nmbr = v.Nmbr

SELECT * FROM EnDict e LEFT JOIN VnDict v ON e.Nmbr = v.Nmbr

SELECT * FROM EnDict e LEFT OUTER JOIN VnDict v ON e.Nmbr = v.Nmbr

-- lấy tiếng Việt làm đầu
SELECT * FROM VnDict v LEFT OUTER JOIN EnDict e ON e.Nmbr = v.Nmbr

-- lấy cả 2
SELECT * FROM VnDict v FULL OUTER JOIN EnDict e ON e.Nmbr = v.Nmbr

-- In ra ba bộ từ điển Anh, Việt (Anh làm chuẩn) 
-- của những con số từ 3 trở lên
SELECT * FROM EnDict e LEFT JOIN VnDict v
ON e.Nmbr = v.Nmbr
WHERE e.Nmbr >= 3

SELECT * FROM EnDict e FULL JOIN VnDict v
ON e.Nmbr = v.Nmbr
WHERE e.Nmbr >= 3 -- toang, mất số 5

SELECT * FROM EnDict e FULL JOIN VnDict v
ON e.Nmbr = v.Nmbr
WHERE v.Nmbr >= 3 -- toang, mất số 4

-- chuẩn
SELECT * FROM EnDict e FULL JOIN VnDict v
ON e.Nmbr = v.Nmbr
WHERE v.Nmbr >= 3 OR e.Nmbr >= 3