a = [10, 20, 30, 40]
b = ['crunchy frog', 'ram bladder', 'lark vomit']
print(a, b)

# nested list 
c = ['spam', 2.0, 5, [10, 20]]
print(c)

# list are mutable
numbers = [17, 123]
numbers[1] = 5
print(numbers)  # [17, 5]

# If an index has a negative value, it counts backward from the end of the list.
print(numbers[-1])

# Traversing a list
cheeses = ['Cheddar', 'Edam', 'Gouda']
for cheese in cheeses:
    print(cheese)

for i in range(len(numbers)):
    numbers[i] = numbers[i] * 2
print(numbers)
# for x in empty:
#     print('This never happens.')

# LIST OPERATIONS
a = [1, 2, 3]
b = [4, 5, 6]
c = a + b
d = []
d.append(a)
d.append(b)
print(c)  # [1, 2, 3, 4, 5, 6]
print(d)

z = [0] * 4
t = [1, 2, 3] * 5
print(z)  # [0, 0, 0, 0]
print(t)  # [1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3]

# LIST SLICES
t = ['a', 'b', 'c', 'd', 'e', 'f'] 
print(t[1:3])  # ['b', 'c']
print(t[:4])  # ['a', 'b', 'c', 'd']
print(t[3:])  # ['d', 'e', 'f']

# A slice operator on the left side of an assignment can update multiple elements:
t = ['a', 'b', 'c', 'd', 'e', 'f']
t[1:3] = ['x', 'y']
print(t)  # ['a', 'x', 'y', 'd', 'e', 'f']

# LIST METHODS
t = ['a', 'b', 'c']
t.append('d')
print(t)  # ['a', 'b', 'c', 'd']

t1 = ['a', 'b', 'c']
t2 = ['d', 'e']
t1.extend(t2)
print(t1)  # ['a', 'b', 'c', 'd', 'e']

t = ['d', 'c', 'e', 'b', 'a']
t.sort()
print(t)  # ['a', 'b', 'c', 'd', 'e']

# Most list methods are void; they modify the list and return None. If you accidentally
# write t = t.sort(), you will be disappointed with the result.

# DELETE ELEMENTS
t = ['a', 'b', 'c']
x = t.pop(1)
print(t)  # ['a', 'c']
print(x)  # b
# pop() : If you don’t provide an index, it deletes and returns the last element

t = ['a', 'b', 'c']
del t[1]  # If you don’t need the removed value, you can use the del statement
print(t)  # ['a', 'c']

t = ['a', 'b', 'c']
t.remove('b')  # know the element you want to remove (but not the index)
print(t)  # ['a', 'c']

# Lists and functions
nums = [3, 41, 12, 9, 74, 15]
print(len(nums))  # 6
print(max(nums))  # 74
print(min(nums))  # 3
print(sum(nums))  # 154
print(sum(nums) / len(nums))  # 25

# ======================
numlist = list()  # make an empty list 
while (True):
    inp = input('Enter a number: ')
    if inp == 'done': break
    value = float(inp)
    numlist.append(value)
    
average = sum(numlist) / len(numlist)
print('Average:', average)

# List & String
# Convert from string to list
s = 'spam'
t = list(s)
print(t)  # ['s', 'p', 'a', 'm']

# break a string into words
s = 'pining for the fjords'
t = s.split()
print(t)  # ['pining', 'for', 'the', 'fjords']
print(t[2])  # the

s = 'spam-spam-spam'
delimiter = '-'
s.split(delimiter)  # ['spam', 'spam', 'spam']

t = ['pining', 'for', 'the', 'fjords']
delimiter = ' '
delimiter.join(t)  # 'pining for the fjords'

# Parsing lines
fhand = open('mbox-short.txt')
for line in fhand:
    line = line.rstrip()
    if not line.startswith('From '): continue
    words = line.split()
    print(words[2])
