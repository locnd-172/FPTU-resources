# Reading file
fhand = open('mbox-short.txt')
count = 0
for line in fhand:
    count = count + 1   
print('Line Count:', count)

# read: read the whole file into one string
fhand = open('mbox-short.txt')
inp = fhand.read()
print(len(inp))  # 94626
print(inp[:20])  # From stephen.marquar

fhand = open('mbox-short.txt')
print(len(fhand.read()))  # 94626
print(len(fhand.read()))  # 0

# SEARCH THROUGH THE FILE
fhand = open('mbox-short.txt')
for line in fhand:
    line = line.rstrip()
    # Skip 'uninteresting lines'
    if not line.startswith('From:'):
        continue
    # Process our 'interesting' line
    print(line)
    
fhand = open('mbox-short.txt')
for line in fhand:
    line = line.rstrip()
    if line.find('@uct.ac.za') == -1: continue
    print(line)

# Letting the user choose the file name
fname = input('Enter the file name: ')
fhand = open(fname)
count = 0
for line in fhand:
    if line.startswith('Subject:'):
        count = count + 1
print('There were', count, 'subject lines in', fname)

# Use try, except

fname = input('Enter the file name: ')
try:
    fhand = open(fname)
except:
    print('File cannot be opened:', fname)
    exit()
count = 0
for line in fhand:
    if line.startswith('Subject:'):
        count = count + 1
print('There were', count, 'subject lines in', fname)

