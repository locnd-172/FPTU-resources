import sqlite3

conn = sqlite3.connect("Bills.sqlite")
cur = conn.cursor()

# Create table
init_script = '''
    DROP TABLE IF EXISTS Bill;
    
    CREATE TABLE Bill (
        ID TEXT NOT NULL PRIMARY KEY UNIQUE,
        Name TEXT,
        Type TEXT,
        NumOfKw REAL,
        Price REAL,
        Money REAL
    );
'''
cur.executescript(init_script)

# Insert data from file to SQLite database
bills_data = open('CustomerBills.txt')
line_id = 0
for line in bills_data:
    if line_id < 1:
        line_id += 1
        continue
    
    # name_type = re.findall("^KH\d*\t(.*)?\t\d*$", line)
    line = line.strip().split('\t')
    customer_id = line[0].strip()
    name = line[1].strip()
    type_ = line[2].strip()
    number_of_kw = float(line[3].strip())
    
    price = 2500
    if type_ == 'A':
        price = 1500
    elif type_ == 'B':
        price = 2000
    else:
        price = 2500

    money = number_of_kw * price
    
    cur.execute('INSERT INTO Bill(ID, Name, Type, NumOfKw, Price, Money) VALUES (?,?,?,?,?,?)',
                (customer_id, name, type_, number_of_kw, price, money))
    
    conn.commit()

# Format output for alignment
def format_output(ls, header):
    it = 0
    for el in ls:
        if header == 0 and it > 2 and (el == int(el)):
            x = "{:.0f}".format(el)     # print float with 0 fraction
        else:
            x = str(el)                 # print as normal
            
        if it == 1:
            print(x.ljust(15), end='')  # print name
        else:
            print(x.ljust(10), end='')  # print other columns
            
        it += 1
        
    print()

# Get bill data from from database
query = "SELECT * FROM Bill WHERE money > 150000 ORDER BY Money"
cur.execute(query)
data = cur.fetchall()

# Print query result of bills data
print("Customer bill list:")
format_output(["ID", "Full Name", "Type", "NumOfKw", "Price", "Money"], 1)
for customer_bill in data:
    format_output(customer_bill, 0)
    
bills_data.close()
cur.close()
