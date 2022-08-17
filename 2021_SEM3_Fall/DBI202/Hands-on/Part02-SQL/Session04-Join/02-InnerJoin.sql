Use Cartesian

SELECT * FROM VnDict, EnDict  -- tích đề các

SELECT * FROM VnDict CROSS JOIN EnDict

SELECT * FROM VnDict vn, EnDict en
WHERE vn.Nmbr = en.Nmbr

SELECT * FROM VnDict INNER JOIN EnDict ON VnDict.Nmbr = EnDict.Nmbr

SELECT * FROM VnDict vn, EnDict en
WHERE vn.Nmbr > en.Nmbr

SELECT * FROM VnDict vn, EnDict en
WHERE vn.Nmbr != en.Nmbr

SELECT * FROM VnDict vn JOIN EnDict en
ON vn.Nmbr != en.Nmbr