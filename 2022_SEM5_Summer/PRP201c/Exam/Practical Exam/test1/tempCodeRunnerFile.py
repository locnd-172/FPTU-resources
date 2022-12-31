import sqlite3

conn = sqlite3.connect("test1/employees.sqlite")
cur = conn.cursor()

cur.executescript('''
DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
    id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    name    TEXT,
    rate    REAL,
    salary  REAL,
    total   REAL,
    tax     REAL
);
''')


def main():
    try: 
        data = open("test1/data.txt")
    except:
        print("File not found!")
        exit()
        
    for emp in data:
        e = emp.strip().split()
        name = e[0]
        rate = float(e[1])
        salary = float(e[2])
        total = rate * salary
        tax = total * 5 / 100 if total >= 9000000 else 0
        # employee = (name, rate, salary, tax)
        # employees.append(employee)
        print(name, rate, salary, total, tax)
        cur.execute('INSERT INTO employees (name, rate, salary, total, tax) VALUES(?,?,?,?,?)', (name, rate, salary, total, tax))
        conn.commit()
    
    cur.execute('SELECT * FROM employees ORDER BY name')
    employees = cur.fetchall()
    print()
    for emp in employees:
        # if float(emp["rate"]) > 3:
        print(emp)
        print()

            
main()
