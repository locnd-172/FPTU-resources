# The function dict creates a new dictionary with no items. Because dict is the name of a built-in function, you should avoid using it as a variable name.
eng2sp = dict()
print(eng2sp)  # {}

eng2sp['one'] = 'uno'
print(eng2sp)  # {'one': 'uno'}

eng2sp = {'one': 'uno', 'two': 'dos', 'three': 'tres'}
print(eng2sp)

print(eng2sp['two'])  # 'dos'

vals = list(eng2sp.values())
print(vals)
print('uno' in vals)  # True

# how many times each letter appears.
word = 'brontosaurus'
d = dict()
for c in word:
    if c not in d:
        d[c] = 1
    else:
        d[c] = d[c] + 1
print(d)

# Dictionaries have a method called get that takes a key and a default value
counts = { 'chuck': 1 , 'annie': 42, 'jan': 100}
print(counts.get('jan', 0))  # 100
print(counts.get('tim', 0))  # 0
word = 'brontosaurus'
d = dict()
for c in word:
    d[c] = d.get(c, 0) + 1
print(d)

# Dictionary and file
# fname = input('Enter the file name: ')
# try:
#     fhand = open(fname)
# except:
#     print('File cannot be opened:', fname)
#     exit()
# counts = dict()
# for line in fhand:
#     words = line.split()
#     for word in words:
#         if word not in counts:
#             counts[word] = 1
#         else:
#             counts[word] += 1
# print(counts)

# Looping and dictionary
counts = { 'chuck': 1 , 'annie': 42, 'jan': 100}
for key in counts:
    print(key, counts[key])
    
counts = { 'chuck': 1 , 'annie': 42, 'jan': 100}
lst = list(counts.keys())
print(lst)
lst.sort()
for key in lst:
    print(key, counts[key])
    

# Advanced text parsing
# line.translate(str.maketrans(fromstr, tostr, deletestr))
import string
fname = input('Enter the file name: ')

try:
    fhand = open(fname)
except:
    print('File cannot be opened:', fname)
    exit()

counts = dict()
for line in fhand:
    line = line.rstrip()
    line = line.translate(line.maketrans('', '', string.punctuation))
    print(line)
    line = line.lower()
    words = line.split()
    for word in words:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1
print(counts)
