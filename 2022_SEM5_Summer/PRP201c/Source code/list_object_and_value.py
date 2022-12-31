a = 'banana'
b = 'banana'
# To check whether two variables refer to the same object, you can use the is operator.
print(a is b)  # True

a = [1, 2, 3]
b = [1, 2, 3]
print(a is b)  # False
# the two lists are EQUIVALENT, because they have the same elements, but not IDENTICAL

# ALIASING
# If a refers to an object and you assign b = a, then both variables refer to the same object:
a = [1, 2, 3]
b = a
print(b is a)  # True
# The association of a variable with an object is called a reference. In this example, there are two references to the same object.

# An object with more than one reference has more than one name, so we say that the object is ALIASED.
b[0] = 17
print(a)  # [17, 2, 3]


# LIST ARGUMENTS
# When you pass a list to a function, the function gets a reference to the list
def delete_head(t):
    del t[0]


letters = ['a', 'b', 'c']
delete_head(letters)
print(letters)  # ['b', 'c']
# The parameter t and the variable letters are aliases for the same object

# distinguish between operations that modify lists and operations that create new lists
t1 = [1, 2]
t2 = t1.append(3)
print(t1)  # [1, 2, 3]
print(t2)  # None

t1 = [1, 2]
t3 = t1 + [3]
print(t3)  # [1, 2, 3]
print(t2 is t3)  # False
# => the append method modifies a list, but the + operator creates a new list


