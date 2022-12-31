# If the iterable contains any non-string values, it raises a TypeError exception.

# .join() with lists
numList = ['1', '2', '3', '4']
separator = ', '
print(separator.join(numList))

# .join() with tuples
numTuple = ('1', '2', '3', '4')
print(separator.join(numTuple))

s1 = 'abc'
s2 = '123'

# each element of s2 is separated by s1
# '1'+ 'abc'+ '2'+ 'abc'+ '3'
print('s1.join(s2):', s1.join(s2))

# each element of s1 is separated by s2
# 'a'+ '123'+ 'b'+ '123'+ 'b'
print('s2.join(s1):', s2.join(s1))

# Example 2: The join() method with sets
# .join() with sets
test = {'2', '1', '3'}
s = ', '
print(s.join(test)) # 2, 3, 1


test = {'Python', 'Java', 'Ruby'}
s = '->->'
print(s.join(test)) # Python->->Ruby->->Java

# Example 3: The join() method with dictionaries
# .join() with dictionaries
test = {'mat': 1, 'that': 2}
s = '->'
# joins the keys only
print(s.join(test))

test = {1: 'mat', 2: 'that'}
s = ', '
# this gives error since key isn't string
# print(s.join(test))

