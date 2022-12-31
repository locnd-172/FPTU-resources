# import counter class from collections module
from collections import Counter
 
# Creation of a Counter Class object using string as an iterable data container
x = Counter("geeksforgeeks")
 
# printing the elements of counter object
for i in x.elements():
    print (i, end=" ")
print()

# Example - 2
b = Counter({'geeks': 4, 'for': 1, 'gfg': 2, 'python': 3})
for i in b.elements():
    print (i, end=" ")
print()
 
# Example - 3
c = Counter([1, 2, 21, 12, 2, 44, 5,
              13, 15, 5, 19, 21, 5])
 
for i in c.elements():
    print (i, end=" ")
print()             
               
# Example - 4
d = Counter(a=2, b=3, c=6, d=1, e=5)
for i in d.elements():
    print (i, end=" ")
print()

# ======================================
print("--------------------------------------------")
# creating a raw data-set
x = Counter ("geeksforgeeks")
 
# will return a itertools chain object
# which is basically a pseudo iterable container whose
# elements can be used when called with a iterable tool
print(x.elements())
print(type(x))

# Example 5
# creating a raw data-set using keyword arguments
x = Counter (a=2, x=3, b=3, z=1, y=5, c=0, d=-3)
 
# printing out the elements
for i in x.elements():
    print("% s : % s" % (i, x[i]), end="\n")
    
# ======================================
 
# making a list
ls = [1, 1, 2, 2, 1, 3, 4, 2, 1, 1, 3, 4, 8]
 
# instantiating a Counter object
ob = Counter(ls)
print(ob)
print()

for k, v in ob.items(): 
    print(k, v)
    
for p in ob.items():
    print(p)
    
print()
# Counter.items()
items = ob.items()
 
print("The datatype is "
      +str(type(items)))
 
# displaying the dict_items
print(items)
 
# iterating over the dict_items
for i in items:
    print(i)
    
# ===================================
# Initialization 
# A Python program to show different ways to create
# Counter
# from collections import Counter
  
# With sequence of items 
print(Counter(['B', 'B', 'A', 'B', 'C', 'A', 'B', 'B', 'A', 'C']))
  
# with dictionary
print(Counter({'A':3, 'B':5, 'C':2}))
  
# with keyword arguments
print(Counter(A=3, B=5, C=2))

import collections

coun = collections.Counter()
# coun = Counter()
  
coun.update([1, 2, 3, 1, 2, 1, 1, 2])
print(coun)  # Counter({1: 4, 2: 3, 3: 1})
  
coun.update([1, 2, 4])
print(coun)  # Counter({1: 5, 2: 4, 3: 1, 4: 1})

c1 = Counter(A=4, B=3, C=10)
c2 = Counter(A=10, B=3, C=4)
  
c1.subtract(c2)
print(c1)

# ===================================
# Python program to demonstrate accessing of
# Counter elements
  
# Create a list
z = ['yellow', 'yellow', 'blue', 'red', 'blue', 'blue', 'red']
col_count = Counter(z)
print(col_count)
  
col = ['blue', 'red', 'yellow', 'green']
  
# Here green is not in col_count 
# so count of green will be zero
for color in col:
    print (color, col_count[color])

# The elements() method returns an iterator that produces all of the items known to the Counter.
coun = Counter(a=1, b=2, c=3)
print(coun)
  
print(list(coun.elements()))

# ===========================================
# most_common() is used to produce a sequence of the n most frequently encountered input values and their respective
coun = Counter(a=1, b=2, c=3, d=120, e=1, f=219)
  
# This prints 3 most frequent characters
for letter, count in coun.most_common(3):
    print('%s: %d' % (letter, count))
print()
  
for letter, count in coun.most_common():
    print('%s: %d' % (letter, count))
print()
    
for letter, count in coun.items():
    print('%s: %d' % (letter, count))
print()

for x in coun.most_common():
    print(x[0], x[1])