# tuple is a sequence of values much like a list. The values stored in a tuple can
# be any type, and they are indexed by integers. The important difference is that
# tuples are IMMUTABLE. Tuples are also COMPARABLE and HASHABLE so we can sort lists
# of them and use tuples as key values in Python dictionaries.

t = 'a', 'b', 'c', 'd', 'e'
t = ('a', 'b', 'c', 'd', 'e')

# To create a tuple with a single element, you have to include the final comma:
t1 = ('a',)
print(type(t1))  # <type 'tuple'>

t2 = ('a')
print(type(t2))  # <type 'str'>

t = tuple()
print(t)  # ()

# If the argument is a sequence (string, list, or tuple), the result of the call to tuple is a tuple with the elements of the sequence:
t = tuple('lupins')
print(t)  # ('l', 'u', 'p', 'i', 'n', 's')
print(t[0])  # l
print(t[1:3])  # ('u', 'p')

# replace one tuple with another:
t = ('A',) + t[1:]
print(t)  # ('A', 'u', 'p', 'i', 'n', 's')

# COMPARING TUPLES
print((0, 1, 2) < (0, 3, 4))  # TRUE
print((0, 1, 2) < (0, 1, 1000))  # TRUE

# This feature lends itself to a pattern called DSU for:
# - Decorate a sequence by building a list of tuples with one or more sort keys preceding the elements from the sequence,
# - Sort the list of tuples using the Python built-in sort, and
# - Undecorate by extracting the sorted elements of the sequence.
txt = 'but soft what light in yonder window breaks'
words = txt.split()
t = list()
for word in words:
    t.append((len(word), word))
print(t)
t.sort(reverse=True)
res = list()
for length, word in t:
    res.append(word)
print(res)

# ability to have a tuple on the left side of an assignment statement
m = [ 'have', 'fun' ]
x, y = m  # equally valid syntax  (x, y) = m
print(x)  # 'have'
print(y)  # 'fun'

# swap the values of two variables in a single statement
a = 1
b = 2
a, b = b, a
# Both sides of this statement are tuples, but the left side is a tuple of variables; the right side is a tuple of expressions
addr = 'monty@python.org'
uname, domain = addr.split('@')
print(uname, domain)

# Dictionaries and tuples
# Dictionaries have a method called items that returns a list of tuples, where each tuple is a key-value pair:
d = {'a':10, 'b':1, 'c':22}
t = list(d.items())
print(t)  # [('b', 1), ('a', 10), ('c', 22)]

# output the contents of a dictionary sorted by key
d = {'a':10, 'b':1, 'c':22}
t = list(d.items())
print(t)  # [('b', 1), ('a', 10), ('c', 22)]
t.sort()
print(t)  # [('a', 10), ('b', 1), ('c', 22)]
 
# Multiple assignment with dictionaries
for key, val in list(d.items()):
    print(val, key)

# sort by value, not key.
# Dictionaries have a method called ITEMS that returns a list of tuples, where each tuple is a key-value pair:
d = {'a':10, 'b':1, 'c':22}
l = list()
for key, val in d.items():
    l.append((val, key))
print(l)  # [(10, 'a'), (22, 'c'), (1, 'b')]
l.sort(reverse=True)
print(l)  # [(22, 'c'), (10, 'a'), (1, 'b')]

# The most common words
import string
fhand = open('romeo.txt')
counts = dict()
for line in fhand:
    line = line.translate(str.maketrans('', '', string.punctuation))
    line = line.lower()
    words = line.split()
    for word in words:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1
# Sort the dictionary by value
lst = list()
for key, val in list(counts.items()):
    lst.append((val, key))
    
lst.sort(reverse=True)

for key, val in lst[:10]:
    print(key, val)

# Using tuples as keys in dictionaries
# Because tuples are hashable and lists are not, if we want to create a composite key to use in a dictionary we must use a tuple as the key.
directory = dict()
last = "Nguyen"
first = "Loc"
number = "1011"
directory[last, first] = number
for last, first in directory:
    print(first, last, directory[last, first])
    
# there are a few cases where you might prefer tuples:
# 1. In some contexts, like a return statement, it is syntactically simpler to create a tuple than a list. In other contexts, you might prefer a list.
# 2. If you want to use a sequence as a dictionary key, you have to use an immutable type like a tuple or string.
# 3. If you are passing a sequence as an argument to a function, using tuples reduces the potential for unexpected behavior due to aliasing.

# Because tuples are immutable, they don’t provide methods like sort and reverse,
# which modify existing lists. However Python provides the built-in functions sorted
# and reversed, which take any sequence as a parameter and return a new sequence
# with the same elements in a different order.

# List comprehension
# convert a list of strings into numbers that you can sum up, you would write
list_of_ints_in_strings = ['42', '65', '12']
list_of_ints = []
for x in list_of_ints_in_strings:
    list_of_ints.append(int(x))
print(sum(list_of_ints))

# list comprehension way
list_of_ints_in_strings = ['42', '65', '12']
list_of_ints = [ int(x) for x in list_of_ints_in_strings ]
print(sum(list_of_ints))


# sort a list
ls = [1, 5, 8, 2, 5, 6]
ls.sort()
print(ls)

l = [1, 5, 8, 2, 5, 6]
l = sorted(l)
print(l)