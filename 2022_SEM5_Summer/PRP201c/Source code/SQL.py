import sqlite3
conn = sqlite3.connect('music.sqlite')
cur = conn.cursor()

cur.execute('DROP TABLE IF EXISTS Tracks')
cur.execute('CREATE TABLE Tracks (title TEXT, plays INTEGER)')

cur.execute('INSERT INTO Tracks (title, plays) VALUES (?, ?)', ('Thundersthêruck', 20))
cur.execute('INSERT INTO Tracks (title, plays) VALUES (?, ?)', ('My Way', 15))

conn.commit()

cur.execute('SELECT title, plays FROM Tracks')
for row in cur:
    print(row)

# UPDATE Tracks SET plays = 16 WHERE title = 'My Way'

# cur.execute('DELETE FROM Tracks WHERE plays < 100')
# conn.commit()

conn.close()

